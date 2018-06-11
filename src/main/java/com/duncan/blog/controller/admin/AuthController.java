package com.duncan.blog.controller.admin;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duncan.blog.constant.WebConst;
import com.duncan.blog.controller.BaseController;
import com.duncan.blog.dto.LogActions;
import com.duncan.blog.exception.TipException;
import com.duncan.blog.model.bo.RestResponseBo;
import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.service.ILogService;
import com.duncan.blog.service.IUserService;
import com.duncan.blog.utils.TaleUtils;

@Controller
@RequestMapping("/admin")
@Transactional(rollbackFor=TipException.class)
public class AuthController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILogService logService;
	
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public RestResponseBo<?> doLogin(@RequestParam String username, 
								  @RequestParam String password, 
								  @RequestParam(required=false) String remember_me, 
								  HttpServletRequest request, 
								  HttpServletResponse response) {
		Integer error_count = cache.get("login_error_count");
		try {
			UserVo user = this.userService.login(username, password);
			request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
			if (StringUtils.isNotBlank(remember_me)) {
				TaleUtils.setCookie(response, user.getUid());
			}
			this.logService.insertLog(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), user.getUid());
		} catch (Exception e) {
			error_count = error_count == null ? 1 : ++error_count;
			if (error_count > 3) {
				return RestResponseBo.fail("你的密码已经输错3次，请10分钟后再尝试");
			}
			cache.set("login_error_count", error_count, 10 * 60);
			String msg = "登录失败";
			if (e instanceof TipException) {
				msg = e.getMessage();
			} else {
				LOGGER.error(msg, e);
			}
			return RestResponseBo.fail(msg);
		}
		return RestResponseBo.ok();
	}
	
	@RequestMapping("/logout")
	public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
		Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
		cookie.setPath("/");
		cookie.setValue(null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		try {
			response.sendRedirect("/admin/login");
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("注销失败", e);
		}
	}
	
}
