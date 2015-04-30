/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author leorain
 *@date 2014-3-19 下午5:54:18
 *
 *包含课程编号 课程名称 对应授课老师集合
 */
public class SuperCourse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4079161327736017835L;
	
	private String courseBianHao;
	private String courseName;
	private List<SubTeacher> subTeachers;
	public String getCourseBianHao() {
		return courseBianHao;
	}
	public void setCourseBianHao(String courseBianHao) {
		this.courseBianHao = courseBianHao;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public List<SubTeacher> getSubTeachers() {
		return subTeachers;
	}
	public void setSubTeachers(List<SubTeacher> subTeachers) {
		this.subTeachers = subTeachers;
	}

}
