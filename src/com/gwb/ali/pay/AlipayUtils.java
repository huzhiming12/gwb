package com.gwb.ali.pay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class AlipayUtils
{
	public static String mapToString(Map<String, String> map)
	{
		String result = "";

		Set<Entry<String, String>> set = map.entrySet();

		for (Map.Entry<String, String> entry : set)
		{
			if (entry.getValue() != null && !entry.getValue().equals(""))
				result += entry.getKey() + "=\"" + entry.getValue() + "\"&";
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	// 读取文件
	public static String readFile(String path)
	{
		String result = "";
		File file = new File(path);
		try
		{
			InputStream inputStream = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) > 0)
			{
				result = new String(buffer, 0, len);
			}
			result = result.replace("\n", "");
			inputStream.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	// 发送http_Post请求
	public static String doPost(String content, String url)
	{
		CloseableHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = null;
		entity = new StringEntity(content, "utf-8");
		httpPost.setHeader("Content-Type", "text/json;charset=utf-8");
		httpPost.setEntity(entity);
		String res = "";
		try
		{
			// 获取返回结果
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			res = EntityUtils.toString(httpEntity, "utf-8");
			response.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			httpClient.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return res;
	}

}
