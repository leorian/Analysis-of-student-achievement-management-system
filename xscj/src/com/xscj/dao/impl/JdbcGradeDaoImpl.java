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

import com.xscj.dao.GradeDao;
import com.xscj.domain.Grade;
import com.xscj.domain.GradeInfo;
@Repository
public class JdbcGradeDaoImpl implements GradeDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int getGradeCount() {
		String sqlStr = "SELECT count(*) FROM sp_classes;";
		return jdbcTemplate.queryForInt(sqlStr);
	}

	@Override
	public List<Grade> getAllGrades() {
		String sqlStr = "SELECT * FROM sp_classes;";
		final List<Grade> grades = new ArrayList<Grade>();
		jdbcTemplate.query(sqlStr, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Grade grade = new Grade();
				grade.setBianHao(rs.getString("cs_id"));
				grade.setYear(rs.getInt("cs_date"));
				grade.setClassID(rs.getInt("cs_class"));
				grade.setAdviser(rs.getString("cs_adviser"));
				grades.add(grade);
			}});
		return grades;
	}

	@Override
	public int getMaxYear() {
		String sqlStr = "SELECT max(cs_date) FROM sp_classes WHERE delflag = false";
		return jdbcTemplate.queryForInt(sqlStr);
	}

	@Override
	public int getValidGradeCount(int minYear, int maxYear) {
		String sqlStr = "SELECT count(*) FROM sp_classes WHERE delflag = false AND cs_date between ? and ?;";
		Object[] args = {minYear, maxYear};
		return jdbcTemplate.queryForInt(sqlStr,args);
	}

	@Override
	public List<Grade> getValidAllGrades(int minYear, int maxYear) {
		String sqlStr = "SELECT * FROM sp_classes WHERE delflag = false AND cs_date between ? and ?;"; 
		Object[] args = {minYear, maxYear};
		final List<Grade> grades = new ArrayList<Grade>();
		jdbcTemplate.query(sqlStr, args, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Grade grade = new Grade();
				grade.setBianHao(rs.getString("cs_id"));
				grade.setYear(rs.getInt("cs_date"));
				grade.setClassID(rs.getInt("cs_class"));
				grade.setAdviser(rs.getString("cs_adviser"));
				grades.add(grade);
			}});
		return grades;
	}

	@Override
	public int getClassesCount(int maxYear) {
		String sqlString = "SELECT count(*) FROM sp_classes WHERE delflag = false AND cs_date = ?";
		Object[] args = {maxYear}; 
		return jdbcTemplate.queryForInt(sqlString, args);
	}

	@Override
	public int AddGradeToDb(Grade grade) {
		String sqlString = "INSERT INTO sp_classes VALUES(?, ?, ?, ?,false);";
		Object[] args = {grade.getBianHao(), grade.getYear(), grade.getClassID(), grade.getAdviser()};
		
		return jdbcTemplate.update(sqlString, args);
	}

	@Override
	public List<Grade> getMaxYearGrades(int maxYear) {
		String sqlStr = "SELECT * FROM sp_classes WHERE delflag = false AND cs_date = ?;"; 
		Object[] args = { maxYear};
		final List<Grade> grades = new ArrayList<Grade>();
		jdbcTemplate.query(sqlStr, args, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Grade grade = new Grade();
				grade.setBianHao(rs.getString("cs_id"));
				grade.setYear(rs.getInt("cs_date"));
				grade.setClassID(rs.getInt("cs_class"));
				grade.setAdviser(rs.getString("cs_adviser"));
				grades.add(grade);
			}});
		return grades;
	}

	@Override
	public Grade getGradeBybianHao(String bianHao) {
		String sqlString = "SELECT * FROM sp_classes WHERE delflag = false AND cs_id = ?;";
		Object[] argsObjects = {bianHao};
		final Grade grade = new Grade();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				grade.setBianHao(rs.getString("cs_id"));
				grade.setYear(rs.getInt("cs_date"));
				grade.setClassID(rs.getInt("cs_class"));
				grade.setAdviser(rs.getString("cs_adviser"));
			}});
		return grade;
	}

	@Override
	public void deleteChoiceGrades(final String[] gradeStrings) {
		
		String sqlStringPrefix="DELETE FROM sp_score_record WHERE sr_gradeid = ?;";//删除关联班级的所有考试成绩记录
		jdbcTemplate.batchUpdate(sqlStringPrefix, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				String gradeString = gradeStrings[index];
				ps.setString(1, gradeString);
			}
			
			@Override
			public int getBatchSize() {
				return gradeStrings.length;
			}
		});
		
		String sqlStringPrefix2 = "DELETE FROM sp_ctc WHERE ctc_classid = ?;";
jdbcTemplate.batchUpdate(sqlStringPrefix2, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				String gradeString = gradeStrings[index];
				ps.setString(1, gradeString);
			}
			
			@Override
			public int getBatchSize() {
				return gradeStrings.length;
			}
		});
		
		String sqlString = "DELETE FROM sp_classes WHERE cs_id = ?;";
		
		jdbcTemplate.batchUpdate(sqlString, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				String gradeString = gradeStrings[index];
				ps.setString(1, gradeString);
			}
			
			@Override
			public int getBatchSize() {
				return gradeStrings.length;
			}
		});
		
	}

	@Override
	public Grade getGradeBystuXueHao(int stuXueHao) {
		String sqlString = "SELECT * FROM sp_classes WHERE cs_id = (SELECT s_class FROM sp_student WHERE s_id = ?)";
		Object[] argsObjects = {stuXueHao};
		final Grade grade = new Grade();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				grade.setBianHao(rs.getString("cs_id"));
				grade.setYear(rs.getInt("cs_date"));
				grade.setClassID(rs.getInt("cs_class"));
				grade.setAdviser(rs.getString("cs_adviser"));
				
			}
		});
		return grade;
	}

	@Override
	public List<Grade> getALLValidGrades() {
		String sqlStr = "SELECT * FROM sp_classes WHERE delflag = false;";
		final List<Grade> grades = new ArrayList<Grade>();
		jdbcTemplate.query(sqlStr, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Grade grade = new Grade();
				grade.setBianHao(rs.getString("cs_id"));
				grade.setYear(rs.getInt("cs_date"));
				grade.setClassID(rs.getInt("cs_class"));
				grade.setAdviser(rs.getString("cs_adviser"));
				grades.add(grade);
			}});
		return grades;
	}

	@Override
	public List<GradeInfo> getGradeInfos() {
		String sqlString = "SELECT cs_id, cs_date, cs_class, cs_adviser, t_name, COUNT(s_class) FROM sp_classes, sp_student,sp_teacher WHERE sp_classes.cs_id = sp_student.s_class AND sp_classes.cs_adviser = sp_teacher.t_id GROUP BY cs_id ORDER BY cs_id; ";
		final List<GradeInfo> gradeInfos = new ArrayList<GradeInfo>();
		jdbcTemplate.query(sqlString, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				GradeInfo gradeInfo = new GradeInfo();
				gradeInfo.setGradeID(rs.getString("cs_id"));
				gradeInfo.setYear(rs.getInt("cs_date"));
				gradeInfo.setClassID(rs.getInt("cs_class"));
				gradeInfo.setAdviserID(rs.getString("cs_adviser"));
				gradeInfo.setAdviserName(rs.getString("t_name"));
				gradeInfo.setStuCount(rs.getInt(6));
				gradeInfos.add(gradeInfo);
			}
		});
		String sqlString2 = "SELECT cs_id, cs_date, cs_class, cs_adviser, t_name FROM sp_classes, sp_teacher WHERE sp_classes.cs_adviser = sp_teacher.t_id AND cs_id NOT IN (SELECT DISTINCT s_class FROM sp_student);";
		jdbcTemplate.query(sqlString2, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				GradeInfo gradeInfo = new GradeInfo();
				gradeInfo.setGradeID(rs.getString("cs_id"));
				gradeInfo.setYear(rs.getInt("cs_date"));
				gradeInfo.setClassID(rs.getInt("cs_class"));
				gradeInfo.setAdviserID(rs.getString("cs_adviser"));
				gradeInfo.setAdviserName(rs.getString("t_name"));
				gradeInfo.setStuCount(0);
				gradeInfos.add(gradeInfo);
			}
		});
		return gradeInfos;
	}

	@Override
	public List<Grade> getGradesEnableDel() {
		String sqlStr = "SELECT * FROM sp_classes WHERE cs_id NOT IN(SELECT DISTINCT s_class FROM sp_student);";
		final List<Grade> grades = new ArrayList<Grade>();
		jdbcTemplate.query(sqlStr, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Grade grade = new Grade();
				grade.setBianHao(rs.getString("cs_id"));
				grade.setYear(rs.getInt("cs_date"));
				grade.setClassID(rs.getInt("cs_class"));
				grade.setAdviser(rs.getString("cs_adviser"));
				grades.add(grade);
			}});
		return grades;
	}

	@Override
	public List<Integer> getYears() {
		String sqlStr = "SELECT DISTINCT cs_date FROM sp_classes;";
		final List<Integer> years = new ArrayList<Integer>();
		jdbcTemplate.query(sqlStr, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				years.add(rs.getInt("cs_date"));
			}});
		return years;
	}

	@Override
	public List<GradeInfo> getGradeInfos(int year) {
		String sqlString = "SELECT cs_id, cs_date, cs_class, cs_adviser, t_name, COUNT(s_class) FROM sp_classes, sp_student,sp_teacher WHERE cs_date = ? AND sp_classes.cs_id = sp_student.s_class AND sp_classes.cs_adviser = sp_teacher.t_id GROUP BY cs_id ORDER BY cs_id; ";
		Object[] args = {year};
		final List<GradeInfo> gradeInfos = new ArrayList<GradeInfo>();
		jdbcTemplate.query(sqlString, args, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				GradeInfo gradeInfo = new GradeInfo();
				gradeInfo.setGradeID(rs.getString("cs_id"));
				gradeInfo.setYear(rs.getInt("cs_date"));
				gradeInfo.setClassID(rs.getInt("cs_class"));
				gradeInfo.setAdviserID(rs.getString("cs_adviser"));
				gradeInfo.setAdviserName(rs.getString("t_name"));
				gradeInfo.setStuCount(rs.getInt(6));
				gradeInfos.add(gradeInfo);
			}
		});
		String sqlString2 = "SELECT cs_id, cs_date, cs_class, cs_adviser, t_name FROM sp_classes, sp_teacher WHERE cs_date = ? AND sp_classes.cs_adviser = sp_teacher.t_id AND cs_id NOT IN (SELECT DISTINCT s_class FROM sp_student);";
		Object[] args2 = {year};
		jdbcTemplate.query(sqlString2, args2, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				GradeInfo gradeInfo = new GradeInfo();
				gradeInfo.setGradeID(rs.getString("cs_id"));
				gradeInfo.setYear(rs.getInt("cs_date"));
				gradeInfo.setClassID(rs.getInt("cs_class"));
				gradeInfo.setAdviserID(rs.getString("cs_adviser"));
				gradeInfo.setAdviserName(rs.getString("t_name"));
				gradeInfo.setStuCount(0);
				gradeInfos.add(gradeInfo);
			}
		});
		return gradeInfos;
	}

	@Override
	public int hasRecord(String teacherID) {
		String sqlStr = "SELECT COUNT(*) FROM sp_classes WHERE cs_adviser = ?;";
		Object[] args= {teacherID};
		return jdbcTemplate.queryForInt(sqlStr, args);
	}
}
