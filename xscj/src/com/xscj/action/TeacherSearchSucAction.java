/**
 * 
 */
package com.xscj.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Teacher;
import com.xscj.service.TeacherManager;

/**
 * @author leorain
 *@date 2014-4-13 下午5:49:29
 */
public class TeacherSearchSucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2526827005782511442L;
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	
	private String teacherBianHao;
	
	
	private Teacher teacher;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
		teacher = teacherManager.getTeacherInfo(teacherBianHao);
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
	

}
