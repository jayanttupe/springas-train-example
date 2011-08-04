package com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.base.baseApprovalRecord;
import java.util.Date;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.spmsdd.TradeMethods;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.base.baseApprovalRecordItems.BaseApprovalRecordItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spmsdd.PartCentreLocation;

/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:11
 */
public class BaseApprovalRecord extends BaseEntity {

	private Date approvalDate;
	/**
	 * 业务编号
	 */
	private String approvalNumber;
	/**
	 * 自动流水号
	 */
	private String approvalRecordId;
	private Date declarationDate;
	private String remark;
	private String state;
	public BaseApprovalRecordItems m_BaseApprovalRecordItems;
//	public BaseContactEntity m_BaseContactEntity;
	public String m_BondedWarehousePartsInventory;
	public PartCentreLocation m_PartCentreLocation;
	public TradeMethods  m_TradeMethods ;
	public CAGECode m_CAGECode;

	public BaseApprovalRecord(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}