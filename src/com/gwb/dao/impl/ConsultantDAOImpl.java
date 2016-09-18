package com.gwb.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.ConsultantBean;
import com.gwb.bean.GoodAtFieldBean;
import com.gwb.bean.Pager;
import com.gwb.dao.ConsultantDAO;
import com.gwb.model.Consultant;

@Component("consultantDAO")
public class ConsultantDAOImpl implements ConsultantDAO
{

	private String sql = "select new com.gwb.bean.ConsultantBean(c.id, c.mobilePhone, "
			+ "c.phone, c.icon,c.name,c.position,c.e_mail,c.introduction, ci.name,"
			+ "c.registerTime,c.password,c.state) from Consultant c left join c.city ci ";

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

	// *********************方法区*******************************

	/**
	 * @author:huzhiming
	 * @function：查找顾问
	 * @field:
	 * @return_type:
	 */
	@Override
	public ConsultantBean loadConsBeanById(String consId)
	{
		String hql = sql + " where c.id = '" + consId + "'";
		List<ConsultantBean> list = dataDAO.loadItems(hql);
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public Consultant loadConsultantById(String consId)
	{
		String hql = "from Consultant c where c.id = '" + consId + "'";
		List<Consultant> list = dataDAO.loadItems(hql);
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public ConsultantBean loadConsultantByPhone(String mobilePhone)
	{
		String hql = sql + " where c.mobilePhone = '" + mobilePhone + "' and (c.state=1 or c.state=4)";
		List<ConsultantBean> list = dataDAO.loadItems(hql);
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	/**
	 * @author:huzhiming
	 * @function：查找顾问
	 */
	@Override
	public Map<String, Object> searchConsultant(String hql, String hql1, Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		;
		Map<String, Object> map = new HashMap<>();
		map.put("consultant", dataDAO.loadItems(hql, pager));
		// 查询数据的总条数
		pager.setTotalNum(dataDAO.loadItemsNum(hql1));
		// 计算总页数
		pager.caculatePageNum();
		map.put("pager", pager);
		return map;
	}

	/**
	 * @author:huzhiming
	 * @function：添加顾问
	 * @field:
	 * @return_type:
	 */
	@Override
	public boolean addConsult(Consultant consultant)
	{
		return dataDAO.addItem(consultant);
	}

	/**
	 * @author:huzhiming
	 * @function：删除顾问
	 * @field:
	 * @boolean:
	 */
	@Override
	public void delConsultant(String consId)
	{
		String hql = "update Consultant set state=3 where id ='" + consId + "'";
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：更新顾问信息
	 * @field:
	 * @boolean:
	 */
	@Override
	public void updateInfo(String item, String value, String consId)
	{
		String hql = "update Consultant set " + item + " ='" + value + "' where id = '" + consId + "'";
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：更新顾问信息
	 * @field:
	 * @return_type:
	 */
	@Override
	public void updateInfo(Consultant consultant)
	{
		dataDAO.updateItem(consultant);
	}

	/**
	 * @author:huzhiming
	 * @function：加载顾问擅长领域
	 */
	@Override
	public List<GoodAtFieldBean> loadFields(String consId)
	{
		String hql = "select new com.gwb.bean.GoodAtFieldBean(g.id,g.field.title)from GoodAtField g where g.consultant.id='"
				+ consId + "'";
		return dataDAO.loadItems(hql);
	}

	// 加载导出用户信息
	@Override
	public List<ConsultantBean> getConsList()
	{
		String hql = sql + "where c.state=1";
		return dataDAO.loadItems(hql);
	}

	@Override
	public void delConsGoodAtFields(String consId)
	{
		String hql = "delete GoodAtField g where g.consultant.id='" + consId + "'";
		dataDAO.updateData(hql);
	}

}
