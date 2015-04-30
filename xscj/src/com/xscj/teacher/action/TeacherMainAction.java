/**
 * 
 */
package com.xscj.teacher.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.TeachingTable;
import com.xscj.service.TeacherManager;
import com.xscj.service.TeachingArrange;

/**
 * @author leorain
 *
 */
public class TeacherMainAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2197447525599867392L;
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	
	private List<TeachingTable>teachingTables;
 
	@Override
	public String execute() throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		String userName = (String)actionContext.getSession().get("userName");//从session中获取教师编号
		String userRole = (String)actionContext.getSession().get("userRole");
	 
		if(userName != null && userRole.equals("教师"))
		{
			teachingTables = teachingArrange.getTeachingTables(userName);
			String teacherName = teacherManager.getTeacherNameByTeacherID(userName);
			actionContext.getSession().put("teacherName", teacherName);
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
	}

	public List<TeachingTable> getTeachingTables() {
		return teachingTables;
	}

	public void setTeachingTables(List<TeachingTable> teachingTables) {
		this.teachingTables = teachingTables;
	}
}
