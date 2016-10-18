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

@RestController(value="FIConfigManagementController")
@RequestMapping(FIConfigManagementController.URL)
public class FIConfigManagementController {
	
	public static final String URL = "/fis";
	
	private static final Logger LOG = LoggerFactory.getLogger(FIConfigManagementController.class);
	
	private static final String CLASS_NAME = FIConfigManagementController.class.getSimpleName();
	
	private ServiceResponse response = null;
	
	@Autowired
	private IConfigManagementService configService;
	
	@RequestMapping(value="/{fiId}/configEntries" , method=RequestMethod.POST)
	public ServiceResponse createConfigurationInFISpace(@PathVariable(name="fiId" , required = true ) String fiId , 
			@RequestBody ConfigEntry configEntry , BindingResult result) {
		
		final String methodName = "createConfigurationInFISpace";
		LOG.info(CLASS_NAME+"."+methodName + "  Entry : Configuration  Parameters  : FiID , Config Entry {} {}" , fiId, configEntry );
		
		if (result.hasErrors()){
			response =  new ServiceResponse(false,result.getAllErrors(),result.getObjectName());
			LOG.error(CLASS_NAME+ "." + methodName + " Error Encountered !! {}" , response);
		}else
			response = configService.createFIConfiguration(fiId,configEntry);
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}
	
	@RequestMapping(value="/{fiId}/configEntries" , method=RequestMethod.GET)
	public ServiceResponse getAllConfigurationsFromFISpace(@PathVariable(name="fiId" , required = true ) String fiId ){
		
		final String methodName = "getAllConfigurationsFromFISpace";
		LOG.info(CLASS_NAME+"."+methodName + "  Entry : Configuration  Parameters  : FiID {}" , fiId );			
		response = configService.fetchAllFIConfigurations();
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}	

	@RequestMapping(value="/{fiId}/configEntries/{keyName}" , method=RequestMethod.GET)
	public ServiceResponse getConfigEntryFromFISpace(@PathVariable(name = "fiId", required = true) String fiId,
			@PathVariable(name = "keyName", required = true) String keyName) {
		
		final String methodName = "getConfigEntryFromFISpace";
		LOG.info(CLASS_NAME+"."+methodName + "  Entry : Configuration  Parameters  : FiID , KeyName {} {} " , fiId , keyName );
		response = configService.fetchFIConfiguration(keyName);
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}
	
	@RequestMapping(value="/{fiId}/configEntries/{keyName}" , method=RequestMethod.PUT)
	public ServiceResponse updateConfigEntryInFISpace(@PathVariable(name = "fiId", required = true) String fiId,
			@PathVariable(name = "keyName", required = true) String keyName) {
		
		final String methodName = "updateConfigEntryInFISpace";
		LOG.info(CLASS_NAME+"."+methodName + "  Entry : Configuration  Parameters  : FiID , KeyName {} {} " , fiId , keyName );
		response = configService.updateFIConfiguration("");
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}
	
}
