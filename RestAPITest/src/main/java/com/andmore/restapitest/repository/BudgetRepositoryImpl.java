package com.andmore.restapitest.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.andmore.restapitest.model.Budget;

/**
 * Imp for BudgetRepository.
 * 
 * @author Andres.
 */
@Repository
public class BudgetRepositoryImpl extends GenericRepositoryImpl<Budget>
		implements BudgetRepository {

	private static final long serialVersionUID = -1497180899718451209L;
	private final Logger logger = LoggerFactory
			.getLogger(BudgetRepositoryImpl.class);

	 /**
	 * Consult all existing Budgets, filtering by timeLine range.  
	 * @param timeLine used to define the range date.
	 * @return Budget List.
	 */
	@Override
	public List<Budget> getAllByTimeLine(Date timeLine) {
		logger.debug("getting all budgets by TimeLine: {}", timeLine);

		Map<String, Object> paramsNameAndValues = new HashMap<String, Object>();
		paramsNameAndValues.put("timeLine", timeLine);
		StringBuilder query = new StringBuilder(128);
		query.append("select b from Budget b where :timeLine BETWEEN b.start_date AND b.end_date");

		List<Budget> budgets = this.getAllByQuery(query.toString(),paramsNameAndValues);
	
		if (logger.isDebugEnabled()) {
			int numBugets = 0;
			if (budgets != null) {
				numBugets = CollectionUtils.size(budgets);
			}
			logger.debug("TimeLine {} number of Bugets readed: ", timeLine,numBugets);
		}
		return budgets;
	}

}
