/**
 * 
 */
package com.xscj.stu.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.StuService;

/**
 * @author leorain
 *
 */
public class PassModifyAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7652431825461314873L;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	private String result;
	private int xuehao;
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
		if(stuService.isExisits(xuehao, oldPass)>0)
		{
			stuService.updatePass(xuehao, newPass);
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
	public int getXuehao() {
		return xuehao;
	}
	public void setXuehao(int xuehao) {
		this.xuehao = xuehao;
	}

}
