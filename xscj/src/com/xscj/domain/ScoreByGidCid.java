/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *@date 2014-4-3 下午7:51:06
 *学生学号、学生姓名、考试时间、考试类型、学期、最后成绩
 */
public class ScoreByGidCid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3304216081533415450L;
	
	private int stuXueHao;//学生学号
	private String stuName;//学生姓名
	private String examTime;//考试时间
	private String examType;//考试类型
	private int xueqi;//学期
	private Double score;//成绩
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
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	

}
