package com.xscj.test.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.ScoreService;
import com.xscj.service.StuService;
import com.xscj.service.TeachingArrange;

 
public class ScoreCountTestAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7103265542020117890L;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	private String gradeID;
	private int gradeCount;
	private int xueqi;
	private int teachingCount;
	private String examType;
	private int scoreCount;
	private int n ;
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		 gradeCount = stuService.isExisits(gradeID);
		 teachingCount = teachingArrange.isExisits(gradeID, xueqi);
		 scoreCount = scoreService.isExisits(gradeID, xueqi, examType);
		 if(gradeCount*teachingCount == scoreCount)
		 {
			 n = scoreCount;
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
	public String getGradeID() {
		return gradeID;
	}
	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
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
	public int getGradeCount() {
		return gradeCount;
	}
	public void setGradeCount(int gradeCount) {
		this.gradeCount = gradeCount;
	}
	public int getTeachingCount() {
		return teachingCount;
	}
	public void setTeachingCount(int teachingCount) {
		this.teachingCount = teachingCount;
	}
	public int getScoreCount() {
		return scoreCount;
	}
	public void setScoreCount(int scoreCount) {
		this.scoreCount = scoreCount;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	
	
	
}
