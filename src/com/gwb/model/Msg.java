package com.gwb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_msg")
public class Msg
{

	private int id;
	private String uuid;

	private String msg_id;
	// 时间
	private Long timestamp;
	// 发送方
	private String from_user;
	// 接收方
	private String to_user;
	// 单聊还是群聊
	private String chat_type;
	// 消息类型。txt: 文本消息；img: 图片；loc: 位置；audio: 语音
	private String type;
	// 消息内容
	private String msg;
	//// 上传图片消息地址，
	private String url;
	// 图片名称
	private String filename;
	// 上传缩略图地址
	private String thumb;
	// secret在上传图片后会返回，只有含有secret才能够下载此图片
	private String secret;
	// thumb_secret在上传缩略图后会返回
	private String thumb_secret;
	// 订单id
	private String orderId;

	public Msg()
	{
		super();
	}

	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public String getMsg_id()
	{
		return msg_id;
	}

	public void setMsg_id(String msg_id)
	{
		this.msg_id = msg_id;
	}

	public Long getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Long timestamp)
	{
		this.timestamp = timestamp;
	}

	public String getFrom_user()
	{
		return from_user;
	}

	public void setFrom_user(String from_user)
	{
		this.from_user = from_user;
	}

	public String getTo_user()
	{
		return to_user;
	}

	public void setTo_user(String to_user)
	{
		this.to_user = to_user;
	}

	public String getChat_type()
	{
		return chat_type;
	}

	public void setChat_type(String chat_type)
	{
		this.chat_type = chat_type;
	}

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

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

}
