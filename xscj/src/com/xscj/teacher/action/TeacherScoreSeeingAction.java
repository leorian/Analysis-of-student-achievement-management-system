/**
 * 
 */
package com.xscj.teacher.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Grade;
import com.xscj.domain.ScoreByGCXT;
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;
import com.xscj.service.TeachingArrange;

/**
 * @author leorain
 *@date 2014-4-6 下午3:42:59
 *通过班级编号、课程编号、学期、考试类型
 *查询某个班级某个学期某次考试某个课程的考试情况
 */
public class TeacherScoreSeeingAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7893978704983467001L;
	
	@Autowired
	@Qualifier("scoreServiceImpl")
	private ScoreService scoreService;//成绩管理业务逻辑处理类
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;//班级管理业务逻辑处理类
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	private String gradeID;//班级编号
	private Grade grade;
	private String courseID;//课程编号
	private Course course;
	private int xueqi;//学期
	private String examType;//考试类型;
	private String teacherName;
	private List<ScoreByGCXT> scoreByGCXTs;
	@Override
	public String execute() throws Exception {
		
		if (gradeID == null || courseID == null || examType == null) {
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
		
		grade = gradeSetUp.getGradeBybianHao(gradeID);
		course = coursePlan.getCourseByCourseID(courseID);
		scoreByGCXTs = scoreService.getscByGCXTs(gradeID, courseID, xueqi, examType);
		
		if (scoreByGCXTs.size()==0) {
			actionContext.getSession().put("SeeFlag", true);
			return INPUT;
		}
		else {
			actionContext.getSession().put("SeeFlag", false);
		}
		
		return SUCCESS;
	}
	@Override
	public void validate() {
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
	public List<ScoreByGCXT> getScoreByGCXTs() {
		return scoreByGCXTs;
	}
	public void setScoreByGCXTs(List<ScoreByGCXT> scoreByGCXTs) {
		this.scoreByGCXTs = scoreByGCXTs;
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
	
}
