/**
 * 
 */
package com.xscj.delete.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.GradeSetUp;
import com.xscj.service.TeacherManager;
import com.xscj.service.TeachingArrange;

/**
 * @author leorain
 *
 */
public class TeacherDeleteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5773379591633913234L;
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	private String bianHao;

	public String getBianHao() {
		return bianHao;
	}

	public void setBianHao(String bianHao) {
		this.bianHao = bianHao;
	}

	@Override
	public String execute() throws Exception {
		if(gradeSetUp.hasRecord(bianHao) > 0)
		{
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("teacherAdviserDelErrorFlag", true);
			return INPUT;
		}
		else if(teachingArrange.hasRecord(bianHao) >  0)
		{
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("teacherDelErrorFlag", true);
			return INPUT;
		}
		teacherManager.deleteTeacherByBianHao(bianHao);
		return SUCCESS;
	}
	
}
