package com.xscj.domain;

import java.io.Serializable;

public class StuScoreCount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6320269760017603841L;
	
	private int stuXueHao;
	private String stuNameString;
	private double scoreSum;
	public int getStuXueHao() {
		return stuXueHao;
	}
	public void setStuXueHao(int stuXueHao) {
		this.stuXueHao = stuXueHao;
	}
	public String getStuNameString() {
		return stuNameString;
	}
	public void setStuNameString(String stuNameString) {
		this.stuNameString = stuNameString;
	}
	public double getScoreSum() {
		return scoreSum;
	}
	public void setScoreSum(double scoreSum) {
		this.scoreSum = scoreSum;
	}
	

}
