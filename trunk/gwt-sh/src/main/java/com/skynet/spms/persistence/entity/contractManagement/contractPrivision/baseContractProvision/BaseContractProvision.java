package com.skynet.spms.persistence.entity.contractManagement.contractPrivision.baseContractProvision;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.contractManagement.template.baseContractTemplate.ContractProvision;
import com.skynet.spms.persistence.entity.spmsdd.ContractType;

/**
 * 基础合同条款
 * @author tqc
 * @version 1.0
 * @created 21-四月-2011 11:30:15
 */
@Entity
@Table(name="SPMS_BASECONTRACTPROVISION")
public class BaseContractProvision extends BaseEntity {

	/** 合同类型 **/
	public ContractType m_ContractType;

	/** 合同条款 **/
	private List<ContractProvision> baseContractProvision;

	public BaseContractProvision() {
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "CONTRACT_TYPE")
	public ContractType getM_ContractType() {
		return m_ContractType;
	}

	public void setM_ContractType(ContractType m_ContractType) {
		this.m_ContractType = m_ContractType;
	}

	@OneToMany
	@JoinColumn(name = "CONTRACTPROVISION_ID")
	public List<ContractProvision> getBaseContractProvision() {
		return baseContractProvision;
	}

	public void setBaseContractProvision(
			List<ContractProvision> baseContractProvision) {
		this.baseContractProvision = baseContractProvision;
	}

}