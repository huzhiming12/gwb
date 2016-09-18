package com.gwb.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;
import com.gwb.interceptor.NotLoginException;
import com.gwb.model.WithDraw;
import com.gwb.service.WalletService;
import com.gwb.util.ResultUtils;

@Component("walletAction")
@Scope("protoType")
public class WalletAction
{
	private String id;
	private float money;
	private Pager pager;
	private String password;
	private WithDraw withDraw;

	public WithDraw getWithDraw()
	{
		return withDraw;
	}

	public void setWithDraw(WithDraw withDraw)
	{
		this.withDraw = withDraw;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public float getMoney()
	{
		return money;
	}

	public void setMoney(float money)
	{
		this.money = money;
	}

	private WalletService walletService;

	public WalletService getWalletService()
	{
		return walletService;
	}

	public void setWalletService(WalletService walletService)
	{
		this.walletService = walletService;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @author:huzhiming
	 * @function：顾问钱包详情
	 * @field: id:顾问id
	 * @return_type: WalletBean
	 */
	public void walletDetail()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", walletService.walletDetail(id));
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
	 * @function：加载顾问钱包的财务明细
	 * @field: id：顾问id pager.pageNow:查询的页数
	 * @return_type:
	 */
	public void loadFinancialDetal()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", walletService.loadFinancialDetail(id, pager));
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
	 * @function：顾问提现
	 * @field: withdraw.consultant.id:顾问id withdraw.money：提现的金额
	 *         withdraw.name:提现人姓名 withdraw.account：提现人账号
	 * @void:
	 */
	public void withdraw()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = walletService.withdraw(withDraw);
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
	 * @function：更换支付密码
	 * @field: id:顾问id password：更新的支付密码
	 * @return_type:
	 */
	public void changePayPassword()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			walletService.changePayPassword(id, password);
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
	 * @function：校验支付密码
	 * @field: id：顾问id password：支付密码
	 * @return_type: true 正确 false 错误
	 */
	public void checkPayPassword()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = walletService.checkPayPassword(id, password);
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

}
