/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *@date 2014-4-14 下午9:05:25
 */
public class ScoreCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1412096366147525305L;
	
	private int stuXueHao;//学号
	private String stuName;//姓名
	private String courseID;//课程编号
	private String courseName;//课程名称
	private double score;//成绩
	
	public int getStuXueHao() {
		return stuXueHao;
	}
	public void setStuXueHao(int stuXueHao) {
		this.stuXueHao = stuXueHao;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
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
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	

}
