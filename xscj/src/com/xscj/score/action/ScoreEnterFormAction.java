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
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;
import com.xscj.service.StuService;
import com.xscj.service.TeachingArrange;

/**
 * @author leorain
 *@date 2014-3-31 上午11:57:37
 *成绩录入主页面Action
 */
public class ScoreEnterFormAction extends ActionSupport {

	/**
	 * @author leorain
	 * @date 2014-3-31 上午11:57:31
	 */
	private static final long serialVersionUID = -9084241344409632807L;
	/**
	 * 
	 * 字段区
	 * */
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp; //班级操作业务逻辑操作类
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;//课程操作业务逻辑操作类
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange; //教学安排业务逻辑操作类
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService;
	private String gradeID;//班级编号
	private int xueqi; //学期
	private String courseID;//课程编号
	private Grade grade;//通过班级编号获得班级信息
	private Course course;//通过课程编号获得课程信息
	private String teacherName;//授课教师
	private List<SubStudent> subStudents; 
	private String currentDate; //当前日期
	private String examType;
	/**
	 * 
	 * 验证区
	 * */
	@Override
	public void validate() {
		grade = gradeSetUp.getGradeBybianHao(gradeID.trim());//班级信息
		course = coursePlan.getCourseByCourseID(courseID.trim());//课程信息	
		teacherName = teachingArrange.getTeacherNameByTeaching(gradeID, xueqi, courseID.trim());//没有相关授课教师就会返回空	
		//subStudents = stuService.getAllSubStudentsByGradeID(gradeID);//某个班级对应的所有学生
		subStudents = stuService.getAllSubStudentsByGXTC(gradeID, xueqi, examType, courseID);
		SimpleDateFormat dFormat = new SimpleDateFormat("YYYY-MM-dd");
		currentDate = dFormat.format(new Date());
	}
	/**
	 * 
	 * 请求处理区
	 * */
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	/**
	 * 
	 * 字段setter和getter方法区
	 * 
	 * */
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
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public List<SubStudent> getSubStudents() {
		return subStudents;
	}
	public void setSubStudents(List<SubStudent> subStudents) {
		this.subStudents = subStudents;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
}
