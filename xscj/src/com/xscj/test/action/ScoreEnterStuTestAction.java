package com.xscj.test.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.ScoreService;
import com.xscj.service.TeachingArrange;
import com.xscj.util.Util;

 
public class ScoreEnterStuTestAction extends ActionSupport {
	
	/**
	 * @author leorain
	 * @date 2014-4-16 下午2:10:45
	 */
	private static final long serialVersionUID = 7103265542020117890L;
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	private String examType;
	private String stuXueHao;
	private int xueqi;
	private int n=0;
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
			n = teachingArrange.isExisits(Integer.parseInt(stuXueHao), xueqi);
			int m = scoreService.getCount(Integer.parseInt(stuXueHao), xueqi, examType);
			if(n == m && n !=0)
			{
				n = -1;
			}
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
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
}
