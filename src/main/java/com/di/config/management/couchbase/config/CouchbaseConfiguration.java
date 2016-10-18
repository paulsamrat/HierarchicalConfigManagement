package com.di.config.management.couchbase.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration{
	
	@Value("${couchbase.cluster.bucket.name}")
	private String bucketName;

	@Value("${couchbase.cluster.bucket.password}")
	private String bucketPassword;

	@Value("${couchbase.cluster.ip}")
	private String ip;
	
	@Override
	protected List<String> getBootstrapHosts() {
		// TODO Auto-generated method stub
		return Arrays.asList(this.ip);
	}

	@Override
	protected String getBucketName() {
		// TODO Auto-generated method stub
		return this.bucketName;
	}

	@Override
	protected String getBucketPassword() {
		// TODO Auto-generated method stub
		return this.bucketPassword;
	}

}
