package com.xscj.test.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.TeacherManager;

 
public class TeacherSearchTestAction extends ActionSupport {
	/**
	 * @author leorain
	 * @date 2014-4-15 下午7:45:27
	 */
	private static final long serialVersionUID = 5757892802419038017L;
	
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	private String teacherBianHao;
	private int n;
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		if(teacherBianHao==null || teacherBianHao.trim().equals(""))
		{
			n = 0;
		}
		else {
			n = teacherManager.isExisit(teacherBianHao);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("flag", n);
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public String getTeacherBianHao() {
		return teacherBianHao;
	}
	public void setTeacherBianHao(String teacherBianHao) {
		this.teacherBianHao = teacherBianHao;
	}
	
}
