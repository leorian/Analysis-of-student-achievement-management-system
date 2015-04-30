package com.xscj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.xscj.dao.TeacherDao;
import com.xscj.domain.SubTeacher;
import com.xscj.domain.Teacher;

@Repository
public class JdbcTeacherDaoImpl implements TeacherDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int getTeacherCount() {
		
			String sqlStr= "SELECT count(*) FROM sp_teacher;";
			
		return jdbcTemplate.queryForInt(sqlStr);
	}

	@Override
	public int addTeacherToDb(Teacher teacher) {
		String sqlStr = "INSERT INTO sp_teacher(t_id, t_name, t_pass, t_sex, t_nation, " +
				"t_pol_stat, t_idcard, t_address, t_job_time, t_edu_bg, t_gra_ins, t_phone, t_course) VALUES(?,?,?,?,?,?," +
				"?,?,?,?,?,?,?);";
		Object[] args = {teacher.getBianHao(), teacher.getName(), teacher.getPassword(), teacher.getSex(), 
				teacher.getNation(), teacher.getPolStat(), teacher.getIdCard(), teacher.getAddress(), 
				teacher.getJobTime(), teacher.getEduBg(), teacher.getGraIns(), teacher.getPhone(), teacher.getCourseID()};
		return jdbcTemplate.update(sqlStr, args);
	}

	@Override
	public List<Teacher> queryByPage(int pageSize, int pageNow) {
		//String sqlStr = "SELECT * FROM sp_teacher WHERE delflag = false order by t_id limit ?, ?"; 
		String sqlStr = "SELECT t_id, t_name, t_pass, t_sex, t_nation, t_pol_stat, t_idcard, t_address, t_job_time, t_edu_bg, t_gra_ins, t_phone, t_course, c_name"
				+ " FROM sp_teacher, sp_course WHERE sp_teacher.t_course = sp_course.c_id order by t_id limit ?, ?";
		final List<Teacher> teachers = new ArrayList<Teacher>();
		Object[] args = {pageSize*pageNow-pageSize, pageSize};
		jdbcTemplate.query(sqlStr, args, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Teacher teacher = new Teacher();
				teacher.setBianHao(rs.getString("t_id"));
				teacher.setName(rs.getString("t_name"));
				teacher.setPassword(rs.getString("t_pass"));
				teacher.setSex(rs.getString("t_sex"));
				teacher.setNation(rs.getString("t_nation"));
				teacher.setPolStat(rs.getString("t_pol_stat"));
				teacher.setIdCard(rs.getString("t_idcard"));
				teacher.setAddress(rs.getString("t_address"));
				teacher.setJobTime(rs.getString("t_job_time"));
				teacher.setEduBg(rs.getString("t_edu_bg"));
				teacher.setGraIns(rs.getString("t_gra_ins"));
				teacher.setPhone(rs.getString("t_phone"));
				teacher.setCourseID(rs.getString("t_course"));
				teacher.setCourseName(rs.getString("c_name"));
				teachers.add(teacher);
			}});
		return teachers;
	}

	@Override
	public List<String> getAllTeacherBh() {
		String sqlString = "SELECT t_id FROM sp_teacher WHERE delflag = false;";
		final List<String> bianHaoList = new ArrayList<String>();
		jdbcTemplate.query(sqlString, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String string = new String();
				string = rs.getString("t_id");
				bianHaoList.add(string);
			}});
		return bianHaoList;
	}

	@Override
	public List<SubTeacher> getAllSubTeachers() {
		String sqlStr = "SELECT t_id, t_name FROM sp_teacher WHERE delflag = false"; 
		final List<SubTeacher> subTeachers = new ArrayList<SubTeacher>();
		jdbcTemplate.query(sqlStr,  new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				SubTeacher subTeacher = new SubTeacher();
				subTeacher.setBianHao(rs.getString("t_id"));
				subTeacher.setName(rs.getString("t_name"));
				subTeachers.add(subTeacher);
			}});
		return subTeachers;
	}

	@Override
	public List<SubTeacher> getAllSubTeachersByCourseID(String courseID) {
		String sqlStr = "SELECT t_id, t_name FROM sp_teacher WHERE t_course = ? AND delflag = false;";
		Object[] args = {courseID};
		final List<SubTeacher> subTeachers = new ArrayList<SubTeacher>();
		jdbcTemplate.query(sqlStr, args,  new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				SubTeacher subTeacher = new SubTeacher();
				subTeacher.setBianHao(rs.getString("t_id"));
				subTeacher.setName(rs.getString("t_name"));
				subTeachers.add(subTeacher);
			}});
		return subTeachers;
	}

	@Override
	public int getValidTeacherCount() {
		String sqlStr= "SELECT count(*) FROM sp_teacher WHERE delflag = false;";
		
		return jdbcTemplate.queryForInt(sqlStr);
	}

	@Override
	public void deleteTeacherByBianHao(String bianHao) {
		String sqlString = "DELETE FROM sp_teacher WHERE t_id = ? ;";
		Object[] argsObjects = {bianHao};
		jdbcTemplate.update(sqlString, argsObjects);
	}

	@Override
	public String getTeacherNameByTeacherID(String teacherBianHao) {
		String sqlString = "SELECT t_name FROM sp_teacher WHERE t_id = ? ;";
		Object[] argsObjects = {teacherBianHao};
		String teacherNameString = jdbcTemplate.queryForObject(sqlString, argsObjects, java.lang.String.class);
		return teacherNameString;
	}

	@Override
	public Teacher getTeacherInfo(String teacherBianHao) {
		//String sqlString = "SELECT * FROM sp_teacher WHERE t_id = ?";
		String sqlString = "SELECT t_id, t_name, t_pass, t_sex, t_nation, t_pol_stat, t_idcard, t_address, t_job_time, t_edu_bg, t_gra_ins, t_phone, t_course, c_name"
				+ " FROM sp_teacher, sp_course WHERE sp_teacher.t_course = sp_course.c_id AND t_id = ?";
		Object[] argsObjects = {teacherBianHao};
		final Teacher teacher = new Teacher();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				teacher.setBianHao(rs.getString("t_id"));
				teacher.setName(rs.getString("t_name"));
				teacher.setPassword(rs.getString("t_pass"));
				teacher.setSex(rs.getString("t_sex"));
				teacher.setNation(rs.getString("t_nation"));
				teacher.setPolStat(rs.getString("t_pol_stat"));
				teacher.setIdCard(rs.getString("t_idcard"));
				teacher.setAddress(rs.getString("t_address"));
				teacher.setJobTime(rs.getString("t_job_time"));
				teacher.setEduBg(rs.getString("t_edu_bg"));
				teacher.setGraIns(rs.getString("t_gra_ins"));
				teacher.setPhone(rs.getString("t_phone"));
				teacher.setCourseID(rs.getString("t_course"));
				teacher.setCourseName(rs.getString("c_name"));
			}
		});
		return teacher;
	}

	@Override
	public void updateTeacherInfo(Teacher teacher) {
		String sqlString = "UPDATE sp_teacher SET t_name = ?, t_pass = ?," +
				" t_sex = ?, t_nation = ?, t_pol_stat = ?, t_idcard = ?," +
				" t_address = ?, t_job_time = ?, t_edu_bg = ?, t_gra_ins = ?," +
				" t_phone = ?, t_course = ? WHERE t_id = ?";
		Object[] argsObjects = {
				teacher.getName(), teacher.getPassword(), teacher.getSex(),
				teacher.getNation(), teacher.getPolStat(), teacher.getIdCard(), teacher.getAddress(),
				teacher.getJobTime(),teacher.getEduBg(),teacher.getGraIns(),teacher.getPhone(),teacher.getCourseID(),teacher.getBianHao()};
				jdbcTemplate.update(sqlString, argsObjects);
		}

	@Override
	public int isExisit(String teacherBianHao) {
		String sqlString = "SELECT COUNT(*) FROM sp_teacher WHERE t_id = ?;";
		Object[] argsObjects = {teacherBianHao};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int isExisits(String gradeID, String password) {
		String sqlString = "SELECT COUNT(*) FROM sp_teacher WHERE t_id = ? AND t_pass = ? ;";
		Object[] argsObjects = {gradeID, password};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public void updatePass(String bianHao, String newPass) {
		String sqlString = "UPDATE sp_teacher SET t_pass = ? WHERE t_id = ?;";
		Object[] argsObjects = {newPass, bianHao};
		jdbcTemplate.update(sqlString, argsObjects);
	}
	}

