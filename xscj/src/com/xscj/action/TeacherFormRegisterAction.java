package com.xscj.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Teacher;
import com.xscj.service.CategoryService;
import com.xscj.service.CoursePlan;
import com.xscj.service.TeacherManager;

public class TeacherFormRegisterAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7238388060614358410L;
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	@Autowired
	@Qualifier("categoryServiceImpl")
	private CategoryService categoryService;
	
	private Teacher teacher;
	private int count;
	private Course course;
	@Override
	public void validate() {
		
	}

	@Override
	public String execute() throws Exception {
		if(teacher == null)
			return INPUT;
		teacherManager.addTeacherToDb(teacher);
		categoryService.updateCount("T");
		count = teacherManager.getValidTeacherCount();
		course = coursePlan.getCourseByCourseID(teacher.getCourseID());
		return SUCCESS;
	}
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
