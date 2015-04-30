package com.xscj.service;

public interface AdministratorManager {
	public int isExisits(String name, String password);
	
	public void updatePass(String adminName, String password);
}
