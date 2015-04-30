/**
 * 
 */
package com.xscj.score.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Grade;
import com.xscj.domain.SubStudent;
import com.xscj.service.GradeSetUp;
import com.xscj.service.StuService;
import com.xscj.service.TeachingArrange;

/**
 *@author leorain
 *@date 2014-4-7 上午6:57:32
 */
public class ScoreEnterSimpleStuAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 198282266324008025L;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
		grade = gradeSetUp.getGradeBystuXueHao(stuXueHao);//通过学生的学号获得班级信息
		courses = teachingArrange.getCourses(grade.getBianHao(), xueqi, examType,stuXueHao);
		subStudent = stuService.getSubStudentByXueHao(stuXueHao);
		SimpleDateFormat dFormat = new SimpleDateFormat("YYYY-MM-dd");
		currentDate = dFormat.format(new Date());
	}
	private int stuXueHao;//学号
	private int xueqi;//学期
	private String examType;//考试类型
	private Grade grade;
	private List<Course> courses;
	private SubStudent subStudent;
	private String currentDate;
	

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public SubStudent getSubStudent() {
		return subStudent;
	}

	public void setSubStudent(SubStudent subStudent) {
		this.subStudent = subStudent;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
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
	

}
