package com.xscj.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.service.CoursePlan;
import com.xscj.service.TeacherManager;

public class TeacherFormAction extends ActionSupport {

	/**
	 * 		管理员——教师注册页面
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	private List<Course> courses;
	
 
	public List<Course> getCourses() {
		return courses;
	}
	private String now;
	public String getNow() {
		return now;
	}
	private int count;
	public int getCount() {
		return count;
	}
	@Override
	public String execute() throws Exception {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			now = sdf.format(new Date());
			courses = coursePlan.getValidAllCourses();
			count = teacherManager.getValidTeacherCount();
		return SUCCESS;
	}
}
