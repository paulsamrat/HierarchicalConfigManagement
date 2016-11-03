package com.di.config.management.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseQueryExecutionException;
import org.springframework.stereotype.Repository;

import com.couchbase.client.java.error.CASMismatchException;
import com.couchbase.client.java.error.DocumentDoesNotExistException;
import com.di.config.management.couchbase.repository.ICouchbaseConfigEntryRepository;
import com.di.config.management.dao.IConfigManagementCouchBaseDAO;
import com.di.config.management.entity.CouchbaseConfigEntry;

/**
 * @author   samrat paul
 * @created  25 sep 2016
 */
@Repository("ConfigManagementCouchBaseDAOImpl")
public class ConfigManagementCouchBaseDAOImpl implements IConfigManagementCouchBaseDAO {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConfigManagementCouchBaseDAOImpl.class);
	
	private static final String CLASS_NAME = ConfigManagementCouchBaseDAOImpl.class.getSimpleName();
	
	@Autowired
	private ICouchbaseConfigEntryRepository  couchbaseConfigEntryRepository;
	
	@Override
	public boolean createConfiguration(CouchbaseConfigEntry couchbaseConfigEntry) throws CouchbaseQueryExecutionException{
		final String methodName = "createConfiguration";
		LOG.info(CLASS_NAME + "." + methodName + " *** Creating Configuration In CouchBase Server : Entry ***");
		couchbaseConfigEntryRepository.save(couchbaseConfigEntry);
		LOG.info(CLASS_NAME + "." + methodName + " *** Exit ***");
		return true;
	}
	
	@Override
	public List<CouchbaseConfigEntry> fetchAllConfigurations(String docId) throws CouchbaseQueryExecutionException {
		final String methodName = "fetchAllConfigurations";
		LOG.info(CLASS_NAME + "." + methodName + " *** Entry ***");
		return couchbaseConfigEntryRepository.findByDocIdLike(docId);
	}


	@Override
	public CouchbaseConfigEntry fetchConfiguration(String docId) throws CouchbaseQueryExecutionException {
		final String methodName = "fetchConfiguration";
		LOG.info(CLASS_NAME + "." + methodName + " *** Entry ***");
		return couchbaseConfigEntryRepository.findOne(docId);
	}


	@Override
	public void updateConfiguration(CouchbaseConfigEntry couchbaseConfigEntry) throws CouchbaseQueryExecutionException,DocumentDoesNotExistException,CASMismatchException {
		final String methodName = "updateConfiguration";
		LOG.info(CLASS_NAME + "." + methodName + " *** Entry ***");
		couchbaseConfigEntryRepository.save(couchbaseConfigEntry);
		LOG.info(CLASS_NAME + "." + methodName + " *** Exit ***");
	}

}
