package com.gwb.admin.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.ws.Action;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;
import com.gwb.bean.UserBean;
import com.gwb.excel.DownLoad;
import com.gwb.excel.ExcelTool;
import com.gwb.excel.UserEx;
import com.gwb.model.Card;
import com.gwb.model.User;
import com.gwb.service.UserService;
import com.gwb.util.LogUtils;
import com.gwb.util.PagerTool;
import com.gwb.util.ResultUtils;
import com.opensymphony.xwork2.ActionSupport;

@Component("adUserAction")
@Scope("prototype")
public class UserAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private List<UserBean> users;
	private User user;
	private List<Card> cards;
	private UserBean userBean;
	private Pager pager;
	private PagerTool pagerTool;
	private String condition;
	private int id;
	private String selector;

	public String getSelector()
	{
		return selector;
	}

	public void setSelector(String selector)
	{
		this.selector = selector;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCondition()
	{
		return condition;
	}

	public void setCondition(String condition)
	{
		this.condition = condition;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public PagerTool getPagerTool()
	{
		return pagerTool;
	}

	public void setPagerTool(PagerTool pagerTool)
	{
		this.pagerTool = pagerTool;
	}

	public UserBean getUserBean()
	{
		return userBean;
	}

	public void setUserBean(UserBean userBean)
	{
		this.userBean = userBean;
	}

	private UserService userService;

	public List<UserBean> getUsers()
	{
		return users;
	}

	public void setUsers(List<UserBean> users)
	{
		this.users = users;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public List<Card> getCards()
	{
		return cards;
	}

	public void setCards(List<Card> cards)
	{
		this.cards = cards;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Resource(name = "userService")
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	// 用户列表
	@SuppressWarnings("unchecked")
	@Action()
	public String userList()
	{
		Map<String, Object> map = userService.loadUserList(pager);

		users = (List<UserBean>) map.get("user");
		pager = (Pager) map.get("pager");
		pagerTool = new PagerTool(pager, "admin/user_userList");
		selector = "userList";
		return SUCCESS;
	}

	// 用户信息
	public String userInfo()
	{
		userBean = userService.loadUserInfo(user.getId());
		userBean.setCards(userService.loadCards(user.getId()));
		return SUCCESS;
	}

	// 审核用户
	public void reviewUser()
	{
		String result = "";
		try
		{
			result = userService.reviewUser(user.getId(), user.getState());
			LogUtils.info("review User-userID:" + user.getId());
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "审核失败";
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 禁用用户
	public void lockUser()
	{
		boolean result = true;
		try
		{
			userService.lockUser(user.getId());
		} catch (Exception e)
		{
			result = false;
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 启用用户
	public void unlockUser()
	{
		boolean result = true;
		try
		{
			userService.unlockUser(user.getId());
		} catch (Exception e)
		{
			result = false;
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 删除用户
	public void delUser()
	{
		boolean result = true;
		try
		{
			userService.delUser(user.getId());
		} catch (Exception e)
		{
			result = false;
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 导出用户列表
	public void exportUserList()
	{
		ExcelTool<UserEx> ex = new ExcelTool<>();
		String[] headers = { "id", "姓名", "职务", "邮箱", "手机号", "电话", "个人简介", "注册时间" };
		List<UserEx> userlist = userService.loadUserList();
		OutputStream out;
		try
		{
			out = new FileOutputStream("user.xls");
			ex.exportExcel("用户列表", headers, userlist, out);
			out.close();
			DownLoad.download("user.xls", ServletActionContext.getResponse());

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
