/**
 * 
 */
package com.xscj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xscj.dao.CategoryDao;
import com.xscj.service.CategoryService;

/**
 * @author leorain
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	@Qualifier("jdbcCategoryDaoImpl")
	private CategoryDao categoryDao;
	/* (non-Javadoc)
	 * @see com.xscj.service.CategoryService#getCount(java.lang.String)
	 */
	@Override
	public int getCount(String categorys) {
		return categoryDao.getCount(categorys);
	}

	/* (non-Javadoc)
	 * @see com.xscj.service.CategoryService#updateCount(java.lang.String)
	 */
	@Override
	public void updateCount(String categorys) {
		categoryDao.updateCount(categorys);
	}

}
