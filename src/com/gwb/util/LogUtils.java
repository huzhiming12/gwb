package com.gwb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils
{

	// 使用slf4j创建日志对象，好处是以后更换为其它日志工具时，只要修改配置文件，不用修改代码
	private static Logger logger = LoggerFactory.getLogger("MyLog");

	public static void error(String message)
	{
		logger.error(message);
	}

	public static void warn(String message)
	{
		logger.warn(message);
	}

	public static void info(String message)
	{
		logger.info(message);
	}

	public static void debug(String message)
	{
		logger.debug(message);
	}
}
