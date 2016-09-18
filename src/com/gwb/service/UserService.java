package com.gwb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.bean.CardBean;
import com.gwb.bean.Pager;
import com.gwb.bean.UserBean;
import com.gwb.dao.CardDAO;
import com.gwb.dao.RequestDAO;
import com.gwb.dao.UserDAO;
import com.gwb.dao.WalletDAO;
import com.gwb.excel.UserEx;
import com.gwb.model.Card;
import com.gwb.model.User;
import com.gwb.util.Config;
import com.gwb.util.FileUtils;
import com.gwb.util.Md5Utils;
import com.gwb.util.SmsUtils;
import com.hx.server.imp.HxUserRegister;

@Component("userService")
public class UserService
{
	private UserDAO userDAO;
	private CardDAO cardDAO;
	private WalletDAO walletDAO;
	private RequestDAO requestDAO;

	public UserDAO getUserDAO()
	{
		return userDAO;
	}

	@Resource(name = "userDAO")
	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	public CardDAO getCardDAO()
	{
		return cardDAO;
	}

	@Resource(name = "cardDAO")
	public void setCardDAO(CardDAO cardDAO)
	{
		this.cardDAO = cardDAO;
	}

	public WalletDAO getWalletDAO()
	{
		return walletDAO;
	}

	@Resource(name = "walletDAO")
	public void setWalletDAO(WalletDAO walletDAO)
	{
		this.walletDAO = walletDAO;
	}

	public RequestDAO getRequestDAO()
	{
		return requestDAO;
	}

	@Resource(name = "requestDAO")
	public void setRequestDAO(RequestDAO requestDAO)
	{
		this.requestDAO = requestDAO;
	}

	// 用户注册
	@Transactional
	public Map<String, Object> userRegister(User user, String cards)
	{

		String id = UUID.randomUUID().toString();
		// 设置注册时间
		user.setRegistTime(new Date());
		user.setId(id);
		// 密码加密
		user.setPassword(Md5Utils.encodeByMD5(user.getPassword()));
		Map<String, Object> res = new HashMap<>();
		if (userDAO.addUser(user))
		{
			res.put("status", 200);
		} else
		{
			res.put("status", 400);
			res.put("error", "注册失败，请稍后重试");
		}
		// 添加名片信息
		if (!cards.equals(""))
		{
			String[] strings = cards.split(";");
			for (String str : strings)
			{
				Card card = new Card();
				card.setUserId(id);
				card.setUrl(str);
				cardDAO.addcard(card);
			}
		}
		return res;
	}

	// 删除用户
	@Transactional
	public void delUser(String userId)
	{
		userDAO.delUser(userId);
	}

	// 禁用用户
	@Transactional
	public void lockUser(String userId)
	{
		userDAO.updateInfo("state", "4", userId);
	}

	// 启用用户
	@Transactional
	public void unlockUser(String userId)
	{
		userDAO.updateInfo("state", "1", userId);
	}

	// 通过id查找用户
	@Transactional
	public User findUserByid(String id)
	{
		return userDAO.loadUserById(id);
	}

	// 电话号码是否使用
	@Transactional
	public Map<String, Object> isPhoneUse(String mobilePhone)
	{
		Map<String, Object> map = new HashMap<>();
		if (userDAO.loadUserByPhone(mobilePhone) != null)
		{
			map.put("res", true);
		} else
			map.put("res", false);
		map.put("status", 200);
		return map;
	}

	// 更新用户信息
	@Transactional
	public void updateInfo(User user)
	{
		userDAO.updateInfo("icon", user.getIcon(), user.getId());
		userDAO.updateInfo("name", user.getName(), user.getId());
		userDAO.updateInfo("position", user.getPosition(), user.getId());
		userDAO.updateInfo("e_mail", user.getE_mail(), user.getId());
		userDAO.updateInfo("phone", user.getPhone(), user.getId());
		userDAO.updateInfo("introduction", user.getIntroduction(), user.getId());
	}

	// 更换用户头像
	@Transactional
	public void changeIcon(String id, String url)
	{
		// 获取原先的icon
		UserBean tempUser = userDAO.loadUserInfo(id);
		// 删除原来的icon
		if (tempUser != null && tempUser.getIcon() != null)
			FileUtils.delFile(tempUser.getIcon());
		userDAO.updateInfo("icon", url, id);
	}

	// 更新用户姓名
	@Transactional
	public void changeName(String id, String name)
	{
		userDAO.updateInfo("name", name, id);
	}

	// 更新用户职务
	@Transactional
	public void changePosition(String id, String position)
	{
		userDAO.updateInfo("position", position, id);
	}

	// 更新密码
	@Transactional
	public Map<String, Object> changePassword(String id, String password, String oldPassword)
	{
		Map<String, Object> res = new HashMap<>();
		UserBean userBean = userDAO.loadUserInfo(id);
		if (userBean != null)
		{
			// 对密码进行MD5加密
			password = Md5Utils.encodeByMD5(password);
			if (userBean.getPassword().equals(Md5Utils.encodeByMD5(oldPassword)))
			{
				userDAO.updateInfo("password", password, id);
				res.put("status", 200);
			} else
			{
				res.put("error", "原密码输入错误");
				res.put("status", 400);
			}
		} else
		{
			res.put("error", "账号不存在");
			res.put("status", 400);
		}
		return res;
	}

	// 更换密码
	@Transactional
	public void changePassword(String password, String mobilePhone)
	{
		UserBean userBean = userDAO.loadUserByPhone(mobilePhone);
		userDAO.updateInfo("password", Md5Utils.encodeByMD5(password), userBean.getId());
	}

	// 更新邮箱
	@Transactional
	public void changeE_mail(String id, String e_mail)
	{
		userDAO.updateInfo("e_mail", e_mail, id);
	}

	// 更新电话
	@Transactional
	public void changePhone(String id, String phone)
	{
		userDAO.updateInfo("phone", phone, id);
	}

	// 更新自我介绍
	@Transactional
	public void changeIntroduction(String id, String introduction)
	{
		userDAO.updateInfo("introduction", introduction, id);
	}

	// 添加名片
	@Transactional
	public Map<String, Object> addCards(String userId, String cards)
	{
		Map<String, Object> res = new HashMap<>();
		boolean flag = true;
		// 添加名片信息
		String[] strings = cards.split(";");
		for (String str : strings)
		{
			Card card = new Card();
			card.setUserId(userId);
			card.setUrl(str);
			if (!cardDAO.addcard(card))
				flag = false;
		}
		if (flag)
			res.put("status", 200);
		else
		{
			res.put("status", 400);
			res.put("error", "名片上传失败，请稍后重试");
		}
		return res;

	}

	// 用户登录 0：用户不存在 1:密码错误 2：登录成功
	@Transactional
	public Map<String, Object> userLogin(HttpServletRequest request, String mobilePhone, String password)
	{
		Map<String, Object> res = new HashMap<>();
		UserBean tempUser = userDAO.loadUserByPhone(mobilePhone);
		res.put("status", 400);
		if (tempUser != null)
		{
			if (tempUser.getState() == 4)
			{
				res.put("status", 400);
				res.put("error", "账户已禁用，请联系管理员");
				return res;
			}
			if (tempUser.getPassword().equals(Md5Utils.encodeByMD5(password)))
			{
				res.put("status", 200);
				res.put("res", tempUser);
				HttpSession session = request.getSession();
				session.setAttribute("username", mobilePhone);
				session.setAttribute("userType", "User");
			} else
				res.put("error", "密码错误");
		} else
			res.put("error", "账号不存在");
		return res;
	}

	// 加载用户名片
	@Transactional
	public List<CardBean> loadCards(String userId)
	{
		List<CardBean> cards = cardDAO.loadCards(userId);
		return cards;
	}

	// 加载用户信息
	@Transactional
	public UserBean loadUserInfo(String userId)
	{
		UserBean userBean = userDAO.loadUserInfo(userId);
		userBean.setCards(loadCards(userBean.getId()));
		return userBean;
	}

	// 加载用户列表 admin
	@Transactional
	public Map<String, Object> loadUserList(Pager pager)
	{
		return userDAO.loadUserList(pager);
	}

	// 查询用户
	@Transactional
	public List<UserEx> loadUserList()
	{
		List<UserBean> beans = userDAO.getUserList();
		List<UserEx> uExcels = new ArrayList<>();
		for (UserBean bean : beans)
		{
			UserEx user = new UserEx();
			user.setId(bean.getId());
			user.setName(bean.getName());
			user.setPosition(bean.getPosition());
			user.setEmail(bean.getE_mail());
			user.setMobilePhone(bean.getMobilePhone());
			user.setPhone(bean.getPhone());
			user.setIntroduction(bean.getIntroduction());
			user.setRegistTime(bean.getRegistTime());
			uExcels.add(user);
		}
		return uExcels;
	}

	// 更新用户状态
	@Transactional
	public String reviewUser(String id, int state)
	{
		if (state == 1)
		{
			UserBean userBean = userDAO.loadUserInfo(id);
			// 已经有审核通过的顾问
			if (userDAO.loadUserByPhone(userBean.getMobilePhone()) != null)
			{
				userDAO.updateInfo("state", "2", id);
				return "已有账号审核通过，直接驳回该申请";
			} else
			{
				userDAO.updateInfo("state", state + "", id);
				// 注册环信账号
				HxUserRegister.userRegister("u" + userBean.getMobilePhone(), "123456");
				String msg = "【顾问帮】亲爱的" + userBean.getName() + "先生/女士，您注册的顾问帮账号已经审核通过，请及时登录完善信息。";
				try
				{
					SmsUtils.sendSms(Config.SMS_API_KEY, msg, userBean.getMobilePhone());
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		} else
			userDAO.updateInfo("state", state + "", id);
		return "审核成功";
	}

}
