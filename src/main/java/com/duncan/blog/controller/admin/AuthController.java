package com.duncan.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AuthController {
	
	@RequestMapping("/login")
	public String login() {
		
		return "admin/login";
	}
	
}
