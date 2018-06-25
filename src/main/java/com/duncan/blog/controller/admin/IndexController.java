package com.duncan.blog.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duncan.blog.constant.WebConst;
import com.duncan.blog.controller.BaseController;
import com.duncan.blog.dto.LogActions;
import com.duncan.blog.exception.TipException;
import com.duncan.blog.model.bo.CommentBo;
import com.duncan.blog.model.bo.RestResponseBo;
import com.duncan.blog.model.vo.ContentVo;
import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.service.ICommentService;
import com.duncan.blog.service.IContentService;
import com.duncan.blog.service.ILogService;
import com.duncan.blog.service.IUserService;
import com.duncan.blog.utils.TaleUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
@Transactional(rollbackFor=TipException.class)
public class IndexController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILogService logService;
	
	@Autowired
	private IContentService contentService;
	
	@Autowired
	private ICommentService commentService;
	
	@RequestMapping(value={"", "/index"})
	public String index() {
		return "admin/index";
	}
	
	/**
	 * 个人设置页面
	 * @return
	 */
	@GetMapping("/profile")
	public String profile() {
		return "admin/profile";
	}
	
	/**
	 * 修改个人信息
	 * @param screenName
	 * @param email
	 * @param request
	 * @param session
	 * @return
	 */
	@PostMapping("/profile")
	@ResponseBody
	public RestResponseBo<?> saveProfile(@RequestParam String screenName, @RequestParam String email, 
			HttpServletRequest request, HttpSession session) {
		UserVo user = this.user(request);
		try {
			if (StringUtils.isNoneBlank(screenName) && StringUtils.isNotBlank(email)) {
				UserVo temp = new UserVo();
				temp.setUid(user.getUid());
				temp.setScreenName(screenName);
				temp.setEmail(email);
				this.userService.updateByUid(temp);
				this.logService.insertLog(LogActions.UP_INFO.getAction(), null, 
						request.getRemoteAddr(), this.getUid(request));
				
				UserVo userVo = (UserVo) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
				userVo.setScreenName(screenName);
				userVo.setEmail(email);
				session.setAttribute(WebConst.LOGIN_SESSION_KEY, userVo);
			}
			return RestResponseBo.ok();
		} catch (Exception e) {
			String msg = "个人信息修改失败";
			if (e instanceof TipException) {
				msg = e.getMessage();
			} else {
				LOGGER.error(msg, e);
			}
			return RestResponseBo.fail(msg);
		}
	}
	
	/**
	 * 修改密码
	 * @param oldPassword
	 * @param password
	 * @param request
	 * @param session
	 * @return
	 */
	@PostMapping("/password")
	@ResponseBody
	public RestResponseBo<?> upPwd(@RequestParam String oldPassword, @RequestParam String password, 
			HttpServletRequest request, HttpSession session) {
		UserVo user = this.user(request);
		if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(password)) {
			return RestResponseBo.fail("请确认信息填写完整");
		}
		if (!user.getPassword().equals(TaleUtils.md5Encode(user.getUsername() + oldPassword))) {
			return RestResponseBo.fail("旧密码错误");
		}
		if (password.length() < 6 || password.length() > 14) {
			return RestResponseBo.fail("请输入6-14位的密码");
		}
		try {
			UserVo temp = new UserVo();
			temp.setUid(this.getUid(request));
			String pwd = TaleUtils.md5Encode(user.getUsername() + password);
			temp.setPassword(pwd);
			this.userService.updateByUid(temp);
			this.logService.insertLog(LogActions.UP_PWD.getAction(), null, request.getRemoteAddr(), 
					this.getUid(request));
			
			UserVo userVo = (UserVo) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
			userVo.setPassword(password);
			session.setAttribute(WebConst.LOGIN_SESSION_KEY, userVo);
			return RestResponseBo.ok();
		} catch (Exception e) {
			String msg = "密码修改错误";
			if (e instanceof TipException) {
				msg = e.getMessage();
			} else {
				LOGGER.error(msg, e);
			}
			return RestResponseBo.fail(msg);
		}
	}
	
	/**
	 * 文章预览
	 * @param cid
	 * @param request
	 * @return
	 */
	@GetMapping(value={"/article/{cid}/preview"})
	public String articlePreview(@PathVariable String cid, HttpServletRequest request) {
		ContentVo content = this.contentService.getContent(cid);
		if (null == content) {
			return this.render_404();
		}
		request.setAttribute("article", content);
		request.setAttribute("is_post", true);
		completeArticle(request, content);
	}
	
	/**
	 * 
	 * @param request
	 * @param contentVo
	 */
	public void completeArticle(HttpServletRequest request, ContentVo contentVo) {
		if (contentVo.getAllowComment()) {
			String cp = request.getParameter("cp");
			if (StringUtils.isBlank(cp)) {
				cp = "1";
			}
			request.setAttribute("cp", cp);
			PageInfo<CommentBo>commentsPaginator = this.commentService.getComments(contentVo.getCid(), Integer.parseInt(cp), 6);
		}
		
	}
	
}
