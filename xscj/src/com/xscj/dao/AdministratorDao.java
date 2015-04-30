package com.xscj.dao;

public interface AdministratorDao {
	public int isExisits(String name, String password);
	
	public void updatePass(String adminName, String password);
}
