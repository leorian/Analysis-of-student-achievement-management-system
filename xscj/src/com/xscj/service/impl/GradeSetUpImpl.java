/**
 * 
 */
package com.xscj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xscj.dao.GradeDao;
import com.xscj.domain.Grade;
import com.xscj.domain.GradeInfo;
import com.xscj.service.GradeSetUp;

/**
 * @author leorain
 *
 */
@Service
public class GradeSetUpImpl implements GradeSetUp {
	@Autowired
	@Qualifier("jdbcGradeDaoImpl")
	private GradeDao gradeDao;

	@Override
	public int getGradeCount() {
		return gradeDao.getGradeCount();
	}

	@Override
	public List<Grade> getAllGrades() {
		return gradeDao.getAllGrades();
	}
/**
 * @author leorain
 * @date 2014-3-18 上午9:44:54
 * */
	@Override
	public int getMaxYear() {
		return gradeDao.getMaxYear();
	}

@Override
public int getValidGradeCount(int minYear, int maxYear) {
	return gradeDao.getValidGradeCount(minYear, maxYear);
}

@Override
public List<Grade> getValidAllGrades(int minYear, int maxYear) {
	return gradeDao.getValidAllGrades(minYear, maxYear);
}

@Override
public int getClassesCount(int maxYear) {
	return gradeDao.getClassesCount(maxYear);
}

@Override
public int addGradeToDb(Grade grade) {
	return gradeDao.AddGradeToDb(grade);
}

@Override
public List<Grade> getMaxYearGrades(int maxYear) {
	return gradeDao.getMaxYearGrades(maxYear);
}

@Override
public Grade getGradeBybianHao(String bianHao) {
	return gradeDao.getGradeBybianHao(bianHao);
}

@Override
public void deleteChoiceGrades(String[] gradeStrings) {
	gradeDao.deleteChoiceGrades(gradeStrings);
}

@Override
public Grade getGradeBystuXueHao(int stuXueHao) {
	return gradeDao.getGradeBystuXueHao(stuXueHao);
}

@Override
public List<Grade> getALLValidGrades() {
	return gradeDao.getALLValidGrades();
}

@Override
public List<GradeInfo> getGradeInfos() {
	return gradeDao.getGradeInfos();
}

@Override
public List<Grade> getGradesEnableDel() {
	return gradeDao.getGradesEnableDel();
}

@Override
public List<Integer> getYears() {
	return gradeDao.getYears();
}

@Override
public List<GradeInfo> getGradeInfos(int year) {
	return gradeDao.getGradeInfos(year);
}

@Override
public int hasRecord(String teacherID) {
	return gradeDao.hasRecord(teacherID);
}
}
