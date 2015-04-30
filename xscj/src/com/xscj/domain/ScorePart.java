package com.xscj.domain;

import java.io.Serializable;

public class ScorePart implements Serializable{

	/**
	 * @author leorain
	 * @date 2014-4-2 上午10:39:50
	 * 课程编号、课程名、考试时间、考试类型、成绩
	 */
	private static final long serialVersionUID = -7126711531588602335L;
	
	private String courseID;//课程编号
	private String courseName;//课程名
	private String examTime;//考试时间
	private String examType;//考试类型
	private Double score;//考试成绩
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
