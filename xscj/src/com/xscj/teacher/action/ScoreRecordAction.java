/**
 * 
 */
package com.xscj.teacher.action;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.SubStudent;
import com.xscj.service.StuService;

/**
 * @author leorain
 *@date 2014-3-24 上午10:08:12
 */
public class ScoreRecordAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4160109266241424024L;
	
	@Autowired
	@Qualifier("stuServiceImpl")
	private StuService stuService; //学生服务业务逻辑处理对象
	
	private List<SubStudent> subStudents; //某个具体班级对应的所有学生信息，包括学号和学生姓名
	public List<SubStudent> getSubStudents() {
		return subStudents;
	}
	public void setSubStudents(List<SubStudent> subStudents) {
		this.subStudents = subStudents;
	}
	
	private String courseName; //课程名称
	private String teacherName; //授课教师姓名
	private String gradeName;//班级 届+班级号
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	private String gradeID; //班级编号
	private String courseID; //课程编号
	private String teacherID; //教师编号
	private int xueqi;
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
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	public int getXueqi() {
		return xueqi;
	}
	public void setXueqi(int xueqi) {
		this.xueqi = xueqi;
	}
	
	private String currentDate; //当前日期
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	@Override
	public String execute() throws Exception {
		//以下测试班级编号、课程编号、教师编号、学期
		//System.out.println(gradeID+"----->"+courseID+"------>"+teacherID+"------->"+xueqi);
		//以上测试班级编号、课程编号、教师编号、学期
		
		subStudents = stuService.getAllSubStudentsByGradeID(gradeID);//根据班级编号获得该班级所有学生的学号和姓名
		
		//以下测试某个具体班级所有学生的学号和姓名
		/*for(int i=0; i<subStudents.size(); i++)
		{
			System.out.println(subStudents.get(i).getXuehao()+ "------>" + subStudents.get(i).getStuName());
		}*/
		//以上测试某个班级所有学生的学号和姓名
		
		//以下测试课程名称和授课教师姓名以及班级称呼
		//System.out.println(courseName);
		//System.out.println(teacherName);
		//System.out.println(gradeName);
		//以上测试课程名称和授课教师姓名以及班级称呼
		GregorianCalendar gCalendar = new GregorianCalendar();
		currentDate = gCalendar.get(Calendar.YEAR)+"-"+(gCalendar.get(Calendar.MONTH)+1)+"-"+gCalendar.get(Calendar.DATE);
		return SUCCESS;
	}

}
