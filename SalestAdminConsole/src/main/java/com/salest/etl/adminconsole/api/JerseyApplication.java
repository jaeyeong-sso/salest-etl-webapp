package com.salest.etl.adminconsole.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyApplication extends ResourceConfig {
	
	public JerseyApplication(){
		register(RequestContextFilter.class);
		register(RawDataProcessService.class);
	}
}