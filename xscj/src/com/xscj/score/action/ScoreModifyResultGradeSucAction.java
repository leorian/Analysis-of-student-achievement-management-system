/**
 * 
 */
package com.xscj.score.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Grade;
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;

/**
 * @author leorain
 *@date 2014-4-10 下午1:14:48
 */
public class ScoreModifyResultGradeSucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5325974722007734437L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	private String gradeID;
	private int xueqi;
	private String examType;
	private String courseID;
	private List<Integer> stuXueHaos;
	private List<Double> scores;
	private List<String> stuNames;
	private Grade grade;
	private Course course;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	public void validate() {
		scoreService.updateScores(stuXueHaos, scores, gradeID, xueqi, examType, courseID);
		grade = gradeSetUp.getGradeBybianHao(gradeID);
		course = coursePlan.getCourseByCourseID(courseID);
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
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public List<Integer> getStuXueHaos() {
		return stuXueHaos;
	}
	public void setStuXueHaos(List<Integer> stuXueHaos) {
		this.stuXueHaos = stuXueHaos;
	}
	public List<Double> getScores() {
		return scores;
	}
	public void setScores(List<Double> scores) {
		this.scores = scores;
	}
	public List<String> getStuNames() {
		return stuNames;
	}
	public void setStuNames(List<String> stuNames) {
		this.stuNames = stuNames;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	

}
