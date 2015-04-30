/**
 * 
 */
package com.xscj.teacher.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionContext;
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
 *
 */
public class TeacherScoreAddAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6234943085838266469L;
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
	}
	/**
	 * 
	 * 请求处理区
	 * */
	@Override
	public String execute() throws Exception {
		
		if (gradeID==null || courseID == null || examType == null) {
			return INPUT;
		}
		if (!examType.equals("期中")&& !examType.equals("期末")) {
			return INPUT;
		}
		teacherName = teachingArrange.getTeacherNameByTeaching(gradeID, xueqi, courseID.trim());
		ActionContext actionContext = ActionContext.getContext();
		String teacherNameSession = (String)actionContext.getSession().get("teacherName");
		if(teacherName==null || !teacherName.equals(teacherNameSession))
			return INPUT;
		
		
		grade = gradeSetUp.getGradeBybianHao(gradeID.trim());//班级信息
		course = coursePlan.getCourseByCourseID(courseID.trim());//课程信息	
		subStudents = stuService.getAllSubStudentsByGXTC(gradeID, xueqi, examType, courseID);
		
		if (subStudents.size()==0) {
			actionContext.getSession().put("ErrorFlag", true);
			return INPUT;
		}
		else {
			actionContext.getSession().put("ErrorFlag", false);
		}
		
		SimpleDateFormat dFormat = new SimpleDateFormat("YYYY-MM-dd");
		currentDate = dFormat.format(new Date());
		
		
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
