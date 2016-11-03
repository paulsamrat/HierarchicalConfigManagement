package com.di.config.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.di.config.management.entity.ConfigEntry;
import com.di.config.management.entity.ServiceResponse;
import com.di.config.management.service.IConfigManagementService;

@RestController(value="UserConfigManagementController")
@RequestMapping(UserConfigManagementController.URL)
public class UserConfigManagementController {
	
	public static final String URL = "/user";
	
	private static final Logger LOG = LoggerFactory.getLogger(UserConfigManagementController.class);
	
	private static final String CLASS_NAME = UserConfigManagementController.class.getSimpleName();
	
	private ServiceResponse response = null;
	
	@Autowired
	private IConfigManagementService configService;
	
	@RequestMapping(value="/{fiId}/{userId}/configEntries" , method=RequestMethod.POST)
	public ServiceResponse createConfigurationInUserSpace(
			@PathVariable(name = "fiId", required = true) String fiId,
			@PathVariable(name = "userId", required = true) String userId,
			@RequestBody ConfigEntry configEntry,
			BindingResult result) {
		
		final String methodName = "createConfigurationInUserSpace";
		LOG.info(
				CLASS_NAME + "." + methodName
						+ "  Entry : Configuration  Parameters  : UserId , FiId , Config Entry {} {} {} ",
				userId, fiId, configEntry);
		
		if (result.hasErrors()){
			response =  new ServiceResponse(false,result.getAllErrors(),result.getObjectName());
			LOG.error(CLASS_NAME+ "." + methodName + " Error Encountered !! {}" , response);
		}else
			response = configService.createUserConfiguration(userId, fiId, configEntry);
		
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}
	
	@RequestMapping(value="/{fiId}/{userId}/configEntries" , method=RequestMethod.GET)
	public ServiceResponse getAllConfigurationsFromUserSpace(
			@PathVariable(name = "fiId", required = true) String fiId, 
			@PathVariable(name = "userId", required = true) String userId){
		
		final String methodName = "getAllConfigurationsFromUserSpace";
		LOG.info(CLASS_NAME+"."+methodName + "  Entry : Configuration  Parameters  : UserId,FiID {} {} " , userId, fiId );
		response = configService.fetchAllUserConfigurations(fiId,userId);
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}
		
	@RequestMapping(value="/{fiId}/{userId}/configEntries/{keyName}" , method=RequestMethod.GET)
	public ServiceResponse getConfigEntryFromUserSpace(
			@PathVariable(name = "fiId", required = true) String fiId,
			@PathVariable(name = "userId", required = true) String userId,
			@PathVariable(name = "keyName", required = true) String keyName) {
		
		final String methodName = "getConfigEntryFromUserSpace";
		LOG.info(
				CLASS_NAME + "." + methodName
						+ "  Entry : Configuration  Parameters  : UserId , FiID , KeyName {} {} {}",
				userId, fiId, keyName);		
		
			response = configService.fetchUserConfiguration(userId,fiId,keyName);
		
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}
	
	@RequestMapping(value="/{fiId}/{userId}/configEntries/{keyName}" , method=RequestMethod.PUT)
	public ServiceResponse updateConfigEntryInUserSpace(
			@PathVariable(name = "fiId", required = true) String fiId,
			@PathVariable(name = "userId", required = true) String userId,
			@PathVariable(name = "keyName", required = true) String keyName) {
		
		final String methodName = "updateConfigEntryInUserSpace";
		LOG.info(
				CLASS_NAME + "." + methodName
						+ "  Entry : Configuration  Parameters  : UserId , FiID , KeyName {} {} {}",
				userId, fiId, keyName);		
			response = configService.updateUserConfiguration(fiId, userId,keyName);
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}
}
