/**
 * 
 */
package com.xscj.score.action;

import java.util.Collections;
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
public class ScorePrinterAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2016382909585155916L;

	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private List<Grade> grades;
	
	private List<Integer> years;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
		grades = gradeSetUp.getAllGrades();
		years = gradeSetUp.getYears();
		Collections.sort(years);
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public List<Integer> getYears() {
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}
	
}
