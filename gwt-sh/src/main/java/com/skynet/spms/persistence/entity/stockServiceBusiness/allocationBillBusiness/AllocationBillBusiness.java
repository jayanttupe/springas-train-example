package com.skynet.spms.persistence.entity.stockServiceBusiness.allocationBillBusiness;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;

/**
 * @author fanyx
 * @version 1.1
 * @created 2011-4-15
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_ALLOCATION_BILL")
public class AllocationBillBusiness extends BaseEntity {
	/**
	 * 调拨单编号
	 */
	private String allocationBillNumber;

	/**
	 * 调拨日期
	 */
	private Date businessDate;

	/**
	 * 业务类型
	 */
	private BusinessType businessType;

	/**
	 * 审核日期
	 */
	private Date checkDate;

	/**
	 * 审核人
	 */
	private String checkUser;

	/**
	 * 制单日期
	 */
	private Date createByDate;

	/**
	 * 制单人
	 */
	private String createUser;

	/**
	 * 收货库房ID
	 */
	private String inWareHouse;

	/**
	 * 收货库房名称
	 */
	private String inWareHouseName;

	/**
	 * 发货库房ID
	 */
	private String outWareHouse;

	/**
	 * 发货库房名称
	 */
	private String outWareHouseName;

	/**
	 * 源单据类型
	 */
	private String sourceBillType;

	/**
	 * 状态
	 */
	private String state;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 调入部门
	 */
	private String inDepartment;

	/**
	 * 调出部门
	 */
	private String outDepartment;
	
	private List<AllocationGood> allocationGood;

	public AllocationBillBusiness() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "ALLOCATION_BILL_NUMBER")
	public String getAllocationBillNumber() {
		return allocationBillNumber;
	}

	public void setAllocationBillNumber(String allocationBillNumber) {
		this.allocationBillNumber = allocationBillNumber;
	}

	@Column(name = "BUSINESS_DATE")
	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "BUSINESS_TYPE")
	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	@Column(name = "CHECK_DATE")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "CHECK_USER")
	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	@Column(name = "CREATE_BY_DATE")
	public Date getCreateByDate() {
		return createByDate;
	}

	public void setCreateByDate(Date createByDate) {
		this.createByDate = createByDate;
	}

	@Column(name = "CREATE_USER")
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "IN_WARE_HOUSE")
	public String getInWareHouse() {
		return inWareHouse;
	}

	public void setInWareHouse(String inWareHouse) {
		this.inWareHouse = inWareHouse;
	}

	@Transient
	public String getInWareHouseName() {
		return inWareHouseName;
	}

	public void setInWareHouseName(String inWareHouseName) {
		this.inWareHouseName = inWareHouseName;
	}

	@Column(name = "OUT_WARE_HOUSE")
	public String getOutWareHouse() {
		return outWareHouse;
	}

	public void setOutWareHouse(String outWareHouse) {
		this.outWareHouse = outWareHouse;
	}

	@Transient
	public String getOutWareHouseName() {
		return outWareHouseName;
	}

	public void setOutWareHouseName(String outWareHouseName) {
		this.outWareHouseName = outWareHouseName;
	}

	@Column(name = "SOURCE_BILL_TYPE")
	public String getSourceBillType() {
		return sourceBillType;
	}

	public void setSourceBillType(String sourceBillType) {
		this.sourceBillType = sourceBillType;
	}

	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "IN_DEPARTMENT")
	public String getInDepartment() {
		return inDepartment;
	}

	public void setInDepartment(String inDepartment) {
		this.inDepartment = inDepartment;
	}

	@Column(name = "OUT_DEPARTMENT")
	public String getOutDepartment() {
		return outDepartment;
	}

	public void setOutDepartment(String outDepartment) {
		this.outDepartment = outDepartment;
	}

	@OneToMany(targetEntity= AllocationGood.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="ALLOCATION_BILL_ID")
	public List<AllocationGood> getAllocationGood() {
		return allocationGood;
	}

	public void setAllocationGood(List<AllocationGood> allocationGood) {
		this.allocationGood = allocationGood;
	}

}