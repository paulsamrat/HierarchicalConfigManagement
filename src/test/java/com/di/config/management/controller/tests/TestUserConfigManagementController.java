package com.di.config.management.controller.tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.di.config.management.entity.ConfigEntry;
import com.di.config.management.entity.ServiceResponse;

public class TestUserConfigManagementController {
private static final String SERVER_PATH =  "http://127.0.0.1:8761/user";
	
	private static RestTemplate restTemplate ;
	
	private static ConfigEntry configEntry;
	
	private static Map<String,String> uriVariables = new HashMap<String,String>();
	
	@BeforeClass
	public static void setUp(){
	    restTemplate = new RestTemplate();
	    configEntry = new ConfigEntry("U1-K1", "U1-V1");
	    uriVariables = new HashMap<String,String>();
	    uriVariables.put("fiId", "F1-001");
	    uriVariables.put("userId", "U1-001");
	    uriVariables.put("keyName", "U1-K1");
	    
	}
	
	@Test
	public void testCreateConfigurationInUserSpace(){
		System.out.println(" testCreateConfigurationInUserSpace Entry");
		String uri = SERVER_PATH+"/{userId}/{fiId}/configEntries";
		ServiceResponse response = restTemplate.postForObject(uri, configEntry, ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testCreateConfigurationInUserSpace Exit Response : " + response);
	}
	
	@Test
	public void testGetAllConfigurationsFromUserSpace(){
		System.out.println(" testGetAllConfigurationsFromUserSpace Entry");
		String uri = SERVER_PATH+"/{userId}/{fiId}/configEntries";
		ServiceResponse response = restTemplate.getForObject(uri, ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testGetAllConfigurationsFromUserSpace Exit Response : " + response);
	}
	
	@Test
	public void testGetConfigEntryFromUserSpace() {
		System.out.println(" testGetConfigEntryFromUserSpace Entry");
		String uri = SERVER_PATH+"/{userId}/{fiId}/configEntries/{keyName}";
		ServiceResponse response = restTemplate.getForObject(uri, ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testGetConfigEntryFromUserSpace Exit Response : " + response);
	}
	
	@Test
	public void testUpdateConfigEntryInUserSpace() {
		System.out.println(" testUpdateConfigEntryInUserSpace Entry");
		String uri = SERVER_PATH+"/{userId}/{fiId}/configEntries/{keyName}";
		ResponseEntity<ServiceResponse> response = restTemplate.exchange(uri, HttpMethod.PUT, null,ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatusCode().is2xxSuccessful(), true);
		System.out.println("testUpdateConfigEntryInUserSpace Exit Response : " + response);
	}
}
