package com.xscj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xscj.dao.StuDao;
import com.xscj.domain.SimpleStudent;
import com.xscj.domain.Student;
import com.xscj.domain.SubStudent;
import com.xscj.service.StuService;

@Service
public class StuServiceImpl  implements StuService{
	@Autowired
	@Qualifier("jdbcStuDaoImpl")
	private StuDao stuDao;

	@Override
	public int getStuCount() {
		return stuDao.getStuCount();
	}

	@Override
	public int addStuToDb(Student student) {
		return stuDao.addStuToDb(student);
	}

	@Override
	public int addStuDb(int id, Student student) {
		 
		return stuDao.addStuToDb(id, student);
	}

	@Override
	public List<SimpleStudent> queryByPage(int pageSize, int pageNow) {
		return stuDao.queryByPage(pageSize, pageNow);
	}

	@Override
	public int getValidStuCount() {
		return stuDao.getValidStuCount();
	}

	@Override
	public void deleteStuByXueHao(int xuehao) {
		stuDao.deleteStuByXueHao(xuehao);
	}

	@Override
	public List<SubStudent> getAllSubStudentsByGradeID(String gradeID) {
		return stuDao.getAllSubStudentsByGradeID(gradeID);
	}

	@Override
	public SubStudent getSubStudentByXueHao(int stuXueHao) {
		return stuDao.getSubStudentByXueHao(stuXueHao);
	}

	@Override
	public List<SubStudent> getAllSubStudentsByGXTC(String gradeID, int xueqi,
			String examType, String courseID) {
		return stuDao.getAllSubStudentsByGXTC(gradeID, xueqi, examType, courseID);
	}

	@Override
	public Student getStudent(int xuehao) {
		return stuDao.getStudent(xuehao);
	}

	@Override
	public void updateStuInfo(int xuehao, Student student) {
		stuDao.updateStuInfo(xuehao, student);
	}

	@Override
	public SimpleStudent querySimpleStudent(int stuXueHao) {
		return stuDao.querySimpleStudent(stuXueHao);
	}

	@Override
	public List<SimpleStudent> querySimpleStudents(String gradeID) {
		return stuDao.querySimpleStudents(gradeID);
	}

	@Override
	public int isExisits(int stuXueHao) {
		return stuDao.isExisits(stuXueHao);
	}

	@Override
	public int isExisits(String gradeID) {
		return stuDao.isExisits(gradeID);
	}

	@Override
	public int isExisits(int stuXueHao, String password) {
		return stuDao.isExisits(stuXueHao, password);
	}

	@Override
	public void updatePass(int stuXueHao, String password) {
		stuDao.updatePass(stuXueHao, password);
	}
}
