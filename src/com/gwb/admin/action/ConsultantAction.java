package com.gwb.admin.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.ConsultantBean;
import com.gwb.bean.Pager;
import com.gwb.excel.ConsultantEx;
import com.gwb.excel.DownLoad;
import com.gwb.excel.ExcelTool;
import com.gwb.model.Card;
import com.gwb.model.Consultant;
import com.gwb.service.ConsultantService;
import com.gwb.util.PagerTool;
import com.gwb.util.ResultUtils;
import com.opensymphony.xwork2.ActionSupport;

@Component("adConsultantAction")
@Scope("protoType")
public class ConsultantAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private List<ConsultantBean> consultants;
	private Consultant consultant;
	private ConsultantBean consultantBean;
	private List<Card> cards;
	private ConsultantService consultantService;
	private Pager pager;
	private PagerTool pagerTool;
	private String selector;

	public String getSelector()
	{
		return selector;
	}

	public void setSelector(String selector)
	{
		this.selector = selector;
	}

	public PagerTool getPagerTool()
	{
		return pagerTool;
	}

	public void setPagerTool(PagerTool pagerTool)
	{
		this.pagerTool = pagerTool;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public ConsultantBean getConsultantBean()
	{
		return consultantBean;
	}

	public void setConsultantBean(ConsultantBean consultantBean)
	{
		this.consultantBean = consultantBean;
	}

	public List<ConsultantBean> getConsultants()
	{
		return consultants;
	}

	public void setConsultants(List<ConsultantBean> consultants)
	{
		this.consultants = consultants;
	}

	public Consultant getConsultant()
	{
		return consultant;
	}

	public void setConsultant(Consultant consultant)
	{
		this.consultant = consultant;
	}

	public List<Card> getCards()
	{
		return cards;
	}

	public void setCards(List<Card> cards)
	{
		this.cards = cards;
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

	// 顾问列表
	@SuppressWarnings("unchecked")
	public String consultantList()
	{
		Map<String, Object> map = consultantService.loadConsultantList(pager);
		consultants = (List<ConsultantBean>) map.get("consultant");
		pager = (Pager) map.get("pager");
		pagerTool = new PagerTool(pager, "admin/cons_consultantList");
		selector = "consultantList";
		return SUCCESS;
	}

	// 顾问信息
	public String consultantInfo()
	{
		consultantBean = consultantService.loadInfo(consultant.getId());
		consultantBean.setCards(consultantService.loadCards(consultantBean.getId()));
		// ResultUtils.toJson(ServletActionContext.getResponse(), consultant);
		return SUCCESS;
	}

	// 审核顾问
	public void reviewConsultant()
	{
		String result = "";
		try
		{
			result = consultantService.reviewConsultant(consultant.getId(), consultant.getState());
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "审核失败";
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 删除顾问
	public void delConsultant()
	{
		boolean result = true;
		try
		{
			consultantService.delConsultant(consultant.getId());
		} catch (Exception e)
		{
			result = false;
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 禁用顾问
	public void lockConsultant()
	{
		boolean result = true;
		try
		{
			consultantService.lockConsultant(consultant.getId());
		} catch (Exception e)
		{
			result = false;
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 启用顾问
	public void unlockConsultant()
	{
		boolean result = true;
		try
		{
			consultantService.unlockConsultant(consultant.getId());
		} catch (Exception e)
		{
			result = false;
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 导出顾问列表
	public void exportConsList()
	{
		ExcelTool<ConsultantEx> ex = new ExcelTool<>();
		String[] headers = { "id", "姓名", "职务", "手机号", "电话", "邮箱", "个人简介", "所在城市", "擅长领域", "审核状态", "注册时间" };
		List<ConsultantEx> consList = consultantService.loadConsList();
		OutputStream out;
		try
		{
			out = new FileOutputStream("consultant.xls");
			ex.exportExcel("顾问列表", headers, consList, out);
			out.close();
			DownLoad.download("consultant.xls", ServletActionContext.getResponse());

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
