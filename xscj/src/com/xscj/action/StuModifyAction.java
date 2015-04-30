/**
 * 
 */
package com.xscj.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.domain.Student;
import com.xscj.service.GradeSetUp;
import com.xscj.service.StuService;
import com.xscj.util.Util;

/**
 * @author leorain
 *@date 2014-4-13 上午9:06:24
 */
public class StuModifyAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9059023322265498359L;
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private String xuehao;
	
	private Student student;
	
	private Grade grade;
	
	private List<Grade> grades;

	@Override
	public String execute() throws Exception {
		if(xuehao == null ||xuehao.trim().equals("") || !Util.isNumeric(xuehao) || stuService.isExisits(Integer.parseInt(xuehao))==0)
			return INPUT;
		student = stuService.getStudent(Integer.parseInt(xuehao));
		grade = gradeSetUp.getGradeBystuXueHao(Integer.parseInt(xuehao));
		grades = gradeSetUp.getAllGrades();
		return SUCCESS;
	}

	@Override
	public void validate() {
		
	}

	public String getXuehao() {
		return xuehao;
	}

	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
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

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

}
