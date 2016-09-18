package com.gwb.wx.pay;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XMLUtils
{

	public static String ObjectToXMl(Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		String xmlStr = "<xml>";
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
					xmlStr += "<" + key + ">" + value + "</" + key + ">";
				}
			}
			xmlStr += "</xml>";
		} catch (Exception e)
		{
			System.out.println("transBean2Map Error " + e);
		}
		return xmlStr;

	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(String xml)
	{
		Map<String, String> map = new HashMap<String, String>();
		Document document = null;
		try
		{
			document = DocumentHelper.parseText(xml);
		} catch (DocumentException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();
		for (Element e : elementList)
			map.put(e.getName(), e.getText());
		return map;
	}

	public static String mapToString(Map<String, Object> map)
	{

		String tempStr = "<xml>";
		Set<Map.Entry<String, Object>> set = map.entrySet();
		for (Map.Entry<String, Object> entry : set)
		{
			if (entry.getValue() != null)
			{
				if (!entry.getValue().equals(0))
					tempStr += "<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">\n";
			}
		}
		tempStr += "</xml>";
		return tempStr;
	}
}