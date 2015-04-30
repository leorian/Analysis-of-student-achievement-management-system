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
import com.xscj.domain.TeachingDetail;
import com.xscj.service.CoursePlan;
import com.xscj.service.TeacherManager;
import com.xscj.service.TeachingArrange;

/**
 * @author leorain
 *
 */
public class TeacherSeeingAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 532423653546521102L;
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	 
	
	private String teacherBianHao;
	
	private Teacher teacher;
	
	 private Course course;
	
	private List<TeachingDetail> teachingDetails;

	@Override
	public String execute() throws Exception {
		if(teacherBianHao == null)
			return INPUT;
		if(teacherManager.isExisit(teacherBianHao) == 0)
			return INPUT;
		teacher = teacherManager.getTeacherInfo(teacherBianHao);
		teachingDetails = teachingArrange.getTeachingDetails(teacherBianHao);
		course = coursePlan.getCourseByCourseID(teacher.getCourseID());
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
	public List<TeachingDetail> getTeachingDetails() {
		return teachingDetails;
	}

	public void setTeachingDetails(List<TeachingDetail> teachingDetails) {
		this.teachingDetails = teachingDetails;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
