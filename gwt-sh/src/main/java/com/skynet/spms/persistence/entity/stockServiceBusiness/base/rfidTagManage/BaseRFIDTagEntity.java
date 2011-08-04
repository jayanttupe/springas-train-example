package com.skynet.spms.persistence.entity.stockServiceBusiness.base.rfidTagManage;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:11
 */
public class BaseRFIDTagEntity extends BaseEntity {

	/**
	 * RFID标签标识
	 */
	private String rfidTagCode;

	public BaseRFIDTagEntity(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}