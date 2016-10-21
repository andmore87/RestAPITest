package com.andmore.restapitest.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andmore.restapitest.model.Budget;
import com.andmore.restapitest.repository.BudgetRepository;

/**
 * Budget Service implementation.
 * 
 * @author Andres
 *
 */
@Service("budgetService")
@Transactional(readOnly = false)
public class BudgetServiceImpl implements BudgetService {

	private final Logger logger = LoggerFactory
			.getLogger(BudgetServiceImpl.class);

	private BudgetRepository budgetRepository;

	public BudgetServiceImpl() {
		super();
	}

	@Inject
	public BudgetServiceImpl(BudgetRepository budgetRepository) {
		super();
		this.budgetRepository = budgetRepository;

	}

	@Override
	public void create(Budget budget) {
		logger.debug("creating Budget start date {}, end date: {}",
				budget.getStrStart_date(), budget.getStrEnd_date());
		if (budget != null) {
			if (validateMonth(budget.getStart_date(),budget.getEnd_date())) {
				this.budgetRepository.saveOrUpdate(budget);
			}
		}

	}

	@Override
	public void update(Budget budget) {
		Budget persistedBudget = this.getBudget(budget.getId());

		if (persistedBudget == null) {
			throw new IllegalArgumentException("Budget not exists, id: "
					+ budget.getId());
		}
		logger.debug("updating Budget Id {}, end date: {}", budget.getId());

		persistedBudget.setLimit(budget.getLimit());
		persistedBudget.setAmount(budget.getAmount());
		persistedBudget.setStart_date(budget.getStart_date());
		persistedBudget.setEnd_date(budget.getEnd_date());

		this.budgetRepository.saveOrUpdate(persistedBudget);

	}

	@Override
	public void delete(Budget budget) {
		logger.debug("deleting Budget id {}", budget.getId());
		Budget persistedBudget = this.getBudget(budget.getId());
		if (persistedBudget == null) {
			throw new IllegalArgumentException("Budget not exists, id: "
					+ budget.getId());
		}
		this.budgetRepository.delete(budget);

	}

	@Override
	@Transactional(readOnly = true)
	public Budget getBudget(Integer budgetId) {
		Budget budget = budgetRepository.findByPK(Budget.class, budgetId);
		return budget;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Budget> getAllByTimeLine(Date dateLine) {
		logger.debug("getting budgets by dateLine {}", dateLine);

		List<Budget> budgets = budgetRepository.getAllByTimeLine(dateLine);
		logger.debug("num budgets readed {}", CollectionUtils.size(budgets));
		return budgets;
	}

	@Override
	public void deleteByMonth(Budget budget, Date month) {
		logger.debug("deleting Budget id {}, month: {}", budget.getId(), month);
		this.budgetRepository.delete(budget);

	}

	/**
	 * Valid if the start date and end date have the number of day for the
	 * month.
	 * @param start_date used to define the range date.          
	 * @param end_date used to define the range date.        
	 * @return Boolean that indicate if the month is validate.
	 */
	private boolean validateMonth(Date start_date, Date end_date) {
		boolean response = false;
		
		if (start_date.before(end_date)) {
			Calendar cal = Calendar.getInstance();
		    cal.setTime(start_date);
			int daysByMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			int difference =Days.daysBetween(
	                new LocalDate(start_date.getTime()), 
	                new LocalDate(end_date.getTime())).getDays();
			 if(daysByMonth == difference){
				 response = true;
			 }
		}
		return response;
	}

}
