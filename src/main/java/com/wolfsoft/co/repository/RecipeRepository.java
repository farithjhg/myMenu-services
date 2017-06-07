package com.wolfsoft.co.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.wolfsoft.co.domain.Recipe;

/**
* <p>Title: Repository interface - Spring Data JPA </p>
*
* <p>Description:Repository interface for the recipe database table, Generated by Masterclass </p>
*
* <p>Copyright: Copyright (c) 2014</p>
*
* <p>Company: Wolfsoft Co.</p>
*
* @author Farith Jose Heras Garcia
* @version 1.0
*/
public interface RecipeRepository extends PagingAndSortingRepository<Recipe,Integer> {
    public List<Recipe> getRecipeByTypeRec(Integer type, Sort field);
}