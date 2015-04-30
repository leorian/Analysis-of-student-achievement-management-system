/**
 * 
 */
package com.xscj.delete.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.GradeSetUp;

/**
 * @author leorain
 *
 */
public class GradeDeleteAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4648641304245039424L;
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private String[] gradeDel;//需要删除的课程编号集合

	public String[] getGradeDel() {
		return gradeDel;
	}

	public void setGradeDel(String[] gradeDel) {
		this.gradeDel = gradeDel;
	}

	@Override
	public String execute() throws Exception {
		if(gradeDel == null)
		{
			
		}
		else {
			gradeSetUp.deleteChoiceGrades(gradeDel);
		}
		return SUCCESS;
	}
	
}
