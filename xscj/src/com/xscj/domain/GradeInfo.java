/**
 * 
 */
package com.xscj.domain;

import java.io.Serializable;

/**
 * @author leorain
 *@date 2014-4-16 上午8:23:33
 */
public class GradeInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7398988185737777594L;
	
	private String gradeID;
	private int year;
	private int classID;
	private String adviserID;
	private String adviserName;
	private int stuCount;
	public String getGradeID() {
		return gradeID;
	}
	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	
	public String getAdviserID() {
		return adviserID;
	}
	public void setAdviserID(String adviserID) {
		this.adviserID = adviserID;
	}
	public String getAdviserName() {
		return adviserName;
	}
	public void setAdviserName(String adviserName) {
		this.adviserName = adviserName;
	}
	public int getStuCount() {
		return stuCount;
	}
	public void setStuCount(int stuCount) {
		this.stuCount = stuCount;
	}
	

}
