package com.gwb.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.bean.FieldBean;
import com.gwb.bean.Pager;
import com.gwb.dao.FieldDAO;
import com.gwb.model.Field;

@Component("fieldService")
public class FieldService
{
	private FieldDAO fieldDAO;

	public FieldDAO getFieldDAO()
	{
		return fieldDAO;
	}

	@Resource(name = "fieldDAO")
	public void setFieldDAO(FieldDAO fieldDAO)
	{
		this.fieldDAO = fieldDAO;
	}
	// *************************************************

	// 添加咨询领域
	@Transactional
	public boolean addField(Field field)
	{
		return fieldDAO.addField(field);
	}

	// 删除领域
	@Transactional
	public void delField(Field field)
	{
		fieldDAO.delField(field);
	}

	// 加载所有领域信息
	@Transactional
	public Map<String, Object> loadAllFields(Pager pager)
	{
		return fieldDAO.loadAllFields(pager);
	}

	// 加载所有领域信息
	@Transactional
	public List<FieldBean> loadAllFields()
	{
		return fieldDAO.loadAllFields();
	}

}
