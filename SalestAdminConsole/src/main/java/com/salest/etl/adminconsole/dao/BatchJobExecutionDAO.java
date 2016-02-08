package com.salest.etl.adminconsole.dao;

import java.util.List;

import com.salest.etl.adminconsole.model.BatchJobExecution;

public interface BatchJobExecutionDAO {

	public List<BatchJobExecution> list();
}
