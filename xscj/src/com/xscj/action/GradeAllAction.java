/**
 * 
 */
package com.xscj.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.GradeInfo;
import com.xscj.service.GradeSetUp;

/**
 * @author leorain
 *@date 2014-4-16 上午8:21:58
 */
public class GradeAllAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6710984164365237179L;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
		gradeInfos = gradeSetUp.getGradeInfos();
	/*	for (int i = 0; i < gradeInfos.size(); i++) {
			System.out.println(gradeInfos.get(i).getClassID() + " " + gradeInfos.get(i).getStuCount());
		}*/
		
	}
	private List<GradeInfo> gradeInfos;

	public List<GradeInfo> getGradeInfos() {
		return gradeInfos;
	}

	public void setGradeInfos(List<GradeInfo> gradeInfos) {
		this.gradeInfos = gradeInfos;
	}
	

}
