package com.skynet.spms.persistence.entity.approvalEntityManage;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.ApprovalAvailableEntity;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * @author 黄赟
 * @version 1.0
 * @created 07-五月-2011 10:32:56
 */
@Entity
@Table(name = "SPMS_APPROVALRECORD")
public class ApprovalRecord extends BaseEntity {
	//apvrd 审批记录号
	private static final long serialVersionUID = 1L;
	
	private String approvalNumber;
	private boolean isApproval;
	private Priority m_Priority;
	private List<ApprovalAvailableEntity> m_ApprovalAvailableEntity;
	
	/**单据主键 */
	private String sheetNo;
	/** 单据编号 */
	private String itemNumber;
	/**单据类型*/
	private String sheetType;
	/**审批金额*/
	private Float amount;
	
	public ApprovalRecord() {
		super();
	}
	
	@Column(name = "APPROVAL_NUMBER")
	public String getApprovalNumber() {
		return approvalNumber;
	}
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	
	@Column(name="IS_APPROVAL")
	public boolean isApproval() {
		return isApproval;
	}
	public void setApproval(boolean isApproval) {
		this.isApproval = isApproval;
	}
	
	@Column(name="M_PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}
	public void setM_Priority(Priority m_Priority) {
		this.m_Priority = m_Priority;
	}
	
	
	@Reference(itemCls=ApprovalAvailableEntity.class)
	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "APPROVAL_RECORD_ID")
	public List<ApprovalAvailableEntity> getM_ApprovalAvailableEntity() {
		return m_ApprovalAvailableEntity;
	}
	public void setM_ApprovalAvailableEntity(
			List<ApprovalAvailableEntity> m_ApprovalAvailableEntity) {
		this.m_ApprovalAvailableEntity = m_ApprovalAvailableEntity;
	}

	@Column(name="SHEET_NO")
	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}
	@Column(name="ITEMNUMBER")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name="SHEET_TYPE")
	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	
	
	@Column(name="AMOUNT")
	public Float getAmount() {
		return amount;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
}