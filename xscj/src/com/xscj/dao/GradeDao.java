/**
 * 
 */
package com.xscj.dao;

import java.util.List;

import com.xscj.domain.Grade;
import com.xscj.domain.GradeInfo;

/**
 * @author leorain 班级设置底层操作接口
 *
 */
public interface GradeDao {
	/**
	 * @author leorain 得到班级总数的底层操作接口
	 * 
	 * */
	public int getGradeCount();
	
	/**
	 * @author leorain 得到班级列表的底层操作接口
	 * */
	public List<Grade> getAllGrades();
	
	/**
	 * @author leorain得到高一年级所对应届的底层操作接口
	 * */
	public int getMaxYear();
	
	/**
	 * @author leorain得到指定区间内班级个数
	 * @date 2014-3-18 上午9:48:57
	 * 
	 * */
	public int getValidGradeCount(int minYear,int maxYear);
	
	/**
	 * @author leorain得到指定区间内班级列表
	 * @date 2014-3-18 上午9:59:06
	 * */
	public List<Grade> getValidAllGrades(int minYear, int maxYear);
	
	
	/**
	 * @author leorain 得到某一届总共有多少个班
	 * @date 2014-3-18 下午7:40:13
	 * */
	public int getClassesCount(int maxYear);
	
	
	/**
	 * @author leorain 向数据库中添加一条新的班级信息
	 * @date 2014-3-18 下午7:56:48
	 * */
	public int AddGradeToDb(Grade grade);
	
	/**
	 * @author leorain 得到最新一届的班级列表
	 * @date 2014-3-19 上午7:56:46
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
