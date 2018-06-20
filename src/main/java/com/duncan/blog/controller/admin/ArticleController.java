package com.duncan.blog.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duncan.blog.constant.WebConst;
import com.duncan.blog.controller.BaseController;
import com.duncan.blog.dto.Types;
import com.duncan.blog.model.bo.RestResponseBo;
import com.duncan.blog.model.vo.ContentVo;
import com.duncan.blog.model.vo.ContentVoExample;
import com.duncan.blog.model.vo.MetaVo;
import com.duncan.blog.service.IContentService;
import com.duncan.blog.service.IMetaService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/article")
public class ArticleController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	private IMetaService metaService;
	
	@Autowired
	private IContentService contentService;
	
	@GetMapping("/publish")
	public String newArticle(HttpServletRequest request) {
		List<MetaVo> metas = this.metaService.getMetas(Types.CATEGORY.getType());
		request.setAttribute("categories", metas);
		return "admin/article_edit";
	}
	
	@PostMapping("/publish")
	@ResponseBody
	public RestResponseBo<?> publish(ContentVo content, HttpServletRequest request) {
		Integer authorId = this.getUid(request);
		content.setAuthorId(authorId);
		content.setType(Types.ARTICLE.getType());
		if (StringUtils.isBlank(content.getCategories())) {
			content.setCategories("默认分类");
		}
		String result = this.contentService.publish(content);
		if (!WebConst.SUCCESS_RESULT.equals(result)) {
			return RestResponseBo.fail(result);
		}
		return RestResponseBo.ok();
	}
	
	@GetMapping("")
	public String index(@RequestParam(value="page", defaultValue="1") int page, 
						@RequestParam(value="limit", defaultValue="10") int limit, HttpServletRequest request) {
		ContentVoExample example = new ContentVoExample();
		example.setOrderByClause("created desc");
		example.createCriteria().andTypeEqualTo(Types.ARTICLE.getType());
		PageInfo<ContentVo> contentsPaginator = this.contentService.getArticlesWithPage(example, page, limit);
		request.setAttribute("articles", contentsPaginator);
		return "admin/article_list";
	}
}
