package com.di.config.management.controller;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

@RestController("GlobalConfigManagementController")
@RequestMapping(value=GlobalConfigManagementController.URL)
public class GlobalConfigManagementController {
	
	public static final String URL = "/global";

	private static final String CLASS_NAME = GlobalConfigManagementController.class.getSimpleName();
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalConfigManagementController.class);
	
	private ServiceResponse response = null;

	@Autowired
	private IConfigManagementService configService;
	
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value="/configEntries" , method=RequestMethod.POST)
	public ServiceResponse createConfigurationInGlobalSpace(@RequestBody @Valid ConfigEntry configEntry , BindingResult result) {
		
		final String methodName = "createConfigurationInGlobalSpace";
		LOG.info(CLASS_NAME+"."+methodName + "  Entry : ConfigEntry Parameters {} " , configEntry );
		
		if (result.hasErrors()){
			response =  new ServiceResponse(false,result.getAllErrors(),result.getObjectName());
			LOG.error(CLASS_NAME+ "." + methodName + " Error Encountered !! {}" , response);
		}else
			response = configService.createGlobalConfiguration(configEntry);
		
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}

	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value="/configEntries" , method=RequestMethod.GET)
	public ServiceResponse getAllConfigurationsFromGlobalSpace() {
		
		final String methodName = "getAllConfigurationsFromGlobalSpace";
		LOG.info(CLASS_NAME+"."+methodName + " :  Entry : " );
		response = configService.fetchAllGlobalConfigurations();		
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}

	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value="/configEntries/{keyName}" , method=RequestMethod.GET)
	public ServiceResponse getConfigEntryFromGlobalSpace(@PathVariable(name="keyName" , required= true) String keyName) {
		
		final String methodName = "getConfigEntryFromGlobalSpace";
		LOG.info(CLASS_NAME+"."+methodName + " :  Entry : Parameters KeyName :  {}" , keyName );
		response = configService.fetchGlobalConfiguration(keyName);		
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value="/configEntries/{keyName}" , method=RequestMethod.PUT)
	public ServiceResponse updateConfigEntryInGlobalSpace(@PathVariable(name="keyName" , required= true) String keyName) {
		
		final String methodName = "updateConfigEntryInGlobalSpace";
		LOG.info(CLASS_NAME+"."+methodName + " :  Entry : Parameters KeyName :  {}" , keyName );
		response = configService.updateGlobalConfiguration(keyName);
		LOG.info(CLASS_NAME+"." + methodName + "Exit : Service Response {}", response);
		return response;
	}
	
	
}
