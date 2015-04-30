package com.xscj.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.service.CoursePlan;

public class CourseFormAction extends ActionSupport {

	/**
	 * @author leorain 课程添加表单页面
	 */
	private static final long serialVersionUID = 328222599468397267L;
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	private int count; //课程数目
	private List<Course> courses; //课程列表
	private List<Course> coursesEnableDeL;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	@Override
	public String execute() throws Exception {
		count = coursePlan.getValidCourseCount();
		courses = coursePlan.getValidAllCourses();
		coursesEnableDeL = coursePlan.getCoursesEnableDel();
		return SUCCESS;
	}
	public List<Course> getCoursesEnableDeL() {
		return coursesEnableDeL;
	}
	public void setCoursesEnableDeL(List<Course> coursesEnableDeL) {
		this.coursesEnableDeL = coursesEnableDeL;
	}

}
