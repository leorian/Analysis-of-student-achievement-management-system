package com.xscj.stu.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.domain.SubStudent;
import com.xscj.service.GradeSetUp;
import com.xscj.service.StuService;

public class StuMainAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5622477517425279445L;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	
	private Grade grade;
	 
	private SubStudent subStudent;

	@Override
	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		String userName = (String)ctx.getSession().get("userName");
		String userRole = (String)ctx.getSession().get("userRole");
		if(userName!=null && userRole.equals("学生"))
		{
			grade = gradeSetUp.getGradeBystuXueHao(Integer.parseInt(userName));
			subStudent = stuService.getSubStudentByXueHao(Integer.parseInt(userName));
			ctx.getSession().put("userAliaName", subStudent.getStuName());
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public SubStudent getSubStudent() {
		return subStudent;
	}

	public void setSubStudent(SubStudent subStudent) {
		this.subStudent = subStudent;
	}
	
	

}
