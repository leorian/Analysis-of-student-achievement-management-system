/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *@date 2014-4-6 上午11:59:46
 *S 学号	X学期	考试类型
 *
 *课程编号、课程名称、考试时间、最后成绩
 */
public class ScoreBySXT implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6991400526809080929L;
	
	
	private String courseID;//课程编号
	private String courseName;//课程名称
	private String examTime;//考试时间
	private double score;//最后成绩
	
	
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
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}

}
