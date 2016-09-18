package com.gwb.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.CityBean;
import com.gwb.bean.Pager;
import com.gwb.dao.CityDAO;
import com.gwb.model.City;
import com.gwb.model.TempDate;
import com.gwb.util.Config;
import com.gwb.util.DateUtil;

@Component("cityDAO")
public class CityDAOImpl implements CityDAO
{

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

	// ***********************************************

	/**
	 * @author:huzhiming
	 * @function：添加城市
	 * @field:
	 * @boolean:
	 */
	@Override
	public boolean addCity(City city)
	{
		return dataDAO.addItem(city);
	}

	/**
	 * @author:huzhiming
	 * @function：删除城市
	 * @field:
	 * @boolean:
	 */

	@Override
	public void delCity(int cityId)
	{
		String hql = "update City c set c.state=1 where c.id=" + cityId;
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：加载所有城市
	 * @field:
	 * @return_type:
	 */

	@Override
	public Map<String, Object> loadAllCity(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		Map<String, String> conditions = pager.getCondition();
		String temp = " where c.state=0 ";
		if (!conditions.isEmpty())
		{
			String keyword = conditions.get("keyword");
			if (!keyword.equals(""))
			{
				temp += " and c.name like '%" + keyword + "%'";
			}
		}
		String hql = "from City c " + temp;
		List<CityBean> cities = dataDAO.loadItems(hql, pager);
		hql = "select count(*)from City c " + temp;
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();

		Map<String, Object> res = new HashMap<>();
		res.put("city", cities);
		res.put("pager", pager);
		return res;
	}

	@Override
	public List<CityBean> loadAllCity()
	{
		String hql = "from City c where c.state=0";
		return dataDAO.loadItems(hql);
	}

	@SuppressWarnings("static-access")
	@Override
	public void addTempDate()
	{
		Date date = DateUtil.stringToDate("2014-01-01 00:00:00");
		Calendar calendar = new GregorianCalendar();
		boolean flag = true;
		TempDate tempDate;

		while (flag)
		{
			calendar.setTime(date);
			calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
			if (DateUtil.DateToString(date, "yyyy-MM-dd").equals("2026-01-01"))
				break;
			tempDate = new TempDate();
			tempDate.setD(date);
			System.out.println(DateUtil.DateToString(date));
			dataDAO.addItem(tempDate);
		}

	}

}
