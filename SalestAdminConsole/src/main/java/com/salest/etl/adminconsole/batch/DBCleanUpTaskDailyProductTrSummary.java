package com.salest.etl.adminconsole.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class DBCleanUpTaskDailyProductTrSummary extends JdbcDaoSupport implements Tasklet{

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		cleanUpTable();
		return null;
	}
	
	private void cleanUpTable(){
		String[] strSql = {"DELETE FROM daily_product_tr_summary"};
		getJdbcTemplate().batchUpdate(strSql);
	}
}