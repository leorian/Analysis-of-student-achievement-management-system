/**
 * 
 */
package com.xscj.score.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.ScoreCount;
import com.xscj.domain.ScoreSmall;
import com.xscj.domain.ScoreTotal;
import com.xscj.service.ScoreService;
import com.xscj.service.TeachingArrange;

/**
 * @author leorain
 *@date 2014-4-14 下午8:54:02
 */
public class ScoreCountSucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2824127614723638295L;
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	private String gradeID;
	private int xueqi;
	private String examType;
	private List<ScoreCount> scoreCounts;
	private List<Course> courses;
	private List<ScoreTotal> scoreTotals;
	
 
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	public void validate() {
		scoreCounts = scoreService.getScoreCounts(gradeID, xueqi, examType);
		courses = teachingArrange.getCourses(gradeID, xueqi);
		int n= scoreCounts.size()/courses.size();
		scoreTotals = new ArrayList<ScoreTotal>();
		for (int i = 0; i < n; i++) {
			int xuehao = scoreCounts.get(i*courses.size()).getStuXueHao();
			String name = scoreCounts.get(i*courses.size()).getStuName();
			ScoreTotal scoreTotal = new ScoreTotal();
			scoreTotal.setXuehao(xuehao);
			scoreTotal.setName(name);
			double totalScore = 0;
			List<ScoreSmall> scoreSmalls = new ArrayList<ScoreSmall>();
			for (int j = 0; j < courses.size(); j++) {
				totalScore = totalScore + scoreCounts.get(i*courses.size()+j).getScore();
				ScoreSmall scoreSmall = new ScoreSmall();
				scoreSmall.setCourseID(scoreCounts.get(i*courses.size()+j).getCourseID());
				scoreSmall.setCourseName(scoreCounts.get(i*courses.size()+j).getCourseName());
				scoreSmall.setScore(scoreCounts.get(i*courses.size()+j).getScore());
				scoreSmalls.add(scoreSmall);
			}
			scoreTotal.setScoreSmalls(scoreSmalls);
			scoreTotal.setTotalScore(totalScore);
			scoreTotals.add(scoreTotal);
		}
	/*	System.out.println(scoreCounts.size());
		System.out.println(courses.size());*/
	}
	public String getGradeID() {
		return gradeID;
	}
	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public List<ScoreCount> getScoreCounts() {
		return scoreCounts;
	}
	public void setScoreCounts(List<ScoreCount> scoreCounts) {
		this.scoreCounts = scoreCounts;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public List<ScoreTotal> getScoreTotals() {
		return scoreTotals;
	}
	public void setScoreTotals(List<ScoreTotal> scoreTotals) {
		this.scoreTotals = scoreTotals;
	}
	
}
