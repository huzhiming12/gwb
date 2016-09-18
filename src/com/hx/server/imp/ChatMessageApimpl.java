package com.hx.server.imp;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gwb.dao.MsgDAO;
import com.gwb.model.Msg;
import com.gwb.util.Config;
import com.gwb.util.DateUtil;
import com.gwb.util.FileUtils;
import com.gwb.util.PropertiesUtils;
import com.hx.server.comm.Constants;
import com.hx.server.comm.HTTPMethod;
import com.hx.server.comm.Roles;
import com.hx.server.entity.ClientSecretCredential;
import com.hx.server.entity.Credential;
import com.hx.server.entity.EAMessage;
import com.hx.server.utils.HTTPClientUtils;

/**
 * 
 * @author bai 2016-5-20
 * 
 */
@Component("msgImpl")
public class ChatMessageApimpl
{
	private MsgDAO msgDAO;

	public MsgDAO getMsgDAO()
	{
		return msgDAO;
	}

	@Resource(name = "msgDAO")
	public void setMsgDAO(MsgDAO msgDAO)
	{
		this.msgDAO = msgDAO;
	}

	// private JsonNodeFactory factory = new JsonNodeFactory(false);
	// private final String APPKEY = Constants.APPKEY;

	private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
			Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

	// 拼接url
	public String splitURL()
	{
		Properties properties = PropertiesUtils.getPropertiesFromFile(Config.RES_PAHT + Config.SETTING_FILE_PATH);
		String timestamp = properties.getProperty("last_load_timestamp");
		if (timestamp.equals(""))
		{
			Long date = DateUtil.stringToDate(properties.getProperty("start_date")).getTime();
			timestamp = date.toString();
		}
		String url = "ql=select+*+where+timestamp>" + timestamp + "&limit=40";
		try
		{
			URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	@Transactional
	public void loadMsg()
	{
		String url = splitURL();
		String rest = url;
		JsonNode cursor;
		do
		{
			URL msgUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatmessages?" + rest);
			ObjectNode messages = HTTPClientUtils.sendHTTPRequest(msgUrl, credential, null, HTTPMethod.METHOD_GET);
			// System.out.println(messages);
			JsonNode entities = messages.get("entities");
			Gson gson = new Gson();
			Type type = new TypeToken<List<EAMessage>>()
			{
			}.getType();
			String jsonStr = entities.toString();

			List<EAMessage> eaMessages = gson.fromJson(jsonStr, type);
			saveMsg(eaMessages);
			cursor = messages.get("cursor");
			if (cursor != null)
				rest = url + "&cursor=" + cursor.toString();
			else // 没有数据了
			{
				if (eaMessages != null && eaMessages.size() > 0)
				{
					// 更新时间戳
					Long last_time = eaMessages.get(eaMessages.size() - 1).getTimestamp();
					Properties properties = PropertiesUtils
							.getPropertiesFromFile(Config.RES_PAHT + Config.SETTING_FILE_PATH);
					properties.setProperty("last_load_timestamp", last_time.toString());
					PropertiesUtils.saveProperties(Config.RES_PAHT + Config.SETTING_FILE_PATH, properties);
					// FileOutputStream fos;
					// try
					// {
					// // System.out.println(FileUtils.getRootPath());
					// fos = new FileOutputStream(new File(Config.RES_PAHT +
					// "Config.SETTING_FILE_PATH"));
					// // 将Properties集合保存到流中
					// properties.store(fos, "");
					// fos.close();// 关闭流
					// } catch (Exception e)
					// {
					// e.printStackTrace();
					// }
				}

			}
			// 暂停一秒防止过快访问环信数据库
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						Thread.sleep(3000);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}).start();

		} while (cursor != null);
	}

	// 保存信息
	private void saveMsg(List<EAMessage> messages)
	{
		for (EAMessage message : messages)
		{
			Msg msg = new Msg();
			msg.setUuid(message.getUuid());
			msg.setMsg_id(message.getMsg_id());
			msg.setTimestamp(message.getTimestamp());
			msg.setChat_type(message.getChat_type());
			msg.setFrom_user(message.getFrom());
			msg.setTo_user(message.getTo());
			if (message.getPayload().getBodies() != null && message.getPayload().getBodies().size() > 0)
			{
				msg.setType(message.getPayload().getBodies().get(0).getType());
				if (message.getPayload().getExt() != null)
					msg.setOrderId(message.getPayload().getExt().getOrderId());
				if (message.getPayload().getBodies().get(0).getType().equals("txt"))
				{
					msg.setMsg(message.getPayload().getBodies().get(0).getMsg());
				} else // 如果是图片信息 则要下载图片资源
				{
					msg.setUrl(message.getPayload().getBodies().get(0).getUrl());
					msg.setFilename(message.getPayload().getBodies().get(0).getFilename());
					msg.setSecret(message.getPayload().getBodies().get(0).getSecret());
					msg.setThumb(message.getPayload().getBodies().get(0).getThumb());
					msg.setThumb_secret(message.getPayload().getBodies().get(0).getThumb_secret());

					// 下载原图
					if (msg.getUrl() != null)
					{
						String filePath = Config.RES_PAHT + "/msgImage/original/";
						File file = new File(filePath);
						if (!file.exists())
							file.mkdirs();
						// 重新命名文件
						String filename = System.currentTimeMillis() + "."
								+ FileUtils.getExtensionName(msg.getFilename());
						loadImgs(msg.getUrl(), filePath + filename, msg.getSecret(), 0);
						msg.setUrl("/msgImage/original/" + filename);
					}
					if (msg.getThumb() != null)// 下载缩略图
					{
						String filePath = Config.RES_PAHT + "/msgImage/thumb/";
						File file = new File(filePath);
						if (!file.exists())
							file.mkdirs();
						String filename = System.currentTimeMillis() + "."
								+ FileUtils.getExtensionName(msg.getFilename());
						loadImgs(msg.getThumb(), filePath + filename, msg.getThumb_secret(), 1);
						msg.setThumb("/msgImage/thumb/" + filename);
					}
				}
			}
			msgDAO.addMsg(msg);
		}
	}

	// 下载图片
	public void loadImgs(String path, String filename, String secret, int flag)
	{
		List<NameValuePair> headers = new ArrayList<>();
		headers.add(new BasicNameValuePair("accept", "application/octet-stream"));
		if (secret != null)
			headers.add(new BasicNameValuePair("share-secret", secret));
		// 下载缩略图
		if (flag == 1)
			headers.add(new BasicNameValuePair("thumbnail", "true"));
		URL msgUrl = null;
		try
		{
			msgUrl = new URL(path);
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		File file = new File(filename);
		try
		{
			file.createNewFile();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		// System.out.println(filename);
		HTTPClientUtils.downLoadFile(msgUrl, credential, headers, file);
	}

}
