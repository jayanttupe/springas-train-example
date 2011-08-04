package com.skynet.spms.persistence.entity.stockServiceBusiness.discardServiceBusiness;
import java.util.Date;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author Administrator
 * @version 1.0
 * @created 15-三月-2011 12:33:15
 */
public class PartsDiscard extends BaseEntity {

	private String contractNumber;
	private String discardReason;
	private String discardReportNumber;
	private Date discardReportProcessDate;
	private String discardReportProcessor;
	private String discardTypes;
	private int isRepair;
	private String partLocation;
	private float salvageAmount;
	private String state;
	private String stockRoomNumber;
	private int totalItem;
	public PartsDiscardItem m_PartsDiscardItem;

	public PartsDiscard(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}