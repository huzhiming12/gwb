package com.gwb.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ResultUtils
{

	public static void toJson(HttpServletResponse response, Object data)
	{
		Gson gson = new Gson();
		String result = gson.toJson(data);
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void print(HttpServletResponse response, Object data)
	{
		response.setContentType("text/plain; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.print(data.toString());
			out.flush();
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static Map<String, Object> resToMap(Object object)
	{
		Map<String, Object> res = new HashMap<>();
		res.put("status", 200);
		res.put("body", object);
		return res;
	}

}