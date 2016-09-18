package com.gwb.dao;

import java.util.List;
import java.util.Map;

import com.gwb.bean.FieldBean;
import com.gwb.bean.Pager;
import com.gwb.model.Field;

public interface FieldDAO
{
	// 添加咨询领域
	public boolean addField(Field field);

	// 删除咨询领域
	public void delField(Field field);

	// 加载所有咨询领域列表
	public Map<String, Object> loadAllFields(Pager pager);
	
	public List<FieldBean> loadAllFields();

}
