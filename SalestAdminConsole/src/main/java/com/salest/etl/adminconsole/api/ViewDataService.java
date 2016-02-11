package com.salest.etl.adminconsole.api;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salest.etl.adminconsole.dao.HdfsClusterInfoDAO;
import com.salest.etl.adminconsole.dao.HdfsNodesInfoDAO;
import com.salest.etl.adminconsole.model.HdfsClusterInfo;
import com.salest.etl.adminconsole.model.HdfsNodesInfo;

@Component
@Path("/viewdata")
public class ViewDataService {
	
	@Autowired
	HdfsClusterInfoDAO hdfsClusterInfoDAO;
	
	@Autowired
	HdfsNodesInfoDAO hdfsNodesInfoDAO;

	
	@GET
	@Path("/cluster_info")
	@Produces(MediaType.APPLICATION_JSON)
	public HdfsClusterInfo getHDFSClusterInfo() {
	
		return hdfsClusterInfoDAO.getUniqueItem();
	}
	
	@GET
	@Path("/nodes_info")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HdfsNodesInfo> getHDFSNodesInfo() {
	
		return hdfsNodesInfoDAO.list();
	}
}
