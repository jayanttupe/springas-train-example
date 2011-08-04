package com.skynet.spms.persistence.entity.baseSupplyChain.basePartDissembleData;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 部件拆换数据
 * 
 * @author tqc
 * @version 1.0
 * @created 10-三月-2011 11:10:34
 */
@MappedSuperclass
public class BasePartDisassembleData extends BaseEntity {

	/** 拆卸日期 **/
	private Date disassembleDate;

	/** 拆卸类型 **/
	private String disassembleType;

	/** 故障描述 **/
	private String faultDescription;

	/** 拆自机号 **/
	private String fromRegistrationNumber;

	/** 拆换原因 **/
	private String reasonForRemoval;
	
	/**修理工作**/
	private String fixWorkText;
	
	/**部件位置**/
	private String partPostion;

	// add by tqc
	private String m_TSO;

	private String m_TSN;

	private String m_CSO;

	private String m_CSN;
	
	
	@Column(name = "PARTPOSTION")
	public String getPartPostion() {
		return partPostion;
	}

	public void setPartPostion(String partPostion) {
		this.partPostion = partPostion;
	}

	@Column(name = "FIXWORKTEXT")
	public String getFixWorkText() {
		return fixWorkText;
	}

	public void setFixWorkText(String fixWorkText) {
		this.fixWorkText = fixWorkText;
	}

	@Column(name = "M_TSO")
	public String getM_TSO() {
		return m_TSO;
	}

	public void setM_TSO(String m_TSO) {
		this.m_TSO = m_TSO;
	}

	@Column(name = "M_TSN")
	public String getM_TSN() {
		return m_TSN;
	}

	public void setM_TSN(String m_TSN) {
		this.m_TSN = m_TSN;
	}

	@Column(name = "M_CSO")
	public String getM_CSO() {
		return m_CSO;
	}

	public void setM_CSO(String m_CSO) {
		this.m_CSO = m_CSO;
	}

	@Column(name = "M_CSN")
	public String getM_CSN() {
		return m_CSN;
	}

	public void setM_CSN(String m_CSN) {
		this.m_CSN = m_CSN;
	}

	@Column(name = "FROM_REG_NUM")
	public String getFromRegistrationNumber() {
		return fromRegistrationNumber;
	}

	public void setFromRegistrationNumber(String fromRegistrationNumber) {
		this.fromRegistrationNumber = fromRegistrationNumber;
	}

	@Column(name = "RFR_CODE")
	public String getReasonForRemoval() {
		return reasonForRemoval;
	}

	public void setReasonForRemoval(String reasonForRemoval) {
		this.reasonForRemoval = reasonForRemoval;
	}

	@Column(name = "DIS_DATE")
	public Date getDisassembleDate() {
		return disassembleDate;
	}

	public void setDisassembleDate(Date disassembleDate) {
		this.disassembleDate = disassembleDate;
	}

	@Column(name = "DIS_TYPE")
	public String getDisassembleType() {
		return disassembleType;
	}

	public void setDisassembleType(String disassembleType) {
		this.disassembleType = disassembleType;
	}

	@Column(name = "FAULT_DESCRIPTION")
	public String getFaultDescription() {
		return faultDescription;
	}

	public void setFaultDescription(String faultDescription) {
		this.faultDescription = faultDescription;
	}

}