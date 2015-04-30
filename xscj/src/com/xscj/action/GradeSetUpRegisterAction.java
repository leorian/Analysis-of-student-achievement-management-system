/**
 * 
 */
package com.xscj.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.service.CategoryService;
import com.xscj.service.GradeSetUp;

/**
 * @author leorain
 *
 */
public class GradeSetUpRegisterAction extends ActionSupport {

	/**
	 * @author leorain
	 * @date 2014-3-18 下午7:55:37
	 */
	private static final long serialVersionUID = -4766023513573972878L;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("categoryServiceImpl")
	private CategoryService categoryService;
	
	private Grade grade;

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@Override
	public String execute() throws Exception {
		gradeSetUp.addGradeToDb(grade);
		categoryService.updateCount("G");
		return SUCCESS;
	}

}
