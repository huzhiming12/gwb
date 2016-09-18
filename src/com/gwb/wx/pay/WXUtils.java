package com.gwb.wx.pay;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import com.gwb.util.Config;

public class WXUtils
{
	// 生成随机数
	public static String createNonceStr()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}

	// 生成签名
	public static String createSign(Map<String, Object> map)
	{
		String tempStr = "";
		Set<Map.Entry<String, Object>> set = map.entrySet();
		for (Map.Entry<String, Object> entry : set)
		{
			if (entry.getValue() != null)
			{
				if (!entry.getValue().equals(0))
					tempStr += entry.getKey() + "=" + entry.getValue() + "&";
			}
		}
		tempStr = tempStr + "key=" + Config.WX_APP_KEY;
		//System.out.println(tempStr);
		String sign = getMd5(tempStr);
		return sign.toUpperCase();
	}

	// MD5
	public static String getMd5(String str)
	{
		if (str == null || str.length() == 0)
		{
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try
		{
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(str.getBytes("UTF-8"));
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e)
		{
			return null;
		}
	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		Map<String, Object> map = new TreeMap<>();
		try
		{
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors)
			{
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class"))
				{
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}
			}
		} catch (Exception e)
		{
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}
}
