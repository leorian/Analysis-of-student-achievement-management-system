/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *
 */
public class TeachingDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 378563155934898405L;
	private String gradeID;
	private int year;
	private int classID;
	
	private int xueqi;
	
	private String courseID;
	private String courseName;
	
	private String teacherID;
	 
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
	public String getGradeID() {
		return gradeID;
	}
	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
