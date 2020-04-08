package com.iotek.user.service;

import java.util.List;

import com.iotek.user.po.Category;

public interface CategoryService {
	  public List<Category> queryAllCategory();
	  public Category queryCategoryById(Integer categId);
	  public int insertCategory(Category category);
	  public int deleteCategoryById(Integer categId);
	  public int updateCategoryById(Category category);
}
