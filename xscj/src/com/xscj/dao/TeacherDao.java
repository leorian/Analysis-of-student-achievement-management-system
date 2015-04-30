package com.xscj.dao;

import java.util.List;

import com.xscj.domain.SubTeacher;
import com.xscj.domain.Teacher;

public interface TeacherDao {
	/**
	 * 
	 * 得到教师总人数底层实现
	 * */
	public int getTeacherCount();
	
	/**
	 * 
	 * 添加一条新的教师信息底层实现
	 * */
	public int addTeacherToDb(Teacher teacher);
	
	/**
	 * 
	 * 分页查询底层实现代码
	 * */
		public List<Teacher> queryByPage(int pageSize, int pageNow);
		
		/**
		 * 
		 * @author leorain 得到所有教师编号
		 * @date 2014-3-18 下午7:22:16
		 * */
		public List<String> getAllTeacherBh();
		
		/**
		 * @author leorain 得到所有教师的教师编号和教师姓名的组合 集合
		 * @date 2014-3-19 下午6:11:00
		 * */
		public List<SubTeacher> getAllSubTeachers();
		
		/**
		 * @author leorain 得到指定课程的所有授课老师的教师编号和教师姓名的组合集合
		 * @date 2014-3-19 下午6:11:43
		 * 
		 * */
		public List<SubTeacher> getAllSubTeachersByCourseID(String courseID);
		
		/**
		 * @author leorain 得到有效的教师总人数
		 * @date 2014-3-21 下午3:35:09
		 * */
		public int getValidTeacherCount();
		
		/**
		 * @author leorain
		 * @date 2014-3-21 下午3:42:11
		 * 模拟删除操作 设置删除标志
		 * 
		 * */	
		public void deleteTeacherByBianHao(String bianHao);
		
		
		/**
		 * @author leorain
		 * @date 2014-3-23 下午7:38:41
		 * 通过教师编号获从教师注册表中获取教师姓名
		 * */
		public String getTeacherNameByTeacherID(String teacherBianHao);
		
		/**
		 * @author leorain
		 * @date 2014-4-13 下午12:30:31
		 * @param教师编号
		 * @return教师的基本个人信息
		 * 
		 * */
		public Teacher getTeacherInfo(String teacherBianHao);
		
		/**
		 * @author leorain
		 * @date 2014-4-13 下午3:53:06
		 * @param教师对象
		 * @return更新教师基本信息
		 * 
		 * *
		 */
		public void updateTeacherInfo(Teacher teacher);
		/**
		 * @author leorain
		 * @date 2014-4-15 下午9:13:04
		 * @return判断这个教师是否存在
		 * 
		 * */
		public int isExisit(String teacherBianHao);
		
		/**
		 * @author leorain
		 * @date 2014-4-18 下午9:28:17
		 * */
		public int isExisits(String gradeID, String password);
		
		public void updatePass(String bianHao, String newPass);
}
