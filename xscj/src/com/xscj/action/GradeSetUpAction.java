/**
 * 
 */
package com.xscj.action;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Grade;
import com.xscj.domain.SubTeacher;
import com.xscj.service.GradeSetUp;
import com.xscj.service.TeacherManager;

/**
 * @author leorain
 *
 */
public class GradeSetUpAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1659171335921598105L;
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	
	private List<SubTeacher> subTeachers;
	public List<SubTeacher> getSubTeachers() {
		return subTeachers;
	}
	public void setSubTeachers(List<SubTeacher> subTeachers) {
		this.subTeachers = subTeachers;
	}

/*	private List<String> bianHaoList;
	public List<String> getBianHaoList() {
		return bianHaoList;
	}
	*/
	private int classesCount;
	public int getClassesCount() {
		return classesCount;
	}

	private int maxYear; //高一所对应的届
	private int validCount; //有效班级个数
	private List<Grade> validGrades;
	
	private List<Grade> gradesEnableDel;
	public int getMaxYear() {
		return maxYear;
	}
	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}
	public int getValidCount() {
		return validCount;
	}
	public void setValidCount(int validCount) {
		this.validCount = validCount;
	}
	public List<Grade> getValidGrades() {
		return validGrades;
	}
	public void setValidGrades(List<Grade> validGrades) {
		this.validGrades = validGrades;
	}
	@Override
	public String execute() throws Exception {
		GregorianCalendar gc = new GregorianCalendar();
		maxYear = gradeSetUp.getMaxYear();
		if(maxYear == 0)
			maxYear = gc.get(Calendar.YEAR);
		classesCount = gradeSetUp.getClassesCount(maxYear)+1;
		validCount = gradeSetUp.getGradeCount();
		validGrades = gradeSetUp.getALLValidGrades();
		subTeachers = teacherManager.getAllSubTeachers();
		gradesEnableDel = gradeSetUp.getGradesEnableDel();
		return SUCCESS;
	}
	public List<Grade> getGradesEnableDel() {
		return gradesEnableDel;
	}
	public void setGradesEnableDel(List<Grade> gradesEnableDel) {
		this.gradesEnableDel = gradesEnableDel;
	}
	
}
