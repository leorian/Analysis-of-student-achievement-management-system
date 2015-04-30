/**
 * 
 */
package com.xscj.action;

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
 *@date 2014-4-13 上午8:09:06
 */
public class StuSeeingAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5688802378881904185L;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private String xuehao;
	
	private Student student;
	
	private Grade grade;

	@Override
	public String execute() throws Exception {
		if(xuehao == null ||xuehao.trim().equals("") || !Util.isNumeric(xuehao) || stuService.isExisits(Integer.parseInt(xuehao))==0)
			return INPUT;
		student = stuService.getStudent(Integer.parseInt(xuehao));
		grade = gradeSetUp.getGradeBystuXueHao(Integer.parseInt(xuehao));
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
	
		

}
