package com.xscj.test.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.StuService;

public class StuSearchGradeTestAction extends ActionSupport {
	/**
	 * @author leorain
	 * @date 2014-4-15 下午7:45:27
	 */
	private static final long serialVersionUID = 5757892802419038017L;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	private int n;
	
	private String gradeID;
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		n = stuService.isExisits(gradeID);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("flag", n);
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	public String getGradeID() {
		return gradeID;
	}
	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	
}
