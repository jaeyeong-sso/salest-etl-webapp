package com.salest.etl.adminconsole.dao;

import java.util.List;

import com.salest.etl.adminconsole.model.DFSAdminReport;

public interface DFSAdminReportDAO {

	public void save(DFSAdminReport obj);
	public void update(DFSAdminReport target);
	public List<DFSAdminReport> list();	
	
	//public void saveList(List<DFSAdminReport> objList);
	//public void updateItems(List<DFSAdminReport> objList);
}
