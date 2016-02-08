package com.salest.etl.adminconsole.dao;

import java.util.List;

import com.salest.etl.adminconsole.model.DailyTrSummary;

public interface DailyTrSummaryDAO {
	
	public void save(DailyTrSummary obj);
	public List<DailyTrSummary> list();
	public DailyTrSummary getEarliestDateItem();
	public DailyTrSummary getLatestDateItem();
}
