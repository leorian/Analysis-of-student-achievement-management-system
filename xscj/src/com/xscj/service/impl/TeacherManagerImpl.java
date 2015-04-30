package com.xscj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xscj.dao.TeacherDao;
import com.xscj.domain.SubTeacher;
import com.xscj.domain.Teacher;
import com.xscj.service.TeacherManager;

@Service
public class TeacherManagerImpl implements TeacherManager{
	@Autowired
	@Qualifier("jdbcTeacherDaoImpl")
	private TeacherDao teacherDao;

	@Override
	public int getTeacherCount() {
		 
		return teacherDao.getTeacherCount();
	}

	@Override
	public int addTeacherToDb(Teacher teacher) {
	 
		return teacherDao.addTeacherToDb(teacher);
	}

	@Override
	public List<Teacher> queryByPage(int pageSize, int pageNow) {
		 
		return teacherDao.queryByPage(pageSize, pageNow);
	}

	@Override
	public List<String> getAllTeacherBh() {
		return teacherDao.getAllTeacherBh();
	}

	@Override
	public List<SubTeacher> getAllSubTeachers() {
		return teacherDao.getAllSubTeachers();
	}

	@Override
	public List<SubTeacher> getAllSubTeachersByCourseID(String courseID) {
		return teacherDao.getAllSubTeachersByCourseID(courseID);
	}

	@Override
	public int getValidTeacherCount() {
		return teacherDao.getValidTeacherCount();
	}

	@Override
	public void deleteTeacherByBianHao(String bianHao) {
		teacherDao.deleteTeacherByBianHao(bianHao);
	}

	@Override
	public String getTeacherNameByTeacherID(String teacherBianHao) {
		return teacherDao.getTeacherNameByTeacherID(teacherBianHao);
	}

	@Override
	public Teacher getTeacherInfo(String teacherBianHao) {
		return teacherDao.getTeacherInfo(teacherBianHao);
	}

	@Override
	public void updateTeacherInfo(Teacher teacher) {
		teacherDao.updateTeacherInfo(teacher);
	}

	@Override
	public int isExisit(String teacherBianHao) {
		return teacherDao.isExisit(teacherBianHao);
	}

	@Override
	public int isExisits(String gradeID, String password) {
		return teacherDao.isExisits(gradeID, password);
	}

	@Override
	public void updatePass(String bianHao, String newPass) {
		teacherDao.updatePass(bianHao, newPass);
	}
}
