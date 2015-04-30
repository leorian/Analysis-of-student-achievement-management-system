/**
 * 
 */
package com.xscj.admin.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.AdministratorManager;

/**
 * @author leorain
 *
 */
public class AdminPassModifyAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7652431825461314873L;
	
	@Autowired
	@Qualifier("administratorManagerImpl")
	private AdministratorManager administratorManager;
	
	private String result;
	private String adminName;
	private String oldPass;
	private String newPass;
	private String rePass;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		boolean flag = false;
		if(administratorManager.isExisits(adminName, oldPass)>0)
		{
			administratorManager.updatePass(adminName, newPass);
			ActionContext ctxActionContext = ActionContext.getContext();
			ctxActionContext.getSession().put("userName", null);
			ctxActionContext.getSession().put("userRole", null);
			flag = true;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("flag", flag);
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getRePass() {
		return rePass;
	}
	public void setRePass(String rePass) {
		this.rePass = rePass;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
}
