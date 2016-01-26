package com.salest.etl.adminconsole.api;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyApplication extends ResourceConfig {
	
	public JerseyApplication(){
		register(RequestContextFilter.class);
		register(MultiPartFeature.class);
		register(RawDataProcessService.class);
		register(RawFileUploadService.class);
	}
}