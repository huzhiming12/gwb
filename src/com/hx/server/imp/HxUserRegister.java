package com.hx.server.imp;

import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hx.server.comm.Constants;
import com.hx.server.comm.HTTPMethod;
import com.hx.server.comm.Roles;
import com.hx.server.entity.ClientSecretCredential;
import com.hx.server.entity.Credential;
import com.hx.server.utils.HTTPClientUtils;

public class HxUserRegister
{
	private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
			Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

	/**
	 * @author:huzhiming
	 * @function: // 用户注册
	 * @field:
	 * @String:200 注册成功 400：注册失败
	 */
	public static String userRegister(String username, String password)
	{
		String dataBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
		System.out.println(dataBody);
		URL registerUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users");
		ObjectNode objectNode = HTTPClientUtils.sendHTTPRequest(registerUrl, credential, dataBody,
				HTTPMethod.METHOD_POST);
		JsonNode statusCode = objectNode.get("statusCode");
		System.out.println(objectNode);
		return statusCode.toString();
	}
}
