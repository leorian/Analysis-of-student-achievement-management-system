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

import com.xscj.dao.CourseDao;
import com.xscj.domain.Course;
import com.xscj.domain.CourseInfo;

/**
 * @author leorain
 *
 */
@Repository
public class JdbcCourseDao implements CourseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @author leorain 得到课程总数底层操作JDBC实现
	 * 
	 * */
	@Override
	public int getCourseCount() {
		String sqlStr = "SELECT count(*) FROM sp_course;"; 
		return jdbcTemplate.queryForInt(sqlStr);
	}

	/**
	 * 
	 * @author leorain 得到所有课程列表底层操作JDBC实现
	 * 
	 * */
	@Override
	public List<Course> getAllCourses() {
		String sqlStr = "SELECT * FROM sp_course;";
		final List<Course> courses = new ArrayList<Course>();
		jdbcTemplate.query(sqlStr, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Course course = new Course();
				course.setBianHao(rs.getString("c_id"));
				course.setName(rs.getString("c_name"));
				courses.add(course);
			}});
		return courses;
	}

	@Override
	public int addCourseToDb(Course course) {
		String sqlStr = "INSERT INTO sp_course(c_id, c_name) VALUES(?,?);";
		Object[] args = {course.getBianHao(), course.getName()};
		return jdbcTemplate.update(sqlStr, args);
	}

	@Override
	public List<Course> getCoursesByGradeAndXueQi(String GradeStr, int xueqi) {
		String sqlString = "SELECT * FROM sp_course WHERE c_id IN ( SELECT ctc_courseid FROM sp_ctc WHERE ctc_classid = ? AND ctc_xueqi = ? ) AND delflag = false;";
		Object[] args = {GradeStr, xueqi};
		final List<Course> courses = new ArrayList<Course>();
		jdbcTemplate.query(sqlString, args, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Course course = new Course();
				course.setBianHao(rs.getString("c_id"));
				course.setName(rs.getString("c_name"));
				courses.add(course);
			}});
		return courses;
	}

	@Override
	public int getValidCourseCount() {
		String sqlStr = "SELECT count(*) FROM sp_course WHERE delflag = false;"; 
		return jdbcTemplate.queryForInt(sqlStr);
	}

	@Override
	public List<Course> getValidAllCourses() {
		String sqlStr = "SELECT * FROM sp_course WHERE delflag = false;";
		final List<Course> courses = new ArrayList<Course>();
		jdbcTemplate.query(sqlStr, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Course course = new Course();
				course.setBianHao(rs.getString("c_id"));
				course.setName(rs.getString("c_name"));
				courses.add(course);
			}});
		return courses;
	}

	@Override
	public void deleteChoiceCourses(final String[] courseIDs) {
		String sqlStringPrefix = "DELETE FROM sp_score_record WHERE  sr_courseid = ?;";//删除和课程关联的成绩信息
		jdbcTemplate.batchUpdate(sqlStringPrefix, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				String courseID = courseIDs[index];
				ps.setString(1, courseID);
			}
			
			@Override
			public int getBatchSize() {
				return courseIDs.length;
			}
		});
		String sqlStringPrefix2 = "DELETE FROM sp_ctc WHERE ctc_courseid = ? ;";//删除和课程关联的教学安排
		jdbcTemplate.batchUpdate(sqlStringPrefix2, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				String courseID = courseIDs[index];
				ps.setString(1, courseID);
			}
			
			@Override
			public int getBatchSize() {
				return courseIDs.length;
			}
		});
		String sqlString = "DELETE FROM sp_course WHERE c_id = ?;";
		jdbcTemplate.batchUpdate(sqlString, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				String courseID = courseIDs[index];
				ps.setString(1, courseID);
			}
			
			@Override
			public int getBatchSize() {
				return courseIDs.length;
			}
		});
	}

	@Override
	public Course getCourseByCourseID(String courseID) {
		String sqlString = "SELECT * FROM sp_course WHERE c_id = ? ;";
		Object[] argsObjects = {courseID};
		final Course course = new Course();
		jdbcTemplate.query(sqlString, argsObjects, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				course.setBianHao(rs.getString("c_id"));
				course.setName(rs.getString("c_name"));
				
			}
		});
		return course;
	}

	@Override
	public List<CourseInfo> getAllCourseInfos() {
		String sqlString = "SELECT c_id, c_name, COUNT(t_course) FROM sp_teacher,sp_course WHERE sp_teacher.t_course = sp_course.c_id GROUP BY c_id  ORDER BY c_id; ";
		final List<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
		jdbcTemplate.query(sqlString, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseInfo courseInfo = new CourseInfo();
				courseInfo.setCourseID(rs.getString("c_id"));
				courseInfo.setCourseName(rs.getString("c_name"));
				courseInfo.setTeacherCount(rs.getInt(3));
				courseInfos.add(courseInfo);
			}
		});
		String sqlString2="SELECT c_id, c_name FROM sp_course WHERE sp_course.c_id NOT IN (SELECT DISTINCT t_course FROM sp_teacher)";
		jdbcTemplate.query(sqlString2, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CourseInfo courseInfo = new CourseInfo();
				courseInfo.setCourseID(rs.getString("c_id"));
				courseInfo.setCourseName(rs.getString("c_name"));
				courseInfo.setTeacherCount(0);
				courseInfos.add(courseInfo);
			}
		});
		return courseInfos;
	}

	@Override
	public List<Course> getCoursesEnableDel() {
		String sqlString = "SELECT * FROM sp_course WHERE c_id NOT IN (SELECT DISTINCT t_course FROM sp_teacher);";
		final List<Course> courses = new ArrayList<Course>();
		jdbcTemplate.query(sqlString, new RowCallbackHandler() {
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
}
