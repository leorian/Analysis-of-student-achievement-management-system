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

/**
 * @author leorain
 * @date 2014-4-2 上午8:45:22
 *
 */
public class ScoreQueryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2185801406159796197L;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;//课程业务逻辑操作处理类
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private List<Course> courses;//所有课程信息
	
	private List<Grade> grades;
	
	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
		courses = coursePlan.getValidAllCourses();
		grades = gradeSetUp.getALLValidGrades();
	}
	

}
