package com.skynet.spms.client.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PartInfoVO implements IsSerializable  {
	/***
	 * 制造商主键
	 */
	private String manufacturerCodeId;
	/**
	 * 制造商code
	 */
	private String manufacturerCode;
	
	/**
	 * 关键字
	 */
	private String keyword;
	/**
	 * ATA章节号
	 */
	private String ataNumber;
	/**
	 * 单位
	 */
	private String unitOfMeasureCode;
	/**
	 * 适用机型
	 */
	private String suitableAircraftModel;

	/***
	 * 描述(中文名称)
	 */
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturerCodeId() {
		return manufacturerCodeId;
	}

	public void setManufacturerCodeId(String manufacturerCodeId) {
		this.manufacturerCodeId = manufacturerCodeId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAtaNumber() {
		return ataNumber;
	}

	public void setAtaNumber(String ataNumber) {
		this.ataNumber = ataNumber;
	}

	public String getUnitOfMeasureCode() {
		return unitOfMeasureCode;
	}

	public void setUnitOfMeasureCode(String unitOfMeasureCode) {
		this.unitOfMeasureCode = unitOfMeasureCode;
	}

	public String getSuitableAircraftModel() {
		return suitableAircraftModel;
	}

	public void setSuitableAircraftModel(String suitableAircraftModel) {
		this.suitableAircraftModel = suitableAircraftModel;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

}
