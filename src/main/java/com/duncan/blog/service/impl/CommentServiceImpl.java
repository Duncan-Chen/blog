package com.duncan.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duncan.blog.dao.CommentVoMapper;
import com.duncan.blog.model.bo.CommentBo;
import com.duncan.blog.model.vo.CommentVo;
import com.duncan.blog.model.vo.CommentVoExample;
import com.duncan.blog.service.ICommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CommentServiceImpl implements ICommentService {

	@Resource
	private CommentVoMapper commentDao;
	
	@Override
	public PageInfo<CommentBo> getComments(Integer cid, int page, int limit) {
		if (null != cid) {
			PageHelper.startPage(page, limit);
			CommentVoExample example = new CommentVoExample();
			example.createCriteria().andCidEqualTo(cid).andParentEqualTo(0)
				.andStatusIsNotNull().andStatusEqualTo("approved");
			example.setOrderByClause("coid desc");
			List<CommentVo> commentVos = this.commentDao.selectByExampleWithBLOB(example);
			PageInfo<CommentVo> commentPaginator = new PageInfo<>(commentVos);
			PageInfo<CommentBo> returnBo = copyPageInfo(commentPaginator);
			if (commentVos.size() != 0) {
				List<CommentBo> commentBos = new ArrayList<CommentBo>();
				commentVos.forEach(parent -> {
					CommentBo commentBo = new CommentBo(parent);
					commentBos.add(commentBo);
				});
				returnBo.setList(commentBos);
			}
			return returnBo;
		}
		return null;
	}

	private <T> PageInfo<T> copyPageInfo(PageInfo<?> commentPaginator) {
		PageInfo<T> returnBo = new PageInfo<T>();
		returnBo.setPageSize(commentPaginator.getPageSize());
		returnBo.setPageNum(commentPaginator.getPageNum());
		returnBo.setEndRow(commentPaginator.getEndRow());
		returnBo.setTotal(commentPaginator.getTotal());
		returnBo.setHasNextPage(commentPaginator.isHasNextPage());
		returnBo.setHasPreviousPage(commentPaginator.isHasPreviousPage());
		returnBo.setIsFirstPage(commentPaginator.isIsFirstPage());
		returnBo.setIsLastPage(commentPaginator.isIsLastPage());
		returnBo.setNavigateFirstPage(commentPaginator.getNavigateFirstPage());
		returnBo.setNavigateLastPage(commentPaginator.getNavigateLastPage());
		returnBo.setNavigatepageNums(commentPaginator.getNavigatepageNums());
		returnBo.setSize(commentPaginator.getSize());
		returnBo.setPrePage(commentPaginator.getPrePage());
		returnBo.setNextPage(commentPaginator.getNextPage());
		return returnBo;
	}

}
