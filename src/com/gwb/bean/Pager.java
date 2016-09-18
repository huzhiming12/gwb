package com.gwb.bean;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("pager")
@Scope("prototype")
public class Pager
{
	// 当前页数
	private int pageNow = 1;
	// 每页显示的条数
	private int pageSize;
	// 数据的总条数
	private int totalNum;
	// 总的页数
	private int pageNum;

	// 条件 （搜索条件 如 name:zhangsan）
	private Map<String, String> condition = new HashMap<>();

	public Pager()
	{
	}

	public int getPageNow()
	{
		return pageNow;
	}

	public void setPageNow(int pageNow)
	{
		this.pageNow = pageNow;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getTotalNum()
	{
		return totalNum;
	}

	public void setTotalNum(int totalNum)
	{
		this.totalNum = totalNum;
	}

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	// 计算总页数
	public void caculatePageNum()
	{
		if (totalNum % pageSize > 0)
			pageNum = totalNum / pageSize + 1;
		else
			pageNum = totalNum / pageSize;
	}

	public Map<String, String> getCondition()
	{
		return condition;
	}

	public void setCondition(Map<String, String> condition)
	{
		this.condition = condition;
	}

}
