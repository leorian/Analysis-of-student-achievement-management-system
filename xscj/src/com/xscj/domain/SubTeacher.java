/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 * @date 2014-3-19 下午5:47:27
 * 包含两个教师属性 教师编号 教师姓名
 */
public class SubTeacher implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7447988961983299891L;
	private String bianHao;
	private String name;
	
	public String getBianHao() {
		return bianHao;
	}
	public void setBianHao(String bianHao) {
		this.bianHao = bianHao;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
