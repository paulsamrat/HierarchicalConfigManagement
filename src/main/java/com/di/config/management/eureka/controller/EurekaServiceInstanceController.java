package com.di.config.management.eureka.controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  Samrat Paul
 * @created 2:09:52 PM
 */
@RestController
@RequestMapping(value=EurekaServiceInstanceController.URL)
public class EurekaServiceInstanceController {
	
	public  static final String URL = "/eurekaServices";
	
	private static final Logger LOG = LoggerFactory.getLogger(EurekaServiceInstanceController.class);
	
	private static final String CLASS_NAME = EurekaServiceInstanceController.class.getSimpleName();
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient lbClient;
	
	
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@RequestMapping(value="/services" , method= RequestMethod.GET)
	public String getRegisteredService(){
		final String methodName = "getRegisteredService";
		LOG.info(CLASS_NAME + "." + methodName + " *** Entry *** ");
		String server = "";
		try{
//			List<ServiceInstance> serviceInstances=  discoveryClient.getInstances("HierarchicalConfigManagement-Client");
//			System.out.println(serviceInstances);
			ServiceInstance serviceInstance= lbClient.choose("HierarchicalConfigManagement-Client");
			server = serviceInstance.toString();
		}catch(Exception e){
			LOG.error("Exception occured while fetching  registered service {}" , e);
		}
		LOG.info(CLASS_NAME + "." + methodName + " *** Exit *** ");
		return server;
	}
	
	
}
