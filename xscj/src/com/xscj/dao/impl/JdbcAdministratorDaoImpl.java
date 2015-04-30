/**
 * 
 */
package com.xscj.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xscj.dao.AdministratorDao;

/**
 * @author leorain
 *
 */
@Repository
public class JdbcAdministratorDaoImpl implements AdministratorDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int isExisits(String name, String password) {
		String sqlString = "SELECT COUNT(*) FROM sp_admin WHERE admin_name = ? AND admin_pass = ? ;";
		Object[] argObjects = {name, password};
		return jdbcTemplate.queryForInt(sqlString, argObjects);
	}

	@Override
	public void updatePass(String adminName, String password) {
		String sqlStr = "UPDATE sp_admin SET admin_pass = ? WHERE admin_name = ?;";
		Object[] argObjects = {password, adminName};
		jdbcTemplate.update(sqlStr, argObjects);
	}
}
