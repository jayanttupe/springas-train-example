package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author wangyx
 * @version 1.0
 * @created 27-四月-2011 10:12:08
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_TRANSPROTATION_DOCUMENT")
public class TransprotationDocument extends BaseEntity {

	/**
	 * 单证ID
	 */
	private String documentID;
	/**
	 * 单证编号
	 */
	private String documentNumber;
	/**
	 * 单证名称
	 */
	private String documentName;
	/**
	 * 登记人
	 */
	private String registrant;
	/**
	 * 备注
	 */
	private String remarkText;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 运输单证类型
	 */
	private String transportDocumentsType;
	
    public TransprotationDocument (){
			
	}
	public void finalize() throws Throwable {
			super.finalize();
	}

	@Column(name = "DOCUMENT_ID")
	public String getDocumentID() {
		return documentID;
	}
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	@Column(name = "DOCUMENT_NUMBER")
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	@Column(name = "DOCUMENT_NAME")
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	@Column(name = "REGISTRANT")
	public String getRegistrant() {
		return registrant;
	}
	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}
	@Column(name = "REMARK_TEXT")
	public String getRemarkText() {
		return remarkText;
	}
	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "TRANSPORT_DOCUMENTS_TYPE")
	public String getTransportDocumentsType() {
		return transportDocumentsType;
	}
	public void setTransportDocumentsType(String transportDocumentsType) {
		this.transportDocumentsType = transportDocumentsType;
	}
	
}
