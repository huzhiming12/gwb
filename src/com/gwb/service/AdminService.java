package com.gwb.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.dao.AdminDAO;
import com.gwb.model.Admin;
import com.gwb.util.Md5Utils;

@Component("adminService")
public class AdminService
{
	private AdminDAO adminDAO;

	public AdminDAO getAdminDAO()
	{
		return adminDAO;
	}

	@Resource(name = "adminDAO")
	public void setAdminDAO(AdminDAO adminDAO)
	{
		this.adminDAO = adminDAO;
	}

	// 0 不存在 1:密码错误 2：登录成功
	@Transactional
	public int adminLogin(Admin admin, HttpServletRequest request)
	{
		Admin temp = adminDAO.adminInfo(admin.getUserName());
		int flag = 0;
		if (temp != null)
		{
			if (!temp.getPassword().equals(Md5Utils.encodeByMD5(admin.getPassword())))
				flag = 1;
			else
			{
				flag = 2;
				HttpSession session = request.getSession();
				session.setAttribute("username", admin.getUserName());
				session.setAttribute("userType", "Admin");
			}
		}
		return flag;
	}

	// 修改密码
	@Transactional
	public int changePwd(Admin admin, String oldpwd)
	{
		int result = 1;
		Admin temp = adminDAO.adminInfo(admin.getUserName());
		if (temp != null) // 账号存在
		{
			if (Md5Utils.encodeByMD5(oldpwd).equals(temp.getPassword()))
			{
				adminDAO.updateInfo(admin.getUserName(), "password", Md5Utils.encodeByMD5(admin.getPassword()));
				result = 3;
			} else
				result = 2;
		} else
			result = 1;
		System.out.println(result);
		return result;
	}

}
