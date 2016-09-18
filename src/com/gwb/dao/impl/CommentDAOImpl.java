package com.gwb.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.CommentBean;
import com.gwb.bean.Pager;
import com.gwb.dao.CommentDAO;
import com.gwb.model.Comment;
import com.gwb.util.Config;

@Component("commentDAO")
public class CommentDAOImpl implements CommentDAO
{
	private String str = "select new com.gwb.bean.CommentBean(c.id, c.content, "
			+ "c.user.mobilePhone,c.user.name, c.user.icon,c.user.position, "
			+ "c.consultant.mobilePhone, c.order.orderId,c.createTime) from Comment c ";

	private DataDAO dataDAO;

	public DataDAO getDataDAO()
	{
		return dataDAO;
	}

	@Resource(name = "dataDAO")
	public void setDataDAO(DataDAO dataDAO)
	{
		this.dataDAO = dataDAO;
	}

	/**
	 * @author:huzhiming
	 * @function：添加评论
	 */
	@Override
	public boolean addComment(Comment comment)
	{
		return dataDAO.addItem(comment);
	}

	/**
	 * @author:huzhiming
	 * @function：加载顾问评价
	 */
	@Override
	public Map<String, Object> loadCommentByCons(String consId, Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.COMMENT_PAGER_SIZE);
		String hql = str + " where c.consultant.id = '" + consId + "' order by createTime desc";

		Map<String, Object> map = new HashMap<>();
		map.put("comment", dataDAO.loadItems(hql, pager));

		hql = "select count(*) from Comment c where c.consultant.id='" + consId+"'";
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();
		map.put("pager", pager);
		return map;
	}

	/**
	 * @author:huzhiming
	 * @function：加载订单的评价
	 */
	@Override
	public CommentBean loadOrderComment(int id)
	{
		String hql = str + " where c.order.id=" + id + "order by createTime desc";
		List<CommentBean> commentBeans = dataDAO.loadItems(hql);
		if (commentBeans != null && commentBeans.size() > 0)
			return commentBeans.get(0);
		return null;
	}

}
