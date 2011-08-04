package com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.barCodeEntity.base1DBarCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;

/**
 * 一维条码采用Code 128
 * 采用定长方式设计。
 * 
 * 
 * 条码编码内容由以下业务属性组成（参见收料单管理实体域模型），
 * <ol>
 * 	<li>收料单编号</li>
 * </ol>
 * @author FANYX
 * @version 1.0
 * @created 13-六月-2011 11:08:58
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_RECEIVING_SHEET_1D")
public class ReceivingSheet1DBarCode extends Base1DBarCode {

	/**
	 * 打印出来的收料单条码
	 */
	private String receivingSheetID;

	@Column(name = "RECEIVING_SHEET_ID")
	public String getReceivingSheetID() {
		return receivingSheetID;
	}

	public void setReceivingSheetID(String receivingSheetID) {
		this.receivingSheetID = receivingSheetID;
	}

}