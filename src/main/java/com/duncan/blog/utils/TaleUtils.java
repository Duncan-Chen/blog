package com.duncan.blog.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import com.duncan.blog.constant.WebConst;
import com.duncan.blog.model.vo.UserVo;

public class TaleUtils {

	/**
	 * markdown转化为html
	 * @param markdown
	 * @return
	 */
	public static String mdToHtml(String markdown) {
		if (StringUtils.isBlank(markdown)) {
			return "";
		}
		List<Extension> extensions = Arrays.asList(TablesExtension.create());
		Parser parser = Parser.builder().extensions(extensions).build();
		Node document = parser.parse(markdown);
		HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
		String content = renderer.render(document);
		content = Commons.emoji(content);
		return content;
	}

	/**
	 * 返回当前登录的用户
	 * @param req
	 */
	public static UserVo getLoginUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (null == session) {
			return null;
		}
		return (UserVo) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
	}
	
	public static void setCookie(HttpServletResponse resp, Integer uid) {
		try {
			String enAes = Tools.enAes(uid.toString(), WebConst.AES_SALT);
			Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, enAes);
			boolean isSSL = false;
			cookie.setPath("/");
			cookie.setSecure(isSSL);
			cookie.setMaxAge(30 * 60);
			resp.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取cookie中用户的id
	 * @param req
	 * @return
	 */
	public static Integer getCookieUid(HttpServletRequest req) {
		if (null != req) {
			Cookie cookie = cookieRaw(WebConst.USER_IN_COOKIE, req);
			if (cookie != null && cookie.getValue() != null) {
				try {
					String uid = Tools.deAes(cookie.getValue(), WebConst.AES_SALT);
					return StringUtils.isNoneBlank(uid) && Tools.isNumber(uid) ? Integer.valueOf(uid) : null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	private static Cookie cookieRaw(String name, HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

	/**
	 * md5加密
	 * @param source
	 * @return
	 */
	public static String md5Encode(String source) {
		if (StringUtils.isBlank(source)) {
			return null;
		}
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] encodes = digest.digest(source.getBytes());
		StringBuilder hexString = new StringBuilder();
		for (byte encode : encodes) {
			String hex = Integer.toHexString(0xff & encode);
			if (hex.length() == 1) {
				hexString.append("0");
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	private static final Pattern SLUG_REGEX = Pattern.compile("^[a-zA-Z0-9_-]{5,100}$", Pattern.CASE_INSENSITIVE);
	
	public static boolean isPath(String slug) {
		if (StringUtils.isNotBlank(slug)) {
			if (slug.contains("/") || slug.contains(" ") || slug.contains(".")) {
				return false;
			}
			Matcher matcher = SLUG_REGEX.matcher(slug);
			return matcher.find();
		}
		return false;
	}

}
