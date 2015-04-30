/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *
 */
public class GC implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4319232307300289952L;
	
	private String gradeId;
	private int gradeYear;
	private int gradeClassid;
	private String gradeAdviser;
	
	private String courseId;
	private String courseName;
	
	private int xueqi;
	
	
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public int getGradeYear() {
		return gradeYear;
	}
	public void setGradeYear(int gradeYear) {
		this.gradeYear = gradeYear;
	}
	public int getGradeClassid() {
		return gradeClassid;
	}
	public void setGradeClassid(int gradeClassid) {
		this.gradeClassid = gradeClassid;
	}
	public String getGradeAdviser() {
		return gradeAdviser;
	}
	public void setGradeAdviser(String gradeAdviser) {
		this.gradeAdviser = gradeAdviser;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}

}
