/**
 * 
 */
package com.xscj.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.domain.SimpleStudent;
import com.xscj.service.GradeSetUp;
import com.xscj.service.StuService;

/**
 * @author leorain
 *@date 2014-4-13 下午8:01:58
 */
public class StuSearchSucByGradeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6572221422883925520L;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private String gradeID;

	private List<SimpleStudent> simpleStudents;
	
	private List<Grade> grades;
	
	@Override
	public String execute() throws Exception {
		simpleStudents = stuService.querySimpleStudents(gradeID);
		if(simpleStudents == null || simpleStudents.size() == 0)
			return INPUT;
		grades = gradeSetUp.getAllGrades();
		return SUCCESS;
	}

	@Override
	public void validate() {
		
	}

	public String getGradeID() {
		return gradeID;
	}

	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}

	public List<SimpleStudent> getSimpleStudents() {
		return simpleStudents;
	}

	public void setSimpleStudents(List<SimpleStudent> simpleStudents) {
		this.simpleStudents = simpleStudents;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	

}
