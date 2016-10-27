package com.di.config.management.eureka.tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class TestEurekaServiceConfigurations {
	
	private static final String SERVER_PATH =  "http://127.0.0.1:8761/eurekaServices";
	
	@Autowired
	private static RestTemplate restTemplate ;
		
	private static Map<String,String> uriVariables = new HashMap<String,String>();

	@BeforeClass
	public static void setUp(){
	    restTemplate = new RestTemplate();
	}
	
	@Test
	public void testGetAllRegisteredServices(){
		System.out.println("\n testGetAllRegisteredServices Entry");
		String uri = SERVER_PATH+"/services";
		String response = restTemplate.getForObject(uri, String.class);
		Assert.assertNotNull(response);
		System.out.println("\n testGetAllRegisteredServices Exit Response : " + response);
	}
}
