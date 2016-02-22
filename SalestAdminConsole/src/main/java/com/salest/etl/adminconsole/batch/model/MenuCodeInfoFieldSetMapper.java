package com.salest.etl.adminconsole.batch.model;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class MenuCodeInfoFieldSetMapper implements FieldSetMapper<MenuCodeInfo>{

	public MenuCodeInfo mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		MenuCodeInfo item = new MenuCodeInfo();
		
		item.setCategory(fieldSet.readString(0));
		item.setProduct_code(fieldSet.readInt(1));
		item.setProduct_name(fieldSet.readString(2));
		item.setPrice(fieldSet.readInt(3));
		
		return item;
	}

}
