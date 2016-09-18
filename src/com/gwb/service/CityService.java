package com.gwb.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.bean.CityBean;
import com.gwb.bean.Pager;
import com.gwb.dao.CityDAO;
import com.gwb.model.City;

@Component("cityService")
public class CityService
{

	private CityDAO cityDAO;

	public CityDAO getCityDAO()
	{
		return cityDAO;
	}

	@Resource(name = "cityDAO")
	public void setCityDAO(CityDAO cityDAO)
	{
		this.cityDAO = cityDAO;
	}

	// 添加城市
	@Transactional
	public boolean addCity(City city)
	{
		return cityDAO.addCity(city);
	}

	// 加载所有城市
	@Transactional
	public Map<String, Object> loadAllCity(Pager pager)
	{
		return cityDAO.loadAllCity(pager);
	}

	// 加载所有城市
	@Transactional
	public List<CityBean> loadAllCity()
	{
		return cityDAO.loadAllCity();
	}

	@Transactional
	public void delCity(int cityId)
	{
		cityDAO.delCity(cityId);
	}
	
	@Transactional
	public void addTempDate()
	{
		cityDAO.addTempDate();
	}

	@Transactional
	public void testAddCity()
	{
		City city1 = new City();
		city1.setName("ddd");
		cityDAO.addCity(city1);
		if (true)
			throw new RuntimeException("error");
		@SuppressWarnings("unused")
		City city2 = new City();
		city2.setName("aaa");
		cityDAO.addCity(city2);
	}

}
