package com.andmore.restapitest.repository;

import java.util.Date;
import java.util.List;

import com.andmore.restapitest.model.Entry;
import com.andmore.restapitest.model.User;


/**
 * Repository for Entry
 * 
 * @author Andres.
 *
 */
public interface EntryRepository extends GenericRepository<Entry>, java.io.Serializable {
	List<Entry> getAllByTimeLine(Date timeLine, User user);
}
