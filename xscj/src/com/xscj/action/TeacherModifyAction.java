/**
 * 
 */
package com.xscj.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Teacher;
import com.xscj.service.CoursePlan;
import com.xscj.service.TeacherManager;

/**
 * @author leorain
 * @date 2014-4-13 下午1:58:42
 *
 */
public class TeacherModifyAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8842580583968372826L;
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	private String teacherBianHao;
	
	private Teacher teacher;
	
	private List<Course> courses;

	@Override
	public String execute() throws Exception {
		if(teacherBianHao == null)
			return INPUT;
		if(teacherManager.isExisit(teacherBianHao) == 0)
			return INPUT;
		teacher = teacherManager.getTeacherInfo(teacherBianHao);
		courses = coursePlan.getAllCourses();
		return SUCCESS;
	}

	@Override
	public void validate() {
		
	}

	public String getTeacherBianHao() {
		return teacherBianHao;
	}

	public void setTeacherBianHao(String teacherBianHao) {
		this.teacherBianHao = teacherBianHao;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	

}
