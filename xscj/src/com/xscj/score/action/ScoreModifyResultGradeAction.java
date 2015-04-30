/**
 * 
 */
package com.xscj.score.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Grade;
import com.xscj.domain.IDNameScore;
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;

/**
 * @author leorain
 *
 *@date 2014-4-10 上午9:02:15
 *
 */
public class ScoreModifyResultGradeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7473328677047280168L;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	private String gradeID;
	private int xueqi;
	private String examType;
	private String courseID;
	
	private Grade grade;
	private Course course;
	private List<IDNameScore> idNameScores;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	public void validate() {
/*		System.out.println(gradeID + " " + xueqi + " " + examType + " " + courseID);
*/	
		grade = gradeSetUp.getGradeBybianHao(gradeID);
		course = coursePlan.getCourseByCourseID(courseID);
		idNameScores = scoreService.getIdNameScores(gradeID, xueqi, examType, courseID);
		
		//System.out.println(idNameScores.size());
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
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<IDNameScore> getIdNameScores() {
		return idNameScores;
	}
	public void setIdNameScores(List<IDNameScore> idNameScores) {
		this.idNameScores = idNameScores;
	}
	
	
	
	
}
