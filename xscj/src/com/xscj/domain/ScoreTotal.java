/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author leorain
 *
 */
public class ScoreTotal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3771299835011173717L;
	private int xuehao;
	private String name;
	private double totalScore;
	private List<ScoreSmall> scoreSmalls;
	public int getXuehao() {
		return xuehao;
	}
	public void setXuehao(int xuehao) {
		this.xuehao = xuehao;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	public List<ScoreSmall> getScoreSmalls() {
		return scoreSmalls;
	}
	public void setScoreSmalls(List<ScoreSmall> scoreSmalls) {
		this.scoreSmalls = scoreSmalls;
	}
	

}
