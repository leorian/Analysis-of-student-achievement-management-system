package com.xscj.score.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.domain.ScoreBySXT;
import com.xscj.domain.SubStudent;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;
import com.xscj.service.StuService;

public class ScoreQueryResultFourAction extends ActionSupport{

	/**
	 * @author leorain
	 * @date 2014-4-6 下午12:23:45
	 * 接收学号、学期、考试类型
	 */
	private static final long serialVersionUID = -2912267748483392213L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;//学生成绩操作业务逻辑操作处理类
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private int stuXueHao;//学号
	private SubStudent student;
	private Grade grade;
	private String examType;//考试类型
	private int xueqi;//学期
	private List<ScoreBySXT> scoreBySXTs;//查询成绩封装
	private double scoreSum;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	public void validate() {
		student = stuService.getSubStudentByXueHao(stuXueHao);
		grade = gradeSetUp.getGradeBystuXueHao(stuXueHao);
		scoreBySXTs = scoreService.getScoreBySXTs(stuXueHao, xueqi, examType);
		scoreSum = 0;
		for (int i = 0; i < scoreBySXTs.size(); i++) {
			scoreSum = scoreSum + scoreBySXTs.get(i).getScore();
		}
	}
	public int getStuXueHao() {
		return stuXueHao;
	}
	public void setStuXueHao(int stuXueHao) {
		this.stuXueHao = stuXueHao;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}
	public List<ScoreBySXT> getScoreBySXTs() {
		return scoreBySXTs;
	}
	public void setScoreBySXTs(List<ScoreBySXT> scoreBySXTs) {
		this.scoreBySXTs = scoreBySXTs;
	}
	public SubStudent getStudent() {
		return student;
	}
	public void setStudent(SubStudent student) {
		this.student = student;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public double getScoreSum() {
		return scoreSum;
	}
	public void setScoreSum(double scoreSum) {
		this.scoreSum = scoreSum;
	}
	
}
