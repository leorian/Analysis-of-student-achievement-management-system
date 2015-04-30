package com.xscj.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class ExecRandomPassAction extends ActionSupport {

	/**
	 * 		产生学生登录随机密码
	 */
	private static final long serialVersionUID = -5282763303327761078L;
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		Random random = new Random(System.currentTimeMillis());
		int pass = Math.abs(random.nextInt());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("random", pass);
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	
}
