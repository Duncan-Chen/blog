package com.duncan.blog.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.duncan.blog.constant.WebConst;
import com.duncan.blog.model.vo.ContentVo;
import com.vdurmont.emoji.EmojiParser;

@Component
public class Commons {
	
	/**
	 * 网站配置项
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String site_option(String key, String defaultValue) {
		if (StringUtils.isBlank(key)) {
			return "";
		}
		String str = WebConst.initConfig.get(key);
		if (StringUtils.isNotBlank(str)) {
			return str;
		} else {
			return defaultValue;
		}
	}
	
	/**
	 * 网站配置项
	 * @param key
	 * @return
	 */
	public static String site_option(String key) {
		return site_option(key, "");
	}
	
	/**
	 * 网站链接的全址
	 * @param sub 追加的地址
	 * @return
	 */
	public static String site_url(String sub) {
		return site_option("site_url") + sub;
	}
	
	/**
	 * 网站链接
	 * @return
	 */
	public static String site_url() {
		return site_url("");
	}
	
	/**
	 * 文章链接
	 * @param cid
	 * @param slug
	 * @return
	 */
	public static String permalink(Integer cid, String slug) {
		return site_url("/article/" + (StringUtils.isNotBlank(slug) ? slug : cid.toString()));
	}
	
	/**
	 * 文章链接
	 * @param contentVo
	 * @return
	 */
	public static String permalink(ContentVo contentVo) {
		return permalink(contentVo.getCid(), contentVo.getSlug());
	}
	
	/**
	 * 显示分类
	 * @param categories
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String show_categories(String categories) throws UnsupportedEncodingException {
		if (StringUtils.isNoneBlank(categories)) {
			String[] arr = categories.split(",");
			StringBuffer sbuf = new StringBuffer();
			for (String c : arr) {
				sbuf.append("<a href=\"/category/" + URLEncoder.encode(c, "UTF-8") + "\">" + c + "</a>");
			}
			return sbuf.toString();
		}
		return show_categories("默认分类");
	}
	
	/**
	 * 显示文章缩略图
	 * @param contentVo
	 * @return
	 */
	public static String show_thumb(ContentVo contentVo) {
		int cid = contentVo.getCid();
		int size = cid % 20;
		size = size == 0 ? 1 : size;
		return "/user/img/rand/" + size + ".jpg";
	}
	
	/**
	 * 获取文章第一张图片
	 * @param content
	 * @return
	 */
	public static String show_thumb(String content) {
		content = TaleUtils.mdToHtml(content);
		if (content.contains("<img")) {
			String img = "";
			String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
			Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
			Matcher m_image = p_image.matcher(content);
			if (m_image.find()) {
				img = img + m_image.group();
				Matcher m = Pattern.compile("src\\s*=\\s*\'?\"?(.*?)(\'|\"|>|\\s+)").matcher(img);
				if (m.find()) {
					return m.group(1);
				}
			}
		}
		return "";
	}
	
	private static final String[] ICONS = {"bg-ico-book", "bg-ico-game", "bg-ico-note", "bg-ico-chat", 
		"bg-ico-code", "bg-ico-image", "bg-ico-web", "bg-ico-link", "bg-ico-design", "bg-ico-lock"};
	
	/**
	 * 显示文章图标
	 * @param cid
	 * @return
	 */
	public static String show_icon(int cid) {
		return ICONS[cid % ICONS.length];
	}
	
	/**
	 * 字符串转换为emoji表情
	 * @param value
	 * @return
	 */
	public static String emoji(String value) {
		return EmojiParser.parseToUnicode(value);
	}
	
	public static String random(int max, String imgSuffix) {
		return MyUUID.random(1, max) + imgSuffix;
	}
	
}
