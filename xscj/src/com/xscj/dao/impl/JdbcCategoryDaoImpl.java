/**
 * 
 */
package com.xscj.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xscj.dao.CategoryDao;

/**
 * @author leorain
 *
 */
@Repository
public class JdbcCategoryDaoImpl implements CategoryDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public int getCount(String categorys) {
		String sqlString = "SELECT count FROM sp_record WHERE categorys = ?;";
		Object[] argsObjects = {categorys};
		return jdbcTemplate.queryForInt(sqlString, argsObjects);
	}

	@Override
	public void updateCount(String categorys) {
		int count = getCount(categorys);
		count = count +1;
		String sqlString = "UPDATE sp_record SET count = ? WHERE categorys = ?;";
		Object[] argsoObjects = {count, categorys};
		jdbcTemplate.update(sqlString, argsoObjects);
	}

}
