package com.duncan.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class IndexController {
	
	@RequestMapping("")
	public String index() {
		return "admin/index";
	}
	
}
