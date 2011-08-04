package com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.base.baseApprovalRecordItems;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.UnitOfMeasure;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;

/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:11
 */
public class BaseApprovalRecordItems extends BaseEntity {

	private String accountBookItemsNumber;
	private String approvalRecordId;
	private String approvalRecordItemsNumber;
	private String chineseName;
	private String clearanceAccountBookNumber;
	private String contractPartsNumber;
	private String describe;
	private String englishName;
	private String hSCode;
	private String partNumber;
	private String partSerialNumber;
	private int quantity;
	private float totalAmount;
	private float unitPrice;
	public UnitOfMeasure m_UnitOfMeasure;
	public UnitOfMeasureCode m_UnitOfMeasureCode;
	public InternationalCurrencyCode m_InternationalCurrencyCode;

	public BaseApprovalRecordItems(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}