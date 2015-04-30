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

import com.xscj.dao.TeachingArrangeDao;
import com.xscj.domain.Course;
import com.xscj.domain.Teacher;
import com.xscj.domain.Teaching;
import com.xscj.domain.TeachingDetail;
import com.xscj.domain.TeachingGidXq;
import com.xscj.domain.TeachingTable;

/**
 * @author leorain
 *
 */
@Repository
public class JdbcTeachingArrangeDaoImpl implements TeachingArrangeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addTeachingArrange(final List<Teaching> teachings) {
		final String sqlStr = "INSERT INTO sp_ctc VALUES(?,?,?,?);";
		
		jdbcTemplate.batchUpdate(sqlStr, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				Teaching teaching = teachings.get(index);
				ps.setString(1, teaching.getClassID());
				ps.setString(2, teaching.getTeacherID());
				ps.setString(3, teaching.getCourseID());
				ps.setInt(4, teaching.getXueqi());		
			}
			
			@Override
			public int getBatchSize() {
				return teachings.size();
			}
		});
		
	}

	@Override
	public List<String> getCourseNameByTeacherID(String teacherBianHao) {
		String sqlString = "SELECT c_name FROM sp_course WHERE c_id IN (SELECT DISTINCT ctc_courseid FROM sp_ctc WHERE ctc_teacherid = ?); ";		
		Object[] args = {teacherBianHao};
		final List<String> courseNameStrings = new ArrayList<String>();
		jdbcTemplate.query(sqlString, args, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String courseNameString = rs.getString("c_name");
				courseNameStrings.add(courseNameString);
			}
		});
		return courseNameStrings;
	}

	@Override
	public List<Teaching> getTeachingsByTeacherID(String teacherBianHao) {
		String sqlsString = "SELECT * FROM sp_ctc WHERE ctc_teacherid = ? ;";
		Object[] argsObjects = {teacherBianHao};
		final List<Teaching> teachings = new ArrayList<Teaching>();
		jdbcTemplate.query(sqlsString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Teaching teaching = new Teaching();
				teaching.setClassID(rs.getString("ctc_classid"));
				teaching.setCourseID(rs.getString("ctc_courseid"));
				teaching.setTeacherID(rs.getString("ctc_teacherid"));
				teaching.setXueqi(rs.getInt("ctc_xueqi"));
				teachings.add(teaching);
			}
		});
		return teachings;
	}

	@Override
	public String getTeacherNameByTeaching(String gradeID, int xueqi,
			String courseID) {
		String sqlString = "SELECT * FROM sp_teacher WHERE t_id = (SELECT ctc_teacherid FROM sp_ctc WHERE ctc_classid = ? AND ctc_courseid = ? AND ctc_xueqi = ?);";
		Object[] argsObjects = {gradeID, courseID, xueqi};
		final Teacher teacher = new Teacher();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				teacher.setName(rs.getString("t_name"));
			}
		});
		return teacher.getName(); 
	}

	@Override
	public List<Course> getCourses(String gradeID, int xueqi, String examType, int stuXueHao) {
		String sqlString = "SELECT * FROM sp_course WHERE c_id IN (SELECT ctc_courseid FROM sp_ctc WHERE ctc_classid = ? AND ctc_xueqi = ?) AND c_id NOT IN (SELECT sr_courseid FROM sp_score_record WHERE sr_gradeid = ? AND sr_xueqi = ? AND sr_examtype = ? AND sr_stuid = ?);";
		Object[] argsObjects = {gradeID, xueqi, gradeID, xueqi, examType, stuXueHao};
		final List<Course> courses = new ArrayList<Course>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Course course = new Course();
				course.setBianHao(rs.getString("c_id"));
				course.setName(rs.getString("c_name"));
				courses.add(course);
			}
		});
		return courses;
	}

	@Override
	public List<TeachingDetail> getTeachingDetails(String teacherBianHao) {
		String sqlString = "SELECT ctc_classid, cs_date, cs_class, ctc_courseid, c_name, ctc_xueqi, ctc_teacherid FROM sp_ctc, sp_classes, sp_course WHERE ctc_teacherid = ? AND sp_ctc.ctc_classid = sp_classes.cs_id AND sp_ctc.ctc_courseid = sp_course.c_id;";
		Object[] argsObjects = {teacherBianHao};
		final List<TeachingDetail> teachingDetails = new ArrayList<TeachingDetail>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TeachingDetail teachingDetail = new TeachingDetail();
				teachingDetail.setGradeID(rs.getString("ctc_classid"));
				teachingDetail.setYear(rs.getInt("cs_date"));
				teachingDetail.setClassID(rs.getInt("cs_class"));
				teachingDetail.setCourseID(rs.getString("ctc_courseid"));
				teachingDetail.setCourseName(rs.getString("c_name"));
				teachingDetail.setXueqi(rs.getInt("ctc_xueqi"));
				teachingDetail.setTeacherID(rs.getString("ctc_teacherid"));
				teachingDetails.add(teachingDetail);
			}
		});
		return teachingDetails;
	}

	@Override
	public List<Course> getCourses(String gradeID, int xueqi) {
		String sqlString = "SELECT ctc_courseid, c_name FROM sp_ctc, sp_course WHERE ctc_classid = ? AND ctc_xueqi = ? AND sp_ctc.ctc_courseid = sp_course.c_id order by ctc_courseid;";
		Object[] argsObjects = {gradeID, xueqi};
		final List<Course> courses = new ArrayList<Course>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Course course = new Course();
				course.setBianHao(rs.getString("ctc_courseid"));
				course.setName(rs.getString("c_name"));
				courses.add(course);
			}
		});
		return courses;
	}

	@Override
	public int isTrue(String gradeID, int xueqi, String courseID) {
		String sqlString = "SELECT count(*) FROM sp_ctc WHERE ctc_classid = ? AND ctc_xueqi = ? AND ctc_courseid = ?";
		Object[] argsObjects = {gradeID, xueqi, courseID};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int isExisits(int stuXueHao, int xueqi) {
		String sqlString ="SELECT COUNT(*) FROM sp_ctc WHERE ctc_classid IN (SELECT s_class FROM sp_student WHERE s_id = ?) AND ctc_xueqi = ? ;";
		Object[] argsObjects = {stuXueHao, xueqi};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public int isExisits(String gradeID, int xueqi) {
		String sqlString ="SELECT COUNT(*) FROM sp_ctc WHERE ctc_classid = ? AND ctc_xueqi = ? ;";
		Object[] argsObjects = {gradeID, xueqi};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public List<TeachingTable> getTeachingTables(String teacherID) {
		String sqlString = "SELECT ctc_classid, cs_date, cs_class, cs_adviser,t_name, ctc_courseid, c_name, ctc_xueqi FROM sp_ctc, sp_teacher, sp_classes, sp_course WHERE sp_ctc.ctc_classid=sp_classes.cs_id AND sp_classes.cs_adviser=sp_teacher.t_id AND sp_ctc.ctc_courseid = sp_course.c_id AND ctc_teacherid = ?;";
		Object[] args = {teacherID};
		final List<TeachingTable> teachingTables = new ArrayList<TeachingTable>();
		jdbcTemplate.query(sqlString, args, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TeachingTable teachingTable = new TeachingTable();
				teachingTable.setGradeID(rs.getString("ctc_classid"));
				teachingTable.setYear(rs.getInt("cs_date"));
				teachingTable.setClassID(rs.getInt("cs_class"));
				teachingTable.setAdviserID(rs.getString("cs_adviser"));
				teachingTable.setAdviserName(rs.getString("t_name"));
				teachingTable.setCourseID(rs.getString("ctc_courseid"));
				teachingTable.setCourseName(rs.getString("c_name"));
				teachingTable.setXueqi(rs.getInt("ctc_xueqi"));
				teachingTables.add(teachingTable);
			}
		});
		return teachingTables;
	}

	@Override
	public List<TeachingGidXq> geTeachingGidXqs(String gradeID, int xueqi) {
		
		String sqlString ="SELECT ctc_courseid, c_name, ctc_teacherid, t_name FROM sp_ctc, sp_course, sp_teacher WHERE sp_ctc.ctc_courseid =  sp_course.c_id AND sp_ctc.ctc_teacherid = sp_teacher.t_id AND ctc_classid = ? AND ctc_xueqi = ? ;";
		Object[] argsObjects = {gradeID, xueqi};
		final List<TeachingGidXq> teachingGidXqs = new ArrayList<TeachingGidXq>();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TeachingGidXq teachingGidXq = new TeachingGidXq();
				teachingGidXq.setCourseID(rs.getString("ctc_courseid"));
				teachingGidXq.setCourseName(rs.getString("c_name"));
				teachingGidXq.setTeacherID(rs.getString("ctc_teacherid"));
				teachingGidXq.setTeacherName(rs.getString("t_name"));
				teachingGidXqs.add(teachingGidXq);
			}
		});
		
		return teachingGidXqs;
	}

	@Override
	public void deleteTeaching(final String gradeID, final int xueqi, final List<String> courseID) {
		String sqlStringPre = "DELETE FROM sp_score_record WHERE sr_gradeID = ? AND sr_xueqi = ? AND sr_courseid = ? ;";
		jdbcTemplate.batchUpdate(sqlStringPre, new BatchPreparedStatementSetter(){

			@Override
			public int getBatchSize() {
				return courseID.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int index)
					throws SQLException {
				ps.setString(1, gradeID);
				ps.setInt(2, xueqi);
				ps.setString(3,  courseID.get(index));
				
			}});
		
		String sqlString = "DELETE FROM sp_ctc WHERE ctc_classid = ? AND ctc_xueqi = ? AND ctc_courseid = ? ;";
		jdbcTemplate.batchUpdate(sqlString, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				ps.setString(1, gradeID);
				ps.setInt(2, xueqi);
				ps.setString(3,  courseID.get(index));
			}
			
			@Override
			public int getBatchSize() {
				return courseID.size();
			}
		});
	}

	@Override
	public int hasRecord(String teacherID) {
		String sqlString = "SELECT COUNT(*) FROM sp_ctc WHERE ctc_teacherid = ?;";
		Object[] args= {teacherID};
		return jdbcTemplate.queryForInt(sqlString, args);
	}

 
}
