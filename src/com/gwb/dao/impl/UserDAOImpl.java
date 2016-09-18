package com.gwb.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;
import com.gwb.bean.UserBean;
import com.gwb.dao.UserDAO;
import com.gwb.model.User;
import com.gwb.util.Config;

@Component("userDAO")
public class UserDAOImpl implements UserDAO
{

	String sql = "select new com.gwb.bean.UserBean(u.id, u.icon, u.name, u.position, "
			+ "u.e_mail, u.mobilePhone,u.phone,u.introduction, u.registTime, u.password,u.state) from User u";
	private DataDAO dataDAO;

	public DataDAO getDataDAO()
	{
		return dataDAO;
	}

	@Resource(name = "dataDAO")
	public void setDataDAO(DataDAO dataDAO)
	{
		this.dataDAO = dataDAO;
	}

	/**
	 * @author:huzhiming
	 * @function：添加用户 必填项：mobilephone
	 */
	@Override
	public boolean addUser(User user)
	{
		return dataDAO.addItem(user);
	}

	/**
	 * @author:huzhiming
	 * @function：删除用户
	 */
	@Override
	public void delUser(String userId)
	{
		String hql = "update User set state=3 where id='" + userId + "'";
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：通过id查找用户
	 */
	@Override
	public User loadUserById(String id)
	{
		String hql = sql + " where u.state!=3 and u.id='" + id + "'";
		List<User> users = dataDAO.loadItems(hql);
		if (users != null && users.size() > 0)
		{
			return users.get(0);
		}
		return null;
	}

	@Override
	public UserBean loadUserInfo(String id)
	{
		String hql = sql + " where u.state!=3 and u.id= '" + id + "'";
		List<UserBean> users = dataDAO.loadItems(hql);
		if (users != null && users.size() > 0)
		{
			return users.get(0);
		}
		return null;
	}

	/**
	 * @author:huzhiming
	 * @function：通过电话查找用户
	 */
	@Override
	public UserBean loadUserByPhone(String phone)
	{
		String hql = sql + " where u.mobilePhone= '" + phone + "' and (u.state=1 or u.state=4)";
		List<UserBean> users = dataDAO.loadItems(hql);
		if (users != null && users.size() > 0)
		{
			return users.get(0);
		}
		return null;
	}

	/**
	 * @author:huzhiming
	 * @function：更新用户用户信息
	 * @field:item-更新项目 value：更新内容 condition:条件(手机号码)
	 * @return_type:
	 */
	@Override
	public void updateInfo(String item, String value, String id)
	{
		String hql = "update User set " + item + " ='" + value + "' where id = '" + id + "'";
		// System.out.println(hql);
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：加载用户列表
	 */
	@Override
	public Map<String, Object> loadUserList(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		String hql = sql;
		// 查询条件
		Map<String, String> conditions = pager.getCondition();
		String str = " where u.state!=3 ";
		if (!conditions.isEmpty())
		{
			String value = (String) conditions.get("condition");
			str += "  and (u.name like '%" + value + "%' or u.mobilePhone like '%" + value + "%' or u.position like '%"
					+ value + "%')";
		}
		hql += str + " order by registTime desc";
		List<UserBean> users = dataDAO.loadItems(hql, pager);
		// 查询总条数
		hql = "select count(*)from User u" + str + " order by registTime desc";
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();
		Map<String, Object> map = new HashMap<>();
		map.put("user", users);
		map.put("pager", pager);
		return map;
	}

	// 导出审核通过的用户
	@Override
	public List<UserBean> getUserList()
	{
		String hql = sql + " where u.state=1 ";
		return dataDAO.loadItems(hql);
	}

	// 获取用户人数
	@Override
	public int loadUserNum()
	{
		String hql = "select count(*) from User where state=1";
		return dataDAO.loadItemsNum(hql);
	}

}
