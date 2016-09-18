package com.gwb.admin.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.model.Admin;
import com.gwb.service.AdminService;
import com.gwb.util.ResultUtils;
import com.opensymphony.xwork2.ActionSupport;

@Component("adIndexAction")
@Scope("protoType")
public class IndexAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private Admin admin;
	private AdminService adminService;
	private String oldpwd;

	public String getOldpwd()
	{
		return oldpwd;
	}

	public void setOldpwd(String oldpwd)
	{
		this.oldpwd = oldpwd;
	}

	public AdminService getAdminService()
	{
		return adminService;
	}

	@Resource(name = "adminService")
	public void setAdminService(AdminService adminService)
	{
		this.adminService = adminService;
	}

	public Admin getAdmin()
	{
		return admin;
	}

	public void setAdmin(Admin admin)
	{
		this.admin = admin;
	}

	public void loginCheck()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", adminService.adminLogin(admin, ServletActionContext.getRequest()));
		} catch (Exception e)
		{
			result.put("status", 400);
			e.printStackTrace();
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	public String login()
	{
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (session.getAttribute("username") != null && session.getAttribute("userType").equals("Admin"))
		{
			return "login";
		}
		return SUCCESS;
	}

	public String logout()
	{
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("username");
		session.removeAttribute("userType");
		return "logout";
	}

	public String changePassword()
	{
		return SUCCESS;
	}

	public void changePwdResult()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("res", adminService.changePwd(admin, oldpwd));
			result.put("status", 200);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误！");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "密码修改失败，请稍后重试！");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

}
