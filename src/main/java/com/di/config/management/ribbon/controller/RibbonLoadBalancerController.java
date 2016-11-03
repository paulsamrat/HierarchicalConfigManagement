package com.di.config.management.ribbon.controller;

import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.di.config.management.entity.ServiceResponse;

/**
 * @author  Samrat Paul
 * @created 2:09:52 PM
 */
@RestController
@RequestMapping(value=RibbonLoadBalancerController.URL)
public class RibbonLoadBalancerController {
	
	public  static final String URL = "/ribbons";
	
	private static final Logger LOG = LoggerFactory.getLogger(RibbonLoadBalancerController.class);
	
	private static final String CLASS_NAME = RibbonLoadBalancerController.class.getSimpleName();

	
	@Autowired
	private LoadBalancerClient lbClient;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@RequestMapping(value="/getLoadBalancedServer" , method= RequestMethod.GET)
	public ServiceResponse getLoadBalancedServer(){
		final String methodName = "getLoadBalancedServer";
		LOG.info(CLASS_NAME + "." + methodName + " *** Entry *** ");
		ServiceResponse response = null;
		try{
			ServiceInstance serviceInstance = lbClient.choose(appName);
			LOG.info("load balanced server picked " + serviceInstance.toString());
			response =  new ServiceResponse(true,serviceInstance.getHost(),serviceInstance.getPort());
		}catch(Exception e){
			LOG.error("Exception occured while fetching  registered service {}" , e);
		}
		LOG.info(CLASS_NAME + "." + methodName + " *** Exit *** ");
		return response;
	}
	
	
}
