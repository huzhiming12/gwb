package com.gwb.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.interceptor.NotLoginException;
import com.gwb.model.User;
import com.gwb.service.UserService;
import com.gwb.util.ResultUtils;
import com.opensymphony.xwork2.ActionSupport;

@Component("userAction")
@Scope(value = "prototype")
public class UserAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private User user;
	private String card;
	private String oldPassword;

	public String getOldPassword()
	{
		return oldPassword;
	}

	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
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

	public String getCard()
	{
		return card;
	}

	public void setCard(String card)
	{
		this.card = card;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * @author:huzhiming
	 * @function：检测电话号码是否使用
	 * @field:user.mobilePhone
	 * @return_type: true:已使用 false:未使用
	 */
	public void phoneCheck()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result = userService.isPhoneUse(user.getMobilePhone());
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数有误");

		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：用户注册 手机号码注册
	 * @field: user.mobilePhone, user.password, user.name， user.position
	 *         card:名片以;分隔
	 * @return_type: true or false
	 */
	public void register()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = userService.userRegister(user, card);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数有误");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，请稍后再试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}
	
	//更新用户信息
	public void updateInfo()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			userService.updateInfo(user);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数有误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
		
	}

	/**
	 * @author:huzhiming
	 * @function：更换头像
	 * @field:
	 * @return_type:
	 */
	public void changeIcon()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			userService.changeIcon(user.getId(), user.getIcon());
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数有误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：更新用户姓名
	 * @field:1 user.name 2 user.mobilePhone
	 * @return_type: true or false
	 */
	public void changeName()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			userService.changeName(user.getId(), user.getName());
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数有误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：更新用户职务
	 * @field: user.position user.mobilePhone
	 * @return_type: true or false
	 */
	public void changePosition()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			userService.changePosition(user.getId(), user.getPosition());
		} catch (NullPointerException e)
		{
			// 参数有问题
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数有误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：更新密码
	 * @field: user.password user.mobilePhone
	 * @return_type:
	 */
	public void changePassword()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result = userService.changePassword(user.getId(), user.getPassword(), oldPassword);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数有误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：更新用户电话
	 * @field:
	 * @return_type:
	 */
	public void changePhone()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			userService.changePhone(user.getId(), user.getPhone());
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result = new HashMap<>();
			result.put("status", 400);
			result.put("error", "参数有误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：更新个人简介
	 * @field:
	 * @return_type:
	 */
	public void changeIntroduction()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			userService.changeIntroduction(user.getId(), user.getIntroduction());
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数有误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：更新用户e_mail
	 * @field: user.e_mail user.mobilePhone
	 * @void:
	 */
	public void changeE_mail()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			userService.changeE_mail(user.getId(), user.getE_mail());
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数有误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：添加名片
	 * @field: user.id card
	 * @void:
	 */
	public void addCards()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = userService.addCards(user.getId(), card);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：用户登录
	 * @field:user.mobilePhone user.password
	 * @void:0：用户不存在 1:密码错误 2：登录成功
	 */
	public void login()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = userService.userLogin(ServletActionContext.getRequest(), user.getMobilePhone(),
					user.getPassword());
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数有误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：加载用户信息
	 */
	public void userInfo()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", userService.loadUserInfo(user.getId()));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "请求参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	public void forgetPassword()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			userService.changePassword(user.getPassword(), user.getMobilePhone());
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数有误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);

	}

}
