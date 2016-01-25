package com.salest.etl.adminconsole.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Path("/rawdata")
public class RawDataProcessService {
	
	//@Autowired
	//private HDFSService hdfsService;

	@GET
	@Path("/aggByDaily")
	public Response test() {
		
		return Response.status(Response.Status.OK).entity("TEST OK").build();
	}
	
	
	@POST
	@Path("/aggByDaily")
	public Response execAggTrData() {
		
		return Response.status(Response.Status.OK).build();
		
		/*
		if( hdfsService.executeMapReduceAggTransactionPerDaily()){
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		*/
		

	}
}