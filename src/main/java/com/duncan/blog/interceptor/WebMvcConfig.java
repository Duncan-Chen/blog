package com.duncan.blog.interceptor;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Resource
	private BaseInterceptor baseInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(baseInterceptor);
	}
	
}
