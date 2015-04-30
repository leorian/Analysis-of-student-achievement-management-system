package com.xscj.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.domain.Student;
import com.xscj.service.CategoryService;
import com.xscj.service.GradeSetUp;
import com.xscj.service.StuService;

public class StuFormRegisterAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7238388060614358410L;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService; 
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("categoryServiceImpl")
	private CategoryService categoryService;
	
	private int count;
	private Student student;
	private Grade grade;
	@Override
	public void validate() {
		
	}

	@Override
	public String execute() throws Exception {
		if(student==null)
			return INPUT;
		if(stuService.getStuCount() == 0){
			stuService.addStuDb(1, student);
			categoryService.updateCount("S");
			}
		else {
			int count = categoryService.getCount("S");
			stuService.addStuDb( count+1,student);
			categoryService.updateCount("S");
		}
		count = stuService.getValidStuCount();
		grade = gradeSetUp.getGradeBystuXueHao(count);
		return SUCCESS;
	}
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
}
