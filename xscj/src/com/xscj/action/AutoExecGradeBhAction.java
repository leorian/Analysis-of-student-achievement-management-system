/**
 * 
 */
package com.xscj.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.service.CategoryService;

/**
 * @author leorain
 *@date 2014-3-18 下午7:03:22
 */
public class AutoExecGradeBhAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 620302265220993036L;
	@Autowired
	@Qualifier("categoryServiceImpl")
	private CategoryService categoryService;
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		String bianHao = "G"+ (10000+categoryService.getCount("G")+1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bianHao", bianHao);
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}

}
