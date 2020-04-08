package com.iotek.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iotek.user.po.Category;

@Repository
public interface CategoryDao {
      public List<Category> queryAllCategory();
  	  public Category queryCategoryById(Integer categId);
  	  public int insertCategory(Category category);
  	  public int deleteCategoryById(Integer categId);
  	  public int updateCategoryById(Category category);
}
