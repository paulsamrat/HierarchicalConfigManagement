package com.di.config.management.controller.tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.di.config.management.entity.ConfigEntry;
import com.di.config.management.entity.ServiceResponse;

public class TestGlobalConfigManagementController {
	
	
	private static RestTemplate restTemplate ;
	
	private static ConfigEntry configEntry;
	
	private static Map<String,String> uriVariables = new HashMap<String,String>();

	private ServiceResponse serviceResponse = null;
	
	@BeforeClass
	public static void setUp(){
	    restTemplate = new RestTemplate();
	    configEntry = new ConfigEntry("G-K1", "G-V1");
	    uriVariables = new HashMap<String,String>();
	    uriVariables.put("keyName", "G-K1");
	}
	
	@Before
	public void loadBalancedServerSetUp(){
		serviceResponse = restTemplate.getForObject("http://127.0.0.1:9000/ribbons/getLoadBalancedServer", ServiceResponse.class);
	}
	//@Test
	public void testCreateConfigurationInGlobalSpace(){
		System.out.println(" testCreateConfigurationInGlobalSpace Entry");
		String uri = "http://"+ serviceResponse.getIp()+":" +serviceResponse.getPort()+"/global/configEntries";
		ServiceResponse response = restTemplate.postForObject(uri, configEntry, ServiceResponse.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testCreateConfigurationInGlobalSpace Exit Response : " + response);
	}
	
	//@Test
	public void testGetAllConfigurationsFromGlobalSpace(){
		System.out.println(" testGetAllConfigurationsFromGlobalSpace Entry");
		String uri = "http://"+ serviceResponse.getIp()+":" +serviceResponse.getPort()+"/global/configEntries";
		ServiceResponse response = restTemplate.getForObject(uri, ServiceResponse.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testGetAllConfigurationsFromGlobalSpace Exit Response : " + response);
	}
	
	@Test
	public void testGetConfigEntryFromGlobalSpace() {
		System.out.println(" testGetConfigEntryFromGlobalSpace Entry");
		String uri = "http://"+ serviceResponse.getIp()+":" +serviceResponse.getPort()+"/global/configEntries/{keyName}";
		ServiceResponse response = restTemplate.getForObject(uri, ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testGetConfigEntryFromGlobalSpace Exit Response : " + response);
	}
	
	//@Test
	public void testUpdateConfigEntryInGlobalSpace() {
		System.out.println(" testUpdateConfigEntryInGlobalSpace Entry");
		String uri = "http://"+ serviceResponse.getIp()+":" +serviceResponse.getPort()+"/global/configEntries/{keyName}";
		ResponseEntity<ServiceResponse> response = restTemplate.exchange(uri, HttpMethod.PUT, null,ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatusCode().is2xxSuccessful(), true);
		System.out.println("testUpdateConfigEntryInGlobalSpace Exit Response : " + response);
	}
}
