/**
 * 
 */
package com.xscj.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.domain.TeachingGidXq;
import com.xscj.service.GradeSetUp;
import com.xscj.service.TeachingArrange;
import com.xscj.util.Util;

/**
 * @author leorain
 *@date 2014-4-20 上午10:02:00
 */
public class TeachingArrangeModifyAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9208383736895093863L;
	
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	private Grade grade;
	private String gradeChose;
	private String xueqiChose;
	private List<TeachingGidXq> teachingGidXqs;
	@Override
	public String execute() throws Exception {
		if(gradeChose == null || xueqiChose == null)
			return INPUT;
		if(gradeChose.trim().equals("") || xueqiChose.trim().equals(""))
			return INPUT;
		if(!Util.isNumeric(xueqiChose)) {
			return INPUT;
		}
		if(teachingArrange.isExisits(gradeChose, Integer.parseInt(xueqiChose)) == 0)
		{
			 ActionContext ctx = ActionContext.getContext();
			 ctx.getSession().put("teachingArrangeFlag", true);
			return INPUT;
		}
		grade = gradeSetUp.getGradeBybianHao(gradeChose);
		teachingGidXqs = teachingArrange.geTeachingGidXqs(gradeChose, Integer.parseInt(xueqiChose));
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
	public String getGradeChose() {
		return gradeChose;
	}
	public void setGradeChose(String gradeChose) {
		this.gradeChose = gradeChose;
	}
	public String getXueqiChose() {
		return xueqiChose;
	}
	public void setXueqiChose(String xueqiChose) {
		this.xueqiChose = xueqiChose;
	}
	public List<TeachingGidXq> getTeachingGidXqs() {
		return teachingGidXqs;
	}
	public void setTeachingGidXqs(List<TeachingGidXq> teachingGidXqs) {
		this.teachingGidXqs = teachingGidXqs;
	}
	
}
