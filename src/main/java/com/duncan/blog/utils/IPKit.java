package com.duncan.blog.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 */
public class IPKit {

	/**
	 * 获取客户端ip地址
	 * @param req
	 * @return
	 */
	public static Object getIpAddrByRequest(HttpServletRequest req) {
		String ip = req.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}
		return ip;
	}

}
