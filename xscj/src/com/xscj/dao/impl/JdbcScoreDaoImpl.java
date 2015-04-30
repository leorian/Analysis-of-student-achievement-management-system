/**
 * 
 */
package com.xscj.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

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

/**
 * @author leorain
 *@date 2014-3-24 下午8:19:50
 */
@Repository
public class JdbcScoreDaoImpl implements ScoreDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addScoresToDb(final List<Score> scores) {
		String sqlString = "INSERT INTO sp_score_record(sr_gradeid,sr_stuid, sr_courseid, sr_examtime" +
				", sr_examtype, sr_xueqi, sr_score) VALUES(?,?,?,?,?,?,?);";
		jdbcTemplate.batchUpdate(sqlString, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				Score score = scores.get(index);
				ps.setString(1, score.getGradeID());
				ps.setInt(2, score.getStuXueHao());
				ps.setString(3, score.getCourseID());
				ps.setString(4, score.getExamTime());
				ps.setString(5, score.getExamType());
				ps.setInt(6, score.getXueqi());
				ps.setDouble(7, score.getScore());
			}
			
			@Override
			public int getBatchSize() {
				return scores.size();
			}
		});
	}

	@Override
	public List<SimpleScore> getSimpleScores(String gradeID, String courseID,
			String examType, String examTime, int xueqi) {
		String sqlString = "SELECT sr_stuid, sr_score FROM sp_score_record WHERE sr_gradeid=? AND sr_courseid=? AND sr_examtime=? AND sr_examtype=? AND sr_xueqi=?;";
		Object[] argsObjects = {gradeID, courseID, examTime, examType, xueqi};
		final List<SimpleScore> simpleScores = new ArrayList<SimpleScore>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				SimpleScore simpleScore = new SimpleScore();
				simpleScore.setXuehao(rs.getInt("sr_stuid"));
				simpleScore.setScore(rs.getDouble("sr_score"));
				simpleScores.add(simpleScore);
			}
		});
		return simpleScores;
	}

	@Override
	public List<ScorePart> getScoreParts(String gradeID, int xueqi,
			int stuXueHao) {
		String sqlString = "SELECT sr_courseid, c_name, sr_examtime, sr_examtype, sr_score FROM sp_score_record, sp_course WHERE sr_gradeid= ? AND sr_xueqi = ? AND sr_stuid = ? AND sp_course.c_id = sp_score_record.sr_courseid;";
		Object[] argsObjects = {gradeID, xueqi, stuXueHao};
		final List<ScorePart> scoreParts = new ArrayList<ScorePart>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ScorePart scorePart = new ScorePart();
				
				scorePart.setCourseID(rs.getString("sr_courseid"));
				scorePart.setCourseName(rs.getString("c_name"));
				scorePart.setExamTime(rs.getString("sr_examtime"));
				scorePart.setExamType(rs.getString("sr_examtype"));
				scorePart.setScore(rs.getDouble("sr_score"));
				scoreParts.add(scorePart);
			}
		});
		return scoreParts;
	}

	@Override
	public List<ScorePart> getScorePartsByXueHao(int stuXueHao) {
		String sqlString = "SELECT sr_courseid, c_name, sr_examtime, sr_examType, sr_score FROM sp_score_record, sp_course WHERE sr_stuid = ? AND sp_course.c_id = sp_score_record.sr_courseid;";
		Object[] args = {stuXueHao};
		final List<ScorePart> scoreParts = new ArrayList<ScorePart>();
		jdbcTemplate.query(sqlString, args, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ScorePart scorePart = new ScorePart();
				
				scorePart.setCourseID(rs.getString("sr_courseid"));
				scorePart.setCourseName(rs.getString("c_name"));
				scorePart.setExamTime(rs.getString("sr_examtime"));
				scorePart.setExamType(rs.getString("sr_examtype"));
				scorePart.setScore(rs.getDouble("sr_score"));
				scoreParts.add(scorePart);
			}
		});
		return scoreParts;
	}

	@Override
	public List<ScoreXueqi> getScoreXueqis(int stuXueHao, String courseID) {
		String sqlString = "SELECT sr_xueqi, sr_examtime, sr_examtype, sr_score FROM sp_score_record WHERE sr_stuid = ? AND sr_courseid = ?;";
		Object[] argsObjects = {stuXueHao, courseID};
		final List<ScoreXueqi> scoreXueqis = new ArrayList<ScoreXueqi>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ScoreXueqi scoreXueqi = new ScoreXueqi();
				
				scoreXueqi.setXueqi(rs.getInt("sr_xueqi"));
				scoreXueqi.setExamTime(rs.getString("sr_examtime"));
				scoreXueqi.setExamType(rs.getString("sr_examtype"));
				scoreXueqi.setScore(rs.getDouble("sr_score"));
				scoreXueqis.add(scoreXueqi);
			}
		});
		return scoreXueqis;
	}

	@Override
	public List<ScoreByGidCid> getScoreByGidCids(String gradeID, String courseID) {
		String sqlString = "SELECT sr_stuid, s_name, sr_examtime, sr_examtype, sr_xueqi, sr_score FROM sp_score_record, sp_student WHERE sr_gradeid = ? AND sr_courseid = ? AND sp_student.s_id = sp_score_record.sr_stuid;";
		Object[] argsObjects={gradeID, courseID};
		final List<ScoreByGidCid> scoreByGidCids = new ArrayList<ScoreByGidCid>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ScoreByGidCid scoreByGidCid = new ScoreByGidCid();
				scoreByGidCid.setStuXueHao(rs.getInt("sr_stuid"));
				scoreByGidCid.setStuName(rs.getString("s_name"));
				scoreByGidCid.setExamTime(rs.getString("sr_examtime"));
				scoreByGidCid.setExamType(rs.getString("sr_examtype"));
				scoreByGidCid.setXueqi(rs.getInt("sr_xueqi"));
				scoreByGidCid.setScore(rs.getDouble("sr_score"));
				scoreByGidCids.add(scoreByGidCid);
			}
		});
		return scoreByGidCids;
	}

	@Override
	public List<ScoreBySXT> getScoreBySXTs(int stuXueHao, int xueqi,
			String examType) {
		String sqlString = "SELECT sr_courseid, c_name, sr_examtime, sr_score FROM sp_score_record, sp_course WHERE sr_stuid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sp_score_record.sr_courseid=sp_course.c_id";
		Object[] argsObjects = {stuXueHao, xueqi, examType};
		final List<ScoreBySXT> scoreBySXTs = new ArrayList<ScoreBySXT>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ScoreBySXT scoreBySXT = new ScoreBySXT();
				scoreBySXT.setCourseID(rs.getString("sr_courseid"));
				scoreBySXT.setCourseName(rs.getString("c_name"));
				scoreBySXT.setExamTime(rs.getString("sr_examtime"));
				scoreBySXT.setScore(rs.getDouble("sr_score"));
				scoreBySXTs.add(scoreBySXT);
			}
		});
		return scoreBySXTs;
	}

	@Override
	public List<ScoreByGCXT> getscByGCXTs(String gradeID, String courseID,
			int xueqi, String examType) {
		String sqlsString = "SELECT sr_stuid, s_name, sr_examtime, sr_score FROM sp_score_record, sp_student WHERE sr_gradeid = ? AND sr_courseid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sp_score_record.sr_stuid = sp_student.s_id;";
		Object[] argObjects = {gradeID,courseID,xueqi,examType};
		final List<ScoreByGCXT> scoreByGCXTs = new ArrayList<ScoreByGCXT>();
		jdbcTemplate.query(sqlsString, argObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ScoreByGCXT scoreByGCXT = new ScoreByGCXT();
				scoreByGCXT.setStuXueHao(rs.getInt("sr_stuid"));
				scoreByGCXT.setStuName(rs.getString("s_name"));
				scoreByGCXT.setExamTime(rs.getString("sr_examtime"));
				scoreByGCXT.setScore(rs.getDouble("sr_score"));
				scoreByGCXTs.add(scoreByGCXT);
			}
		});
		return scoreByGCXTs;
	}

	@Override
	public SimpleScore getScore(int stuXueHao, int xueqi, String examType,
			String courseID) {
		String sqlString = "SELECT sr_stuid, sr_score FROM sp_score_record WHERE sr_stuid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sr_courseid = ?;";
		Object[] argsObjects = {stuXueHao, xueqi, examType, courseID};
		final SimpleScore simpleScore = new SimpleScore();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				simpleScore.setXuehao(rs.getInt("sr_stuid"));
				simpleScore.setScore(rs.getDouble("sr_score"));
			}
		});
		return simpleScore;
	}

	@Override
	public void updateScore(double newScore, int stuXueHao, int xueqi,
			String examType, String courseID) {
		String sqlString = "UPDATE sp_score_record SET sr_score = ? WHERE sr_stuid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sr_courseid = ?;";
		Object[] argsObjects = {newScore, stuXueHao, xueqi, examType, courseID};
		jdbcTemplate.update(sqlString, argsObjects);
	}

	@Override
	public List<IDNameScore> getIdNameScores(String gradeID, int xueqi,
			String examType, String courseID) {
		String sqlString = "SELECT sr_stuid,s_name,sr_score FROM sp_score_record, sp_student WHERE sr_gradeid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sr_courseid = ? AND sp_student.s_id = sp_score_record.sr_stuid;";
		Object[] argsObjects = {gradeID, xueqi, examType, courseID};
		final List<IDNameScore> idNameScores = new ArrayList<IDNameScore>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				IDNameScore idNameScore = new IDNameScore();
				idNameScore.setStuXueHao(rs.getInt("sr_stuid"));
				idNameScore.setName(rs.getString("s_name"));
				idNameScore.setScore(rs.getDouble("sr_score"));
				idNameScores.add(idNameScore);
			}
		});
		return idNameScores;
	}

	@Override
	public void updateScores(final List<Integer> stuXueHaos, final List<Double> scores,
			final String gradeID, final int xueqi, final String examType, final String courseID) {
		String sqlString = "UPDATE sp_score_record SET sr_score = ? WHERE sr_stuid = ? AND sr_gradeid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sr_courseid = ? ;";
		jdbcTemplate.batchUpdate(sqlString, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setDouble(1, scores.get(i));
				ps.setInt(2, stuXueHaos.get(i));
				ps.setString(3, gradeID);
				ps.setInt(4, xueqi);
				ps.setString(5, examType);
				ps.setString(6, courseID);
			}
			
			@Override
			public int getBatchSize() {
				return stuXueHaos.size();
			}
		});
	}

	@Override
	public void deleteStuScore(int stuXueHao, int xueqi, String examType,
			String courseID) {
		String sqlString ="DELETE FROM sp_score_record WHERE sr_stuid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sr_courseid = ? ;";
		Object[] objects = {stuXueHao, xueqi, examType, courseID};
		jdbcTemplate.update(sqlString, objects);
	}

	@Override
	public void deleteStuScores(final List<Integer> stuXueHaos, final int xueqi,
			final String examType, final String courseID) {
		String sqlString ="DELETE FROM sp_score_record WHERE sr_stuid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sr_courseid = ? ;";
		jdbcTemplate.batchUpdate(sqlString, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				ps.setInt(1, stuXueHaos.get(index));
				ps.setInt(2, xueqi);
				ps.setString(3, examType);
				ps.setString(4, courseID);
			}
			
			@Override
			public int getBatchSize() {
				return stuXueHaos.size();
			}
		});
	}

	@Override
	public List<ScoreCount> getScoreCounts(String gradeID, int xueqi,
			String examType) {
		String sqlString ="SELECT sr_stuid, s_name, sr_courseid, c_name, sr_score FROM sp_score_record, sp_student, sp_course  WHERE sr_gradeid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sp_score_record.sr_stuid = sp_student.s_id AND sp_score_record.sr_courseid = sp_course.c_id order by sr_stuid, sr_courseid;";
		Object[] args= {gradeID, xueqi, examType};
		final List<ScoreCount> scoreCounts = new ArrayList<ScoreCount>();
		jdbcTemplate.query(sqlString, args, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ScoreCount scoreCount = new ScoreCount();
				scoreCount.setStuXueHao(rs.getInt("sr_stuid"));
				scoreCount.setStuName(rs.getString("s_name"));
				scoreCount.setCourseID(rs.getString("sr_courseid"));
				scoreCount.setCourseName(rs.getString("c_name"));
				scoreCount.setScore(rs.getDouble("sr_score"));
				scoreCounts.add(scoreCount);
			}
		});
		return scoreCounts;
	}

	@Override
	public int getCount(int stuXueHao, int xueqi, String examType) {
		String sqlString ="SELECT count(*) FROM sp_score_record WHERE sr_stuid = ? AND sr_xueqi = ? AND sr_examtype = ?; ";
		Object[] argsObjects  = {stuXueHao, xueqi, examType};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int getCount(String gradeID, int xueqi, String examType, String courseID) {
		String sqlString="SELECT COUNT(*) FROM sp_score_record WHERE sr_gradeid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sr_courseid = ?; ";
		Object[] argsObjects = {gradeID, xueqi, examType, courseID};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int hasScoreRecord(int stuXueHao) {
		String sqlString = "SELECT COUNT(*) FROM sp_score_record WHERE sr_stuid = ?";
		Object[] argsObjects = {stuXueHao};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int hasScoreRecord(int stuXueHao, String courseID) {
		String sqlString = "SELECT COUNT(*) FROM sp_score_record WHERE sr_stuid = ? AND sr_courseid = ?;";
		Object[] argsObjects = {stuXueHao, courseID};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int hasScoreRecord(int stuXueHao, String examType, int xueqi) {
		String sqlString = "SELECT COUNT(*) FROM sp_score_record WHERE sr_stuid = ? AND sr_examtype = ? AND sr_xueqi = ?;";
		Object[] argsObjects = {stuXueHao, examType, xueqi};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int hasScoreRecord(String gradeID, String courseID) {
		String sqlString = "SELECT COUNT(*) FROM sp_score_record WHERE sr_gradeid = ? AND sr_courseid = ?;";
		Object[] argsObjects = {gradeID, courseID};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int hasScoreRecord(String gradeID, String courseID, int xueqi,
			String examTypeString) {
		String sqlString = "SELECT COUNT(*) FROM sp_score_record WHERE sr_gradeid = ? AND sr_courseid = ? AND sr_xueqi = ? AND sr_examtype = ?;";
		Object[] argsObjects = {gradeID, courseID, xueqi, examTypeString};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int hasScoreRecord(int stuXueHao, String courseID, int xueqi,
			String examType) {
		String sqlString = "SELECT COUNT(*) FROM sp_score_record WHERE sr_stuid = ? AND sr_courseid = ? AND sr_xueqi = ? AND sr_examtype = ?;";
		Object[] argsObjects = {stuXueHao, courseID, xueqi, examType};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int isExisits(String gradeID, int xueqi, String examType) {
		String sqlString = "SELECT COUNT(*) FROM sp_score_record WHERE sr_gradeid = ? AND sr_xueqi = ? AND sr_examtype = ?;";
		Object[] argsObjects = {gradeID, xueqi, examType};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public List<StuScoreCount> getStuScoreCounts(String gradeID, int xueqi,
			String examType) {
		String sqlString = "SELECT sr_stuid ,s_name,SUM(sr_score) AS sumtotal  FROM sp_score_record, sp_student WHERE sr_gradeid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sp_score_record.sr_stuid = sp_student.s_id GROUP BY sr_stuid ORDER BY sumtotal DESC;";
		Object[] argObjects = {gradeID, xueqi, examType};
		final List<StuScoreCount> stuScoreCounts = new ArrayList<StuScoreCount>();
	    jdbcTemplate.query(sqlString, argObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				StuScoreCount stuScoreCount = new StuScoreCount();
				stuScoreCount.setStuXueHao(rs.getInt("sr_stuid"));
				stuScoreCount.setStuNameString(rs.getString("s_name"));
				stuScoreCount.setScoreSum(rs.getDouble(3));
				stuScoreCounts.add(stuScoreCount);
			}
		});
		
		return stuScoreCounts;
	}

	@Override
	public List<ScoreGroup> getScoreGroups(final String gradeID, final int xueqi, final String examType) {
		String sqlStr="SELECT sr_courseid, c_name, max(sr_score), min(sr_score), avg(sr_score) FROM sp_score_record, sp_course WHERE "
				+ "sr_gradeid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sp_score_record.sr_courseid=sp_course.c_id GROUP BY sr_courseid ORDER BY sr_courseid";
		Object[] args = {gradeID, xueqi, examType};
		final List<ScoreGroup> scoreGroups = new ArrayList<ScoreGroup>();
		jdbcTemplate.query(sqlStr, args, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ScoreGroup scoreGroup = new ScoreGroup();
				scoreGroup.setCourseId(rs.getString("sr_courseid"));
				scoreGroup.setCourseName(rs.getString("c_name"));
				scoreGroup.setMaxScore(rs.getInt(3));
				scoreGroup.setMinScore(rs.getInt(4));
				scoreGroup.setAvgScore(rs.getInt(5));
				String sqlStr2 = "SELECT COUNT(*) FROM sp_score_record WHERE sr_gradeid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sr_courseid = ? AND sr_score >= 60;";
				Object[] args2 = {gradeID, xueqi, examType, rs.getString("sr_courseid")};
				scoreGroup.setGoodCount(jdbcTemplate.queryForInt(sqlStr2, args2));
				String sqlStr3 = "SELECT COUNT(*) FROM sp_score_record WHERE sr_gradeid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sr_courseid = ? AND sr_score < 60;";
				Object[] args3 = {gradeID, xueqi, examType, rs.getString("sr_courseid")};
				scoreGroup.setBadCount(jdbcTemplate.queryForInt(sqlStr3, args3));
				scoreGroups.add(scoreGroup);
			}});
		return scoreGroups;
	}
}
