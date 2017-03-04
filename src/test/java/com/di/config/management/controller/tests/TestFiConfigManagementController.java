package com.di.config.management.controller.tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

import com.di.config.management.entity.ConfigEntry;
import com.di.config.management.entity.ServiceResponse;

/**
 * @author  Samrat Paul
 * @created 11:47:52 AM
 */
@RunWith(SpringRunner.class) 
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class TestFiConfigManagementController {
    
	@Configuration
	static class ContextConfiguration{
		
		// this bean will be injected into the TestFiConfigManagementController class
        
		@Bean
        public RestTemplate restTemplate() {
        	RestTemplate restTemplate = new RestTemplate();
            // set properties, etc.
            return restTemplate;
		}   
		
	}
	
	@Autowired
	private RestTemplate restTemplate ;
		
	private static ConfigEntry configEntry;
	
	private static Map<String,String> uriVariables = new HashMap<String,String>();
	
    private ServiceResponse serviceResponse = null;
    
	@BeforeClass
	public static void setUp(){
	    configEntry = new ConfigEntry("F1-K1", "F1-V1");
	    uriVariables = new HashMap<String,String>();
	    uriVariables.put("fiId", "F1-001");
	    uriVariables.put("keyName", "F1-K1");  	    
	}
	
	@Before
	public void loadBalancedServerSetUp(){
		serviceResponse = restTemplate.getForObject("http://127.0.0.1:9000/ribbons/getLoadBalancedServer", ServiceResponse.class);
	}
	
	//@Test
	public void testCreateConfigurationInFISpace(){
		System.out.println(" testCreateConfigurationInFISpace Entry");
		String uri = "http://"+ serviceResponse.getIp()+":" +serviceResponse.getPort()+ "/fis/{fiId}/configEntries";
		System.out.println(" uri formed " + uri);
		ServiceResponse response = restTemplate.postForObject(uri, configEntry, ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testCreateConfigurationInFISpace Exit Response : " + response);
	}
	
	//@Test
	public void testGetAllConfigurationsFromFISpace(){
		System.out.println(" testGetAllConfigurationsFromFISpace Entry");
		String uri = "http://"+ serviceResponse.getIp()+":" +serviceResponse.getPort()+"/fis/{fiId}/configEntries";
		ServiceResponse response = restTemplate.getForObject(uri, ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testGetAllConfigurationsFromFISpace Exit Response : " + response);
	}
	
	@Test
	public void testGetConfigEntryFromFISpace() {
		System.out.println(" testGetConfigEntryFromFISpace Entry");
		String uri =  "http://"+ serviceResponse.getIp()+":" +serviceResponse.getPort()+"/fis/{fiId}/configEntries/{keyName}";
		ServiceResponse response = restTemplate.getForObject(uri, ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.isSuccessful(), true);
		System.out.println("testGetConfigEntryFromFISpace Exit Response : " + response);
	}
	
	//@Test
	public void testUpdateConfigEntryInFISpace() {
		System.out.println(" updateConfigEntryInFISpace Entry");
		String uri =  "http://"+ serviceResponse.getIp()+":" +serviceResponse.getPort()+"/fis/{fiId}/configEntries/{keyName}";
		ResponseEntity<ServiceResponse> response = restTemplate.exchange(uri, HttpMethod.PUT, null,ServiceResponse.class, uriVariables);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatusCode().is2xxSuccessful(), true);
		System.out.println("testUpdateConfigEntryInFISpace Exit Response : " + response);
	}

	
}
