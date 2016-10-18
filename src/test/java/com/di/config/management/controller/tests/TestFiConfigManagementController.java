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

/**
 * @author  Samrat Paul
 * @created 11:47:52 AM
 */
public class TestFiConfigManagementController {
    
	private static final String SERVER_PATH =  "http://127.0.0.1:8761/fis";
	
	private static RestTemplate restTemplate ;
	
	private static ConfigEntry configEntry;
	
	private static Map<String,String> uriVariables = new HashMap<String,String>();
	
	@BeforeClass
	public static void setUp(){
	    restTemplate = new RestTemplate();
	    configEntry = new ConfigEntry("F1-K1", "F1-V1");
	    uriVariables = new HashMap<String,String>();
	    uriVariables.put("fiId", "F1-001");
	    uriVariables.put("keyName", "F1-K1");
	    
	}
	
	@Test
	public void testCreateConfigurationInFISpace(){
		System.out.println(" testCreateConfigurationInFISpace Entry");
		String uri = SERVER_PATH+"/{fiId}/configEntries";
		ServiceResponse response = restTemplate.postForObject(uri, configEntry, ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testCreateConfigurationInFISpace Exit Response : " + response);
	}
	
	@Test
	public void testGetAllConfigurationsFromFISpace(){
		System.out.println(" testGetAllConfigurationsFromFISpace Entry");
		String uri = SERVER_PATH+"/{fiId}/configEntries";
		ServiceResponse response = restTemplate.getForObject(uri, ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testGetAllConfigurationsFromFISpace Exit Response : " + response);
	}
	
	@Test
	public void testGetConfigEntryFromFISpace() {
		System.out.println(" testGetConfigEntryFromFISpace Entry");
		String uri = SERVER_PATH+"/{fiId}/configEntries/{keyName}";
		ServiceResponse response = restTemplate.getForObject(uri, ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testGetConfigEntryFromFISpace Exit Response : " + response);
	}
	
	@Test
	public void testUpdateConfigEntryInFISpace() {
		System.out.println(" updateConfigEntryInFISpace Entry");
		String uri = SERVER_PATH+"/{fiId}/configEntries/{keyName}";
		ResponseEntity<ServiceResponse> response = restTemplate.exchange(uri, HttpMethod.PUT, null,ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatusCode().is2xxSuccessful(), true);
		System.out.println("testUpdateConfigEntryInFISpace Exit Response : " + response);
	}
}
