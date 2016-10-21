package com.andmore.restapitest.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.andmore.restapitest.model.Entry;
import com.andmore.restapitest.model.User;

/**
 * Imp for EntryRepository.
 * 
 * @author Andres.
 */
@Repository
public class EntryRepositoryImpl extends GenericRepositoryImpl<Entry>
		implements EntryRepository {

	private static final long serialVersionUID = -1497180899718451209L;
	private final Logger logger = LoggerFactory
			.getLogger(EntryRepositoryImpl.class);

	 /**
	 * Consult all existing Entry, filtering by timeLine range and user.  
	 * @param timeLine used to define the range date.
	 * @return Budget List.
	 */
	@Override
	public List<Entry> getAllByTimeLine(Date timeLine, User user) {
		logger.debug("getting all entries by TimeLine: {}", timeLine);

		Map<String, Object> paramsNameAndValues = new HashMap<String, Object>();
		paramsNameAndValues.put("timeLine", timeLine);
		paramsNameAndValues.put("userName", user.getName());
		paramsNameAndValues.put("password", user.getPassword());
		StringBuilder query = new StringBuilder(128);
		query.append("select e from Entry e where :timeLine BETWEEN e.date AND SYSDATE AND b.owner.name =:userName AND b.owner.password =:password");

		List<Entry> entries = this.getAllByQuery(query.toString(),paramsNameAndValues);
	
		if (logger.isDebugEnabled()) {
			int numEntries = 0;
			if (entries != null) {
				numEntries = CollectionUtils.size(entries);
			}
			logger.debug("TimeLine {} number of Entries readed: ", timeLine,numEntries);
		}
		return entries;
	}

}
