package com.salest.etl.adminconsole.dao;

import com.salest.etl.adminconsole.model.HdfsClusterInfo;

public interface HdfsClusterInfoDAO {
	
	public HdfsClusterInfo getUniqueItem();
	public void save(HdfsClusterInfo obj);
	public void update(HdfsClusterInfo target);
}
