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
import com.xscj.domain.GC;
import com.xscj.domain.Grade;
import com.xscj.domain.Teaching;
import com.xscj.service.CoursePlan;
import com.xscj.service.GradeSetUp;
import com.xscj.service.TeacherManager;
import com.xscj.service.TeachingArrange;

/**
 * @author leorain
 *@date 2014-3-23 下午7:00:19
 */
public class TeacherTeachingAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8335980315893837964L;
	
	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;
	
	@Autowired
	@Qualifier("teacherManagerImpl")
	private TeacherManager teacherManager;
	
	@Autowired
	@Qualifier("gradeSetUpImpl")
	private GradeSetUp gradeSetUp;
	
	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	private String teacherBianHao; //教师编号 （通过教师编号可以获得教师的授课科目以及授课班级、学期情况）
	public String getTeacherBianHao() {
		return teacherBianHao;
	}
	public void setTeacherBianHao(String teacherBianHao) {
		this.teacherBianHao = teacherBianHao;
	}
	
	private String teacherNameString;//某个教师编号所对应的教师姓名
	public String getTeacherNameString() {
		return teacherNameString;
	}
	public void setTeacherNameString(String teacherNameString) {
		this.teacherNameString = teacherNameString;
	}
	
	private List<Teaching> teachings; //某个具体教师的教学安排
	public List<Teaching> getTeachings() {
		return teachings;
	}
	public void setTeachings(List<Teaching> teachings) {
		this.teachings = teachings;
	}
	
	private List<GC>gcs; //包含班级详细信息 课程详细信息
	public List<GC> getGcs() {
		return gcs;
	}
	public void setGcs(List<GC> gcs) {
		this.gcs = gcs;
	}
	@Override
	public String execute() throws Exception {
		teacherBianHao = "T10002";
		teacherNameString = teacherManager.getTeacherNameByTeacherID(teacherBianHao);//通过教师编号获得该教师的姓名
		//以下测试获得教师姓名
	//	System.out.println(teacherNameString);
		//以上测试获得教师姓名
		teachings = teachingArrange.getTeachingsByTeacherID(teacherBianHao);
		gcs = new ArrayList<GC>();
		for(int i=0; i<teachings.size(); i++)
		{
			Grade grade = gradeSetUp.getGradeBybianHao(teachings.get(i).getClassID());
			GC gc = new GC();
			gc.setGradeId(teachings.get(i).getClassID());
			gc.setGradeYear(grade.getYear());
			gc.setGradeClassid(grade.getClassID());
			gc.setGradeAdviser(teacherManager.getTeacherNameByTeacherID(grade.getAdviser()));
			Course course = coursePlan.getCourseByCourseID(teachings.get(i).getCourseID());
			gc.setCourseId(teachings.get(i).getCourseID());
			gc.setCourseName(course.getName());
			gc.setXueqi(teachings.get(i).getXueqi());
			gcs.add(gc);
		}
		//以下测试获得教学安排
		/*for(int i=0; i<gcs.size(); i++)
		{
			System.out.println(gcs.get(i).getGradeYear() + "届（"+ gcs.get(i).getGradeClassid() + ")班第"+gcs.get(i).getXueqi()+"学期");
			System.out.println(gcs.get(i).getCourseName());
		}*/
		//以上测试获得教学安排
		return SUCCESS;
	}
}
