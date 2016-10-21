/**
 * 
 */
package com.andmore.restapitest.service;

import java.util.Date;
import java.util.List;

import com.andmore.restapitest.model.Entry;
import com.andmore.restapitest.model.User;

/**
 * Entry Service.
 * @author Andres
 *
 */
public interface EntryService {
	
	public void create(Entry entry);
	public void update(Entry entry);
	public void delete(Entry entry);
	public void deleteByMonth(Entry entry, Date month);
	public Entry getEntry(Integer entryId);
	public List<Entry> getAllByTimeLine(Date dateLine, User user);
	
	 

}
