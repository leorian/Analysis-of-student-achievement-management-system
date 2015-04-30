/**
 * 
 */
package com.xscj.teacher.action;

import java.util.ArrayList;
import java.util.List;

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
 * @date 2014-4-14 上午10:16:02
 */
public class TeacherScoreDelSucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1928068595400973192L;
	
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
	
	private List<Integer> stuXueHaos;
	private List<SubStudent> subStudents;
	private int xueqi;
	private String examType;
	private String courseID;
	private String gradeID;
	private Grade grade;
	private Course course;
	@Override
	public String execute() throws Exception {
		if(gradeID == null || courseID == null || examType == null || stuXueHaos == null)
		{
			return INPUT;
		}
		scoreService.deleteStuScores(stuXueHaos, xueqi, examType, courseID);
		subStudents = new ArrayList<SubStudent>();
		for (int i = 0; i < stuXueHaos.size(); i++) {
			SubStudent subStudent = stuService.getSubStudentByXueHao(stuXueHaos.get(i));
			subStudents.add(subStudent);
		}
		course = coursePlan.getCourseByCourseID(courseID);
		grade = gradeSetUp.getGradeBybianHao(gradeID);
		return SUCCESS;
	}
	@Override
	public void validate() {
	}
	public List<Integer> getStuXueHaos() {
		return stuXueHaos;
	}
	public void setStuXueHaos(List<Integer> stuXueHaos) {
		this.stuXueHaos = stuXueHaos;
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
	public List<SubStudent> getSubStudents() {
		return subStudents;
	}
	public void setSubStudents(List<SubStudent> subStudents) {
		this.subStudents = subStudents;
	}
	public String getGradeID() {
		return gradeID;
	}
	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
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
