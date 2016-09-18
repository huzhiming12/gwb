package com.gwb.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.model.Feedback;
import com.gwb.service.CityService;
import com.gwb.service.FeedbackService;
import com.gwb.service.FieldService;
import com.gwb.util.Config;
import com.gwb.util.FileUtils;
import com.gwb.util.ResultUtils;
import com.gwb.util.SmsUtils;
import com.opensymphony.xwork2.ActionSupport;

@Component("toolAction")
@Scope("protoType")
public class ToolAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;

	private FieldService fieldService;
	private CityService cityService;
	private FeedbackService feedbackService;
	private String mobilePhone;
	private Feedback feedback;

	// 上传文件集合
	private File file;
	// 上传文件名集合
	private String fileFileName;
	// 上传文件内容类型集合
	private String fileContentType;

	public Feedback getFeedback()
	{
		return feedback;
	}

	public void setFeedback(Feedback feedback)
	{
		this.feedback = feedback;
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public String getFileFileName()
	{
		return fileFileName;
	}

	public void setFileFileName(String fileFileName)
	{
		this.fileFileName = fileFileName;
	}

	public String getFileContentType()
	{
		return fileContentType;
	}

	public void setFileContentType(String fileContentType)
	{
		this.fileContentType = fileContentType;
	}

	public String getMobilePhone()
	{
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}

	public FeedbackService getFeedbackService()
	{
		return feedbackService;
	}

	@Resource(name = "feedbackService")
	public void setFeedbackService(FeedbackService feedbackService)
	{
		this.feedbackService = feedbackService;
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

	// *******************************************************

	/**
	 * @author:huzhiming
	 * @function：加载所有领域信息
	 * @field:
	 * @return_type:
	 */
	public void loadFields()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", fieldService.loadAllFields());
		} catch (Exception e)
		{
			e.printStackTrace();
			result.put("status", 200);
			result.put("error", "参数错误");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：加载所有城市
	 * @field:
	 * @void:
	 */
	public void loadCitys()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", cityService.loadAllCity());
		} catch (Exception e)
		{
			e.printStackTrace();
			result.put("status", 200);
			result.put("error", "参数错误");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：获取验证码
	 * @field: mobilePhone:手机号码
	 * @return_type: code
	 */
	public void getVerificationCode()
	{
		String code = SmsUtils.createVerificationCode();
		String text = "【顾问帮】您的验证码是" + code + "。如非本人操作，请忽略本短信";
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", code);
			System.out.println(code);
			System.out.println(SmsUtils.sendSms(Config.SMS_API_KEY, text, mobilePhone));
		} catch (Exception e)
		{
			result.put("status", 200);
			result.put("error", "参数错误");
			e.printStackTrace();
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 文件上传
	public void uploadFile()
	{
		Map<String, Object> result;
		try
		{
			result = uploadFile(file, fileFileName);
		} catch (Exception e)
		{
			result = new HashMap<>();
			result.put("status", 400);
			result.put("error", "文件上传失败，请检查文件");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	private Map<String, Object> uploadFile(File file, String filename)
	{
		String path = Config.RES_PAHT + Config.USER_ICON_PATH;

		filename = System.currentTimeMillis() + "_" + new Random().nextInt(10000) + "."
				+ FileUtils.getExtensionName(filename);
		// 上传文件
		boolean res1 = FileUtils.uploadFile(file, path, filename);
		Map<String, Object> map = new HashMap<>();

		if (res1)
		{
			map.put("status", 200);
			map.put("res", Config.USER_ICON_PATH + filename);
		} else
		{
			map.put("status", 400);
			map.put("error", "文件有误,上传失败");
		}
		return map;
	}

	// 添加用户反馈
	public void addFeedback()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = feedbackService.addFeedback(feedback);
		} catch (NullPointerException e)
		{
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (RuntimeException e)
		{
			result.put("status", 400);
			result.put("error", "反馈失败，稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}
	
	public void loadPrice()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("online_price", Config.ONLINE_CONSULT_PRICE);
			result.put("offline_price", Config.OFFLINE_CONSULT_PRICE);
		} catch (NullPointerException e)
		{
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (RuntimeException e)
		{
			result.put("status", 400);
			result.put("error", "反馈失败，稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}
}
