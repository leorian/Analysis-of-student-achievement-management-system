/**
 * 
 */
package com.xscj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xscj.dao.AdministratorDao;
import com.xscj.service.AdministratorManager;

/**
 * @author leorain
 *
 */
@Service
public class AdministratorManagerImpl implements AdministratorManager {
	
	@Autowired
	@Qualifier("jdbcAdministratorDaoImpl")
	private AdministratorDao administratorDao;

	@Override
	public int isExisits(String name, String password) {
		return administratorDao.isExisits(name, password);
	}

	@Override
	public void updatePass(String adminName, String password) {
		administratorDao.updatePass(adminName, password);
	}

}
