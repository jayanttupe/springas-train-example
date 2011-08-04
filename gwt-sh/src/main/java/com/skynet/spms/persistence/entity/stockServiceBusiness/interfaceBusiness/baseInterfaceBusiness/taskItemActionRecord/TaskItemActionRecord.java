package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.taskItemActionRecord;

import java.util.Date;

/**
 * @author 朱江
 * @version 1.0
 * @created 15-三月-2011 12:33:20
 */
public class TaskItemActionRecord {

	private String ATA;
	private String cargoSpaceNumber;
	private String cerificateNumber;
	private String currentStatus;
	private Date expireDate;
	private String internationalCommodityCode;
	/**
	 * 是或否危险品
	 */
	private String isDangerous;
	private String isSaleForbidden;
	private String itemNo;
	private String lotNumber;
	private Date manufacturDate;
	private String manufacturerCode;
	private String partDescribe;
	private String partNumber;
	private String partNumberOriginal;
	private String partSerialNumber;
	private int quantity;
	/**
	 * 上架时推荐库房,捡货时的指定库房
	 */
	private String stockRoomNumber;
	private String taskNo;
	private String unit;

	public TaskItemActionRecord(){

	}

	public void finalize() throws Throwable {

	}

}