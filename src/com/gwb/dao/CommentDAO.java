package com.gwb.dao;

import java.util.Map;

import com.gwb.bean.CommentBean;
import com.gwb.bean.Pager;
import com.gwb.model.Comment;

public interface CommentDAO
{
	// 添加评论
	public boolean addComment(Comment comment);

	// 加载顾问评价
	public Map<String, Object> loadCommentByCons(String consId, Pager pager);

	// 加载订单评价
	public CommentBean loadOrderComment(int orderId);

}
