package com.hx.server.entity;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class EMsgBody implements Serializable
{
	private List<Body> bodies;
	private ExtendMsg ext;

	public List<Body> getBodies()
	{
		return bodies;
	}

	public void setBodies(List<Body> bodies)
	{
		this.bodies = bodies;
	}

	@Override
	public String toString()
	{
		return "EMsgBody [bodies=" + bodies + "]";
	}

	public ExtendMsg getExt()
	{
		return ext;
	}

	public void setExt(ExtendMsg ext)
	{
		this.ext = ext;
	}

}
