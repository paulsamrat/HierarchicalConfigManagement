package com.di.config.management.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseQueryExecutionException;
import org.springframework.stereotype.Service;

import com.couchbase.client.core.CouchbaseException;
import com.di.config.management.dao.IConfigManagementCouchBaseDAO;
import com.di.config.management.entity.ConfigEntry;
import com.di.config.management.entity.CouchbaseConfigEntry;
import com.di.config.management.entity.ServiceResponse;
import com.di.config.management.service.IConfigManagementService;
import com.di.config.management.utility.CouchbaseDocumentHelper;
/*
 * @author samrat paul 
 * @created 24 Sep 2016
 */
@Service("ConfigManagementCouchBaseServiceImpl")
public class ConfigManagementCouchBaseServiceImpl implements IConfigManagementService {
	
	private static final String CLASS_NAME = ConfigManagementCouchBaseServiceImpl.class.getSimpleName();
	
	private static final Logger LOG = LoggerFactory.getLogger(ConfigManagementCouchBaseServiceImpl.class);
	
	@Autowired
	private IConfigManagementCouchBaseDAO configDAO;
	
	@Autowired
	private CouchbaseDocumentHelper couchbaseDocumentHelper;
	
	private ServiceResponse response;
	
	//put a key with the given name and value in global space
	@Override
	public ServiceResponse createGlobalConfiguration(ConfigEntry configEntry) {

		final String methodName = "createGlobalConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try { 
			
			configDAO.createConfiguration(
					couchbaseDocumentHelper.convertToCouchBaseDocument(configEntry, couchbaseDocumentHelper
							.generateDocId(ConfigEntry.Space.GLOBAL.getCode(), null, null, configEntry.getKeyName())));
			response =  new ServiceResponse(true, "successfully saved global configuration");
		
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while saving global configuration  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}
	
	// fetch all global configurations
	@Override
	public ServiceResponse fetchAllGlobalConfigurations() {
		final String methodName = "fetchAllGlobalConfigurations";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try { 
			List<CouchbaseConfigEntry> resultList = configDAO.fetchAllConfigurations(ConfigEntry.Space.GLOBAL.getCode());
			LOG.info(" All Global COnfigurations fetched {} " , resultList);
			response =  new ServiceResponse(true, "successfully fetched all global configurations", resultList);
		
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while fetching all global configurations  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}

	// update the global configuration associated with the key name 
	@Override
	public ServiceResponse updateGlobalConfiguration(String keyName) {
		
		final String methodName = "updateGlobalConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try { 
			configDAO.updateConfiguration(keyName);
			response =  new ServiceResponse(true, "successfully fetched global configuration");
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while fetching global configuration  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}

	//fetch  the global configuration associated with key name
	@Override
	public ServiceResponse fetchGlobalConfiguration(String keyName) {
		final String methodName = "fetchGlobalConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		ServiceResponse response = null;
		try { 
			CouchbaseConfigEntry couchbaseConfigEntry = configDAO.fetchConfiguration("global"+keyName);
			response =  new ServiceResponse(true, "successfully fetched global configuration", Arrays.asList(couchbaseConfigEntry));
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while fetching global configuration  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}

	// create a fi configuration
	@Override
	public ServiceResponse createFIConfiguration(String fiId , ConfigEntry configEntry) {
		
		final String methodName = "createFIConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try { 
			
			configDAO.createConfiguration(
					couchbaseDocumentHelper.convertToCouchBaseDocument(configEntry, couchbaseDocumentHelper
							.generateDocId(ConfigEntry.Space.FI.getCode(), null, fiId, configEntry.getKeyName())));
			response =  new ServiceResponse(true, "successfully saved fi configuration");
		
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while saving fi configuration  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}

	
	// fetch all fi configurations
	@Override
	public ServiceResponse fetchAllFIConfigurations() {
		
		final String methodName = "fetchAllFIConfigurations";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try { 
			List<CouchbaseConfigEntry> resultList = configDAO.fetchAllConfigurations(ConfigEntry.Space.FI.getCode());
			LOG.info(" All FI COnfigurations fetched {} " , resultList);
			response =  new ServiceResponse(true, "successfully fetched all global configurations", resultList);
		
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while fetching all FI configurations  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}

	//update fi configuration
	
	/**
	 * @param keyName : the doc id in couchbase server
	 * @return response 
	 */
	@Override
	public ServiceResponse updateFIConfiguration(String keyName) {
		
		final String methodName = "updateFIConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try { 
			configDAO.updateConfiguration(keyName);
			response =  new ServiceResponse(true, "successfully updated FI configuration");
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while updating fi configuration  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}

	//fetch fi configuration associated with key name
	@Override
	public ServiceResponse fetchFIConfiguration(String keyName) {
		final String methodName = "fetchFIConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		ServiceResponse response = null;
		try { 
			CouchbaseConfigEntry couchbaseConfigEntry = configDAO
					.fetchConfiguration(ConfigEntry.Space.FI.getCode().concat(keyName));
			response =  new ServiceResponse(true, "successfully fetched fi configuration", Arrays.asList(couchbaseConfigEntry));
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while fetching fi configuration  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}

	//create user  configuration in user space
	@Override
	public ServiceResponse createUserConfiguration(String userId, String fiId,ConfigEntry configEntry) {
		
		final String methodName = "createUserConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try { 
			
			configDAO.createConfiguration(
					couchbaseDocumentHelper.convertToCouchBaseDocument(configEntry, couchbaseDocumentHelper
							.generateDocId(ConfigEntry.Space.USER.getCode(), userId, fiId, configEntry.getKeyName())));
			response =  new ServiceResponse(true, "successfully saved user configuration");
		
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while saving user configuration  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}

	// fetch all user configurations
	@Override
	public ServiceResponse fetchAllUserConfigurations() {
		
		final String methodName = "fetchAllUserConfigurations";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try { 
			List<CouchbaseConfigEntry> resultList = configDAO.fetchAllConfigurations(ConfigEntry.Space.GLOBAL.getCode());
			LOG.info(" All user Configurations fetched {} " , resultList);
			response =  new ServiceResponse(true, "successfully fetched all user configurations", resultList);
		
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while fetching all user configurations  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}

	@Override
	public ServiceResponse updateUserConfiguration(String keyName) {
		
		final String methodName = "updateUserConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try { 
			configDAO.updateConfiguration(keyName);
			response =  new ServiceResponse(true, "successfully updated user configuration");
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while updating user configuration  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}
	
	
	@Override
	public ServiceResponse fetchUserConfiguration(String userId, String fiId, String keyName) {
		
		final String methodName = "fetchUserConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		ServiceResponse response = null;
		try { 
			CouchbaseConfigEntry couchbaseConfigEntry = configDAO
					.fetchConfiguration(couchbaseDocumentHelper.generateDocId(ConfigEntry.Space.USER.getCode(), userId, fiId, keyName));
			response =  new ServiceResponse(true, "successfully fetched user configuration", Arrays.asList(couchbaseConfigEntry));
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while fetching user configuration  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}


	
	

	
}
