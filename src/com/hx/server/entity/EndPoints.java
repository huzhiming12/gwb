package com.hx.server.entity;

import java.net.URL;

import com.hx.server.comm.Constants;
import com.hx.server.utils.HTTPClientUtils;


public interface EndPoints {

	public static final URL ROOT_URL = HTTPClientUtils.getURL("");

	public static final URL MANAGEMENT_URL = HTTPClientUtils.getURL("/management");

	public static final URL TOKEN_ORG_URL = HTTPClientUtils.getURL("/management/token");

	public static final URL APPLICATION_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/"));

	public static final URL TOKEN_APP_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/token");


	public static final URL CHATMESSAGES_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/")
			+ "/chatmessages");

}
