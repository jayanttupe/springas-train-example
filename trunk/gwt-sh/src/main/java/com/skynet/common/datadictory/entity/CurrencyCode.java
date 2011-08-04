package com.skynet.common.datadictory.entity;

import com.skynet.common.datadictory.DataDictEntity;

public class CurrencyCode implements DataDictEntity{
	
	private int unit=1;
	
	private String name;

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
