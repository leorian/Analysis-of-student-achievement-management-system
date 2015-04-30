package com.xscj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xscj.dao.ScoreDao;
import com.xscj.domain.IDNameScore;
import com.xscj.domain.Score;
import com.xscj.domain.ScoreByGCXT;
import com.xscj.domain.ScoreByGidCid;
import com.xscj.domain.ScoreBySXT;
import com.xscj.domain.ScoreCount;
import com.xscj.domain.ScoreGroup;
import com.xscj.domain.ScorePart;
import com.xscj.domain.ScoreXueqi;
import com.xscj.domain.SimpleScore;
import com.xscj.domain.StuScoreCount;
import com.xscj.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService{
	@Autowired
	@Qualifier("jdbcScoreDaoImpl")
	private ScoreDao scoreDao;

	@Override
	public void addScoresToDb(final List<Score> scores) {		
		scoreDao.addScoresToDb(scores);
	}

	@Override
	public List<SimpleScore> getSimpleScores(String gradeID, String courseID,
			String examType, String examTime, int xueqi) {
		return scoreDao.getSimpleScores(gradeID, courseID, examType, examTime, xueqi);
	}

	@Override
	public List<ScorePart> getScoreParts(String gradeID, int xueqi,
			int stuXueHao) {
		return scoreDao.getScoreParts(gradeID, xueqi, stuXueHao);
	}

	@Override
	public List<ScorePart> getScorePartsByXueHao(int stuXueHao) {
		return scoreDao.getScorePartsByXueHao(stuXueHao);
	}

	@Override
	public List<ScoreXueqi> getScoreXueqis(int stuXueHao, String courseID) {
		return scoreDao.getScoreXueqis(stuXueHao, courseID);
	}

	@Override
	public List<ScoreByGidCid> getScoreByGidCids(String gradeID, String courseID) {
		return scoreDao.getScoreByGidCids(gradeID, courseID);
	}

	@Override
	public List<ScoreBySXT> getScoreBySXTs(int stuXueHao, int xueqi,
			String examType) {
		return scoreDao.getScoreBySXTs(stuXueHao, xueqi, examType);
	}

	@Override
	public List<ScoreByGCXT> getscByGCXTs(String gradeID, String courseID,
			int xueqi, String examType) {
		return scoreDao.getscByGCXTs(gradeID, courseID, xueqi, examType);
	}

	@Override
	public SimpleScore getScore(int stuXueHao, int xueqi, String examType,
			String courseID) {
		return scoreDao.getScore(stuXueHao, xueqi, examType, courseID);
	}

	@Override
	public void updateScore(double newScore, int stuXueHao, int xueqi,
			String examType, String courseID) {
		scoreDao.updateScore(newScore, stuXueHao, xueqi, examType, courseID);
	}

	@Override
	public List<IDNameScore> getIdNameScores(String gradeID, int xueqi,
			String examType, String courseID) {
		return scoreDao.getIdNameScores(gradeID, xueqi, examType, courseID);
	}

	@Override
	public void updateScores(List<Integer> stuXueHaos, List<Double> scores,
			String gradeID, int xueqi, String examType, String courseID) {
		scoreDao.updateScores(stuXueHaos, scores, gradeID, xueqi, examType, courseID);
	}

	@Override
	public void deleteStuScore(int stuXueHao, int xueqi, String examType,
			String courseID) {
		scoreDao.deleteStuScore(stuXueHao, xueqi, examType, courseID);
	}

	@Override
	public void deleteStuScores(List<Integer> stuXueHaos, int xueqi,
			String examType, String courseID) {
		scoreDao.deleteStuScores(stuXueHaos, xueqi, examType, courseID);
	}

	@Override
	public List<ScoreCount> getScoreCounts(String gradeID, int xueqi,
			String examType) {
		return scoreDao.getScoreCounts(gradeID, xueqi, examType);
	}

	@Override
	public int getCount(int stuXueHao, int xueqi, String examType) {
		return scoreDao.getCount(stuXueHao, xueqi, examType);
	}

	@Override
	public int getCount(String gradeID, int xueqi, String examType, String courseID) {
		return scoreDao.getCount(gradeID, xueqi, examType, courseID);
	}

	@Override
	public int hasScoreRecord(int stuXueHao) {
		return scoreDao.hasScoreRecord(stuXueHao);
	}

	@Override
	public int hasScoreRecord(int stuXueHao, String courseID) {
		return scoreDao.hasScoreRecord(stuXueHao, courseID);
	}

	@Override
	public int hasScoreRecord(int stuXueHao, String examType, int xueqi) {
		return scoreDao.hasScoreRecord(stuXueHao, examType, xueqi);
	}

	@Override
	public int hasScoreRecord(String gradeID, String courseID) {
		return scoreDao.hasScoreRecord(gradeID, courseID);
	}

	@Override
	public int hasScoreRecord(String gradeID, String courseID, int xueqi,
			String examTypeString) {
		return scoreDao.hasScoreRecord(gradeID, courseID, xueqi, examTypeString);
	}

	@Override
	public int hasScoreRecord(int stuXueHao, String courseID, int xueqi,
			String examType) {
		return scoreDao.hasScoreRecord(stuXueHao, courseID, xueqi, examType);
	}

	@Override
	public int isExisits(String gradeID, int xueqi, String examType) {
		return scoreDao.isExisits(gradeID, xueqi, examType);
	}

	@Override
	public List<StuScoreCount> getStuScoreCounts(String gradeID, int xueqi,
			String examType) {
		return scoreDao.getStuScoreCounts(gradeID, xueqi, examType);
	}

	@Override
	public List<ScoreGroup> getScoreGroups(String gradeID, int xueqi,
			String examType) {
		return scoreDao.getScoreGroups(gradeID, xueqi, examType);
	}
}
