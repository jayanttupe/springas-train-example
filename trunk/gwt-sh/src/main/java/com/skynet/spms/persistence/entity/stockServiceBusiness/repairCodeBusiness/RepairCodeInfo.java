package com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness;

import java.util.Date;

/**
 * @author 黄赟
 * @version 1.0
 * @created 15-三月-2011 12:33:17
 */
public class RepairCodeInfo {

	private Date repairCodeApplyDate;
	private String repairCodeApplyUser;
	private String repairCodeConfirm;
	private String repairCodeNumber;
	private String repairCoderEntity;
	private String repairCodeType;
	private Date repairDate;
	private String repairType;
	private String repairUser;
	public repairCodeType m_repairCodeType;
	public repairCodeEntity m_repairCodeEntity;
	public repairType m_repairType;

	public RepairCodeInfo(){

	}

	public void finalize() throws Throwable {

	}

}