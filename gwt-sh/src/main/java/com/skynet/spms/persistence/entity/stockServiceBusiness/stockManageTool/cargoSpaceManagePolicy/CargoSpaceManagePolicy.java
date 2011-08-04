package com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.cargoSpaceManagePolicy;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author fanyx
 * @version 1.1
 * @created 2011-3-29
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_CARGO_SPACE_MANAGE_POLICY")
public class CargoSpaceManagePolicy extends BaseEntity {
	/**
	 * 货位策略编号
	 */
	private String policyNumber;

	/**
	 * 货位编号
	 */
	private String cargoSpaceNumber;

	/**
	 * 货位策略描述
	 */
	private String remark;

	/**
	 * 适用件号
	 */
	private String storablePartNumber;

	/**
	 * 状态
	 */
	private int status;

	public CargoSpaceManagePolicy() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "POLICY_NUMBER")
	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@Column(name = "CARGO_SPACE_NUMBER")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}

	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STORABLE_PART_NUMBER")
	public String getStorablePartNumber() {
		return storablePartNumber;
	}

	public void setStorablePartNumber(String storablePartNumber) {
		this.storablePartNumber = storablePartNumber;
	}

	@Column(name = "STATUS")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}