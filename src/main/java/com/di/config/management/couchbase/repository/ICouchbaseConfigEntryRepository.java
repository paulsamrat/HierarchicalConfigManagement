package com.di.config.management.couchbase.repository;

import org.springframework.data.repository.CrudRepository;

import com.di.config.management.entity.CouchbaseConfigEntry;

public interface ICouchbaseConfigEntryRepository extends CrudRepository<CouchbaseConfigEntry, String>{
	
	/**
     * Additional custom finder method, backed by an auto-generated
     * N1QL query.
     */
    //List<CouchbaseConfigEntry> findByFieldStartingWith(String spaceId);
}
