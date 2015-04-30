package com.xscj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xscj.dao.CourseDao;
import com.xscj.domain.Course;
import com.xscj.domain.CourseInfo;
import com.xscj.service.CoursePlan;

@Service
public class CoursePlanImpl implements CoursePlan {
	@Autowired
	@Qualifier("jdbcCourseDao")
	private CourseDao courseDao;
	/**
	 * 
	 * @author leorain 得到课程总数业务逻辑处理实现类
	 *
	 * */
	@Override
	public int getCourseCount() {
		return courseDao.getCourseCount();
	}
	
	/**
	 * @author leorain 得到所有课程列表业务逻辑处理实现类
	 * */
	@Override
	public List<Course> getAllCourses() {
		return courseDao.getAllCourses();
	}

	@Override
	public int addCourseToDb(Course course) {
		return courseDao.addCourseToDb(course);
	}

	@Override
	public List<Course> getCoursesByGradeAndXueQi(String GradeStr, int xueqi) {
		return courseDao.getCoursesByGradeAndXueQi(GradeStr, xueqi);
	}

	@Override
	public int getValidCourseCount() {
		return courseDao.getValidCourseCount();
	}

	@Override
	public List<Course> getValidAllCourses() {
		return courseDao.getValidAllCourses();
	}

	@Override
	public void deleteChoiceCourses(String[] courseIDs) {
		courseDao.deleteChoiceCourses(courseIDs);
	}

	@Override
	public Course getCourseByCourseID(String courseID) {
		return courseDao.getCourseByCourseID(courseID);
	}

	@Override
	public List<CourseInfo> getAllCourseInfos() {
		return courseDao.getAllCourseInfos();
	}

	@Override
	public List<Course> getCoursesEnableDel() {
		return courseDao.getCoursesEnableDel();
	}
}
