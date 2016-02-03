package com.salest.etl.adminconsole.batch.model;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class DailyTrSummaryFieldSetMapper implements FieldSetMapper<DailyTrSummary>{

	public DailyTrSummary mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		DailyTrSummary item = new DailyTrSummary();
		
		item.setDate(fieldSet.readDate(0,"yyyy-MM-dd"));
		item.setNum_of_order(fieldSet.readByte(1));
		item.setTotal_amount(fieldSet.readLong(2));
		
		return item;
	}

}
