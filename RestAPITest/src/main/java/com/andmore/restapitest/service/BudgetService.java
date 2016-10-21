/**
 * 
 */
package com.andmore.restapitest.service;

import java.util.Date;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import com.andmore.restapitest.model.Budget;

/**
 * Budget Service.
 * @author Andres
 *
 */
public interface BudgetService {
	
	public void create(Budget budget);
	public void update(Budget budget);
	public void delete(Budget budget);
	public void deleteByMonth(Budget budget, Date month);
	public Budget getBudget(Integer budgetId);
	
	//@PreAuthorize ("hasRole('ROLE_USER')")
	public List<Budget> getAllByTimeLine(Date dateLine);
	
	 

}
