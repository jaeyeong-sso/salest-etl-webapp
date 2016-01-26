package com.salest.etl.adminconsole.api;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salest.etl.adminconsole.hadoop.HDFSService;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Component
@Path("/upload")
public class RawFileUploadService {
	
	@Autowired
	private HDFSService hdfsService;
	
	@POST
	@Path("/tr_csv")
	@Consumes({"multipart/form-data"})
	public Response uploadCSVFile(@FormDataParam("file") InputStream fileInputStream, @FormDataParam("file") FormDataContentDisposition contentDispositionHeader){
		if (contentDispositionHeader.getFileName() != null){
			
			hdfsService.appenToFileOnHDFS(fileInputStream, contentDispositionHeader.getFileName());
	      
			return Response.status(Response.Status.OK).build();
	    }
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
