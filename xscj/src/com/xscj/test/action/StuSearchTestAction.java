package com.xscj.test.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.StuService;

 
public class StuSearchTestAction extends ActionSupport {
	/**
	 * @author leorain
	 * @date 2014-4-15 下午7:45:27
	 */
	private static final long serialVersionUID = 5757892802419038017L;
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	private int n;
	
	private String stuXueHao;
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
		if (stuXueHao == null || stuXueHao.trim().equals("")) {
			n = 0;
		}else if (com.xscj.util.Util.isNumeric(stuXueHao)) {
			 n = stuService.isExisits(Integer.parseInt(stuXueHao));
		}
		else {
			n = 0;
		}
		 map.put("flag", n);
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	public String getStuXueHao() {
		return stuXueHao;
	}
	public void setStuXueHao(String stuXueHao) {
		this.stuXueHao = stuXueHao;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	
}
