/**
 * 
 */
package com.xscj.score.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.ScoreByGidCid;
import com.xscj.service.ScoreService;

/**
 * @author leorain
 *@date 2014-4-3 下午7:44:03
 *通过班级编号以及课程编号获得某个班级所有的学生考试成绩
 */
public class ScoreQueryResultThirdAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4902642490617924608L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	private String gradeID;//班级编号
	private String courseID;//课程编号
	public String getGradeID() {
		return gradeID;
	}
	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	private List<ScoreByGidCid> scoreByGidCids; //查询到的成绩相关信息
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	public void validate() {
		/*scoreByGidCids = scoreService.getScoreByGidCids(gradeID, courseID);*/
	}
	public List<ScoreByGidCid> getScoreByGidCids() {
		return scoreByGidCids;
	}
	public void setScoreByGidCids(List<ScoreByGidCid> scoreByGidCids) {
		this.scoreByGidCids = scoreByGidCids;
	}

}
