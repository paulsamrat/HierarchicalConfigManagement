package com.di.config.management;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AutoReloadableMsgSrcConfigurator {
	
	@Bean
	public ResourceLoaderAware  messageSource(){
	ReloadableResourceBundleMessageSource msgSource = new ReloadableResourceBundleMessageSource();
	msgSource.setBasename("classpath:bootstrap");
	msgSource.setCacheSeconds(6);
	return msgSource;
	
	}
}
