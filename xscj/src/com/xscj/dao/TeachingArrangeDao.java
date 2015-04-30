/**
 * 
 */
package com.xscj.dao;

import java.util.List;

import com.xscj.domain.Course;
import com.xscj.domain.Teaching;
import com.xscj.domain.TeachingDetail;
import com.xscj.domain.TeachingGidXq;
import com.xscj.domain.TeachingTable;

/**
 * @author leorain
 * @date 2014-3-20 上午8:47:17 教学安排底层操作接口
 *
 */
public interface TeachingArrangeDao {
	/**
	 * @author leorain 向数据库中添加教学安排
	 * @date 2014-3-20 上午9:01:59
	 * 
	 * */
	public void addTeachingArrange( final List<Teaching> teachings);
	
	
	/**
	 * @author leorain
	 * @date 2014-3-23 下午7:12:36
	 * 通过教师编号从教学安排表中查询教师授课的课程编号，再由
	 * 课程编号从课程表中获取课程名
	 * 
	 * */
	public List<String> getCourseNameByTeacherID(String teacherBianHao);
	
	
	/**
	 * @author leorain
	 * @date 2014-3-23 下午8:00:58
	 * 通过教师编号从教学安排表中获得某个具体教师的教学安排情况
	 * */
	public List<Teaching> getTeachingsByTeacherID(String teacherBianHao);
	
	/**
	 * @author leorain
	 * @date 2014-3-31 下午1:01:29
	 * 通过班级编号、学期、课程编号从教学安排表中获得某个具体授课老师的姓名
	 * */
	public String getTeacherNameByTeaching(String gradeID, int xueqi, String courseID);
	
	/**
	 * @author leorain
	 * @date 2014-4-7 上午7:20:31
	 * 通过班级编号、学期获得从教学安排表中获得某个班级某个学期具体的教学安排
	 * */
	public List<Course> getCourses(String gradeID, int xueqi, String examType, int stuXueHao);
	
	/**
	 * @author leorain
	 * @date 2014-4-13 下午1:18:50
	 * @param教师编号
	 * @return返回某个教师具体的教师教学安排
	 * 
	 * */
	public List<TeachingDetail> getTeachingDetails(String teacherBianHao);
	
	/**
	 * @author leorain
	 * @date 2014-4-14 下午9:40:02
	 * @return返回某个班级的具体教学安排
	 * */
	public List<Course> getCourses(String gradeID, int xueqi);
	
	public int isTrue(String gradeID, int xueqi, String courseID);
	
	/**
	 * @author leorain
	 * @date 2014-4-16 下午2:14:03
	 * @return通过学生的学号获得该学生某个学期是否有教学安排
	 * 
	 * */
	public int isExisits(int stuXueHao, int xueqi);
	
	/**
	 * @author leorain
	 * @date 2014-4-17 上午9:15:53
	 * @return查询某个班级某个学期的教学安排情况
	 * */
	public int isExisits(String gradeID, int xueqi);
	
	/**
	 *@author leorain
	 *@date 2014-4-19 下午2:40:13
	 *@return获得某个老师的教学安排表
	 * */
	public List<TeachingTable> getTeachingTables(String teacherID);
	
	/**
	 * @author leorain
	 * @date 2014-4-20 上午10:32:15
	 * @return获得某个班级具体的教学安排情况课程及授课老师
	 * */
	public List<TeachingGidXq> geTeachingGidXqs(String gradeID, int xueqi);
	
	public void deleteTeaching(String gradeID, int xueqi, List<String> courseID);
	
	/**
	 * @author leorain
	 * @判断某个教师是否有教学安排
	 * */
	public int hasRecord(String teacherID);
	
}
