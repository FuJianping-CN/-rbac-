package com.iotek.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.user.dao.CategoryDao;
import com.iotek.user.po.Category;

@Service(value="categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> queryAllCategory() {
		// TODO Auto-generated method stub
		return this.categoryDao.queryAllCategory();
	}

	@Override
	public Category queryCategoryById(Integer categId) {
		// TODO Auto-generated method stub
		return this.categoryDao.queryCategoryById(categId);
	}

	@Override
	public int insertCategory(Category category) {
		// TODO Auto-generated method stub
		return this.categoryDao.insertCategory(category);
	}

	@Override
	public int deleteCategoryById(Integer categId) {
		// TODO Auto-generated method stub
		return this.categoryDao.deleteCategoryById(categId);
	}

	@Override
	public int updateCategoryById(Category category) {
		// TODO Auto-generated method stub
		return this.categoryDao.updateCategoryById(category);
	}

}
