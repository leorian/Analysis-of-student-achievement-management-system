/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *
 */
public class ScoreGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3511072539794821811L;
	
	private String courseId;//课程编号
	private String courseName;//课程名称
	private int maxScore;//最高成绩
	private int minScore;//最低成绩
	private int avgScore;//平均成绩
	private int badCount;//不及格人数
	private int goodCount;//及格人数
	private int baiFenBi;//及格率
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
	public int getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	public int getMinScore() {
		return minScore;
	}
	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}
	public int getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(int avgScore) {
		this.avgScore = avgScore;
	}
	public int getBadCount() {
		return badCount;
	}
	public void setBadCount(int badCount) {
		this.badCount = badCount;
	}
	public int getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}
	public int getBaiFenBi() {
		return baiFenBi;
	}
	public void setBaiFenBi(int baiFenBi) {
		this.baiFenBi = baiFenBi;
	}
	

}
