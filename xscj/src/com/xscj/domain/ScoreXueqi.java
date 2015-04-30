/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *
 */
public class ScoreXueqi implements Serializable {

	/**
	 * @author leorain
	 * @date 2014-4-3 上午10:10:27
	 * 学期、考试时间、考试类型、成绩
	 */
	private static final long serialVersionUID = -1051196136049003713L;
	
	private int xueqi;//学期
	private String examTime;//考试时间
	private String examType;//考试类型
	private double score;//成绩
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
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
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}

}
