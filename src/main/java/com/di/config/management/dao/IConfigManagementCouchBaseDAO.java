package com.di.config.management.dao;

import java.util.List;

import org.springframework.data.couchbase.core.CouchbaseQueryExecutionException;

import com.di.config.management.entity.CouchbaseConfigEntry;

public interface IConfigManagementCouchBaseDAO {
	
	public boolean createConfiguration (CouchbaseConfigEntry couchbaseConfigEntry) throws CouchbaseQueryExecutionException;
	
	public List<CouchbaseConfigEntry> fetchAllConfigurations(String spaceId) throws CouchbaseQueryExecutionException;
	
	public CouchbaseConfigEntry fetchConfiguration(String docId) throws CouchbaseQueryExecutionException;

	public void updateConfiguration(String docId) throws CouchbaseQueryExecutionException;	
	
}
