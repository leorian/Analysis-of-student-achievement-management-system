package com.xscj.dao;

import java.util.List;

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

public interface ScoreDao {
	/**
	 * @author leorain
	 * @date 2014-3-24 下午8:25:04
	 * 
	 * 批量插入学生成绩记录数据
	 * */
	public void addScoresToDb(final List<Score> scores);
	/**
	 * @author leorain
	 * @date 2014-4-1 下午1:23:02
	 * */
	public List<SimpleScore> getSimpleScores(String gradeID, String courseID, String examType, String examTime, int xueqi);
	
	/**
	 * @author leorain
	 * @date 2014-4-2 上午10:48:57
	 * 通过班级编号、学期、学生学号查询学生考试成绩
	 * */
	public List<ScorePart> getScoreParts(String gradeID, int xueqi, int stuXueHao);
	
	/**
	 * @author leorain
	 * @date 2014-4-3 上午7:55:39
	 * 通过学生学号查询学生考试成绩
	 * */
	public List<ScorePart> getScorePartsByXueHao(int stuXueHao);
	
	/**
	 * @author leorain
	 * @date 2014-4-3 上午10:14:10
	 * 通过学生学号和课程编号查询这个学生截止目前为止某一门课程所有考试情况
	 * */
	public List<ScoreXueqi> getScoreXueqis(int stuXueHao, String courseID);
	
	/**
	 * @author leorain
	 * @date 2014-4-3 下午7:55:55
	 * 通过班级编号和课程编号查询某个班级所有学生某门课程的所有成绩记录
	 * */
	public List<ScoreByGidCid> getScoreByGidCids(String gradeID, String courseID);
	
	/**
	 * @author leorain
	 * @date 2014-4-6 下午12:36:01
	 * @param学号
	 * @param学期
	 * @param类型
	 * @return通过学生学号和学期以及考试类型查询学生在某次考试中的所有成绩
	 * 
	 * **/
	public List<ScoreBySXT> getScoreBySXTs(int stuXueHao, int xueqi, String examType);
	
	/**
	 * @author leorain
	 * @date 2014-4-6 下午4:06:00
	 * @param班级编号
	 * @param课程编号
	 * @param学期
	 * @param考试类型
	 * @return通过班级编号、课程编号、学期、考试类型
	 * 查询某个班级某个学期某个课程某次考试所有学生的考试情况
	 * 
	 * */
	public List<ScoreByGCXT> getscByGCXTs(String gradeID, String courseID, int xueqi, String examType);
	
	/**
	 * @author leorain
	 * @date 2014-4-9 下午9:29:34
	 * @param学生学号
	 * @param学期
	 * @param考试类型
	 * @param课程编号
	 * 
	 * */
	public SimpleScore getScore(int stuXueHao, int xueqi, String examType, String courseID );
	
	/**
	 * @author leorain
	 * @date 2014-4-9 下午11:05:18
	 * @param新的考试成绩
	 * @param学生学号
	 * @param学期
	 * @param考试类型
	 * @param课程编号
	 * */
	
	public void updateScore(double newScore, int stuXueHao, int xueqi, String examType, String courseID);
	
	/**
	 * @author leorain
	 * @date 2014-4-10 上午9:36:02
	 * @return学生学号、学生姓名、学生成绩
	 * */
	public List<IDNameScore> getIdNameScores(String gradeID, int xueqi, String examType, String courseID);

	/**
	 * @author leorain
	 * @date 2014-4-10 下午1:45:55
	 * 
	 * */
	public void updateScores(List<Integer> stuXueHaos, List<Double> scores, String gradeID, int xueqi, String examType, String courseID);
	
	
	
	/**
	 * @author leorain
	 * @date 2014-4-14 上午9:27:06
	 * @param学生学号
	 * @param学期
	 * @param考试类型
	 * @param课程编号
	 * @return删除某个学生某个课程某次考试的成绩
	 * */
	public void deleteStuScore(int stuXueHao, int xueqi, String examType, String courseID);
	/**
	 * @author leorain
	 * @date 2014-4-14 上午10:34:56
	 * @param学生学号
	 * @param学期
	 * @param考试类型
	 * @param课程编号
	 * @return删除某个班级某些学生某个课程某次考试的成绩
	 * */
	public void deleteStuScores(List<Integer> stuXueHaos, int xueqi, String examType, String courseID);
	
	/**
	 * @author leorain
	 * @date 2014-4-14 下午9:16:06
	 * @param班级编号
	 * @param学期
	 * @param考试类型
	 * @return某个班级某次考试的成绩情况
	 * */
	public List<ScoreCount> getScoreCounts(String gradeID, int xueqi, String examType);
	
	/**
	 * @author leorain
	 * @date 2014-4-16 下午8:38:39
	 * @return获得某个班级某个学期某次考试某个学生的考试成绩记录总数
	 * */
	public int getCount(int stuXueHao, int xueqi, String examType);
	/**
	 * @author leorain
	 * @date 2014-4-16 下午9:09:01
	 * */
	public int getCount(String gradeID, int xueqi, String examType, String courseID);
	
	/**
	 * @author leorain
	 * @date 2014-4-16 下午10:26:10
	 * @return是否有某个学生的成绩记录
	 * */
	public int hasScoreRecord(int stuXueHao);
	/**
	 * @author leorain
	 * @date 2014-4-16 下午10:26:10
	 * @return是否有某个学生某门课程的成绩记录
	 * */
	public int hasScoreRecord(int stuXueHao, String courseID);
	
	/**
	 * @author leorain
	 * @date 2014-4-16 下午11:20:42
	 * */
	public int hasScoreRecord(int stuXueHao, String examType, int xueqi);
	/**
	 * @author leorain
	 * @date 2014-4-16 下午11:42:35
	 * */
	public int hasScoreRecord(String gradeID, String courseID);
	
	/**
	 * @author leorain
	 * @date 2014-4-17 上午8:14:47
	 * 
	 * 
	 * */
	public int hasScoreRecord(String gradeID, String courseID, int xueqi, String examTypeString);
	
	/**
	 * @author leorain
	 * @date 2014-4-17 上午8:40:08
	 * 
	 * */
	public int hasScoreRecord(int stuXueHao, String courseID, int xueqi, String examType);
	
	/**
	 * @author leorain
	 * @date 2014-4-17 上午9:21:31
	 * 
	 * */
	public int isExisits(String gradeID, int xueqi, String examType);
	
	/**
	 * @author leorain
	 * @date 2014-4-17 上午10:07:31
	 * @return获得班级每个同学的总分集合
	 * */
	public List<StuScoreCount> getStuScoreCounts(String gradeID, int xueqi, String examType);
	
	
	/**
	 * @author leorain
	 * */
	
	public List<ScoreGroup> getScoreGroups(String gradeID, int xueqi, String examType);
}
