/**
 * 
 */
package com.xscj.score.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Grade;
import com.xscj.domain.SubStudent;
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;
import com.xscj.service.StuService;

/**
 * @author leorain
 *
 */
public class ScoreDeleteResultSucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4869140195165932014L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	private int stuXueHao;
	private SubStudent subStudent;
	private Grade grade;
	private int xueqi;
	private String examType;
	private String courseID;
	private Course course;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	public void validate() {
		System.out.println(stuXueHao + " " + xueqi + " " + examType + " " + courseID);
		scoreService.deleteStuScore(stuXueHao, xueqi, examType, courseID);
		
		subStudent = stuService.getSubStudentByXueHao(stuXueHao);
		grade = gradeSetUp.getGradeBystuXueHao(stuXueHao);
		course = coursePlan.getCourseByCourseID(courseID);
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
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public SubStudent getSubStudent() {
		return subStudent;
	}
	public void setSubStudent(SubStudent subStudent) {
		this.subStudent = subStudent;
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
