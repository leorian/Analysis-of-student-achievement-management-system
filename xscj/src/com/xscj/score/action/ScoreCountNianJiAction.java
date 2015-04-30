/**
 * 
 */
package com.xscj.score.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.GradeInfo;
import com.xscj.domain.ScoreGroup;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;

/**
 * @author leorain
 *
 */
public class ScoreCountNianJiAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4475992531739795458L;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	private String nianJi;
	private String xueqi;
	private String examType;
	
	private List<GradeInfo> gradeInfos;
	private Map<GradeInfo, List<ScoreGroup>> map;
	
	@Override
	public String execute() throws Exception {
		if(nianJi == null ||xueqi == null || examType == null || nianJi.trim().equals("") || xueqi.trim().equals("") || examType.equals(""))
		{
			return INPUT;
		}
		gradeInfos = gradeSetUp.getGradeInfos(Integer.parseInt(nianJi));
		map = new HashMap<GradeInfo, List<ScoreGroup>>();
		for(int i=0;i<gradeInfos.size(); i++)
		{
			map.put(gradeInfos.get(i),  scoreService.getScoreGroups(gradeInfos.get(i).getGradeID(), Integer.parseInt(xueqi), examType));
			
		}
		return SUCCESS;
	}

	@Override
	public void validate() {

	}

	public String getNianJi() {
		return nianJi;
	}

	public void setNianJi(String nianJi) {
		this.nianJi = nianJi;
	}

	public String getXueqi() {
		return xueqi;
	}

	public void setXueqi(String xueqi) {
		this.xueqi = xueqi;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public List<GradeInfo> getGradeInfos() {
		return gradeInfos;
	}

	public void setGradeInfos(List<GradeInfo> gradeInfos) {
		this.gradeInfos = gradeInfos;
	}

	public Map<GradeInfo, List<ScoreGroup>> getMap() {
		return map;
	}

	public void setMap(Map<GradeInfo, List<ScoreGroup>> map) {
		this.map = map;
	}
	
}
