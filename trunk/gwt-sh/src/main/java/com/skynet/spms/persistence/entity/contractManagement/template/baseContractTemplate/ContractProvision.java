package com.skynet.spms.persistence.entity.contractManagement.template.baseContractTemplate;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 合同条款
 * 
 * @author tqc
 * @version 1.0
 * @created 28-三月-2011 13:14:25
 */

@Entity
@Table(name = "SPMS_CONTRACTPROVISION")
public class ContractProvision extends BaseEntity {

	/** 英文内容 **/
	private String content_en;
	/** 中文内容 **/
	private String content_zh;
	/** 项号 **/
	private int itemNumber;
	/** 英文标题 **/
	private String title_en;
	/** 中文标题 **/
	private String title_zh;

	public List<ContractProvision> m_ContractProvision;

	public ContractProvision() {
	}

	@Column(name = "CONTENT_EN")
	public String getContent_en() {
		return content_en;
	}

	public void setContent_en(String content_en) {
		this.content_en = content_en;
	}

	@Column(name = "CONTENT_ZH")
	public String getContent_zh() {
		return content_zh;
	}

	public void setContent_zh(String content_zh) {
		this.content_zh = content_zh;
	}

	@Column(name = "ITEM_NUMBER")
	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "TITLE_EN")
	public String getTitle_en() {
		return title_en;
	}

	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}

	@Column(name = "TITLE_ZH")
	public String getTitle_zh() {
		return title_zh;
	}

	public void setTitle_zh(String title_zh) {
		this.title_zh = title_zh;
	}

	@OneToMany
	@JoinColumn(name = "CP_ID")
	public List<ContractProvision> getM_ContractProvision() {
		return m_ContractProvision;
	}

	public void setM_ContractProvision(
			List<ContractProvision> m_ContractProvision) {
		this.m_ContractProvision = m_ContractProvision;
	}

}