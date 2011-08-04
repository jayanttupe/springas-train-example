package com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.barCodeEntity.base1DBarCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;

/**
 * 一维条码采用 Code 128
 * 采用定长方式设计
 * 
 * 
 * 条码编码内容由以下业务属性组成（参见货位实体域模型）
 * <ol>
 * 	<li>地区代码</li>
 * 	<li>备件中心代码</li>
 * 	<li>库房编号</li>
 * 	<li>区域号</li>
 * 	<li>货架排号</li>
 * 	<li>货架类型</li>
 * 	<li>货架列号</li>
 * 	<li>货架层号</li>
 * 	<li>货位号</li>
 * </ol>
 * @author FANYX
 * @version 1.0
 * @created 13-六月-2011 11:08:57
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_CARGO_SPACE_1DBARCODE")
public class CargoSpace1DBarCode extends Base1DBarCode {

	/**
	 * 打印出来的货位编号  
	 */
	private String cargoSpaceID;

	@Column(name = "CARGO_SPACE_ID")
	public String getCargoSpaceNumber() {
		return cargoSpaceID;
	}

	public void setCargoSpaceNumber(String cargoSpaceID) {
		this.cargoSpaceID = cargoSpaceID;
	}
	
}