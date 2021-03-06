package com.salest.etl.adminconsole.web;
 
import java.util.ArrayList;
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
import com.salest.etl.adminconsole.model.HdfsFileListingInfo;
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
		
		List<HdfsFileListingInfo> hdfsFilesInfoList= hdfsService.doFileListing();
		
		List<HdfsNodesInfo> hdfsNodesInfo = hdfsNodesInfoDAO.list();

 		ModelAndView mv = new ModelAndView("dwhouse_man");
 		mv.addObject("hdfsNodesInfo", hdfsNodesInfo);
 		mv.addObject("hdfsFilesInfoList",hdfsFilesInfoList);
 		
		return mv;
	}

	private void retrieveHDFSInformation(){
		
		hdfsService.reportHDFSClusterStatus();
		
		HashMap<String,String> hdfsClusterInfoMap = hdfsService.getHdfsClusterInfoMap();
		ArrayList<HashMap<String,String>>  hdfsNodesInfoMapArr = hdfsService.getHdfsNodesInfoMapArr();
		
		HdfsClusterInfo clusterInfoModel = new HdfsClusterInfo();
		clusterInfoModel.setConfigured_capacity(hdfsClusterInfoMap.get(HDFSService.dfsadmin_configured_capacity_key));
		clusterInfoModel.setPresent_capacity(hdfsClusterInfoMap.get(HDFSService.dfsadmin_present_capacity_key));
		clusterInfoModel.setDfs_used(hdfsClusterInfoMap.get(HDFSService.dfsadmin_dfs_used_key));
		hdfsClusterInfoDAO.update(clusterInfoModel);
		
		for(HashMap<String,String> hdfsNodeInfoMap : hdfsNodesInfoMapArr){
			
			HdfsNodesInfo nodesInfoModel = new HdfsNodesInfo();
			
			nodesInfoModel.setName(hdfsNodeInfoMap.get(HDFSService.dfsadmin_name_key));
			nodesInfoModel.setHostname(hdfsNodeInfoMap.get(HDFSService.dfsadmin_hostname_key));
			nodesInfoModel.setDfs_used(hdfsNodeInfoMap.get(HDFSService.dfsadmin_dfs_used_key));
			nodesInfoModel.setDfs_remaining(hdfsNodeInfoMap.get(HDFSService.dfsadmin_dfs_remaining_key));
			nodesInfoModel.setDfs_remaining_percent(hdfsNodeInfoMap.get(HDFSService.dfsadmin_dfs_remaining_percent_key));
				
			hdfsNodesInfoDAO.update(nodesInfoModel);	
		}
	}


}