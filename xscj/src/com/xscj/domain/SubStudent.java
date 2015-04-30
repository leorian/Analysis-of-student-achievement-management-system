/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *
 */
public class SubStudent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 403240276197738069L;
	
	private int xuehao;
	private String stuName;
	public int getXuehao() {
		return xuehao;
	}
	public void setXuehao(int xuehao) {
		this.xuehao = xuehao;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

}
