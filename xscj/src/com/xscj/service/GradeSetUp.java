/**
 * 
 */
package com.xscj.service;

import java.util.List;

import com.xscj.domain.Grade;
import com.xscj.domain.GradeInfo;

/**
 * @author leorain 班级设置业务逻辑处理接口
 *
 */
public interface GradeSetUp {
	/**
	 * @author leorain 得到班级总数的业务逻辑处理接口
	 * 
	 * */
	public int getGradeCount();
	
	/**
	 * 
	 * @author leorain 得到所有的班级列表的业务逻辑处理接口
	 * */
	public List<Grade> getAllGrades();
	
	/**
	 * @author leorain 得到高一年级所对应届的业务逻辑处理接口
	 * @date 2014-3-18 上午9:43:48
	 * */
	public int getMaxYear();
	
	/**
	 * @author leorain得到指定区间内班级个数的业务逻辑处理接口
	 * @date 2014-3-18 上午9:52:53
	 * 
	 * */
	public int getValidGradeCount(int minYear, int maxYear);
	
	/**
	 *@author leorain 得到指定区间内班级列表的业务逻辑处理接口
	 *@date 2014-3-18 下午7:42:27
	 * */
	public List<Grade> getValidAllGrades(int minYear, int maxYear);
	
	/**
	 * @author leorain 得到某一届有多少个班级的业务逻辑处理接口
	 * @date 2014-3-18 下午7:43:00
	 * 
	 * */
	public int getClassesCount(int maxYear);
	
	/**
	 * @author leorain 向数据库中添加一条新的班级信息的业务逻辑处理接口
	 * @date 2014-3-18 下午8:01:34
	 * 
	 * */
	public int addGradeToDb(Grade grade);
	
	/**
	 * 
	 * @author leorain得到最新一届的班级列表的业务逻辑处理接口
	 * @date 2014-3-19 上午7:59:11
	 * 
	 * */
	public List<Grade> getMaxYearGrades(int maxYear);
	
	/**
	 * @author leorain得到某条具体的班级信息
	 * @date 2014-3-20 上午10:42:37
	 * */
	public Grade getGradeBybianHao(String bianHao);
	
	/**
	 * @author leorain
	 * @date 2014-3-21 下午2:25:07
	 * 模拟删除 设置删除标志
	 * */
	public void deleteChoiceGrades(String[] gradeStrings);
	
	/**
	 * @author leorain
	 * @date 2014-4-2 上午10:22:49
	 * 通过学生学号获得这个学生的班级信息
	 * */
	public Grade getGradeBystuXueHao(int stuXueHao);
	
	/**
	 * @author leorain
	 * @date 2014-4-3 下午1:05:22
	 * 得到所有合法的班级信息集合
	 * */
	public List<Grade> getALLValidGrades();
	
	/**
	 * @author leorain
	 * @date 2014-4-16 上午8:27:21
	 * @return获得所有班级的详细信息
	 * */
	public List<GradeInfo> getGradeInfos();
	
	/**
	 * @author leorain
	 * @date 2014-4-18 下午3:56:35
	 * 
	 * */
	public List<Grade> getGradesEnableDel();
	
	/**
	 * @author leorain
	 * @date 2014-5-9 下午20:32:34
	 * */
	public List<Integer> getYears();
	
	/**
	 * @author leorain
	 * */
	public List<GradeInfo> getGradeInfos(int year);
	
	/**
	 * @author leorains
	 * */
	public int hasRecord(String teacherID);
}
