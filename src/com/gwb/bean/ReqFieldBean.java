package com.gwb.bean;

/**
 * 需求所属分类
 */
public class ReqFieldBean
{
	private int id;
	// 领域id
	private int fieldId;
	// 领域标题
	private String fieldTitle;
	// 需求id
	private int reqId;

	public ReqFieldBean(int id, int fieldId, String fieldTitle, int reqId)
	{
		super();
		this.id = id;
		this.fieldId = fieldId;
		this.fieldTitle = fieldTitle;
		this.reqId = reqId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getFieldId()
	{
		return fieldId;
	}

	public void setFieldId(int fieldId)
	{
		this.fieldId = fieldId;
	}

	public String getFieldTitle()
	{
		return fieldTitle;
	}

	public void setFieldTitle(String fieldTitle)
	{
		this.fieldTitle = fieldTitle;
	}

	public int getReqId()
	{
		return reqId;
	}

	public void setReqId(int reqId)
	{
		this.reqId = reqId;
	}

}
