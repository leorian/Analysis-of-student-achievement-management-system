package com.xscj.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.service.CategoryService;
import com.xscj.service.CoursePlan;

public class CourseFormRegisterAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4442510776521229467L;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	@Autowired
	@Qualifier("categoryServiceImpl")
	private CategoryService categoryService;
	
	private Course course;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@Override
	public String execute() throws Exception {
		coursePlan.addCourseToDb(course);
		categoryService.updateCount("C");
		return SUCCESS;
	}
}
