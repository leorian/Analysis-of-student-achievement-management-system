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
 * @author leorain 选择班级进行教学安排
 * @date 2014-3-20 上午9:30:46
 */
public class GradeTeachingChoseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6007582553747421510L;

	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;

	private int maxYear;
	private List<Grade> grades;

	public int getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	@Override
	public String execute() throws Exception {
		maxYear = gradeSetUp.getMaxYear();
		grades = gradeSetUp.getValidAllGrades(maxYear - 2, maxYear);
		return SUCCESS;
	}

}
