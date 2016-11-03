package com.di.config.management.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

@Document
public class CouchbaseConfigEntry {
	
	@Id
	private String docId;
	
	@Field
	@NotNull
	private String keyName;

	@Field
	@NotNull
	private String value;
	
	public CouchbaseConfigEntry(){}
	
	public CouchbaseConfigEntry(String docId, String keyName, String value) {
		super();
		this.docId = docId;
		this.keyName = keyName;
		this.value = value;
	}
	
	public String getDocId() {
		return docId;
	}


	public String getKeyName() {
		return keyName;
	}

	public String getValue() {
		return value;
	}
	
	/**
	 * @param keyName the keyName to set
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CouchbaseConfigEntry [");
		if (docId != null)
			builder.append("docId=").append(docId).append(", ");
		if (keyName != null)
			builder.append("keyName=").append(keyName).append(", ");
		if (value != null)
			builder.append("value=").append(value);
		builder.append("]");
		return builder.toString();
	}

	

	
}

	
	

