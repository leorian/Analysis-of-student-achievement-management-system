/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain 班级实体类
 *
 */
public class Grade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2042061606045496690L;
	private String bianHao; //班级编号 
	private int year; //届、年级
	private int classID; //班级
	private String adviser; //班主任
	public String getBianHao() {
		return bianHao;
	}
	public void setBianHao(String bianHao) {
		this.bianHao = bianHao;
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
	public String getAdviser() {
		return adviser;
	}
	public void setAdviser(String adviser) {
		this.adviser = adviser;
	}
}
