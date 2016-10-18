package com.di.config.management.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.di.config.management.entity.ConfigEntry;
import com.di.config.management.entity.CouchbaseConfigEntry;

/**
 * @author samrat paul
 * @time   28 Sep 2016
 */
@Component("CouchbaseDocumentHelper")
public class CouchbaseDocumentHelper {
	
	private static final String CLASS_NAME = CouchbaseDocumentHelper.class.getSimpleName();
	
	private static final Logger LOG = LoggerFactory.getLogger(CouchbaseDocumentHelper.class);
	
	public CouchbaseConfigEntry convertToCouchBaseDocument(ConfigEntry configEntry , String docId){
		
		final String methodName = "convertToCouchBaseDocument";
		LOG.info(CLASS_NAME + "." + methodName + " *** Entry *** POJO Entity " +  configEntry);
		CouchbaseConfigEntry couchbaseConfigEntry = new CouchbaseConfigEntry(docId,
				configEntry.getKeyName(), configEntry.getValue());
		LOG.info(CLASS_NAME + "." + methodName + " *** Exit ***  CouchBase Document formed  " +  couchbaseConfigEntry);
		return couchbaseConfigEntry;
	}
	
	public String generateDocId(String spaceId , String userId , String fiId, String keyName){
		final String methodName = "generateDocId";
		LOG.info(CLASS_NAME + "." + methodName + " *** Entry *** Parameters : spaceId : {}, userId : {}, fiId :{} , keyName:{} ", spaceId, userId, fiId , keyName );
		String docId = new StringBuilder().append(spaceId).append(":").append(null != userId ? userId : "")
				.append(null != fiId ? fiId : "").append(null != keyName ? keyName : "").toString();
		LOG.info(CLASS_NAME + "." + methodName + " *** Exit ***  CouchBaseServer  document id  formed  " +  docId);
		return docId;
	}

}
