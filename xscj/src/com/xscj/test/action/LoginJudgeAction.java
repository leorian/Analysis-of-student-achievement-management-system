/**
 * 
 */
package com.xscj.test.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.AdministratorManager;
import com.xscj.service.StuService;
import com.xscj.service.TeacherManager;
import com.xscj.util.Util;

/**
 * @author leorain
 *
 */
public class LoginJudgeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1339403739798171497L;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	
	@Autowired
	@Qualifier("administratorManagerImpl")
	private AdministratorManager administratorManager;
	
	private String username;
	private String password;
	private String certCode;
	private String userRole;
  
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		ActionContext ctx = ActionContext.getContext();
		String certCodeString = (String)ctx.getSession().get("certCode"); 
		if (!certCode.equalsIgnoreCase(certCodeString)) {
			map.put("judge","certCodeFail");
		}
		else {
			if(userRole.equals("学生"))
			{
				
				 if(Util.isNumeric(username) && stuService.isExisits(Integer.parseInt(username), password) > 0){
					 
					map.put("stuFound", true);
					ctx.getSession().put("userName", username);
					ctx.getSession().put("userRole", userRole);
			  }	 
			}else if("教师".equals(userRole))
			{
			if (teacherManager.isExisits(username, password) > 0) {
					map.put("teacherFound", true);
					ctx.getSession().put("userName", username);
					ctx.getSession().put("userRole", userRole);
				}
			}else if("管理员".equals(userRole))
			{
				if (administratorManager.isExisits(username, password) > 0) {
					map.put("administratorFound", true);
					ctx.getSession().put("userName", username);
					ctx.getSession().put("userRole", userRole);
				}
			}
			
		}
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCertCode() {
		return certCode;
	}
	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
}
