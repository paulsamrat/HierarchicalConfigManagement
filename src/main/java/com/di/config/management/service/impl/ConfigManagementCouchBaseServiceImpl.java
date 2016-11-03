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
			LOG.info("Fetching Global Configuration  for updation : key name {}" , keyName);
			CouchbaseConfigEntry configEntry = configDAO.fetchConfiguration(
					couchbaseDocumentHelper.generateDocId(ConfigEntry.Space.GLOBAL.getCode(), null, null, keyName));
			LOG.info("Global Configuration fetched {}",configEntry);
			LOG.info("updating config entry entity with dummy values");
			configEntry.setKeyName("Updated-"+configEntry.getKeyName());
			configEntry.setValue("Updated-"+configEntry.getValue());
			configDAO.updateConfiguration(configEntry);
			response =  new ServiceResponse(true, "successfully updated global configuration");
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while updating global configuration  {}" , cbe);
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
			CouchbaseConfigEntry couchbaseConfigEntry = configDAO.fetchConfiguration(
					couchbaseDocumentHelper.generateDocId(ConfigEntry.Space.GLOBAL.getCode(), null, null, keyName));
			
			response = (null != couchbaseConfigEntry)
					? new ServiceResponse(true, "successfully fetched global configuration",
							Arrays.asList(couchbaseConfigEntry))
					: new ServiceResponse(false, "no global configuration mapped to key name" + keyName);
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
							.generateDocId(ConfigEntry.Space.FI.getCode(), fiId, null, configEntry.getKeyName())));
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
	public ServiceResponse fetchAllFIConfigurations(String fiId) {
		
		final String methodName = "fetchAllFIConfigurations";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try { 
			List<CouchbaseConfigEntry> resultList = configDAO.fetchAllConfigurations(ConfigEntry.Space.FI.getCode().concat(fiId));
			LOG.info(" All FI COnfigurations fetched {} " , resultList);
			response =  new ServiceResponse(true, "successfully fetched all fi configurations", resultList);
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
	public ServiceResponse updateFIConfiguration(String fiId, String keyName) {
		
		final String methodName = "updateFIConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try {
			LOG.info("Fetching fi Configuration  for updation : fi-id ,  key name {} {} " , fiId, keyName);
			CouchbaseConfigEntry configEntry = configDAO.fetchConfiguration(
					couchbaseDocumentHelper.generateDocId(ConfigEntry.Space.FI.getCode(), fiId, null,keyName));
			LOG.info("Fi Configuration fetched {}",configEntry);
			LOG.info("updating config entry entity with dummy values");
			configEntry.setKeyName("Updated-"+configEntry.getKeyName());
			configEntry.setValue("Updated-"+configEntry.getValue());
			configDAO.updateConfiguration(configEntry);
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
	public ServiceResponse fetchFIConfiguration(String fiId, String keyName) {
		final String methodName = "fetchFIConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		ServiceResponse response = null;
		CouchbaseConfigEntry couchbaseConfigEntry = null;
			try { 
				couchbaseConfigEntry = configDAO
						.fetchConfiguration(couchbaseDocumentHelper.generateDocId(ConfigEntry.Space.FI.getCode(), null, fiId, keyName));
			if (null != couchbaseConfigEntry)
				response =  new ServiceResponse(true, "successfully fetched fi configuration", Arrays.asList(couchbaseConfigEntry));
			else{
				//go one level up the hierarchy and search in GLOBAL LEVEL
				couchbaseConfigEntry = configDAO
						.fetchConfiguration(couchbaseDocumentHelper.generateDocId(ConfigEntry.Space.GLOBAL.getCode(), null, null, keyName));
				if (null != couchbaseConfigEntry)
					response =  new ServiceResponse(true, "successfully fetched global configuration", Arrays.asList(couchbaseConfigEntry));
			}
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
							.generateDocId(ConfigEntry.Space.FI.getCode(), fiId,  userId, configEntry.getKeyName())));
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
	public ServiceResponse fetchAllUserConfigurations(String fiId , String userId) {
		
		final String methodName = "fetchAllUserConfigurations";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try { 
			List<CouchbaseConfigEntry> resultList = configDAO.fetchAllConfigurations(ConfigEntry.Space.FI.getCode().concat(":").concat(fiId).concat(userId));
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
	public ServiceResponse updateUserConfiguration(String fiId, String userId, String keyName) {
		
		final String methodName = "updateUserConfiguration";
		LOG.info(CLASS_NAME+methodName+" *** Entry ***");
		try {
			LOG.info("Fetching user Configuration  for updation : fi-id , user-id,  key name {} {} {} " , fiId, userId, keyName);
			CouchbaseConfigEntry configEntry = configDAO.fetchConfiguration(
					couchbaseDocumentHelper.generateDocId(ConfigEntry.Space.FI.getCode(),fiId,userId,keyName));
			if ( null != configEntry){
				LOG.info("User Configuration fetched {}",configEntry);
				LOG.info("updating config entry entity with dummy values");
				configEntry.setKeyName("Updated-"+configEntry.getKeyName());
				configEntry.setValue("Updated-"+configEntry.getValue());
				configDAO.updateConfiguration(configEntry);
				response =  new ServiceResponse(true, "successfully updated user configuration");
			}
			else
			   response = new ServiceResponse(false,"No User configuration found for fi-id " +  fiId  + " user-id" + userId);	
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
		CouchbaseConfigEntry couchbaseConfigEntry = null;
		try { 
			couchbaseConfigEntry = configDAO
					.fetchConfiguration(couchbaseDocumentHelper.generateDocId(ConfigEntry.Space.FI.getCode(), fiId, userId, keyName));
			if ( null != couchbaseConfigEntry)
					response =  new ServiceResponse(true, "successfully fetched user configuration from user space ", Arrays.asList(couchbaseConfigEntry));
			else {
				// look at FI Space
				couchbaseConfigEntry = configDAO.fetchConfiguration(couchbaseDocumentHelper.generateDocId(ConfigEntry.Space.FI.getCode(), fiId, null, keyName));
				if ( null != couchbaseConfigEntry)
					 response = new ServiceResponse(true,"successfully fetched user configuration from fi space ",Arrays.asList(couchbaseConfigEntry));
				else{
					//look at Global Space
					couchbaseConfigEntry = configDAO.fetchConfiguration(couchbaseDocumentHelper.generateDocId(ConfigEntry.Space.GLOBAL.getCode(), null, null, keyName));
					response = (null != couchbaseConfigEntry)
							? new ServiceResponse(true, "successfully fetched user configuration from global space ",
									Arrays.asList(couchbaseConfigEntry))
							: new ServiceResponse(false, " no user configuration mapped to user-id " + userId + "fi-id"
									+ fiId + "key-name" + keyName);
				}
			}
		}catch(CouchbaseQueryExecutionException  | CouchbaseException cbe){
			LOG.error("exception occured while fetching user configuration  {}" , cbe);
			response =  new ServiceResponse(false, null, cbe.getMessage());
		}
		LOG.info(CLASS_NAME+methodName+" *** Exit : Service Response {}*** " , response );
		return response;
	}
	
}
