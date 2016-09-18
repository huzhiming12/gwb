package com.gwb.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.AppointBean;
import com.gwb.bean.Pager;
import com.gwb.dao.AppointDAO;
import com.gwb.model.Appoint;
import com.gwb.util.Config;
import com.gwb.util.DateUtil;

@Component("appointDAO")
public class AppointDAOImpl implements AppointDAO
{
	String sql = "select new com.gwb.bean.AppointBean(a.id,a.consultant.id,a.consultant.name,a.consultant.icon,a.state,a.createTime,"
			+ "a.dealTime,a.request.id) from Appoint a ";

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
	 * @function：更新邀请的状态
	 * @field:
	 * @return_type:
	 */
	@Override
	public void updateAppointState(int id,int value)
	{
		String date = DateUtil.DateToString(new Date());
		String hql = "update Appoint set state=" + value + ",dealTime='" + date + "'where id="
				+ id;
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：加载邀请详细信息
	 * @field:
	 * @return_type:
	 */
	@Override
	public Appoint appointDetail(int id)
	{
		String hql = "from Appoint where id = " + id;
		List<Appoint> appoints = dataDAO.loadItems(hql);
		if (appoints != null && appoints.size() > 0)
		{
			return appoints.get(0);
		}
		return null;
	}

	/**
	 * @author:huzhiming
	 * @function：更新条目
	 * @field:
	 * @return_type:
	 */
	@Override
	public void updateItem(Appoint appoint)
	{
		dataDAO.updateItem(appoint);
	}

	/**
	 * @author:huzhiming
	 * @function：添加邀请
	 * @field:
	 * @boolean:
	 */
	@Override
	public boolean addAppoint(Appoint appoint)
	{
		return dataDAO.addItem(appoint);
	}

	/**
	 * @author:huzhiming
	 * @function：通过请求id加载邀请
	 * @field:
	 * @List<Appoint>:
	 */
	@Override
	public List<AppointBean> loadAppointsByReqId(int id)
	{
		String hql = sql + " where a.request.id= " + id+" order by a.createTime desc";
		return dataDAO.loadItems(hql);
	}

	@Override
	public boolean isConsAppoint(int reqId, String consId)
	{
		String hql = "select count(*) from Appoint a where a.state=0 and a.request.id=" + reqId
				+ " and a.consultant.id='" + consId + "'";
		int num = dataDAO.loadItemsNum(hql);
		if (num > 0)
			return true;
		else
			return false;
	}

	// 加载顾问收到的邀请
	@Override
	public Map<String, Object> loadConsAppoint(Pager pager, String consId)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.APPOINT_PAGER_SIZE);
		String hql = sql + " where a.consultant.id='" + consId + "' order by a.createTime desc";
		List<AppointBean> list = dataDAO.loadItems(hql, pager);
		hql = "select count(*)from Appoint a where a.consultant.id='" + consId + "'";
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();

		Map<String, Object> res = new HashMap<>();
		res.put("appoint", list);
		res.put("pager", pager);
		return res;
	}

	// 关闭超过12小时未回应的邀请
	@Override
	public void closeOutTimeAppoint()
	{
		Date now = new Date();
		// 12小时前的时间
		Date past = new Date(now.getTime() - 12 * 60 * 60 * 1000);
		String hql = "update t_appoint a,t_request r set a.state=3,r.appointState=1 where a.req_id = r.req_id and a.state=0 and a.createTime<'"
				+ DateUtil.DateToString(past) + "'";
		System.out.println(hql);
		dataDAO.SQLUpdate(hql);
	}
}
