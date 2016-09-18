package com.gwb.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.CityBean;
import com.gwb.bean.FieldBean;
import com.gwb.bean.Pager;
import com.gwb.interceptor.NotLoginException;
import com.gwb.model.Consultant;
import com.gwb.service.CityService;
import com.gwb.service.ConsultantService;
import com.gwb.service.FieldService;
import com.gwb.util.ResultUtils;
import com.opensymphony.xwork2.ActionSupport;

@Component("consultantAction")
@Scope("protoType")
public class ConsultantAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;

	private int id;

	private ConsultantService consultantService;
	private CityService cityService;
	private FieldService fieldService;
	private Consultant consultant;
	private String fieldString;
	private Pager pager;
	private String card;
	private String oldPassword;
	private List<CityBean> cityBeans;
	private List<FieldBean> fieldBeans;
	private int[] field;

	public int[] getField()
	{
		return field;
	}

	public void setField(int[] field)
	{
		this.field = field;
	}

	public CityService getCityService()
	{
		return cityService;
	}

	@Resource(name = "cityService")
	public void setCityService(CityService cityService)
	{
		this.cityService = cityService;
	}

	public FieldService getFieldService()
	{
		return fieldService;
	}

	@Resource(name = "fieldService")
	public void setFieldService(FieldService fieldService)
	{
		this.fieldService = fieldService;
	}

	public List<CityBean> getCityBeans()
	{
		return cityBeans;
	}

	public void setCityBeans(List<CityBean> cityBeans)
	{
		this.cityBeans = cityBeans;
	}

	public List<FieldBean> getFieldBeans()
	{
		return fieldBeans;
	}

	public void setFieldBeans(List<FieldBean> fieldBeans)
	{
		this.fieldBeans = fieldBeans;
	}

	public String getOldPassword()
	{
		return oldPassword;
	}

	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}

	public String getCard()
	{
		return card;
	}

	public void setCard(String card)
	{
		this.card = card;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public ConsultantService getConsultantService()
	{
		return consultantService;
	}

	@Resource(name = "consultantService")
	public void setConsultantService(ConsultantService consultantService)
	{
		this.consultantService = consultantService;
	}

	public Consultant getConsultant()
	{
		return consultant;
	}

	public void setConsultant(Consultant consultant)
	{
		this.consultant = consultant;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public String getFieldString()
	{
		return fieldString;
	}

	public void setFieldString(String fieldString)
	{
		this.fieldString = fieldString;
	}

	// *****************方法区***********************

	/**
	 * @author:huzhiming
	 * @function：检测电话号码是否使用
	 * @field:consultant.mobilePhone
	 * @return_type: true:已使用 false:未使用
	 */
	public void phoneCheck()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", consultantService.isPhoneUse(consultant.getMobilePhone()));

		} catch (Exception e)
		{
			e.printStackTrace();
			result.put("status", 400);
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：顾问注册 手机号码注册
	 */
	public String register()
	{
		cityBeans = cityService.loadAllCity();
		fieldBeans = fieldService.loadAllFields();
		return SUCCESS;
	}

	public String registerResult()
	{
		card = "true";
		try
		{
			consultantService.userRegister(consultant, field);
		} catch (Exception e)
		{
			card = "false";
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 更新顾问信息
	public void updateInfo()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			consultantService.updateInfo(consultant,fieldString);
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
	 * @function：更换头像
	 * @field: 1.file 2.consultant.mobilePhone
	 * @return_type:
	 */
	public void changeIcon()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			consultantService.changeIcon(consultant.getId(), consultant.getIcon());
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
	 * @function：更新顾问姓名
	 * @field:1 consultant.name 2 consultant.mobilePhone
	 * @return_type: true or false
	 */
	public void changeName()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			consultantService.changeName(consultant.getId(), consultant.getName());
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
	 * @function：更新顾问职务
	 * @field: consultant.position consultant.mobilePhone
	 * @return_type: true or false
	 */
	public void changePosition()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			consultantService.changePosition(consultant.getId(), consultant.getPosition());
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
	 * @function：更新顾问所在城市
	 * @field:
	 * @return_type:
	 */
	public void changeCity()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			consultantService.changeCity(consultant.getId(), consultant.getCity().getId());
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
	}

	/**
	 * @author:huzhiming
	 * @function：更新电话
	 * @field:
	 * @return_type:
	 */
	public void changePhone()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			consultantService.changePhone(consultant.getId(), consultant.getPhone());
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

	}

	/**
	 * @author:huzhiming
	 * @function：更新e_mail
	 * @field:
	 * @return_type:
	 */
	public void changeE_mail()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			consultantService.changeE_mail(consultant.getId(), consultant.getE_mail());
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
	 * @function：更新个人简介
	 * @field:
	 * @void:
	 */
	public void changeIntroduction()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			consultantService.changeIntroduction(consultant.getId(), consultant.getIntroduction());
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
	}

	/**
	 * @author:huzhiming
	 * @function：更新密码
	 * @field: consultant.password consultant.mobilePhone
	 * @return_type:
	 */
	public void changePassword()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = consultantService.changePassword(consultant.getId(), consultant.getPassword(), oldPassword);
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
	 * @function：更新顾问擅长领域
	 * @field: fieldString:领域编号以;隔开的字符串
	 * @void:
	 */
	public void changeFields()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			consultantService.addGoodAtField(consultant.getId(), fieldString);
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
	 * @field: file consultant.mobilePhone
	 * @void:
	 */
	public void addCards()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = consultantService.addCards(consultant.getId(), card);
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
	 * @function：顾问登录
	 * @field:consultant.mobilePhone consultant.password
	 * @void:0：用户不存在 1:密码错误 2：登录成功
	 */
	public void login()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = consultantService.userLogin(ServletActionContext.getRequest(), consultant.getMobilePhone(),
					consultant.getPassword());
		} catch (NullPointerException e)
		{
			result.put("status", 400);
			result.put("error", "参数有误");
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
	 * @function：加载顾问信息
	 * @field:
	 * @void:
	 */
	public void loadInfo()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", consultantService.loadInfo(consultant.getId()));
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

	/**
	 * @author:huzhiming
	 * @function：加载顾问名片
	 * @field:
	 * @return_type:
	 */
	public void loadCards()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", consultantService.loadCards(consultant.getId()));
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

	/**
	 * @author:huzhiming
	 * @function：通过领域id加载顾问
	 * @field:
	 * @return_type:
	 */
	public void loadConsultantByField()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", consultantService.loadConsultantByField(id, pager));
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

	/**
	 * @author:huzhiming
	 * @function：加载对顾问的评价
	 * @field: id:顾问id pager.pageNow:第几页
	 * @return_type: Map key:pager value:Pager key:comment value:List
	 *               <CommentBean>
	 */
	public void getConsComments()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", consultantService.loadConsComment(consultant.getId(), pager));
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
			consultantService.changePassword(consultant.getPassword(), consultant.getMobilePhone());
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
