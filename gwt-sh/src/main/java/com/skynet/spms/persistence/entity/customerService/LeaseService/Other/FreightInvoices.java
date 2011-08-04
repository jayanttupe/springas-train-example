package com.skynet.spms.persistence.entity.customerService.LeaseService.Other;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 乔海龙
 * @category 货运发票
 * @version 1.0
 * @created 03-四月-2011 11:32:49
 */
@Entity
@Table(name = "SPMS_FREIGHTINVOICES")
public class FreightInvoices {
	// 交货日期
	private Date DeliveryDate;
	// 运输方式
	private int ModeOfTransportation;
	// 客户税务登记号
	private int CustomerTaxRegistrationNumber;
	// 货运代理
	private int FreightForwarders;
	// 收货人
	private int Consignee;
	// 目的地
	private int Destination;
	// 收货地址
	private int ReceivingdAddress;
	// 发票开给
	private int InvoiceOpenTo;
	// 发票寄给
	private int InvoiceTo;
	// 发票地址
	private int InvoicingAddress;
	// 条款
	private int Clause;

	@Column(name = "DELIVERYDATE")
	public Date getDeliveryDate() {
		return DeliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		DeliveryDate = deliveryDate;
	}

	@Column(name = "MODEOFTRANSPORTATION")
	public int getModeOfTransportation() {
		return ModeOfTransportation;
	}

	public void setModeOfTransportation(int modeOfTransportation) {
		ModeOfTransportation = modeOfTransportation;
	}

	@Column(name = "CUSTOMERTAXREGISTRATIONNUMBER")
	public int getCustomerTaxRegistrationNumber() {
		return CustomerTaxRegistrationNumber;
	}

	public void setCustomerTaxRegistrationNumber(
			int customerTaxRegistrationNumber) {
		CustomerTaxRegistrationNumber = customerTaxRegistrationNumber;
	}

	@Column(name = "FREIGHTFORWARDERS")
	public int getFreightForwarders() {
		return FreightForwarders;
	}

	public void setFreightForwarders(int freightForwarders) {
		FreightForwarders = freightForwarders;
	}

	@Column(name = "CONSIGNEE")
	public int getConsignee() {
		return Consignee;
	}

	public void setConsignee(int consignee) {
		Consignee = consignee;
	}

	@Column(name = "DESTINATION")
	public int getDestination() {
		return Destination;
	}

	public void setDestination(int destination) {
		Destination = destination;
	}

	@Column(name = "RECEIVINGDADDRESS")
	public int getReceivingdAddress() {
		return ReceivingdAddress;
	}

	public void setReceivingdAddress(int receivingdAddress) {
		ReceivingdAddress = receivingdAddress;
	}

	@Column(name = "INVOICEOPENTO")
	public int getInvoiceOpenTo() {
		return InvoiceOpenTo;
	}

	public void setInvoiceOpenTo(int invoiceOpenTo) {
		InvoiceOpenTo = invoiceOpenTo;
	}

	@Column(name = "INVOICETO")
	public int getInvoiceTo() {
		return InvoiceTo;
	}

	public void setInvoiceTo(int invoiceTo) {
		InvoiceTo = invoiceTo;
	}

	@Column(name = "INVOICINGADDRESS")
	public int getInvoicingAddress() {
		return InvoicingAddress;
	}

	public void setInvoicingAddress(int invoicingAddress) {
		InvoicingAddress = invoicingAddress;
	}

	@Id
	@Column(name = "CLAUSE")
	@GeneratedValue
	public int getClause() {
		return Clause;
	}

	public void setClause(int clause) {
		Clause = clause;
	}

}