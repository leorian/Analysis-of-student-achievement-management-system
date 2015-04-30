/**
 * 
 */
package com.xscj.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.service.GradeSetUp;

/**
 * @author leorain
 *
 */
public class StuSearchByGradeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6398323004033552123L;
	
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private List<Grade> grades;

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
		grades = gradeSetUp.getAllGrades();
	}
	
	

}
