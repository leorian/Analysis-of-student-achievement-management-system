package com.xscj.domain;

import java.io.Serializable;

public class Course  implements Serializable{
	/**
	 * @author leorain
	 */
	private static final long serialVersionUID = -7078200135598223756L;
	private String bianHao; //课程编号
	private String name; //课程名字
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
