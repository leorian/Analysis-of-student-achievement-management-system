/**
 * 
 */
package com.xscj.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Teacher;
import com.xscj.service.CoursePlan;
import com.xscj.service.TeacherManager;

/**
 * @author leorain
 *	@date 2014-4-13 下午3:51:21
 */
public class TeacherModifySucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5850873736388743274L;
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	private Teacher teacher;
	
	private Course course;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
			teacherManager.updateTeacherInfo(teacher);
			course = coursePlan.getCourseByCourseID(teacher.getCourseID());
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	

}
