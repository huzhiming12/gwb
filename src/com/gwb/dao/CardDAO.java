package com.gwb.dao;

import java.util.List;

import com.gwb.bean.CardBean;
import com.gwb.model.Card;

public interface CardDAO
{
	// 添加名片
	public boolean addcard(Card card);

	// 通过id删除名片
	public boolean delCardById(int id);

	// 加载用户名片
	public List<CardBean> loadCards(String userId);

}
