package com.skynet.spms.client.vo.approval;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 审批记录VO
 * 
 * @author zhangqiang
 * 
 */
public class ApprovalRecordVO implements IsSerializable {

	/** ID */
	private String id;
	/** 审批编号 */
	private String approvalNumber;
	/** 优先级 */
	private String m_Priority;
	/** 创建时间 */
	private String createDate;
	/** 创建人 */
	private String createBy;
	/** 单据主键 */
	private String sheetNo;
	/** 单据编号 */
	private String itemNumber;
	/** 单据类型 */
	private String sheetType;
	
	private String currentApprovalUser;
	
	private List<ApprovalAvailableVO> approvalAvaibleVoList = new ArrayList<ApprovalAvailableVO>();

	public ApprovalRecordVO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public String getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(String m_Priority) {
		this.m_Priority = m_Priority;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}



	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	public List<ApprovalAvailableVO> getApprovalAvaibleVoList() {
		return approvalAvaibleVoList;
	}

	public void setApprovalAvaibleVoList(
			List<ApprovalAvailableVO> approvalAvaibleVoList) {
		this.approvalAvaibleVoList = approvalAvaibleVoList;
	}

	public String getCurrentApprovalUser() {
		return currentApprovalUser;
	}

	public void setCurrentApprovalUser(String currentApprovalUser) {
		this.currentApprovalUser = currentApprovalUser;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	
}
