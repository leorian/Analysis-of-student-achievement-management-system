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
import com.xscj.domain.IDNameScore;
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;
import com.xscj.service.ScoreService;
import com.xscj.service.TeachingArrange;

/**
 * @author leorain
 *
 *@date 2014-4-10 上午9:02:15
 *
 */
public class TeacherScoreModifyAction extends ActionSupport {

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
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	private String gradeID;
	private int xueqi;
	private String examType;
	private String courseID;
	
	private Grade grade;
	private Course course;
	private List<IDNameScore> idNameScores;
	private String teacherName;
	
	
	@Override
	public String execute() throws Exception {
		if (gradeID==null || courseID == null || examType == null) {
			return INPUT;
		}
		if (!examType.equals("期中") && !examType.equals("期末")) {
			return INPUT;
		}
		
		teacherName = teachingArrange.getTeacherNameByTeaching(gradeID, xueqi, courseID.trim());
		ActionContext actionContext = ActionContext.getContext();
		String teacherNameSession = (String)actionContext.getSession().get("teacherName");
		if(teacherName==null || !teacherName.equals(teacherNameSession))
			return INPUT;
		
		grade = gradeSetUp.getGradeBybianHao(gradeID);
		course = coursePlan.getCourseByCourseID(courseID);
		idNameScores = scoreService.getIdNameScores(gradeID, xueqi, examType, courseID);
		
		if (idNameScores == null || idNameScores.size()==0) {
			actionContext.getSession().put("ModifyFlag", true);
			return INPUT;
		}
		else {
			actionContext.getSession().put("ModifyFlag", false);
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
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	
	
	
}
