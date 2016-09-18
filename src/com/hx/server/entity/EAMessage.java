package com.hx.server.entity;

import com.google.gson.Gson;

public class EAMessage
{
	private String msg_id;
	private String uuid;
	private String type;
	private Long created;
	private Long modified;
	private Long timestamp;
	private String from;

	private String to;
	private String chat_type;
	private EMsgBody payload;

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

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public String getChat_type()
	{
		return chat_type;
	}

	public void setChat_type(String chat_type)
	{
		this.chat_type = chat_type;
	}

	public EMsgBody getPayload()
	{
		return payload;
	}

	public void setPayload(EMsgBody payload)
	{
		this.payload = payload;
	}

	public Long getCreated()
	{
		return created;
	}

	public void setCreated(Long created)
	{
		this.created = created;
	}

	public Long getModified()
	{
		return modified;
	}

	public void setModified(Long modified)
	{
		this.modified = modified;
	}

	public Long getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Long timestamp)
	{
		this.timestamp = timestamp;
	}

	@Override
	public String toString()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
