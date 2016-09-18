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
import com.gwb.bean.ConsultantBean;
import com.gwb.bean.GoodAtFieldBean;
import com.gwb.bean.Pager;
import com.gwb.bean.WalletBean;
import com.gwb.dao.CardDAO;
import com.gwb.dao.CommentDAO;
import com.gwb.dao.ConsultantDAO;
import com.gwb.dao.WalletDAO;
import com.gwb.excel.ConsultantEx;
import com.gwb.model.Card;
import com.gwb.model.Consultant;
import com.gwb.model.Field;
import com.gwb.model.GoodAtField;
import com.gwb.model.Wallet;
import com.gwb.util.Config;
import com.gwb.util.FileUtils;
import com.gwb.util.Md5Utils;
import com.gwb.util.SmsUtils;
import com.hx.server.imp.HxUserRegister;

@Component("consultantService")
public class ConsultantService
{
	private String sql = "select new com.gwb.bean.ConsultantBean(c.id, c.mobilePhone, "
			+ "c.phone, c.icon,c.name,c.position,c.e_mail,c.introduction, c.name,"
			+ "c.registerTime,c.password,c.state) from Consultant c left join c.city ci ";

	private CardDAO cardDAO;
	private ConsultantDAO consultantDAO;
	private WalletDAO walletDAO;
	private CommentDAO commentDAO;

	public CommentDAO getCommentDAO()
	{
		return commentDAO;
	}

	@Resource(name = "commentDAO")
	public void setCommentDAO(CommentDAO commentDAO)
	{
		this.commentDAO = commentDAO;
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

	public ConsultantDAO getConsultantDAO()
	{
		return consultantDAO;
	}

	@Resource(name = "consultantDAO")
	public void setConsultantDAO(ConsultantDAO consultantDAO)
	{
		this.consultantDAO = consultantDAO;
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

	// **********************************************

	// 检测电话号码是否已经注册 true 已经注册 false 未注册
	@Transactional
	public boolean isPhoneUse(String mobilePhone)
	{
		if (consultantDAO.loadConsultantByPhone(mobilePhone) != null)
			return true;
		return false;
	}

	// 顾问注册
	@Transactional
	public void userRegister(Consultant consultant, int[] field)
	{
		String id = UUID.randomUUID().toString();
		// 设置注册时间
		consultant.setRegisterTime(new Date());
		consultant.setId(id);
		// 密码加密
		consultant.setPassword(Md5Utils.encodeByMD5(consultant.getPassword()));
		// 添加顾问
		consultantDAO.addConsult(consultant);
		String fieldString = "";
		for (int i : field)
		{
			fieldString += i + ";";
		}
		fieldString.substring(0, fieldString.length() - 1);
		// 添加顾问擅长领域
		addGoodAtField(id, fieldString);
	}

	// 更新顾问信息信息
	@Transactional
	public void updateInfo(Consultant consultant, String fieldString)
	{
		String id = consultant.getId();
		consultantDAO.updateInfo("icon", consultant.getIcon(), id);
		consultantDAO.updateInfo("name", consultant.getName(), id);
		consultantDAO.updateInfo("position", consultant.getPosition(), id);
		consultantDAO.updateInfo("city.id", consultant.getCity().getId() + "", id);
		consultantDAO.updateInfo("e_mail", consultant.getE_mail(), id);
		consultantDAO.updateInfo("phone", consultant.getPhone(), id);
		consultantDAO.updateInfo("introduction", consultant.getIntroduction(), id);
		addGoodAtField(id, fieldString);
	}

	// 更新头像
	@Transactional
	public void changeIcon(String id, String url)
	{
		// 获取原先的icon
		ConsultantBean tempConsultant = consultantDAO.loadConsBeanById(id);
		// 删除原来的icon
		if (tempConsultant != null && tempConsultant.getIcon() != null)
			FileUtils.delFile(tempConsultant.getIcon());
		consultantDAO.updateInfo("icon", url, id);
	}

	// 更新顾问姓名
	@Transactional
	public void changeName(String id, String name)
	{
		consultantDAO.updateInfo("name", name, id);
	}

	// 更新顾问职位
	@Transactional
	public void changePosition(String id, String position)
	{
		consultantDAO.updateInfo("position", position, id);
	}

	// 更新顾问所在城市
	@Transactional
	public void changeCity(String id, int cityId)
	{
		consultantDAO.updateInfo("city.id", cityId + "", id);
	}

	// 更新联系电话
	@Transactional
	public void changePhone(String id, String phone)
	{
		consultantDAO.updateInfo("phone", phone, id);
	}

	// 更换密码
	@Transactional
	public void changePassword(String password, String mobilePhone)
	{
		ConsultantBean consultantBean = consultantDAO.loadConsultantByPhone(mobilePhone);
		consultantDAO.updateInfo("password", Md5Utils.encodeByMD5(password), consultantBean.getId());
	}

	// 更新邮箱
	@Transactional
	public void changeE_mail(String id, String e_mail)
	{
		consultantDAO.updateInfo("e_mail", e_mail, id);
	}

	// 更新个人简介
	@Transactional
	public void changeIntroduction(String id, String introduction)
	{
		consultantDAO.updateInfo("introduction", introduction, id);
	}

	// 添加名片
	@Transactional
	public Map<String, Object> addCards(String consId, String cards)
	{
		Map<String, Object> res = new HashMap<>();
		boolean flag = true;
		// 添加名片信息
		String[] strings = cards.split(";");
		for (String str : strings)
		{
			Card card = new Card();
			card.setUserId(consId);
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

	// 添加顾问擅长领域
	@Transactional
	public void addGoodAtField(String consId, String fieldString)
	{
		consultantDAO.delConsGoodAtFields(consId);
		Consultant consultant = consultantDAO.loadConsultantById(consId);
		// 将字符串以;分隔开
		String[] temp = fieldString.split(";");
		List<GoodAtField> list = new ArrayList<>();
		for (int i = 0; i < temp.length; i++)
		{
			GoodAtField field = new GoodAtField();
			field.setField(new Field(new Integer(temp[i])));
			field.setConsultant(consultant);
			list.add(field);
		}
		consultant.setFields(list);
		// 更新信息
		consultantDAO.updateInfo(consultant);
	}

	// 更新密码
	@Transactional
	public Map<String, Object> changePassword(String id, String password, String oldPassword)
	{
		Map<String, Object> res = new HashMap<>();
		ConsultantBean consultantBean = consultantDAO.loadConsBeanById(id);
		if (consultantBean != null)
		{

			// 对密码进行MD5加密
			password = Md5Utils.encodeByMD5(password);

			if (consultantBean.getPassword().equals(Md5Utils.encodeByMD5(oldPassword)))
			{
				consultantDAO.updateInfo("password", password, id);
				res.put("status", 200);
			} else
			{
				res.put("error", "原始密码输入错误");
				res.put("status", 400);
			}
		} else
		{
			res.put("error", "账号不存在");
			res.put("status", 400);
		}
		return res;
	}

	// 顾问登录 0：用户不存在 1:密码错误 2：登录成功
	@Transactional
	public Map<String, Object> userLogin(HttpServletRequest request, String mobilePhone, String password)
	{
		Map<String, Object> res = new HashMap<>();
		ConsultantBean tempUser = consultantDAO.loadConsultantByPhone(mobilePhone);
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
				WalletBean walletBean = walletDAO.walletDetail(tempUser.getId());
				if (walletBean.getPassword() == null || walletBean.getPassword().equals(""))
				{
					res.put("isSetPayPassword", false);
				} else
				{
					res.put("isSetPayPassword", true);
				}
				HttpSession session = request.getSession();
				session.setAttribute("username", mobilePhone);
				session.setAttribute("userType", "Consultant");

			} else
				res.put("error", "密码错误");
		} else
			res.put("error", "账号不存在");
		return res;
	}

	// 加载顾问信息
	@Transactional
	public ConsultantBean loadInfo(String id)
	{
		ConsultantBean consultant = consultantDAO.loadConsBeanById(id);
		consultant.setFields(consultantDAO.loadFields(id));
		return consultant;
	}

	// 加载用户名片
	@Transactional
	public List<CardBean> loadCards(String userId)
	{
		List<CardBean> cards = cardDAO.loadCards(userId);
		return cards;
	}

	// 通过领域加载顾问 user
	// 只显示审核通过的顾问
	@Transactional
	public Map<String, Object> loadConsultantByField(int id, Pager pager)
	{
		pager.setPageSize(Config.CONSULTANT_PAGER_SIZE);
		String hql = sql + ",GoodAtField g where c.state=1 and g.field.id=" + id + " and g in elements(c.fields)";
		String hql1 = "select count(*) from Consultant c,GoodAtField g where c.state=1 and g.field.id=" + id
				+ " and g in elements(c.fields)";
		return consultantDAO.searchConsultant(hql, hql1, pager);
	}

	// 加载顾问 admin
	@Transactional
	public Map<String, Object> loadConsultantList(Pager pager)
	{
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		// 查询条件
		Map<String, String> conditions = pager.getCondition();
		String str = " where c.state!=3";
		if (!conditions.isEmpty())
		{
			String value = (String) conditions.get("condition");
			str += " and c.name like '%" + value + "%' or c.mobilePhone like '%" + value + "%' or c.position like '%"
					+ value + "%'";
		}
		String hql = sql + str + " order by registerTime desc";
		String hql1 = "select count(*) from Consultant c" + str;
		return consultantDAO.searchConsultant(hql, hql1, pager);
	}

	// 加载顾问评价
	@Transactional
	public Map<String, Object> loadConsComment(String id, Pager pager)
	{
		return commentDAO.loadCommentByCons(id, pager);
	}

	// 审核顾问
	@Transactional
	public String reviewConsultant(String consId, int state)
	{
		if (state == 1)
		{
			ConsultantBean consultantBean = consultantDAO.loadConsBeanById(consId);
			// 已经有审核通过的顾问
			if (isPhoneUse(consultantBean.getMobilePhone()))
			{
				consultantDAO.updateInfo("state", "2", consId);
				return "已有账号审核通过，直接驳回该申请";
			} else
			{
				consultantDAO.updateInfo("state", state + "", consId);
				// 添加钱包
				Wallet wallet = new Wallet();
				wallet.setWithDrawNum(Config.WITHDRAW_NUM);
				wallet.setConsultant(new Consultant(consultantBean.getId()));
				walletDAO.addWallet(wallet);
				// 注册环信账号
				HxUserRegister.userRegister("c" + consultantBean.getMobilePhone(), "123456");
				String msg = "【顾问帮】亲爱的" + consultantBean.getName() + "先生/女士，您注册的顾问帮账号已经审核通过，请及时登录完善信息。";
				try
				{
					System.out.println(SmsUtils.sendSms(Config.SMS_API_KEY, msg, consultantBean.getMobilePhone()));
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		} else
			consultantDAO.updateInfo("state", state + "", consId);
		return "审核成功";
	}

	// 删除顾问
	@Transactional
	public void delConsultant(String consId)
	{
		consultantDAO.delConsultant(consId);
	}

	// 禁用
	@Transactional
	public void lockConsultant(String consId)
	{
		consultantDAO.updateInfo("state", "4", consId);
	}

	// 启用
	@Transactional
	public void unlockConsultant(String consId)
	{
		consultantDAO.updateInfo("state", "1", consId);
	}

	// 加载导出顾问信息
	@Transactional
	public List<ConsultantEx> loadConsList()
	{
		List<ConsultantBean> beans = consultantDAO.getConsList();
		List<ConsultantEx> exs = new ArrayList<>();
		for (ConsultantBean bean : beans)
		{
			ConsultantEx ex = new ConsultantEx();
			ex.setId(bean.getId());
			ex.setName(bean.getName());
			ex.setPosition(bean.getPosition());
			ex.setMobilePhone(bean.getMobilePhone());
			ex.setPhone(bean.getPhone());
			ex.setE_mail(bean.getE_mail());
			ex.setIntroduction(bean.getIntroduction());
			ex.setCity(bean.getCity());
			ex.setRegisterTime(bean.getRegisterTime());
			switch (bean.getState())
			{
			case 0:
				ex.setState("未处理");
				break;
			case 1:
				ex.setState("通过");
				break;
			case 2:
				ex.setState("驳回");
				break;
			}
			List<GoodAtFieldBean> fieldBeans = consultantDAO.loadFields(bean.getId());
			String temp = "";
			for (GoodAtFieldBean fieldBean : fieldBeans)
			{
				temp += fieldBean.getField() + "|";
			}
			temp = temp.substring(0, (temp.length() - 1));
			ex.setField(temp);
			exs.add(ex);
		}
		return exs;
	}
}
