package com.xscj.stu.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.ScoreService;

 
public class ScoreExisitsTestAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7103265542020117890L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	private int stuXueHao;
	private int xueqi;
	private String examType;
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
		n = scoreService.hasScoreRecord(stuXueHao, examType, xueqi);
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("flag", n);
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getStuXueHao() {
		return stuXueHao;
	}
	public void setStuXueHao(int stuXueHao) {
		this.stuXueHao = stuXueHao;
	}
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
}
