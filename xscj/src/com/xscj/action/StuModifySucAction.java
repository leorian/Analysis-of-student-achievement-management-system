package com.xscj.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.domain.Student;
import com.xscj.service.GradeSetUp;
import com.xscj.service.StuService;

public class StuModifySucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5405954884249043146L;

	/**
	 * 
	 */
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService; 
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	 
	private int xuehao;
	private Student student;
	private Grade grade;
	@Override
	public void validate() {
		 
	}

	@Override
	public String execute() throws Exception {
		if(student == null)
			return INPUT;
		stuService.updateStuInfo(xuehao, student);
		grade = gradeSetUp.getGradeBystuXueHao(xuehao);
		return SUCCESS;
	}
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public int getXuehao() {
		return xuehao;
	}

	public void setXuehao(int xuehao) {
		this.xuehao = xuehao;
	}
	
}
