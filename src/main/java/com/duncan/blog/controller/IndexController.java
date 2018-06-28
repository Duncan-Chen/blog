package com.duncan.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.duncan.blog.constant.WebConst;
import com.duncan.blog.dto.Types;
import com.duncan.blog.model.bo.CommentBo;
import com.duncan.blog.model.vo.ContentVo;
import com.duncan.blog.service.ICommentService;
import com.duncan.blog.service.IContentService;
import com.duncan.blog.utils.IPKit;
import com.github.pagehelper.PageInfo;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private IContentService contentService;
	
	@Autowired
	private ICommentService commentService;
	
	/**
	 * 文章预览
	 * @param cid
	 * @param request
	 * @return
	 */
	@GetMapping(value={"article/{cid}/preview"})
	public String articlePreview(@PathVariable String cid, HttpServletRequest request) {
		ContentVo content = this.contentService.getContent(cid);
		if (null == content) {
			return this.render_404();
		}
		request.setAttribute("article", content);
		request.setAttribute("is_post", true);
		completeArticle(request, content);
		if (!checkHitsFrequency(request, cid)) {
			updateArticleHit(content.getCid(), content.getHits());
		}
		return this.render("post");
	}
	
	/**
	 * 更新文章的点击率
	 * @param request
	 * @param contentVo
	 */
	public void completeArticle(HttpServletRequest request, ContentVo contentVo) {
		if (contentVo.getAllowComment()) {
			String cp = request.getParameter("cp");
			if (StringUtils.isBlank(cp)) {
				cp = "1";
			}
			request.setAttribute("cp", cp);
			PageInfo<CommentBo> commentsPaginator = 
					this.commentService.getComments(contentVo.getCid(), Integer.parseInt(cp), 6);
			request.setAttribute("comments", commentsPaginator);
		}
	}
	
	/**
	 * 检查同一ip地址是否在2小时内访问同一篇文章
	 * @param request
	 * @param cid
	 * @return
	 */
	private boolean checkHitsFrequency(HttpServletRequest request, String cid) {
		String val = IPKit.getIpAddrByRequest(request) + ":" + cid;
		Integer count = this.cache.hget(Types.HITS_FREQUENCY.getType(), val);
		if (null != count && count > 0) {
			return true;
		}
		this.cache.hset(Types.HITS_FREQUENCY.getType(), val, 1, WebConst.HITS_LIMIT_TIME);
		return false;
	}
	
	/**
	 * 点击次数超过十次更新一次数据库
	 * @param cid
	 * @param chits
	 */
	private void updateArticleHit(Integer cid, Integer chits) {
		Integer hits = this.cache.hget("article" + cid, "hits");
		if (chits == null) {
			chits = 0;
		}
		hits = null == hits ? 1 : hits + 1;
		if (hits > WebConst.HIT_EXCEED) {
			ContentVo temp = new ContentVo();
			temp.setCid(cid);
			temp.setHits(hits + chits);
			this.contentService.updateContentByCid(temp);
			this.cache.hset("article" + cid, "hits", 1);
		} else {
			this.cache.hset("article" + cid, "hits", hits);
		}
		
	}
}
