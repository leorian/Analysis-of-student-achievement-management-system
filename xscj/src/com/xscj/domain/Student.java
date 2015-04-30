package com.xscj.domain;

import java.io.Serializable;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2121183829388931414L;
	
	private String name;
	private String password;
	private String sex;
	private String idCard;
	private String address;
	private String nation;
	private String polStat;
	private String schoolTime;
	private String houseHold;
	private String schoolMethod;
	private String classID;
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getSchoolTime() {
		return schoolTime;
	}
	public void setSchoolTime(String schoolTime) {
		this.schoolTime = schoolTime;
	}
	public String getHouseHold() {
		return houseHold;
	}
	public void setHouseHold(String houseHold) {
		this.houseHold = houseHold;
	}
	public String getSchoolMethod() {
		return schoolMethod;
	}
	public void setSchoolMethod(String schoolMethod) {
		this.schoolMethod = schoolMethod;
	}
}
