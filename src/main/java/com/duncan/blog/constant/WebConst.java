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
	public static final int MAX_TITLE_COUNT = 200;
	public static final int MAX_TEXT_COUNT = 200000;
	public static final String SUCCESS_RESULT = "SUCCESS";
	//同一篇文章在两小时内点击都算一次点击
	public static final Integer HITS_LIMIT_TIME = 7200;
	//点击次数超过多少再更新到数据库，减轻数据库压力
	public static final Integer HIT_EXCEED = 10;
}
