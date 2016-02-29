package com.salest.etl.adminconsole.batch.model;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class DailyTimeBaseAggFieldSetMapper implements FieldSetMapper<DailyTimeBaseAgg>{

	public DailyTimeBaseAgg mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		DailyTimeBaseAgg item = new DailyTimeBaseAgg();
		
		item.setDate(fieldSet.readDate(0,"yyyy-MM-dd"));
		item.setH10(fieldSet.readLong(1));
		item.setH11(fieldSet.readLong(2));
		item.setH12(fieldSet.readLong(3));
		item.setH13(fieldSet.readLong(4));
		item.setH14(fieldSet.readLong(5));
		item.setH15(fieldSet.readLong(6));
		item.setH16(fieldSet.readLong(7));
		item.setH17(fieldSet.readLong(8));
		item.setH18(fieldSet.readLong(9));
		item.setH19(fieldSet.readLong(10));
		item.setH20(fieldSet.readLong(11));
		item.setH21(fieldSet.readLong(12));
		item.setH22(fieldSet.readLong(13));
		item.setH23(fieldSet.readLong(14));
		
		return item;
	}

}
