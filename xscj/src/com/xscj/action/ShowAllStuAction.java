package com.xscj.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.SimpleStudent;
import com.xscj.service.StuService;

public class ShowAllStuAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3141028465645313809L;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	
	private List<SimpleStudent> simpleStudents;
	public List<SimpleStudent> getSimpleStudents() {
		return simpleStudents;
	}
	private int rowTotal; //总共有多少条学生记录
	private int pageSize = 10; //每一页可以显示多少条记录
	private int pageNow = 1; //当前是第几页
	private int pageTotal; //总共有多少页
	public int getPageSize() {
		return pageSize;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getRowTotal() {
		return rowTotal;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	@Override
	public String execute() throws Exception {
		//rowTotal = stuService.getStuCount();
		rowTotal = stuService.getValidStuCount();
		pageTotal = ((rowTotal%pageSize)==0)?(rowTotal/pageSize):(rowTotal/pageSize+1);
		if(pageNow<=0)
			pageNow=1;
		if(pageNow > pageTotal)
			pageNow=pageTotal;
		if(rowTotal > 0)
		{
			simpleStudents = stuService.queryByPage(pageSize, pageNow);
		}
		return SUCCESS;
	}
}
