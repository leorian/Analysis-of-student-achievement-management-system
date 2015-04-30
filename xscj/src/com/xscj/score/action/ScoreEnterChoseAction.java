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
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;

/**
 * @author leorain
 *@date 2014-3-31 上午8:51:10
 * 选择班级、选择学期、选择课程界面Action
 */
public class ScoreEnterChoseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1153631246396068601L;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp; //班级管理业务逻辑处理类
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan; //课程管理业务逻辑处理类
	
	private int maxYear;	//高一所对应得界
	private List<Grade> grades;	//所有班级（到目前为止前三界）
	private List<Course> courses; //所有课程
	public int getMaxYear() {
		return maxYear;
	}
	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}
	public List<Grade> getGrades() {
		return grades;
	}
	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	@Override
	public String execute() throws Exception {
		
		maxYear = gradeSetUp.getMaxYear();
		
		grades = gradeSetUp.getValidAllGrades(maxYear-2, maxYear);
		
		courses = coursePlan.getValidAllCourses();
		
		//以下为测试代码**********************************************
		/*System.out.println("高一所对应的的界数是："+maxYear);
			
		System.out.println("以下为目前在校存在的班级");
		for(int i=0; i<grades.size(); i++){
			System.out.println(grades.get(i).getYear()+"界"+grades.get(i).getClassID()+"班");
		}
		System.out.println("以上为目前在校存在的班级");
		
		System.out.println("以下为学校目前存在课程");
		for (int i = 0; i < courses.size(); i++) {
			System.out.println(courses.get(i).getName());
		}
		System.out.println("以上为学校目前存在的课程");*/
		//以上为测试代码**********************************************
		
		return SUCCESS;
	}
}
