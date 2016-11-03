package com.di.config.management.couchbase.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.di.config.management.entity.CouchbaseConfigEntry;
@Repository
public interface ICouchbaseConfigEntryRepository extends CrudRepository<CouchbaseConfigEntry, String>{
	
	/**
     * Additional custom finder method, backed by an auto-generated
     * N1QL query.
     */
    List<CouchbaseConfigEntry> findByDocIdLike(String docId);
}
