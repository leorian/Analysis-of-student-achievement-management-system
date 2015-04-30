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
 *@date 2014-4-7 下午3:54:34
 */
public class ScoreModifyAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1066785374294607140L;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private List<Grade> grades;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
		courses = coursePlan.getValidAllCourses();
		grades = gradeSetUp.getALLValidGrades();
	}
	private List<Course> courses;


	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	
	

}
