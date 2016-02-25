package com.salest.etl.adminconsole.batch.model;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class DailyProductTrSummaryFieldSetMapper implements FieldSetMapper<DailyProductTrSummary> {

	public DailyProductTrSummary mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		DailyProductTrSummary item = new DailyProductTrSummary();
		
		item.setDate(fieldSet.readDate(0,"yyyy-MM-dd"));
		item.setProduct_name(fieldSet.readString(1));
		item.setProduct_cate(fieldSet.readString(2));
		item.setNum_of_product(fieldSet.readByte(3));
		item.setTotal_amount(fieldSet.readLong(4));
		
		return item;
	}

}
