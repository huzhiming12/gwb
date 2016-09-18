package com.gwb.bean;

public class CardBean
{
	/**
	 * 名片类 存储名片的路径
	 */
	private int id;
	// 名片路径
	private String url;

	public CardBean(int id, String url)
	{
		super();
		this.id = id;
		this.url = url;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

}
