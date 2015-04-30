/**
 * 
 */
package com.xscj.teacher.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Grade;
import com.xscj.domain.Score;
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;

/**
 * @author leorain
 *@date 2014-3-24 下午7:41:19
 *
 *成绩注册完成Action
 */
public class TeacherScoreAddSucAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4042685893359076689L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	private List<Score>scores;//分数记录表对应行集合
	
	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	private String gradeID;//班级编号
	private Grade grade;//班级信息
	private String courseID; //课程编号
	private Course course;//课程信息
	private String examTime; //考试时间
	private String examType; //考试类型
	private int xueqi;//学期
	private List<Integer> stuXueHao; //学号
	private List<String> stuName;//姓名
	public List<String> getStuName() {
		return stuName;
	}
	public void setStuName(List<String> stuName) {
		this.stuName = stuName;
	}
	private List<Double> score; //分数
	
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
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}
	public List<Integer> getStuXueHao() {
		return stuXueHao;
	}
	public void setStuXueHao(List<Integer> stuXueHao) {
		this.stuXueHao = stuXueHao;
	}
	public List<Double> getScore() {
		return score;
	}
	public void setScore(List<Double> score) {
		this.score = score;
	}
	
	private double totalScore = 0;//总分
	private int stuCount;//某个班级学生人数
	private double avgScore;//平均分
	private double maxScore;//最高分
	private double minScore;//最低分
	private int failCount = 0;//不及格人数
	private int sucCount = 0;//及格人数
	private int goodCount = 0;//良好人数
	private int secondaryCount = 0;//中等人数
	private int excellentCount = 0;//优秀人数
	
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	public int getStuCount() {
		return stuCount;
	}
	public void setStuCount(int stuCount) {
		this.stuCount = stuCount;
	}
	public double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}
	public double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	public double getMinScore() {
		return minScore;
	}
	public void setMinScore(double minScore) {
		this.minScore = minScore;
	}
	public int getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	public int getSucCount() {
		return sucCount;
	}
	public void setSucCount(int sucCount) {
		this.sucCount = sucCount;
	}
	public int getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}
	public int getSecondaryCount() {
		return secondaryCount;
	}
	public void setSecondaryCount(int secondaryCount) {
		this.secondaryCount = secondaryCount;
	}
	public int getExcellentCount() {
		return excellentCount;
	}
	public void setExcellentCount(int excellentCount) {
		this.excellentCount = excellentCount;
	}
	@Override
	public void validate() {
		
		
	}
	@Override
	public String execute() throws Exception {
		
		if(gradeID == null || courseID == null || examTime == null || examType == null || stuXueHao == null || stuName == null)
		{
			return INPUT;
		}
		
		scores = new ArrayList<Score>();
		stuCount = stuXueHao.size();//学生总人数
		maxScore = 0;
		minScore = 100;
		for (int i = 0; i < stuXueHao.size(); i++) {
			if(score.get(i) >= maxScore)
				maxScore = score.get(i);//求最高分
			if(score.get(i) <= minScore)
				minScore = score.get(i);//求最低分
			totalScore = totalScore + score.get(i);//总分
			Score simpleScore = new Score();
			simpleScore.setGradeID(gradeID);
			simpleScore.setStuXueHao(stuXueHao.get(i));//设置学号
			simpleScore.setCourseID(courseID);//设置课程编号
			simpleScore.setExamTime(examTime);//设置考试时间
			simpleScore.setExamType(examType);//设置考试类型
			simpleScore.setXueqi(xueqi);//设置学期
			simpleScore.setScore(score.get(i));//设置分数
			scores.add(simpleScore);
			int s = (int)(score.get(i)/10);
			switch (s) {
			case 10:
			case 9:
				excellentCount++;
				break;
			case 8:
				secondaryCount++;
				break;
			case 7:
				goodCount++;
				break;
			case 6:
				sucCount++;
				break;
			default:
				failCount++;
				break;
			}
		}
		avgScore = Math.round(totalScore / stuCount);//平均分
		scoreService.addScoresToDb(scores);
		grade = gradeSetUp.getGradeBybianHao(gradeID);
		course = coursePlan.getCourseByCourseID(courseID);
		return SUCCESS;
	}
	

}
