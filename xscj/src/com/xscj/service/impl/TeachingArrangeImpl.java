/**
 * 
 */
package com.xscj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xscj.dao.TeachingArrangeDao;
import com.xscj.domain.Course;
import com.xscj.domain.Teaching;
import com.xscj.domain.TeachingDetail;
import com.xscj.domain.TeachingGidXq;
import com.xscj.domain.TeachingTable;
import com.xscj.service.TeachingArrange;

/**
 * @author leorain
 *
 */
@Service
public class TeachingArrangeImpl implements TeachingArrange {
	
	@Autowired
	@Qualifier("jdbcTeachingArrangeDaoImpl")
	private TeachingArrangeDao teachingArrangeDao;

	@Override
	public void addTeachingArrange(List<Teaching> teachings) {
		teachingArrangeDao.addTeachingArrange(teachings);
	}

	@Override
	public List<String> getCourseNameByTeacherID(String teacherBianHao) {
		return teachingArrangeDao.getCourseNameByTeacherID(teacherBianHao);
	}

	@Override
	public List<Teaching> getTeachingsByTeacherID(String teacherBianHao) {
		return teachingArrangeDao.getTeachingsByTeacherID(teacherBianHao);
	}

	@Override
	public String getTeacherNameByTeaching(String gradeID, int xueqi,
			String courseID) {
		return teachingArrangeDao.getTeacherNameByTeaching(gradeID, xueqi, courseID);
	}

	@Override
	public List<Course> getCourses(String gradeID, int xueqi, String examType, int stuXueHao) {
		return teachingArrangeDao.getCourses(gradeID, xueqi, examType, stuXueHao);
	}

	@Override
	public List<TeachingDetail> getTeachingDetails(String teacherBianHao) {
		// TODO Auto-generated method stub
		return teachingArrangeDao.getTeachingDetails(teacherBianHao);
	}

	@Override
	public List<Course> getCourses(String gradeID, int xueqi) {
		return teachingArrangeDao.getCourses(gradeID, xueqi);
	}

	@Override
	public int isTrue(String gradeID, int xueqi, String courseID) {
		return teachingArrangeDao.isTrue(gradeID, xueqi, courseID);
	}

	@Override
	public int isExisits(int stuXueHao, int xueqi) {
		return teachingArrangeDao.isExisits(stuXueHao, xueqi);
	}

	@Override
	public int isExisits(String gradeID, int xueqi) {
		return teachingArrangeDao.isExisits(gradeID, xueqi);
	}

	@Override
	public List<TeachingTable> getTeachingTables(String teacherID) {
		return teachingArrangeDao.getTeachingTables(teacherID);
	}

	@Override
	public List<TeachingGidXq> geTeachingGidXqs(String gradeID, int xueqi) {
		return teachingArrangeDao.geTeachingGidXqs(gradeID, xueqi);
	}

	@Override
	public void deleteTeaching(String gradeID, int xueqi, List<String> courseID) {
		teachingArrangeDao.deleteTeaching(gradeID, xueqi, courseID);
	}

	@Override
	public int hasRecord(String teacherID) {
		return teachingArrangeDao.hasRecord(teacherID);
	}
}
