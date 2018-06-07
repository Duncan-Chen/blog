package com.duncan.blog.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duncan.blog.constant.WebConst;
import com.duncan.blog.controller.BaseController;
import com.duncan.blog.exception.TipException;
import com.duncan.blog.model.bo.RestResponseBo;
import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.service.IUserService;
import com.duncan.blog.utils.TaleUtils;

@Controller
@RequestMapping("/admin")
public class AuthController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public RestResponseBo doLogin(@RequestParam String username, 
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
	
	public static void main(String[] args) {
		int i = 1;
		int j = i ++;
		System.out.println(j);
	}
	
}
