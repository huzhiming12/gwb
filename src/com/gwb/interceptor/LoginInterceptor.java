package com.gwb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.gwb.util.LogUtils;
import com.opensymphony.xwork2.ActionContext;

@Aspect
@Component
public class LoginInterceptor
{
	@Pointcut(" execution(* com.gwb.service.*.*(..)) && " 
			+ "!execution(* com.gwb.service.*.set*(..)) && "
			+ "!execution(* com.gwb.service.*.get*(..)) ")// + "!execution(* com.gwb.service.*.userRegister(..))&&"
			//+ "!execution(* com.gwb.service.*.isPhoneUse(..))")
			//+ "!execution(* com.gwb.service.AdminService.*(..))&&"
			//+ "!execution(* com.gwb.service.FieldService.*(..))&&" + "!execution(* com.gwb.service.*.userLogin(..))")
	private void intercepterMethod()
	{
	}

	// com.gwb.admin.action 包下面的所有类的所有方法
	@SuppressWarnings("rawtypes")
	@Before("intercepterMethod()")
	public void checkLoginState(JoinPoint joinPoint)
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession();

		// 方法名
		Signature signature = joinPoint.getSignature();
		// 操作类
		Class cls = joinPoint.getTarget().getClass();
		String ip = request.getRemoteAddr();
		// Object[]argrs = joinPoint.getArgs();
		// for(Object object:argrs)
		// {
		//
		// }
		String msg = "Ip:" + ip + "  User:" + session.getAttribute("username") + "  UserType:"
				+ session.getAttribute("userType");
		msg += "  Class:" + cls.getName() + "  Method:" + signature.getName();
		LogUtils.info(msg);

	}
}
