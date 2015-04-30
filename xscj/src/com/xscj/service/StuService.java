package com.xscj.service;

import java.util.List;

import com.xscj.domain.SimpleStudent;
import com.xscj.domain.Student;
import com.xscj.domain.SubStudent;

public interface StuService {
	/**
	 * 
	 *  得到学生总人数业务逻辑
	 * */
	public int getStuCount();
	
	/**
	 * 添加第一条学生信息业务逻辑
	 * 
	 * */
	public int addStuDb(int id, Student student);
	
	/**
	 * 添加一条新的学生信息业务逻辑
	 * 
	 * */
	public int addStuToDb(Student student);
	/**
	 * 分页查询
	 * */
	public List<SimpleStudent> queryByPage(int pageSize, int pageNow);
	
	/**
	 * @author leorain
	 * @date 2014-3-21 下午2:53:09
	 * 
	 * 得到有效的学生总人数
	 * */
	
	public int getValidStuCount();
	
	/**
	 * @author leorain
	 * @date 2014-3-21 下午3:08:35
	 * 模拟删除 设置删除标志
	 * */
	
	public void deleteStuByXueHao(int xuehao);
	
	/**
	 * @author leorain
	 * @date 2014-3-24 上午10:23:37
	 * 根据班级编号获得这个班级所有学生的学号以及姓名
	 * */
	public List<SubStudent> getAllSubStudentsByGradeID(String gradeID);
	
	/**
	 * @author leorain
	 * @date 2014-4-2 上午11:45:48
	 * 根据学生学号查询这个学生的姓名
	 * */
	public SubStudent getSubStudentByXueHao(int stuXueHao);
	
	/**
	 * @author leorain
	 * @date 2014-4-7 下午12:35:01
	 * @param班级编号
	 * @param学期
	 * @param考试类型
	 * @param课程编号
	 * @return 返回某次考试某门课程所有成绩未记录的学生集合
	 * */
	public List<SubStudent> getAllSubStudentsByGXTC(String gradeID, int xueqi, String examType, String courseID);
	
	/**
	 * @author leorain
	 * @date 2014-4-13 上午8:17:03
	 * @param 学生学号
	 * @return 学生的个人基本信息
	 * */
	public Student getStudent(int xuehao);
	
	/**
	 * @author leorain
	 * @date 2014-4-13 上午10:14:57
	 * @param学号
	 * @param学生信息
	 * @return更新学生信息
	 * */
	public void updateStuInfo(int xuehao, Student student);
	

	/**
	 * @author leorain
	 * @date 2014-4-13 下午5:25:39
	 * @param学生学号
	 * @return学生简单的个人信息
	 * 
	 * */
	public SimpleStudent querySimpleStudent(int stuXueHao);

	
	/**
	 * @author leorain
	 * @date 2014-4-13 下午8:05:18
	 * @param班级编号
	 * @return返回某个班级的所有学生集合
	 * 
	 * */
	public List<SimpleStudent> querySimpleStudents(String gradeID);
	
	/**
	 * @author leorain
	 * @date 2014-4-15 下午7:49:52
	 * @return判断学生是否存在
	 * */
	public int isExisits(int stuXueHao);
	
	/**
	 * @author leorain
	 * @date 2014-4-15 下午8:39:44
	 * @return判断某个班级是否有学生存在
	 * */
	public int isExisits(String gradeID);
	
	/**
	 * @author leorain
	 * @date 2014-4-18 下午9:23:30
	 * */
	public int isExisits(int stuXueHao, String password);
	
	
	public void updatePass(int stuXueHao, String password);

}
