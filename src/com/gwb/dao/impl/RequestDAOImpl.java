package com.gwb.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;
import com.gwb.bean.ReqFieldBean;
import com.gwb.bean.RequestBean;
import com.gwb.dao.RequestDAO;
import com.gwb.model.Request;
import com.gwb.model.Uninterested;
import com.gwb.model.User;
import com.gwb.util.Config;

@Component("requestDAO")
public class RequestDAOImpl implements RequestDAO
{

	private String sql = "select new com.gwb.bean.RequestBean(r.id,r.title,r.content,r.user.id,"
			+ "r.user.mobilePhone,r.user.name,r.user.icon,r.user.position,r.consMode,"
			+ "r.createTime,r.appointState,r.state,c.name) from Request r left join r.city c ";
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
	 * @function：添加请求 user
	 */
	@Override
	public boolean addReq(Request request)
	{
		return dataDAO.addItem(request);
	}

	/**
	 * @author:huzhiming
	 * @function：通过用户id删除请求
	 * @field:
	 * @return_type:
	 */
	@Override
	public void delReqByUser(User user)
	{
		String hql = "delete from Request r where r.user.id = " + user.getId();
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：通过请求id删除请求
	 * @field:
	 * @return_type:
	 */
	@Override
	public void delReqById(int id)
	{
		String hql = "delete from Request r where r.id = " + id;
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：请求详情
	 */
	@Override
	public RequestBean loadRequestById(int id)
	{
		String hql = sql + " where r.id=" + id;
		List<RequestBean> requests = dataDAO.loadItems(hql);
		if (requests != null && requests.size() > 0)
		{
			return requests.get(0);
		}
		return null;
	}

	/**
	 * @author:huzhiming
	 * @function：更新请求
	 */
	@Override
	public void updateRequest(int reqId, String item, String value)
	{
		String hql = "update Request set " + item + "= '" + value + "'  where id = " + reqId;
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：加载全部请求 admin
	 */
	@Override
	public Map<String, Object> loadRequest(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);

		Map<String, String> conditions = pager.getCondition();
		String temp = " where r.id!=0";
		if (!conditions.isEmpty())
		{
			String value = (String) conditions.get("condition");
			if (!value.equals(""))
			{
				temp += "  and (r.title like '%" + value + "%' or r.user.name like '%" + value
						+ "%' or r.field.title like '%" + value + "%')";
			}

			String startTime = conditions.get("startTime");
			if (!startTime.equals(""))
			{
				temp += " and r.createTime >'" + startTime + " 00:00:00'";
			}
			String endTime = conditions.get("endTime");
			if (!endTime.equals(""))
			{
				temp += " and r.createTime <'" + endTime + " 23:59:59'";
			}
		}

		String hql = sql + temp + " order by r.createTime desc";
		List<RequestBean> requests = dataDAO.loadItems(hql, pager);
		hql = "select count(*) from Request r " + temp;
		// 查询条数
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();
		Map<String, Object> map = new HashMap<>();
		map.put("request", requests);
		map.put("pager", pager);

		return map;
	}

	/**
	 * @author:huzhiming
	 * @function：加载用户请求 user
	 */
	@Override
	public Map<String, Object> getUserRequests(String userId, Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		// 设置每页显示条数
		pager.setPageSize(Config.USER_REQUEST_PAGER_SIZE);

		String hql = sql + " where (r.state=0 or r.state=2) and r.user.state=1 and r.user.id='" + userId
				+ "' order by createTime desc";
		List<RequestBean> requestBeans = dataDAO.loadItems(hql, pager);
		System.out.println(requestBeans.size());
		hql = "select count(*)from Request r where (r.state=0 or r.state=2) and r.user.state=1 and r.user.id='" + userId + "'";
		// 查询数据条数
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();

		Map<String, Object> map = new HashMap<>();
		map.put("request", requestBeans);
		map.put("pager", pager);
		return map;
	}

	@Override
	public List<RequestBean> loadUnAppointRequest(String userId)
	{
		String hql = "select new com.gwb.bean.RequestBean(r.id,r.title) from Request r where r.state=0 and r.user.id='"
				+ userId + "'";
		List<RequestBean> list = dataDAO.loadItems(hql);
		return list;
	}

	/**
	 * @author:huzhiming
	 * @function：添加顾问不感兴趣的请求 cons
	 */
	@Override
	public boolean uninterestReq(Uninterested uninterested)
	{
		return dataDAO.addItem(uninterested);
	}

	/**
	 * @author:huzhiming
	 * @function：顾问加载请求 cons
	 */
	@Override
	public Map<String, Object> loadConsRequest(String consId, Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.USER_REQUEST_PAGER_SIZE);
		// 过滤已完成的请求
		String str = "where r.user.state =1 and r.state = 0"
				// 过滤顾问不擅长领域的需求
				+ " and r.id in (select rf.request.id from ReqField rf where rf.request.id=r.id and rf.field.id in (select g.field.id from GoodAtField g where g.consultant.id='"
				+ consId + "'))"
				// 过滤不感兴趣的请求
				+ " and r.id not in(select u.request.id from Uninterested u where u.consultant.id='" + consId + "')"
				// 过滤顾问已经回应的请求
				+ "and r.id not in(select re.request.id from Response re where re.consultant.id='" + consId + "')"
				// 过滤回应次数大于等于3次的
				+ " and (select count(*) from Response resp where resp.request.id = r.id)<3 "
				// 包括邀请的请求
				+ " or r.id in (select a.request.id from Appoint a where a.state=0 and a.consultant.id='" + consId
				+ "') order by r.createTime desc";
		String hql = sql + str;
		// System.out.println(hql);
		Map<String, Object> map = new HashMap<>();
		map.put("request", dataDAO.loadItems(hql, pager));
		hql = "select count(*) from Request r " + str;
		// 计算数据条数
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();
		map.put("pager", pager);
		return map;
	}

	// 加载请求的领域
	@Override
	public List<ReqFieldBean> loadReqFields(int reqId)
	{
		String hql = "select new com.gwb.bean.ReqFieldBean(r.id,r.field.id,r.field.title,r.request.id) from ReqField r where r.request.id= "
				+ reqId;
		return dataDAO.loadItems(hql);
	}

	// 导出用户需求
	@Override
	public List<RequestBean> exportRequest(Pager pager)
	{
		Map<String, String> conditions = pager.getCondition();
		String temp = " where r.id!=0";
		if (!conditions.isEmpty())
		{
			String startTime = conditions.get("startTime");
			if (!startTime.equals(""))
			{
				temp += " and r.createTime >'" + startTime + " 00:00:00'";
			}
			String endTime = conditions.get("endTime");
			if (!endTime.equals(""))
			{
				temp += " and r.createTime <'" + endTime + " 23:59:59'";
			}
		}
		String hql = sql + temp + " order by r.createTime desc";
		return dataDAO.loadItems(hql);
	}
}
