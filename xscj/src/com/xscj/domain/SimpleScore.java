/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *
 */
public class SimpleScore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8622399421642920378L;
	
	private int xuehao;
	private double score;
	public int getXuehao() {
		return xuehao;
	}
	public void setXuehao(int xuehao) {
		this.xuehao = xuehao;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}


}
