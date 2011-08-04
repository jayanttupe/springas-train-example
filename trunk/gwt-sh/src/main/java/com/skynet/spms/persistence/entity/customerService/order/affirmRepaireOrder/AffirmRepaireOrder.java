package com.skynet.spms.persistence.entity.customerService.order.affirmRepaireOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.base.baseOrderEntity.BaseOrderEntity;

/**
 * 客户确认修理指令
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:14:56
 */
@Entity
@Table(name = "SPMS_AFFIRMREPAIREORDER")
public class AffirmRepaireOrder extends BaseOrderEntity {

	private String contractNumber;

	private String contractID;

	private String customerRepairInspectionOrderId;

	private Boolean argeeRepaire;

	private String customerIdentificationCode;

	private String linkMan;

	private String linkWay;

	/**
	 * 客户送修指令UUUID
	 * 
	 * @return
	 */
	@Column(name = "CUSREPAIRINSORDERID")
	public String getCustomerRepairInspectionOrderId() {
		return customerRepairInspectionOrderId;
	}

	public void setCustomerRepairInspectionOrderId(
			String customerRepairInspectionOrderId) {
		this.customerRepairInspectionOrderId = customerRepairInspectionOrderId;
	}

	/**
	 * 客户送修合同编号
	 * 
	 * @return
	 */
	@Column(name = "CONTRACTNUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	/**
	 * 客户送修合同UUID
	 * 
	 * @return
	 */
	@Column(name = "CONTRACTID")
	public String getContractID() {
		return contractID;
	}

	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

	/**
	 * 是否修理
	 * 
	 * @return
	 */
	@Column(name = "ARGEEREPAIRE")
	public Boolean getArgeeRepaire() {
		return argeeRepaire;
	}

	public void setArgeeRepaire(Boolean argeeRepaire) {
		this.argeeRepaire = argeeRepaire;
	}

	/**
	 * 客户code UUID
	 * 
	 * @return
	 */
	@Column(name = "CUSTOMERIDENTIFICATIONCODE")
	public String getCustomerIdentificationCode() {
		return customerIdentificationCode;
	}

	public void setCustomerIdentificationCode(String customerIdentificationCode) {
		this.customerIdentificationCode = customerIdentificationCode;
	}

	/**
	 * 联系人
	 * 
	 * @return
	 */
	@Column(name = "LINKMAN")
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	/**
	 * 联系方式
	 * 
	 * @return
	 */
	@Column(name = "LINKWAY")
	public String getLinkWay() {
		return linkWay;
	}

	public void setLinkWay(String linkWay) {
		this.linkWay = linkWay;
	}

}