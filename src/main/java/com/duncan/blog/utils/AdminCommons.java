package com.duncan.blog.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.duncan.blog.model.vo.MetaVo;

/**
 * 
 *后台公共类
 */
@Component
public class AdminCommons {
	
	/**
	 * 判断category与cats是否有交集
	 * @param category
	 * @param cats
	 * @return
	 */
	public static boolean exist_cats(MetaVo category, String cats) {
		String[] arr = StringUtils.split(cats, ",");
		if (null != arr && arr.length > 0) {
			for (String c : arr) {
				if (c.trim().equals(category.getName())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", 
		"inverse", "purple", "pink"};
	
	public static String rand_color() {
		int r = Tools.rand(0, COLORS.length - 1);
		return COLORS[r];
	}
	
}
