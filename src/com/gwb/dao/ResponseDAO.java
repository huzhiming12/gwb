package com.gwb.dao;

import java.util.List;

import com.gwb.bean.ResponseBean;
import com.gwb.model.Response;

public interface ResponseDAO
{

	// 查询某条请求的回应条数
	public int getResponseNum(int id);

	// 添加回应
	public boolean addResponse(Response response);

	// 获取回应的详细信息
	public Response responseDetail(int id);

	// 更新回应内容
	public void updateResponse(String item, String value, int id);

	// 跟新条目
	public void updateItem(Response response);

	// 更新状态
	public void updateResponseState(int i, int state);

	// 通过请求的id查找回应
	public List<ResponseBean> loadResponseByReqId(int id);
}
