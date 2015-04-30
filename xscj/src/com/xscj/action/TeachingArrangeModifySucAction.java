/**
 * 
 */
package com.xscj.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.TeachingArrange;
import com.xscj.util.Util;

/**
 * @author leorain
 *@date 2014-4-20 上午10:02:00
 */
public class TeachingArrangeModifySucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9208383736895093863L;
	
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
 
	private String gradeChose;
	private String xueqiChose;
	private List<String> courseID;
 
	@Override
	public String execute() throws Exception {
		if(gradeChose == null || xueqiChose == null || courseID == null || gradeChose.trim().equals("") || xueqiChose.trim().equals(""))
			return INPUT;
		
		if (!Util.isNumeric(xueqiChose)) {
			return INPUT;
		}
		 teachingArrange.deleteTeaching(gradeChose, Integer.parseInt(xueqiChose), courseID);
		return SUCCESS;
	}
	@Override
	public void validate() {
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
	public List<String> getCourseID() {
		return courseID;
	}
	public void setCourseID(List<String> courseID) {
		this.courseID = courseID;
	}
	
	
	
}
