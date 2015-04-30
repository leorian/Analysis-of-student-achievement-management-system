package com.xscj.test.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.ScoreService;
import com.xscj.util.Util;

 
public class ScoreQueryStuCidTestAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7103265542020117890L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	private String stuXueHao;
	private String courseID;
	private int n;
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		 
		if (stuXueHao==null || stuXueHao.trim().equals("")) {
			n = 0;
		}else if (Util.isNumeric(stuXueHao)) {
			n = scoreService.hasScoreRecord(Integer.parseInt(stuXueHao), courseID);
		}
		else {
			n = 0;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("flag", n);
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	public String getStuXueHao() {
		return stuXueHao;
	}
	public void setStuXueHao(String stuXueHao) {
		this.stuXueHao = stuXueHao;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
}
