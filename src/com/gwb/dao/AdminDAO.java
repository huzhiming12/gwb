package com.gwb.dao;

import com.gwb.model.Admin;

public interface AdminDAO
{
	// 加载管理员信息
	public Admin adminInfo(String userName);

	// 更新信息
	public void updateInfo(String username, String item, String value);

}
