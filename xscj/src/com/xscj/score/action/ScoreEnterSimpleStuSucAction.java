/**
 * 
 */
package com.xscj.score.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.domain.Score;
import com.xscj.domain.SubStudent;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;
import com.xscj.service.StuService;

/**
 * @author leorain
 * @date 2014-4-7 上午9:00:45
 */
public class ScoreEnterSimpleStuSucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1548653597424973933L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
		scoreList = new ArrayList<Score>();
		for (int i = 0; i < courseIDs.size(); i++) {
			Score score = new Score();
			score.setGradeID(gradeID);
			score.setStuXueHao(stuXueHao);
			score.setXueqi(xueqi);
			score.setExamTime(examTime);
			score.setExamType(examType);
			score.setCourseID(courseIDs.get(i));
			score.setScore(scores.get(i));
			scoreList.add(score);
		}
		scoreService.addScoresToDb(scoreList);
		grade = gradeSetUp.getGradeBybianHao(gradeID);
		subStudent = stuService.getSubStudentByXueHao(stuXueHao);
	}
	private String gradeID;//班级编号
	private int stuXueHao;//学生学号
	private SubStudent subStudent;
	private Grade grade;
	private int xueqi;//学期
	private String examType;//考试类型
	private String examTime;//考试时间
	private List<String>courseIDs;//课程编号
	private List<String> courseNames;
	private List<Double> scores;//课程考试成绩
	
	private List<Score> scoreList;
	

	public List<Score> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<Score> scoreList) {
		this.scoreList = scoreList;
	}

	public String getGradeID() {
		return gradeID;
	}

	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}

	public int getStuXueHao() {
		return stuXueHao;
	}

	public void setStuXueHao(int stuXueHao) {
		this.stuXueHao = stuXueHao;
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

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public List<String> getCourseIDs() {
		return courseIDs;
	}

	public void setCourseIDs(List<String> courseIDs) {
		this.courseIDs = courseIDs;
	}

	public List<Double> getScores() {
		return scores;
	}

	public void setScores(List<Double> scores) {
		this.scores = scores;
	}

	public List<String> getCourseNames() {
		return courseNames;
	}

	public void setCourseNames(List<String> courseNames) {
		this.courseNames = courseNames;
	}

	public SubStudent getSubStudent() {
		return subStudent;
	}

	public void setSubStudent(SubStudent subStudent) {
		this.subStudent = subStudent;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	

}
