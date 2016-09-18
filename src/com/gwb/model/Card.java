package com.gwb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_card")
public class Card
{
	/**
	 * 名片类 存储名片的路径
	 */
	private int id;
	// 名片路径
	private String url;
	// 确定用户id
	private String userId;

	@Id
	@GeneratedValue
	@Column(name = "card_id")
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

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

}
