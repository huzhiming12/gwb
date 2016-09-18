package com.gwb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//资源加载
public class PropertiesUtils
{

	public static Properties getProperties(String path)
	{
		Properties p = new Properties();
		try
		{
			InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
			p.load(inputStream);

		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		return p;
	}

	public static Properties getPropertiesFromFile(String path)
	{
		Properties p = new Properties();
		try
		{
			InputStream inputStream = new FileInputStream(path);
			p.load(inputStream);

		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		return p;
	}

	// 保存properties
	public static void saveProperties(String path, Properties properties)
	{
		FileOutputStream fos;
		try
		{
			// System.out.println(FileUtils.getRootPath());
			fos = new FileOutputStream(new File(path));
			// 将Properties集合保存到流中
			properties.store(fos, "");
			fos.close();// 关闭流
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
