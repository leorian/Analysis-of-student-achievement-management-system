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
import com.xscj.domain.ScoreXueqi;
import com.xscj.domain.SubStudent;
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;
import com.xscj.service.StuService;

/**
 * @author leorain
 *@date 2014-4-3 上午10:25:05
 *获得填入的学生学号和选择的课程
 */
public class ScoreQueryResultSecondAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4440522335643672446L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;//成绩操作业务逻辑处理类
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;//班级操作业务逻辑处理类
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;//课程操作业务逻辑处理
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;//学生操作业务逻辑处理
	
	private int stuXueHao;//学号
	private String courseID;//课程编号
	public int getStuXueHao() {
		return stuXueHao;
	}
	public void setStuXueHao(int stuXueHao) {
		this.stuXueHao = stuXueHao;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	private List<ScoreXueqi> scoreXueqis;
	public List<ScoreXueqi> getScoreXueqis() {
		return scoreXueqis;
	}
	public void setScoreXueqis(List<ScoreXueqi> scoreXueqis) {
		this.scoreXueqis = scoreXueqis;
	}
	
	private Grade grade;//班级信息
	private Course course;//课程信息
	private SubStudent subStudent;//学生信息
	
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
	public SubStudent getSubStudent() {
		return subStudent;
	}
	public void setSubStudent(SubStudent subStudent) {
		this.subStudent = subStudent;
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	public void validate() {
		scoreXueqis = scoreService.getScoreXueqis(stuXueHao, courseID);
		grade = gradeSetUp.getGradeBystuXueHao(stuXueHao);//通过学生学号获得该学生的班级信息
		course = coursePlan.getCourseByCourseID(courseID);//通过课程编号获得该学生的课程信息
		subStudent = stuService.getSubStudentByXueHao(stuXueHao);//通过学生学号获得该学生的姓名
		
		/*System.out.println(grade.getYear()+"届"+grade.getClassID()+"班");
		System.out.println(course.getName());
		System.out.println(subStudent.getStuName());
		System.out.println(scoreXueqis.size());*/
	}

}
