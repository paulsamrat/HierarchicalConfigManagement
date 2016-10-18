package com.di.config.management.service;

import com.di.config.management.entity.ConfigEntry;
import com.di.config.management.entity.ServiceResponse;

public interface IConfigManagementService {		
	
	public ServiceResponse createGlobalConfiguration (ConfigEntry configEntry);
	
	public ServiceResponse createFIConfiguration (String fiId , ConfigEntry configEntry);

	public ServiceResponse createUserConfiguration (String userId, String fiId, ConfigEntry configEntry);

	
	public ServiceResponse fetchAllGlobalConfigurations();
	
	public ServiceResponse fetchAllFIConfigurations();

	public ServiceResponse fetchAllUserConfigurations();

	
	
	public ServiceResponse updateGlobalConfiguration(String keyName);
	
	public ServiceResponse updateFIConfiguration(String docId);

	public ServiceResponse updateUserConfiguration(String keyName);

	
	
	public ServiceResponse fetchGlobalConfiguration(String keyName);

	public ServiceResponse fetchFIConfiguration(String keyName);

	public ServiceResponse fetchUserConfiguration(String userId, String fiId, String keyName);
	
	
}
