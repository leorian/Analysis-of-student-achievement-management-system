/**
 * 
 */
package com.xscj.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Course;
import com.xscj.domain.Grade;
import com.xscj.domain.SubTeacher;
import com.xscj.domain.SuperCourse;
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;
import com.xscj.service.TeacherManager;
import com.xscj.service.TeachingArrange;
import com.xscj.util.Util;

/**
 * @author leorain
 * @date 2014-3-19 下午5:44:02
 * 
 * 班级 课程 授课老师安排
 *
 */
public class TeachingArrangeFormAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5258788874477793934L;
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	private String gradeChose;//选择的班级的班级编号
	private Grade grade; //选择的班级对应的详细信息
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	private String xueqiChose;//选择的学期
	
	private List<Course> alreadyChoseCourses; //得到已经选择过的课程列表
	public List<Course> getAlreadyChoseCourses() {
		return alreadyChoseCourses;
	}
	public void setAlreadyChoseCourses(List<Course> alreadyChoseCourses) {
		this.alreadyChoseCourses = alreadyChoseCourses;
	}
	public String getGradeChose() {
		return gradeChose;
	}
	public void setGradeChose(String gradeChose) {
		this.gradeChose = gradeChose;
	}
	public String getXueqiChose() {
		return xueqiChose;
	}
	public void setXueqiChose(String xueqiChose) {
		this.xueqiChose = xueqiChose;
	}

	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan; //课程计划业务逻辑处理类
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager; //教师管理业务逻辑处理类
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
 
	private List<Course> courses;//所有课程
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
 private List<SuperCourse> superCourses; // 课程编号 课程姓名 授课老师集合
 

	public List<SuperCourse> getSuperCourses() {
	return superCourses;
}
public void setSuperCourses(List<SuperCourse> superCourses) {
	this.superCourses = superCourses;
}
	@Override
	public String execute() throws Exception {
		if(gradeChose == null || xueqiChose == null)
			return INPUT;
		if(gradeChose.trim().equals("") || xueqiChose.trim().equals(""))
			return INPUT;
		if(!Util.isNumeric(xueqiChose))
			return INPUT;
		if(coursePlan.getValidCourseCount() == teachingArrange.isExisits(gradeChose, Integer.parseInt(xueqiChose)))
		{
			ActionContext act = ActionContext.getContext();
			act.getSession().put("teachingArrangeStatus", true);
			return INPUT;
		}
		grade = gradeSetUp.getGradeBybianHao(gradeChose);//得到选择的班级编号的班级具体信息
		//courses = coursePlan.getAllCourses(); //得到所有的课程信息，包括课程编号		课程名称
		courses = coursePlan.getValidAllCourses();
		alreadyChoseCourses = coursePlan.getCoursesByGradeAndXueQi(gradeChose, Integer.parseInt(xueqiChose));
		 for(int j=0; j<alreadyChoseCourses.size(); j++)
		 {
			 for(int i=0; i<courses.size(); i++)
			 {
				 if(courses.get(i).getBianHao().equals(alreadyChoseCourses.get(j).getBianHao()))
					 courses.remove(i);
			 }
	 }
		superCourses = new ArrayList<SuperCourse>();
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			SuperCourse superCourse = new SuperCourse();
			superCourse.setCourseBianHao(course.getBianHao());
			superCourse.setCourseName(course.getName());
			List<SubTeacher> teachers  = teacherManager.getAllSubTeachersByCourseID(course.getBianHao()); // 某个课程所对应的授课教师信息，包括教师编号和教师姓名
			if (teachers.isEmpty()) {
				teachers = teacherManager.getAllSubTeachers();//所有授课教师信息，包括教师编号和教师姓名
			}
			superCourse.setSubTeachers(teachers);
			superCourses.add(superCourse);
		}
		return SUCCESS;
	}
}