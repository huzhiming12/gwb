package com.gwb.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.CityBean;
import com.gwb.bean.FieldBean;
import com.gwb.bean.Pager;
import com.gwb.model.City;
import com.gwb.model.Field;
import com.gwb.service.CityService;
import com.gwb.service.FieldService;
import com.gwb.util.Config;
import com.gwb.util.PagerTool;
import com.gwb.util.PropertiesUtils;
import com.gwb.util.ResultUtils;
import com.opensymphony.xwork2.ActionSupport;

@Component("adSettingAction")
@Scope("protoType")
public class SettingAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;
	private List<FieldBean> fields;
	private List<CityBean> cities;
	private Field field;
	private City city;
	private Pager pager;
	private PagerTool pagerTool;
	private String selector;
	private Map<String, String> priceMap = new HashMap<>();

	private FieldService fieldService;
	private CityService cityService;

	public Map<String, String> getPriceMap()
	{
		return priceMap;
	}

	public void setPriceMap(Map<String, String> priceMap)
	{
		this.priceMap = priceMap;
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

	public String getSelector()
	{
		return selector;
	}

	public void setSelector(String selector)
	{
		this.selector = selector;
	}

	public Field getField()
	{
		return field;
	}

	public void setField(Field field)
	{
		this.field = field;
	}

	public City getCity()
	{
		return city;
	}

	public void setCity(City city)
	{
		this.city = city;
	}

	public List<FieldBean> getFields()
	{
		return fields;
	}

	public void setFields(List<FieldBean> fields)
	{
		this.fields = fields;
	}

	public List<CityBean> getCities()
	{
		return cities;
	}

	public void setCities(List<CityBean> cities)
	{
		this.cities = cities;
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

	public CityService getCityService()
	{
		return cityService;
	}

	@Resource(name = "cityService")
	public void setCityService(CityService cityService)
	{
		this.cityService = cityService;
	}

	// 领域列表
	@SuppressWarnings("unchecked")
	public String fieldList()
	{
		Map<String, Object> map = fieldService.loadAllFields(pager);
		fields = (List<FieldBean>) map.get("field");
		pager = (Pager) map.get("pager");
		pagerTool = new PagerTool(pager, "admin/setting_fieldList");
		selector = "fieldList";
		return SUCCESS;
	}

	// 城市列表
	@SuppressWarnings("unchecked")
	public String cityList()
	{
		Map<String, Object> map = cityService.loadAllCity(pager);
		cities = (List<CityBean>) map.get("city");
		pager = (Pager) map.get("pager");
		pagerTool = new PagerTool(pager, "admin/setting_cityList");
		selector = "cityList";
		return SUCCESS;
	}

	// 价格设置
	public String priceSetting()
	{
		selector = "priceSetting";
		priceMap.put("online_price", String.format("%.2f", Config.ONLINE_CONSULT_PRICE));
		priceMap.put("online_percent", String.format("%.2f", Config.ONLINE_PERCENT));
		priceMap.put("offline_price", String.format("%.2f", Config.OFFLINE_CONSULT_PRICE));
		priceMap.put("offline_percent", String.format("%.2f", Config.OFFLINE_PERCENT));
		return SUCCESS;
	}
	
	// 修改咨询价格
	public void changePrice()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			//System.out.println(priceMap.get("online_price"));
			Properties properties = PropertiesUtils.getPropertiesFromFile(Config.RES_PAHT + Config.SETTING_FILE_PATH);
			properties.setProperty("online_price", priceMap.get("online_price"));
			properties.setProperty("online_percent", priceMap.get("online_percent"));
			properties.setProperty("offline_price", priceMap.get("offline_price"));
			properties.setProperty("offline_percent", priceMap.get("offline_percent"));
			PropertiesUtils.saveProperties(Config.RES_PAHT + Config.SETTING_FILE_PATH, properties);
			Config.initPrice();
			result.put("status", 200);

		} catch (Exception e)
		{
			e.printStackTrace();
			result.put("status", 400);
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	public void addFieldResult()
	{
		boolean result = true;
		try
		{
			fieldService.addField(field);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = false;
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	public void addCityResult()
	{
		boolean result = true;
		try
		{
			cityService.addCity(city);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = false;
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	public String addField()
	{
		return SUCCESS;
	}

	public String addCity()
	{
		return SUCCESS;
	}

	// 删除领域
	public void delField()
	{
		boolean result = true;
		try
		{
			fieldService.delField(field);
		} catch (Exception e)
		{
			result = false;
			e.printStackTrace();
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 删除城市
	public void delCity()
	{
		boolean result = true;
		try
		{
			cityService.delCity(city.getId());
		} catch (Exception e)
		{
			result = false;
			e.printStackTrace();
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	public void addTempdate()
	{
		cityService.addTempDate();
	}
}
