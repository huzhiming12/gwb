package com.hx.server.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Body implements Serializable
{
	private String type;
	private String msg;
	//// 上传图片消息地址，在上传图片成功后会返回UUID
	private String url;
	// 图片名称
	private String filename;
	// 上传缩略图地址
	private String thumb;
	// secret在上传图片后会返回，只有含有secret才能够下载此图片
	private String secret;
	// thumb_secret在上传缩略图后会返回
	private String thumb_secret;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getThumb()
	{
		return thumb;
	}

	public void setThumb(String thumb)
	{
		this.thumb = thumb;
	}

	public String getSecret()
	{
		return secret;
	}

	public void setSecret(String secret)
	{
		this.secret = secret;
	}

	public String getThumb_secret()
	{
		return thumb_secret;
	}

	public void setThumb_secret(String thumb_secret)
	{
		this.thumb_secret = thumb_secret;
	}

	@Override
	public String toString()
	{
		return "Body [type=" + type + ", msg=" + msg + ", url=" + url + ", filename=" + filename + ", thumb=" + thumb
				+ ", secret=" + secret + ", thumb_secret=" + thumb_secret + "]";
	}

}
