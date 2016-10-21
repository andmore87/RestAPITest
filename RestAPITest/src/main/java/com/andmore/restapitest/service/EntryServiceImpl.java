package com.andmore.restapitest.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andmore.restapitest.model.Entry;
import com.andmore.restapitest.model.User;
import com.andmore.restapitest.repository.EntryRepository;

/**
 * Budget Service implementation.
 * 
 * @author Andres
 *
 */
@Service("entryService")
@Transactional(readOnly = false)
public class EntryServiceImpl implements EntryService {

	private final Logger logger = LoggerFactory
			.getLogger(EntryServiceImpl.class);

	private EntryRepository entryRepository;

	public EntryServiceImpl() {
		super();
	}

	@Inject
	public EntryServiceImpl(EntryRepository entryRepository) {
		super();
		this.entryRepository = entryRepository;

	}

	@Override
	public void create(Entry entry) {
		logger.debug("creating Entry  ");
		 	if (entry != null) {
		 		this.entryRepository.saveOrUpdate(entry);
			 
		}

	}

	@Override
	public void update(Entry entry) {
		Entry persistedEntry = this.getEntry(entry.getId());

		if (persistedEntry == null) {
			throw new IllegalArgumentException("Entry not exists, id: "
					+ entry.getId());
		}
		logger.debug("updating Entry Id {}, end date: {}", entry.getId());

		persistedEntry.setAmount(entry.getAmount());
		persistedEntry.setCurrency(entry.getCurrency());
		persistedEntry.setModified(entry.getModified());
		persistedEntry.setRate(entry.getRate());

		this.entryRepository.saveOrUpdate(persistedEntry);

	}

	@Override
	public void delete(Entry entry) {
		logger.debug("deleting Budget id {}", entry.getId());
		Entry persistedBudget = this.getEntry(entry.getId());
		if (persistedBudget == null) {
			throw new IllegalArgumentException("Budget not exists, id: "
					+ entry.getId());
		}
		this.entryRepository.delete(entry);

	}

	@Override
	@Transactional(readOnly = true)
	public Entry getEntry(Integer entrytId) {
		Entry entry = entryRepository.findByPK(Entry.class, entrytId);
		return entry;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Entry> getAllByTimeLine(Date dateLine, User user) {
		logger.debug("getting budgets by dateLine {}", dateLine);

		List<Entry> entries = entryRepository.getAllByTimeLine(dateLine, user);
		logger.debug("num entry readed {}", CollectionUtils.size(entries));
		return entries;
	}

	@Override
	public void deleteByMonth(Entry entry, Date month) {
		logger.debug("deleting entry id {}, month: {}", entry.getId(), month);
		this.entryRepository.delete(entry);

	}

	 

}
