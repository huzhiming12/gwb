package com.gwb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

public class FileUtils
{
	// 执行上传功能
	@SuppressWarnings("resource")
	public static boolean uploadFile(File file, String path, String filename)
	{
		try
		{
			InputStream in = new FileInputStream(file);
			File fileLocation = new File(path);
			// 此处也可以在应用根目录手动建立目标上传目录
			if (!fileLocation.exists())
			{
				boolean isCreated = fileLocation.mkdirs();
				if (!isCreated)
				{
					System.out.println("文件夹创建失败");
					// 目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。
					return false;
				}
			}
			System.out.println(filename);
			File uploadFile = new File(path, filename);
			OutputStream out = new FileOutputStream(uploadFile);
			byte[] buffer = new byte[1024 * 1024];
			int length;
			while ((length = in.read(buffer)) > 0)
			{
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException ex)
		{
			System.out.println("上传失败!");
			ex.printStackTrace();
			return false;
		} catch (IOException ex)
		{
			System.out.println("上传失败!");
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	// 删除文件
	public static boolean delFile(String url)
	{
		@SuppressWarnings("deprecation")
		String path = ServletActionContext.getRequest().getRealPath(url);
		File file = new File(path);
		if (file.exists() && !file.isDirectory())
		{
			return file.delete();
		}
		return true;
	}

	/*
	 * Java文件操作 获取文件扩展名
	 */
	public static String getExtensionName(String filename)
	{
		if ((filename != null) && (filename.length() > 0))
		{
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1)))
			{
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/*
	 * Java文件操作 获取不带扩展名的文件名
	 * 
	 */
	public static String getFileNameNoEx(String filename)
	{
		if ((filename != null) && (filename.length() > 0))
		{
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length())))
			{
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	// 获取项目的更路径
	public static String getRootPath()
	{
		String path = FileUtils.class.getClassLoader().getResource("").getPath();
		String rootPath = path.substring(0, path.indexOf("gwb/WEB-INF/"));
		return rootPath;
	}
}
