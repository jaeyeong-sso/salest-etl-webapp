package com.salest.etl.adminconsole.dao;

import java.util.List;

import com.salest.etl.adminconsole.model.HdfsNodesInfo;

public interface HdfsNodesInfoDAO {

	public void save(HdfsNodesInfo obj);
	public void update(HdfsNodesInfo target);
	public List<HdfsNodesInfo> list();	
	
	//public void saveList(List<DFSAdminReport> objList);
	//public void updateItems(List<DFSAdminReport> objList);
}
