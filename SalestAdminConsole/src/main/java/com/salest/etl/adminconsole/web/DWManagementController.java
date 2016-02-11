package com.salest.etl.adminconsole.web;
 
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.salest.etl.adminconsole.dao.HdfsClusterInfoDAO;
import com.salest.etl.adminconsole.dao.HdfsNodesInfoDAO;
import com.salest.etl.adminconsole.hdfs.HDFSService;
import com.salest.etl.adminconsole.model.HdfsClusterInfo;
import com.salest.etl.adminconsole.model.HdfsNodesInfo;
 
@Controller
public class DWManagementController {
	
	@Autowired
	HDFSService hdfsService;
	
	@Autowired
	HdfsClusterInfoDAO hdfsClusterInfoDAO;
	
	@Autowired
	HdfsNodesInfoDAO hdfsNodesInfoDAO;
	
	
	@RequestMapping("/dwhouse_man")
	public ModelAndView dwhouse_man(){	

		this.retrieveHDFSInformation();
		
		List<HdfsNodesInfo> hdfsNodesInfo = hdfsNodesInfoDAO.list();

 		ModelAndView mv = new ModelAndView("dwhouse_man");
 		mv.addObject("hdfsNodesInfo", hdfsNodesInfo);
 		
		return mv;
	}

	private void retrieveHDFSInformation(){
		
		hdfsService.reportHDFSClusterStatus();
		
		HashMap<String,String> hdfsClusterInfoMap = hdfsService.getHdfsClusterInfoMap();
		HashMap<String,String> hdfsNodesInfoMap = hdfsService.getHdfsNodesInfoMap();
		
		HdfsClusterInfo clusterInfoModel = new HdfsClusterInfo();
		clusterInfoModel.setConfigured_capacity(hdfsClusterInfoMap.get(HDFSService.dfsadmin_configured_capacity_key));
		clusterInfoModel.setPresent_capacity(hdfsClusterInfoMap.get(HDFSService.dfsadmin_present_capacity_key));
		hdfsClusterInfoDAO.update(clusterInfoModel);
		 		 
		HdfsNodesInfo nodesInfoModel = new HdfsNodesInfo();
		nodesInfoModel.setName(hdfsNodesInfoMap.get(HDFSService.dfsadmin_name_key));
		nodesInfoModel.setHostname(hdfsNodesInfoMap.get(HDFSService.dfsadmin_hostname_key));
		nodesInfoModel.setDfs_used(hdfsNodesInfoMap.get(HDFSService.dfsadmin_dfs_used_key));
		nodesInfoModel.setDfs_remaining(hdfsNodesInfoMap.get(HDFSService.dfsadmin_dfs_remaining_key));
		nodesInfoModel.setDfs_remaining_percent(hdfsNodesInfoMap.get(HDFSService.dfsadmin_dfs_remaining_percent_key));
			
		hdfsNodesInfoDAO.update(nodesInfoModel);
	}


}