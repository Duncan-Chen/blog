package com.duncan.blog.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duncan.blog.controller.BaseController;
import com.duncan.blog.model.bo.RestResponseBo;
import com.duncan.blog.model.vo.UserVo;
import com.duncan.blog.service.IUserService;

@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController {
	
	@Autowired
	private IUserService userService;
	
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
	
	@PostMapping("/profile")
	public RestResponseBo<?> saveProfile(@RequestParam String screenName, @RequestParam String email, 
			HttpServletRequest request, HttpSession session) {
		UserVo user = this.user(request);
		if (StringUtils.isNoneBlank(screenName) && StringUtils.isNotBlank(email)) {
			UserVo temp = new UserVo();
			temp.setUid(user.getUid());
			temp.setScreenName(screenName);
			temp.setEmail(email);
			this.userService.updateByUid(temp);
		}
		return RestResponseBo.ok();
	}
	
}
