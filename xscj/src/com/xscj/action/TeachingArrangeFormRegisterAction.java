package com.xscj.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.Teaching;
import com.xscj.service.TeachingArrange;

public class TeachingArrangeFormRegisterAction extends ActionSupport {

	/**
	 * @author leorain 班级 课程 授课老师设置完成Action
	 * @date 2014-3-19 下午11:31:28
	 */
	private static final long serialVersionUID = 6900972182521936666L;

	@Autowired
	@Qualifier("teachingArrangeImpl")
	private TeachingArrange teachingArrange;

	private List<String> list = new ArrayList<String>(); // 和课程编号对应的教师编号

	private String[] courseChose;// 课程编号  复选框

	private String[] teacherChose; // 课程编号教师编号或者课程编号  下拉列表框

	private String gradeChose; // 班级编号

	private int xueqiChose; // 学期编号

	private List<Teaching> teachings;

	public List<Teaching> getTeachings() {
		return teachings;
	}

	public void setTeachings(List<Teaching> teachings) {
		this.teachings = teachings;
	}

	public String getGradeChose() {
		return gradeChose;
	}

	public void setGradeChose(String gradeChose) {
		this.gradeChose = gradeChose;
	}

	public int getXueqiChose() {
		return xueqiChose;
	}

	public void setXueqiChose(int xueqiChose) {
		this.xueqiChose = xueqiChose;
	}

	public String[] getCourseChose() {
		return courseChose;
	}

	public void setCourseChose(String[] courseChose) {
		this.courseChose = courseChose;
	}

	public String[] getTeacherChose() {
		return teacherChose;
	}

	public void setTeacherChose(String[] teacherChose) {
		this.teacherChose = teacherChose;
	}

	@Override
	public void validate() { // 验证方法
		if (courseChose == null || teacherChose == null) {
			//addActionError("没有课程和授课老师可供选择！！！");
			addFieldError("courseChose", "课程未选或授课教师未选");
		} else {
			for (int i = 0; i < courseChose.length; i++) {
				for (int j = 0; j < teacherChose.length; j++) {
					if (teacherChose[j].startsWith(courseChose[i])) {
						list.add(teacherChose[j].substring(6));
						break;
					}
				}
			}
			if (courseChose.length != list.size()) {
				//addActionError("课程和授课老师不匹配，请重新选择！！！");
				addFieldError("courseChose", "课程和授课老师不匹配，核对后请重新选择");
			}
		}

	}

	@Override
	public String execute() throws Exception {
		teachings = new ArrayList<Teaching>();
		for (int i = 0; i < courseChose.length; i++) {
			Teaching teaching = new Teaching(); // 教学安排实体类
			teaching.setClassID(gradeChose);
			teaching.setXueqi(xueqiChose);
			teaching.setCourseID(courseChose[i]);
			teaching.setTeacherID(list.get(i));
			teachings.add(teaching);
		}
		teachingArrange.addTeachingArrange(teachings);  //向数据库中添加一个班级某个学期的教学安排计划
		return SUCCESS;
	}

}
