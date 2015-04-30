package com.xscj.domain;

import java.io.Serializable;

public class Teacher implements Serializable {

	/**
	 * 教师实体类
	 */
	private static final long serialVersionUID = 7299619864879824093L;
	private String bianHao;
	private String password;
	private String name;
	private String sex;
	private String idCard;
	private String address;
	private String jobTime;
	private String phone;
	private String nation;
	private String polStat;
	private String eduBg;
	private String graIns;
	private String courseID;
	private String courseName;//课程名称
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getBianHao() {
		return bianHao;
	}
	public void setBianHao(String bianHao) {
		this.bianHao = bianHao;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJobTime() {
		return jobTime;
	}
	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPolStat() {
		return polStat;
	}
	public void setPolStat(String polStat) {
		this.polStat = polStat;
	}
	public String getEduBg() {
		return eduBg;
	}
	public void setEduBg(String eduBg) {
		this.eduBg = eduBg;
	}
	public String getGraIns() {
		return graIns;
	}
	public void setGraIns(String graIns) {
		this.graIns = graIns;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
