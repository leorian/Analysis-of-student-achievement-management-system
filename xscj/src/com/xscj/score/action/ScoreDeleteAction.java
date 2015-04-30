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
 *@date 2014-4-10 下午4:08:58
 */
public class ScoreDeleteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7713699375923979553L;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	private List<Grade> grades;
	private List<Course> courses;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
		grades = gradeSetUp.getALLValidGrades();
		courses = coursePlan.getValidAllCourses();
	}

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
	
}
