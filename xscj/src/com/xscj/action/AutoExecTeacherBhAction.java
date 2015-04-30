package com.xscj.action;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.CategoryService;

public class AutoExecTeacherBhAction extends ActionSupport {

	/**
	 * 		自动产生教师编号
	 */
	private static final long serialVersionUID = -5282763303327761078L;
	@Autowired
	@Qualifier("categoryServiceImpl")
	private CategoryService categoryService;
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		String bianHao = "T"+ (10000+categoryService.getCount("T")+1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bianHao", bianHao);
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	
}
