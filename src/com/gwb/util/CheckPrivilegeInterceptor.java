package com.gwb.util;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class CheckPrivilegeInterceptor extends AbstractInterceptor
{
	public String intercept(ActionInvocation invocation) throws Exception
	{
		// 获取信息
		String username = (String) ActionContext.getContext().getSession().get("username"); // 当前登录用户
		String userType = (String) ActionContext.getContext().getSession().get("userType");
		String namespace = invocation.getProxy().getNamespace(); // 得到命名空间
		String actionName = invocation.getProxy().getActionName();
		String privUrl = namespace + "/" + actionName; // 对应的权限URL
		System.out.println(privUrl);

		if (actionName.startsWith("Consultant_register")/* 顾问注册 */ || actionName.endsWith("phoneCheck")/* 检测电话号码是否使用 */
				|| actionName.endsWith("login")/* 用户登录 */ ||actionName.endsWith("loginCheck")/* 用户登录检测 */ || actionName.startsWith("Alipay_")/* 支付宝返回通知 */
				|| actionName.startsWith("Tool_")/* 工具类 */ || actionName.endsWith("wxPayOrder")/* 微信支付返回结果 */)
		{
			// 放行通过
			return invocation.invoke();
		} else
		{
			HttpServletResponse response =(HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
			// 未登录
			if (username == null || username.equals(""))
			{
				
				if (namespace.equals("/admin"))
				{
					response.sendRedirect("index_login");
					//return invocation.invoke();
				} else
				{
					response.sendRedirect("Tool_notLogin");
					//return invocation.invoke();
				}
				return null;
			} else
			{
				if (!userType.equals("Admin") && namespace.equals("/admin"))
				{
					response.sendRedirect("index_login");
					return null;
				}
				// 放行通过
				return invocation.invoke();
			}
		}

	}
}