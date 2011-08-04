package com.skynet.spms.persistence.entity.stockServiceBusiness.discardServiceBusiness;
import java.util.Date;

import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;

/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:15
 */
public class PartsDiscardItem {

	private String appendFile;
	private Date buyDate;
	private String checkAndAcceptSheetNumber;
	private Date dateOfManufacture;
	private String discardPartStatusDescribe;
	private String discardReportItem;
	private String discardReportNumber;
	private String disposeDescribe;
	private float originalUnitPrice;
	private String partNumber;
	private String partSerialNumber;
	private int quantity;
	private String remainderShelfLife;
	private float remainderTotalPrice;
	private float remainderUnitPrice;
	private Date shelfLifeExpireDate;
	private String unitMeasureCode;
	private String usedTime;
	private int usefulLifePeriod;
	private String usefulLifePeriodUnit;
	public ManufacturerCode m_ManufacturerCode;

	public PartsDiscardItem(){

	}

	public void finalize() throws Throwable {

	}

}