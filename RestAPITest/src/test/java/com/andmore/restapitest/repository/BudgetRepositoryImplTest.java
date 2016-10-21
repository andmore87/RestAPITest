package com.andmore.restapitest.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.andmore.restapitest.model.Budget;

 


/**
 * Testing for class es.carlosgarcia.smallnotes.repository.NoteRepositoryImpl
 * @author Andres.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:restAPITest-test.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BudgetRepositoryImplTest {

 
	private static final Date TIMELINE_TEST1 = new Date();
	private static final String START_DATE = "2016-10-10";
	private static final String END_DATE = "2016-10-30";
	private static final long LIMIT =  2000L;
	private static final double AMOUNT = Double.valueOf(88.7);
	  
	
	@Autowired
	private BudgetRepository budgetRepository;
	 
	
	@Test
	public void shouldGetBudgetsByTimeLine(){
		List<Budget> budgets = this.budgetRepository.getAllByTimeLine(TIMELINE_TEST1);
		Assert.assertNotNull(budgets);
		Assert.assertEquals(3, budgets.size());
	}

 
	

	@Test
	public void shouldCreateBudget(){
		Budget budget = this.createBudget();
		
		this.budgetRepository.saveOrUpdate(budget);
		
		Assert.assertNotNull(budget.getId());
		Assert.assertEquals(START_DATE, budget.getStrStart_date());
		Assert.assertEquals(END_DATE, budget.getStrEnd_date());
		Assert.assertEquals(AMOUNT, budget.getAmount());
		
		List<Budget> budgets = this.budgetRepository.getAll(Budget.class);   
		Assert.assertNotNull(budgets);
		Assert.assertEquals(4, budgets.size());
	}


	@Test
	public void shouldDeleteNote(){
		final int numBudgetsBeforeDelete = 3;
		
		List<Budget> budgets = this.budgetRepository.getAll(Budget.class);
		Assert.assertNotNull(budgets);
		Assert.assertEquals(numBudgetsBeforeDelete, budgets.size());
		
		
		Budget budgetToDelete = budgets.get(1);
		this.budgetRepository.delete(budgetToDelete);
		
		List<Budget> budgetsAfterDelete = budgetRepository.getAll(Budget.class);
		Assert.assertNotNull(budgetsAfterDelete);
		Assert.assertEquals((numBudgetsBeforeDelete - 1), budgetsAfterDelete.size());
		
		for (Budget currentNode : budgetsAfterDelete){
			Assert.assertNotSame(budgetToDelete, currentNode);
		}
	}
	
	
	@Test
	public void shouldUpdateBudget(){
		final Integer BudgetID = Integer.valueOf(1);
		final double amount_updated = Double.valueOf(22.7);
		
		Budget budget = budgetRepository.findByPK(Budget.class, BudgetID);
		budget.setAmount(amount_updated);
		this.budgetRepository.saveOrUpdate(budget);
		
		Budget budgetAfterUpdate = budgetRepository.findByPK(Budget.class, BudgetID);

		Assert.assertEquals(amount_updated, budgetAfterUpdate.getAmount());
		 
	}
	
 
	private Budget createBudget()  {
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		Date start_date = new Date();
		Date end_date = new Date();
		try {
			start_date = formatter.parse(START_DATE);
			end_date = formatter.parse(END_DATE);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		Budget budget = new Budget();
		budget.setId(1);
		budget.setAmount(AMOUNT);
		budget.setLimit(LIMIT);
		budget.setStart_date(start_date);
		budget.setEnd_date(end_date);
		 
		
		return budget;
	}
	
	

}
