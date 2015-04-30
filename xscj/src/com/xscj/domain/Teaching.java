/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 * @date 2014-3-20 上午8:45:33
 *
 */
public class Teaching implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5386511021690140998L;
	
	private String classID;
	private int xueqi;
	private String courseID;
	private String teacherID;
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

}
