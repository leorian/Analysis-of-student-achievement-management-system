/**
 * 
 */
package com.xscj.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.opensymphony.xwork2.ActionSupport;
import com.xscj.domain.CourseInfo;
import com.xscj.service.CoursePlan;

/**
 * @author leorain
 *@date 2014-4-16 上午5:51:05
 */
public class CourseAllAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5190524399548406904L;

	@Autowired
	@Qualifier("coursePlanImpl")
	private CoursePlan coursePlan;
	private List<CourseInfo>courseInfos;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void validate() {
		courseInfos = coursePlan.getAllCourseInfos();
		/*for (int i = 0; i < courseInfos.size(); i++) {
			System.out.println(courseInfos.get(i).getCourseID()+" "+courseInfos.get(i).getCourseName()+" "+courseInfos.get(i).getTeacherCount());
		}*/
	}

	public List<CourseInfo> getCourseInfos() {
		return courseInfos;
	}

	public void setCourseInfos(List<CourseInfo> courseInfos) {
		this.courseInfos = courseInfos;
	}
	
	

}
