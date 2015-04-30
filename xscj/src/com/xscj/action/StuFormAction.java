package com.xscj.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.service.GradeSetUp;
import com.xscj.service.StuService;

public class StuFormAction extends ActionSupport {

	/**
	 * 		管理员——学生注册页面
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private int maxYear;
	public int getMaxYear() {
		return maxYear;
	}
	public List<Grade> getGrades() {
		return grades;
	}
	private List<Grade>grades;
	private String now;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public String getNow() {
		return now;
	}
	private int count;
	public int getCount() {
		return count;
	}
	@Override
	public String execute() throws Exception {
		now = sdf.format(new Date());
		count = stuService.getValidStuCount();
		maxYear = gradeSetUp.getMaxYear();
		grades = gradeSetUp.getMaxYearGrades(maxYear);
		return SUCCESS;
	}
}
