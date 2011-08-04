package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords;
/**
 * 单证记录管理，聚合运输单证明细和报关单证明细
 */
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockArea;

/**
 * @author wangyx
 * @version 1.0
 * @created 27-四月-2011 10:09:32
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_DOCUMENT_RECORDS")
public class DocumentRecords extends BaseEntity {
	
	/**
	 * 物流任务编号 
	 */
	private String logisticsTaskNumber;
	/**
	 * 合同编号
	 */
	private String contractNumber;
	/**
	 * 指令ID
	 */
	private String orderID;
	/**
	 * 指令编号
	 */
	private String orderNumber;
	/**
	 * 运代商
	 */
	private String forwarder;
	/**
	 * 报关代理商
	 */
	private String customsAgent;
	/**
	 * 报关代理商联系人
	 */
	private String linkmanOfCustomsAgent;
	/**
	 * 运代商联系人
	 */
	private String linkmanOfForwarder;
	/**
	 * 报关代理商联系方法
	 */
	private String telephoneOfCustomsAgent;
	/**
	 * 运代商联系方法
	 */
	private String telephoneOfForwarder;
	/**
	 * 运输单证明细
	 */
	private List<TransprotationDocument> transprotationDocumentList;
	/**
	 * 报关单证明细
	 */
	private List<CIQDocument> cIQDocumentList;
	
    public DocumentRecords (){
		
	}
	public void finalize() throws Throwable {
		super.finalize();
	}
	
	@Column(name = "LOGISTICS_TASK_NUMBER")
	public String getLogisticsTaskNumber() {
		return logisticsTaskNumber;
	}
	public void setLogisticsTaskNumber(String logisticsTaskNumber) {
		this.logisticsTaskNumber = logisticsTaskNumber;
	}
	@Column(name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	@Column(name = "ORDER_ID")
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	@Column(name = "ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	@Column(name = "FORWARDER")
	public String getForwarder() {
		return forwarder;
	}
	public void setForwarder(String forwarder) {
		this.forwarder = forwarder;
	}
	@Column(name = "CUSTOMS_AGENT")
	public String getCustomsAgent() {
		return customsAgent;
	}
	public void setCustomsAgent(String customsAgent) {
		this.customsAgent = customsAgent;
	}
	@Column(name = "LINKMAN_OF_CUSTOMSAGENT") 
	public String getLinkmanOfCustomsAgent() {
		return linkmanOfCustomsAgent;
	}
	public void setLinkmanOfCustomsAgent(String linkmanOfCustomsAgent) {
		this.linkmanOfCustomsAgent = linkmanOfCustomsAgent;
	}
	@Column(name = "LINKMAN_OF_FORWARDER")
	public String getLinkmanOfForwarder() {
		return linkmanOfForwarder;
	}
	public void setLinkmanOfForwarder(String linkmanOfForwarder) {
		this.linkmanOfForwarder = linkmanOfForwarder;
	}
	@Column(name = "TELEPHONE_OF_COUSTOMSAGENT")
	public String getTelephoneOfCustomsAgent() {
		return telephoneOfCustomsAgent;
	}
	public void setTelephoneOfCustomsAgent(String telephoneOfCustomsAgent) {
		this.telephoneOfCustomsAgent = telephoneOfCustomsAgent;
	}
	@Column(name = "TELEPHONE_OF_FORWARDER") 
	public String getTelephoneOfForwarder() {
		return telephoneOfForwarder;
	}
	public void setTelephoneOfForwarder(String telephoneOfForwarder) {
		this.telephoneOfForwarder = telephoneOfForwarder;
	}
	@OneToMany(targetEntity= TransprotationDocument.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="DOCUMENT_ID")
	public List<TransprotationDocument> getTransprotationDocumentList() {
		return transprotationDocumentList;
	}
	public void setTransprotationDocumentList(
			List<TransprotationDocument> transprotationDocumentList) {
		this.transprotationDocumentList = transprotationDocumentList;
	}
	@OneToMany(targetEntity= CIQDocument.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="DOCUMENT_ID")
	public List<CIQDocument> getcIQDocumentList() {
		return cIQDocumentList;
	}
	public void setcIQDocumentList(List<CIQDocument> cIQDocumentList) {
		this.cIQDocumentList = cIQDocumentList;
	}
}
