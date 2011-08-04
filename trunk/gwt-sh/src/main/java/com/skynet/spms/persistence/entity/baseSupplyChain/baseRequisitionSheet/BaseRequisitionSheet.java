package com.skynet.spms.persistence.entity.baseSupplyChain.baseRequisitionSheet;
import java.util.Date;
import java.util.List;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:35
 */
public class BaseRequisitionSheet extends BaseEntity {

	private String requisitionSheetNumber;
	private String remarkText;
	/**
	 * 申请单的提交申请的日期，默认为用户提交申请单的日期，用户也可以自行对申请日期进行修改。
	 */
	private Date requisitionDate;
	public List<BussinessStatusEntity> m_BussinessStatusEntity;
	public List<BussinessPublishStatusEntity> m_BussinessPublishStatusEntity;
	public String getRequisitionSheetNumber() {
		return requisitionSheetNumber;
	}
	public void setRequisitionSheetNumber(String requisitionSheetNumber) {
		this.requisitionSheetNumber = requisitionSheetNumber;
	}
	public String getRemarkText() {
		return remarkText;
	}
	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}
	public Date getRequisitionDate() {
		return requisitionDate;
	}
	public void setRequisitionDate(Date requisitionDate) {
		this.requisitionDate = requisitionDate;
	}
	public List<BussinessStatusEntity> getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}
	public void setM_BussinessStatusEntity(
			List<BussinessStatusEntity> m_BussinessStatusEntity) {
		this.m_BussinessStatusEntity = m_BussinessStatusEntity;
	}
	public List<BussinessPublishStatusEntity> getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}
	public void setM_BussinessPublishStatusEntity(
			List<BussinessPublishStatusEntity> m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
	}

}