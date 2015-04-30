/**
 * 
 */
package com.xscj.score.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.domain.ScorePart;
import com.xscj.domain.SubStudent;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;
import com.xscj.service.StuService;

/**
 * @author leorain
 *@date 2014-4-2 上午9:48:36
 */
public class ScoreQueryResultAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6494963640070162463L;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;//班级操作业务逻辑处理类
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;//成绩操作业务逻辑处理类
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	
	public List<ScorePart> getScorePartsa() {
		return scorePartsa;
	}

	public void setScorePartsa(List<ScorePart> scorePartsa) {
		this.scorePartsa = scorePartsa;
	}

	public List<ScorePart> getScorePartsb() {
		return scorePartsb;
	}

	public void setScorePartsb(List<ScorePart> scorePartsb) {
		this.scorePartsb = scorePartsb;
	}

	public List<ScorePart> getScorePartsc() {
		return scorePartsc;
	}

	public void setScorePartsc(List<ScorePart> scorePartsc) {
		this.scorePartsc = scorePartsc;
	}

	public List<ScorePart> getScorePartsd() {
		return scorePartsd;
	}

	public void setScorePartsd(List<ScorePart> scorePartsd) {
		this.scorePartsd = scorePartsd;
	}

	public List<ScorePart> getScorePartse() {
		return scorePartse;
	}

	public void setScorePartse(List<ScorePart> scorePartse) {
		this.scorePartse = scorePartse;
	}

	public List<ScorePart> getScorePartsf() {
		return scorePartsf;
	}

	public void setScorePartsf(List<ScorePart> scorePartsf) {
		this.scorePartsf = scorePartsf;
	}

	private int stuXueHao;//从scoreQuery.jsp中获得的学号

	public int getStuXueHao() {
		return stuXueHao;
	}

	public void setStuXueHao(int stuXueHao) {
		this.stuXueHao = stuXueHao;
	}
	
	private Grade grade;//由学号获得班级信息

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	private List<ScorePart> scorePartsa;//第一学期考试成绩结果
	private List<ScorePart> scorePartsb;//第二学期考试成绩结果
	private List<ScorePart> scorePartsc;//第三学期考试成绩结果
	private List<ScorePart> scorePartsd;//第四学期考试成绩结果
	private List<ScorePart> scorePartse;//第五学期考试成绩结果
	private List<ScorePart> scorePartsf;//第六学期考试成绩结果
	
	private SubStudent student;//查询的学生信息

	public SubStudent getStudent() {
		return student;
	}

	public void setStudent(SubStudent student) {
		this.student = student;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
		grade = gradeSetUp.getGradeBystuXueHao(stuXueHao);
		student = stuService.getSubStudentByXueHao(stuXueHao);
		scorePartsa = scoreService.getScoreParts(grade.getBianHao(), 1, stuXueHao);
		scorePartsb = scoreService.getScoreParts(grade.getBianHao(), 2, stuXueHao);
		scorePartsc = scoreService.getScoreParts(grade.getBianHao(), 3, stuXueHao);
		scorePartsd = scoreService.getScoreParts(grade.getBianHao(), 4, stuXueHao);
		scorePartse = scoreService.getScoreParts(grade.getBianHao(), 5, stuXueHao);
		scorePartsf = scoreService.getScoreParts(grade.getBianHao(), 6, stuXueHao);
		/*System.out.println(stuXueHao);
		System.out.println(grade.getYear()+"届（"+grade.getClassID()+"班）");
		System.out.println("第一学期成绩记录数"+scorePartsa.size());
		System.out.println("第二学期成绩记录数"+scorePartsb.size());
		System.out.println("第三学期成绩记录数"+scorePartsc.size());
		System.out.println("第四学期成绩记录数"+scorePartsd.size());
		System.out.println("第五学期成绩记录数"+scorePartse.size());
		System.out.println("第六学期成绩记录数"+scorePartsf.size());*/
		
		
	}
	
}
