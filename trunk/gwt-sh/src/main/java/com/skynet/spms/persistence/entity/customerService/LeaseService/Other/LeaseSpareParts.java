package com.skynet.spms.persistence.entity.customerService.LeaseService.Other;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 乔海龙
 * @category 租赁备件
 * @version 1.0
 * @created 03-四月-2011 11:32:57
 */
@Entity
@Table(name = "SPMS_LEASESPAREPARTS")
public class LeaseSpareParts {
	// 件号
	private int PartNumber;
	// 制造商代码
	private int ManufacturerCode;
	// 名称
	private int Name;
	// 数量
	private int Quantity;
	// 状况
	private int Status;
	// 原价值
	private int OriginalValue;
	// 租赁开始
	private Date LeaseStart;
	// 租赁结束
	private Date LeaseEnds;
	// 租赁天数
	private int LeaseFate;
	// 日租金
	private int DailyRent;
	// 币种
	private int Currency;
	// 超期租赁费
	private int ExtendedRent;
	// 手续费
	private int Poundage;
	// 总金额
	private int TotalAmount;
	// 随机证件
	private int RandomCertificates;
	// 备注
	private String Remarks;

	@Id
	@GeneratedValue
	@Column(name = "PARTNUMBER")
	public int getPartNumber() {
		return PartNumber;
	}

	public void setPartNumber(int partNumber) {
		PartNumber = partNumber;
	}

	@Column(name = "MANUFACTURERCODE")
	public int getManufacturerCode() {
		return ManufacturerCode;
	}

	public void setManufacturerCode(int manufacturerCode) {
		ManufacturerCode = manufacturerCode;
	}

	@Column(name = "NAME")
	public int getName() {
		return Name;
	}

	public void setName(int name) {
		Name = name;
	}

	@Column(name = "QUANTITY")
	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	@Column(name = "STATUS")
	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	@Column(name = "ORIGINALVALUE")
	public int getOriginalValue() {
		return OriginalValue;
	}

	public void setOriginalValue(int originalValue) {
		OriginalValue = originalValue;
	}

	@Column(name = "LEASESTART")
	public Date getLeaseStart() {
		return LeaseStart;
	}

	public void setLeaseStart(Date leaseStart) {
		LeaseStart = leaseStart;
	}

	@Column(name = "LEASEENDS")
	public Date getLeaseEnds() {
		return LeaseEnds;
	}

	public void setLeaseEnds(Date leaseEnds) {
		LeaseEnds = leaseEnds;
	}

	@Column(name = "LEASEFATE")
	public int getLeaseFate() {
		return LeaseFate;
	}

	public void setLeaseFate(int leaseFate) {
		LeaseFate = leaseFate;
	}

	@Column(name = "DAILYRENT")
	public int getDailyRent() {
		return DailyRent;
	}

	public void setDailyRent(int dailyRent) {
		DailyRent = dailyRent;
	}

	@Column(name = "CURRENCY")
	public int getCurrency() {
		return Currency;
	}

	public void setCurrency(int currency) {
		Currency = currency;
	}

	@Column(name = "EXTENDEDRENT")
	public int getExtendedRent() {
		return ExtendedRent;
	}

	public void setExtendedRent(int extendedRent) {
		ExtendedRent = extendedRent;
	}

	@Column(name = "POUNDAGE")
	public int getPoundage() {
		return Poundage;
	}

	public void setPoundage(int poundage) {
		Poundage = poundage;
	}

	@Column(name = "TOTALAMOUNT")
	public int getTotalAmount() {
		return TotalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		TotalAmount = totalAmount;
	}

	@Column(name = "RANDOMCERTIFICATES")
	public int getRandomCertificates() {
		return RandomCertificates;
	}

	public void setRandomCertificates(int randomCertificates) {
		RandomCertificates = randomCertificates;
	}

	@Column(name = "REMARKS")
	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

}