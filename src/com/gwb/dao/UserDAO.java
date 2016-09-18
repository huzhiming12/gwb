package com.gwb.dao;

import java.util.List;
import java.util.Map;

import com.gwb.bean.Pager;
import com.gwb.bean.UserBean;
import com.gwb.model.User;

public interface UserDAO
{
	// 添加用户
	public boolean addUser(User user);

	// 删除用户
	public void delUser(String userId);

	// 通过id查找用户
	public User loadUserById(String id);

	public UserBean loadUserInfo(String id);

	// 通过电话查找用户
	public UserBean loadUserByPhone(String phone);

	// 更新用户头像 item：更新项目 value：更新值 condition：条件
	public void updateInfo(String item, String value, String id);

	// 加载用户列表
	public Map<String, Object> loadUserList(Pager pager);

	// 加载所有用户
	public List<UserBean> getUserList();

	// 获取用户人数
	public int loadUserNum();

}
