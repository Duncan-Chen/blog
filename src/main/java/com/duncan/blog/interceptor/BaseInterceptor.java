package com.duncan.blog.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.duncan.blog.constant.WebConst;
import com.duncan.blog.dto.Types;
import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.service.IUserService;
import com.duncan.blog.utils.Commons;
import com.duncan.blog.utils.IPKit;
import com.duncan.blog.utils.MapCache;
import com.duncan.blog.utils.MyUUID;
import com.duncan.blog.utils.TaleUtils;

@Component
public class BaseInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);
	private static final String USER_AGENT = "user-agent";
	
	@Resource
	private IUserService userService;
	
	private MapCache cache = MapCache.single();
	
	@Resource
	private Commons commons;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object obj) throws Exception {
		String contextPath = req.getContextPath();
		String uri = req.getRequestURI();
		LOGGER.info("UserAgent: {}", req.getHeader(USER_AGENT));
		LOGGER.info("用户访问地址：{}, 来路地址：{}", uri, IPKit.getIpAddrByRequest(req));
		UserVo user = TaleUtils.getLoginUser(req);
		if (null == user) {
			//安全隐患，cookie伪造
			Integer uid = TaleUtils.getCookieUid(req);
			if (null != uid) {
				user = this.userService.queryUserById(uid);
				req.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
			}
		}
		
		if (uri.startsWith(contextPath + "/admin") && !uri.startsWith(contextPath + "/admin/login") && user == null) {
			resp.sendRedirect(contextPath + "/admin/login");
			return false;
		}
		
		if (req.getMethod().equals("GET")) {
			String csrf_token = MyUUID.UU64();
			cache.hset(Types.CSRF_TOKEN.getType(), csrf_token, uri, 30 * 60);
			req.setAttribute("_csrf_token", csrf_token);
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp,
			Object obj, ModelAndView model) throws Exception {
		req.setAttribute("commons", commons);
	}

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse resp, Object obj, Exception e)
			throws Exception {
		
	}

}
