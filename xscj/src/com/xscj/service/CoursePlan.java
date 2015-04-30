package com.xscj.service;

import java.util.List;

import com.xscj.domain.Course;
import com.xscj.domain.CourseInfo;

public interface CoursePlan {
	/**
	 * 
	 * @author leorain 得到课程总数业务逻辑处理接口
	 *
	 * */
	public int getCourseCount();
	
	/**
	 * @author leorain 得到所有课程列表业务逻辑处理接口
	 * 
	 * */
	public List<Course> getAllCourses();
	
	/**
	 * 
	 * @author leorain 向数据库中添加一条新的课程信息
	 * */
	public int addCourseToDb(Course course);
	
	/**
	 * @author leorain 复合查询 课程表 教学安排表
	 * @date 2014-3-20 上午10:28:24
	 * */
	
	public List<Course> getCoursesByGradeAndXueQi(String GradeStr, int xueqi);
	
	
	/**
	 * @author leorain
	 * @date 2014-3-21 下午12:53:44
	 * 
	 * 得到删除标志为false的课程总记录数目
	 * */
	public int getValidCourseCount();
	
	/**
	 * @author leorain
	 * @date 2014-3-21 下午12:55:41
	 * 得到删除标志为false的课程列表
	 * 
	 * */
	public List<Course> getValidAllCourses();
	
	
	/**
	 * @author leorain
	 * @date 2014-3-21 下午1:26:26
	 * 
	 * 模拟删除
	 * 
	 * **/
	
	public void deleteChoiceCourses(String[] courseIDs);
	
	/**
	 * @author leorain
	 * @date 2014-3-23 下午8:21:26
	 * 通过课程编号获得课程详细信息
	 * */
	public Course getCourseByCourseID(String courseID);
	
	/**
	 * @author leorain
	 * @date 2014-4-16 上午5:54:14
	 * 获得所有课程的详细信息
	 * 
	 * */
	public List<CourseInfo> getAllCourseInfos();
	
	/**
	 * @author leorain
	 * @date 2014-4-18 下午3:43:11
	 * */
	public List<Course> getCoursesEnableDel();
}
