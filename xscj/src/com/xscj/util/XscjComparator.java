package com.xscj.util;

import java.util.Comparator;

import com.xscj.domain.ScoreTotal;

public class XscjComparator implements Comparator<ScoreTotal> {

	@Override
	public int compare(ScoreTotal arg0, ScoreTotal arg1) {
		// TODO Auto-generated method stub
		return (int)arg1.getTotalScore() - (int)arg0.getTotalScore();
	}

}
