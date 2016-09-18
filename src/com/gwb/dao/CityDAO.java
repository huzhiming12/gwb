package com.gwb.dao;

import java.util.List;
import java.util.Map;

import com.gwb.bean.CityBean;
import com.gwb.bean.Pager;
import com.gwb.model.City;

public interface CityDAO
{
	// 添加名片
	public boolean addCity(City city);

	// 删除城市
	public void delCity(int cityId);

	// 加载所有城市
	public Map<String, Object> loadAllCity(Pager pager);

	// 加载所有城市
	public List<CityBean> loadAllCity();
	
	public void addTempDate();

}
