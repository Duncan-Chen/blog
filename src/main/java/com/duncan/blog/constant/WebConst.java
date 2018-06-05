package com.duncan.blog.constant;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class WebConst {
	public static Map<String, String> initConfig = new HashMap<>();
	public static final String USER_IN_COOKIE = "S_L_ID";
	public static final String LOGIN_SESSION_KEY = "login_user";
	/**
	 * aes加密加盐
	 */
	public static final String AES_SALT = "0123456789abcdef";
}
