package com.skynet.spms.persistence.entity.customerService.LeaseService.Other;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author 乔海龙
 * @category 租赁合同
 * @version 1.0
 * @created 03-四月-2011 11:32:55
 */
@Entity
@Table(name = "SPMS_LEASECONTRACT")
public class LeaseContract {
	// 合同编号
	private int ContractNumber;
	// 客户
	private int Customer;
	// 联系人
	private int Linkman;
	// 订货机型
	private int OrderType;
	// 创建日期
	private Date CreateDate;
	// 合同依据
	private int ContractAccording;
	// 优先级
	private int Priority;
	// 飞机编号
	private int AircraftNumbers;
	
	private LeaseSpareParts m_LeaseSpareParts;
	private FreightInvoices m_FreightInvoices;

	@Id
	@GeneratedValue
	@Column(name = "CONTRACTNUMBER")
	public int getContractNumber() {
		return ContractNumber;
	}

	public void setContractNumber(int contractNumber) {
		ContractNumber = contractNumber;
	}

	@Column(name = "CUSTOMER")
	public int getCustomer() {
		return Customer;
	}

	public void setCustomer(int customer) {
		Customer = customer;
	}

	@Column(name = "LINKMAN")
	public int getLinkman() {
		return Linkman;
	}

	public void setLinkman(int linkman) {
		Linkman = linkman;
	}

	@Column(name = "ORDERTYPE")
	public int getOrderType() {
		return OrderType;
	}

	public void setOrderType(int orderType) {
		OrderType = orderType;
	}

	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	@Column(name = "CONTRACTACCORDING")
	public int getContractAccording() {
		return ContractAccording;
	}

	public void setContractAccording(int contractAccording) {
		ContractAccording = contractAccording;
	}

	@Column(name = "PRIORITY")
	public int getPriority() {
		return Priority;
	}

	public void setPriority(int priority) {
		Priority = priority;
	}

	@Column(name = "AIRCRAFTNUMBERS")
	public int getAircraftNumbers() {
		return AircraftNumbers;
	}

	public void setAircraftNumbers(int aircraftNumbers) {
		AircraftNumbers = aircraftNumbers;
	}
	@OneToOne
	@JoinColumn(name = "LSP_ID")
	public LeaseSpareParts getM_LeaseSpareParts() {
		return m_LeaseSpareParts;
	}

	public void setM_LeaseSpareParts(LeaseSpareParts m_LeaseSpareParts) {
		this.m_LeaseSpareParts = m_LeaseSpareParts;
	}
	@OneToOne
	@JoinColumn(name = "FIS_ID")
	public FreightInvoices getM_FreightInvoices() {
		return m_FreightInvoices;
	}

	public void setM_FreightInvoices(FreightInvoices m_FreightInvoices) {
		this.m_FreightInvoices = m_FreightInvoices;
	}

}