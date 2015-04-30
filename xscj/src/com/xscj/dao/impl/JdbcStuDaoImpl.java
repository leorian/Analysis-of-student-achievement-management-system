package com.xscj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.xscj.dao.StuDao;
import com.xscj.domain.SimpleStudent;
import com.xscj.domain.Student;
import com.xscj.domain.SubStudent;

@Repository
public class JdbcStuDaoImpl implements StuDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int getStuCount() {
		String sqlStr = "SELECT count(*) From sp_student;"; 
		return jdbcTemplate.queryForInt(sqlStr);
	}

	@Override
	public int addStuToDb(Student student) {
		String sqlStr = "INSERT INTO sp_student(s_name, s_pass, s_sex, s_idcard, s_address, " +
				"s_nation, s_pol_stat, s_school_time, s_household, s_schoolmethod,s_class) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		Object[] args = {student.getName(), student.getPassword(), student.getSex(), student.getIdCard(),
				student.getAddress(), student.getNation(), student.getPolStat(), student.getSchoolTime(),
				student.getHouseHold(), student.getSchoolMethod(), student.getClassID()};
		return jdbcTemplate.update(sqlStr, args);
	}

	@Override
	public int addStuToDb(int id, Student student) {
		String sqlStr = "INSERT INTO sp_student(s_id, s_name, s_pass, s_sex, s_idcard, s_address, " +
				"s_nation, s_pol_stat, s_school_time, s_household, s_schoolmethod, s_class) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		Object[] args = {id, student.getName(), student.getPassword(), student.getSex(), student.getIdCard(),
				student.getAddress(), student.getNation(), student.getPolStat(), student.getSchoolTime(),
				student.getHouseHold(), student.getSchoolMethod(), student.getClassID()};
		return jdbcTemplate.update(sqlStr, args);
	}

	@Override
	public List<SimpleStudent> queryByPage(int pageSize, int pageNow) {
		//String sqlStr =  "select *  from sp_student WHERE delflag = false order by s_id limit ?, ?;";
		String sqlStr = "select s_id, s_name, s_sex, s_idcard, s_address, s_nation, s_pol_stat, s_school_time, "
				+ "s_household, s_schoolmethod, s_class, cs_date, cs_class"
				+ "  from sp_student, sp_classes WHERE sp_student.s_class = sp_classes.cs_id order by s_id limit ?, ?;";
		final List<SimpleStudent> students = new ArrayList<SimpleStudent>();
		Object[] args = {pageNow*pageSize-pageSize, pageSize};
		jdbcTemplate.query(sqlStr, args, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				 SimpleStudent student = new SimpleStudent();
				 student.setXuehao(rs.getInt("s_id"));
				 student.setName(rs.getString("s_name"));
				 student.setSex(rs.getString("s_sex"));
				 student.setIdCard(rs.getString("s_idcard"));
				 student.setAddress(rs.getString("s_address"));
				 student.setNation(rs.getString("s_nation"));
				 student.setPolStat(rs.getString("s_pol_stat"));
				 student.setSchoolTime(rs.getString("s_school_time"));
				 student.setHouseHold(rs.getString("s_household"));
				 student.setSchoolMethod(rs.getString("s_schoolmethod"));
				 student.setClassID(rs.getString("s_class"));
				 student.setClassName(rs.getInt("cs_date")+"届（"+rs.getInt("cs_class")+"）班");
				students.add(student);
			}});
		return students;
	}

	@Override
	public int getValidStuCount() {
		String sqlStr = "SELECT count(*) From sp_student WHERE delflag = false;"; 
		return jdbcTemplate.queryForInt(sqlStr);
	}

	@Override
	public void deleteStuByXueHao(int xuehao) {
		Object[] args = {xuehao};
		String sqlStringPrefix = "DELETE FROM sp_score_record WHERE sr_stuid = ?;";
		jdbcTemplate.update(sqlStringPrefix, args);//删除某个学生的所有成绩记录
		String sqlsString = "DELETE FROM sp_student WHERE s_id = ? ;";
		jdbcTemplate.update(sqlsString, args);
	}

	@Override
	public List<SubStudent> getAllSubStudentsByGradeID(String gradeID) {
		String sqlString = "SELECT s_id, s_name FROM sp_student WHERE s_class = ? AND delflag = false;";
		Object[] argsObjects = {gradeID};
		final List<SubStudent> subStudents = new ArrayList<SubStudent>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				SubStudent subStudent = new SubStudent();
				subStudent.setXuehao(rs.getInt("s_id"));
				subStudent.setStuName(rs.getString("s_name"));
				subStudents.add(subStudent);
			}
		});
		return subStudents;
	}

	@Override
	public SubStudent getSubStudentByXueHao(int stuXueHao) {
		String sqlString = "SELECT s_id, s_name FROM sp_student WHERE s_id = ?;";
		Object[] argsObjects = {stuXueHao};
		final SubStudent subStudent = new SubStudent();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				subStudent.setXuehao(rs.getInt("s_id"));
				subStudent.setStuName(rs.getString("s_name"));
			}
		});
		return subStudent;
		
	}

	@Override
	public List<SubStudent> getAllSubStudentsByGXTC(String gradeID, int xueqi,
			String examType, String courseID) {
		String sqlString = "SELECT s_id, s_name FROM sp_student WHERE s_class = ? AND delflag = false AND s_id NOT IN (SELECT sr_stuid FROM sp_score_record WHERE sr_gradeid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sr_courseid = ?);";
		Object[] argsObjects = {gradeID,gradeID,xueqi,examType,courseID};
		final List<SubStudent> subStudents = new ArrayList<SubStudent>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				SubStudent subStudent = new SubStudent();
				subStudent.setXuehao(rs.getInt("s_id"));
				subStudent.setStuName(rs.getString("s_name"));
				subStudents.add(subStudent);
			}
		});
		return subStudents;
	}

	@Override
	public Student getStudent(int xuehao) {
		String sql = "SELECT * FROM sp_student WHERE s_id = ?;";
		Object[] argsObjects = {xuehao};
		final Student student = new Student();
		jdbcTemplate.query(sql, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				student.setPassword(rs.getString("s_pass"));
				 student.setName(rs.getString("s_name"));
				 student.setSex(rs.getString("s_sex"));
				 student.setIdCard(rs.getString("s_idcard"));
				 student.setAddress(rs.getString("s_address"));
				 student.setNation(rs.getString("s_nation"));
				 student.setPolStat(rs.getString("s_pol_stat"));
				 student.setSchoolTime(rs.getString("s_school_time"));
				 student.setHouseHold(rs.getString("s_household"));
				 student.setSchoolMethod(rs.getString("s_schoolmethod"));
				 student.setClassID(rs.getString("s_class"));
				
			}
		});
		return student;
	}

	@Override
	public void updateStuInfo(int xuehao, Student student) {
		String sqlString ="UPDATE sp_student SET s_name = ?, s_pass = ?, s_sex = ?," +
				" s_idcard = ?, s_address = ?, s_nation = ?, s_pol_stat = ?, s_school_time = ?," +
				" s_household = ?, s_schoolmethod = ?, s_class = ? WHERE s_id = ?; ";
		Object[] argsObjects = {student.getName(), student.getPassword(), student.getSex(),
				student.getIdCard(), student.getAddress(), student.getNation(), student.getPolStat(), student.getSchoolTime(), student.getHouseHold(), student.getSchoolMethod(), student.getClassID(), xuehao};
		jdbcTemplate.update(sqlString, argsObjects);
		
	}

	@Override
	public SimpleStudent querySimpleStudent(int stuXueHao) {
		//String sqlStr =  "select *  from sp_student WHERE s_id = ?;";
		String sqlStr = "select s_id, s_name, s_sex, s_idcard, s_address, s_nation, s_pol_stat, s_school_time, "
				+ "s_household, s_schoolmethod, s_class, cs_date, cs_class"
				+ "  from sp_student, sp_classes WHERE sp_student.s_class = sp_classes.cs_id AND s_id = ?;";
		 final SimpleStudent student = new SimpleStudent();
		Object[] args = {stuXueHao};
		jdbcTemplate.query(sqlStr, args, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				 student.setXuehao(rs.getInt("s_id"));
				 student.setName(rs.getString("s_name"));
				 student.setSex(rs.getString("s_sex"));
				 student.setIdCard(rs.getString("s_idcard"));
				 student.setAddress(rs.getString("s_address"));
				 student.setNation(rs.getString("s_nation"));
				 student.setPolStat(rs.getString("s_pol_stat"));
				 student.setSchoolTime(rs.getString("s_school_time"));
				 student.setHouseHold(rs.getString("s_household"));
				 student.setSchoolMethod(rs.getString("s_schoolmethod"));
				 student.setClassID(rs.getString("s_class"));
				 student.setClassName(rs.getInt("cs_date")+"届（"+rs.getInt("cs_class")+"）班");
			}});
		return student;
	}

	@Override
	public List<SimpleStudent> querySimpleStudents(String gradeID) {
		//String sqlStr =  "select *  from sp_student WHERE s_class = ?;";
		String sqlStr = "select s_id, s_name, s_sex, s_idcard, s_address, s_nation, s_pol_stat, s_school_time, "
				+ "s_household, s_schoolmethod, s_class, cs_date, cs_class"
				+ "  from sp_student, sp_classes WHERE sp_student.s_class = sp_classes.cs_id AND s_class = ?;";
		final List<SimpleStudent> students = new ArrayList<SimpleStudent>();
		Object[] args = {gradeID};
		jdbcTemplate.query(sqlStr, args, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				 SimpleStudent student = new SimpleStudent();
				 student.setXuehao(rs.getInt("s_id"));
				 student.setName(rs.getString("s_name"));
				 student.setSex(rs.getString("s_sex"));
				 student.setIdCard(rs.getString("s_idcard"));
				 student.setAddress(rs.getString("s_address"));
				 student.setNation(rs.getString("s_nation"));
				 student.setPolStat(rs.getString("s_pol_stat"));
				 student.setSchoolTime(rs.getString("s_school_time"));
				 student.setHouseHold(rs.getString("s_household"));
				 student.setSchoolMethod(rs.getString("s_schoolmethod"));
				 student.setClassID(rs.getString("s_class"));
				 student.setClassName(rs.getInt("cs_date")+"届（"+rs.getInt("cs_class")+"）班");
				students.add(student);
			}});
		return students;
	}

	@Override
	public int isExisits(int stuXueHao) {
		String sqlString = "SELECT COUNT(*) FROM sp_student WHERE s_id = ?;";
		Object[] argsObjects = {stuXueHao};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int isExisits(String gradeID) {
		String sqlsString = "SELECT COUNT(*) FROM sp_student WHERE s_class = ?;";
		Object[] argsObjects = {gradeID};
		return jdbcTemplate.queryForInt(sqlsString, argsObjects);
	}

	@Override
	public int isExisits(int stuXueHao, String password) {
		String sqlString = "SELECT COUNT(*) FROM sp_student WHERE s_id = ? AND s_pass = ?;";
		Object[] argsObjects = {stuXueHao, password};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public void updatePass(int stuXueHao, String password) {
		String sqlString ="UPDATE sp_student SET s_pass = ? WHERE s_id = ?";
		Object[] argsObjects = {password, stuXueHao};
		jdbcTemplate.update(sqlString, argsObjects);
		
	}
}
