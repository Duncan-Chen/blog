package com.duncan.blog.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duncan.blog.controller.BaseController;
import com.duncan.blog.dto.Types;
import com.duncan.blog.model.vo.MetaVo;
import com.duncan.blog.service.IMetaService;

@Controller
@RequestMapping("/admin/article")
public class ArticleController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	private IMetaService metaService;
	
	@GetMapping("/publish")
	public String newArticle(HttpServletRequest request) {
		List<MetaVo> metas = this.metaService.getMetas(Types.CATEGORY.getType());
		request.setAttribute("categories", metas);
		return "admin/article_edit";
	}
}
