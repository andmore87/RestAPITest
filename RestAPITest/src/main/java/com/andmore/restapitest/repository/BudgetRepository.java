package com.andmore.restapitest.repository;

import java.util.Date;
import java.util.List;

import com.andmore.restapitest.model.Budget;


/**
 * Repository for Budget
 * 
 * @author Andres.
 *
 */
public interface BudgetRepository extends GenericRepository<Budget>, java.io.Serializable {
	List<Budget> getAllByTimeLine(Date timeLine);
}
