/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *@date 2014-4-19 下午2:35:16
 */
public class TeachingTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4311985795232349664L;
	
	private String gradeID;//班级编号
	private int year;//届
	private int classID;//学号
	private String adviserID;//班主任编号
	private String adviserName;//班主任姓名
	private int xueqi;//学期
	private String courseID;//课程编号
	private String courseName;//课程姓名
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
	public String getAdviserID() {
		return adviserID;
	}
	public void setAdviserID(String string) {
		this.adviserID = string;
	}
	public String getAdviserName() {
		return adviserName;
	}
	public void setAdviserName(String adviserName) {
		this.adviserName = adviserName;
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	

}
