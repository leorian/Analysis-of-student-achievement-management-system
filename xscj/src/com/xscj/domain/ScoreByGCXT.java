/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *@date 2014-4-6 下午4:01:06
 *学号、姓名、考试时间、最后成绩
 */
public class ScoreByGCXT implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7924468777208251916L;
	private int stuXueHao;//学号
	private String stuName;//学生姓名
	private String examTime;//考试时间
	private double score;//分数
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
