package com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.rfidTag;

import com.skynet.spms.persistence.entity.spmsdd.RFIDTagMemory;
import com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.BaseTagEntity;
import com.smartgwt.client.util.SC;


/**
 * @author FANYX
 * @version 1.0
 * @created 13-六月-2011 11:08:57
 */
public abstract class BaseRFIDTagEntity extends BaseTagEntity {

	/**
	 * 标签到限日期
	 */
	private java.util.Date expiryDate;
	/**
	 * true表示为无源标签，false表示为有源标签
	 */
	private boolean isPassive;
	/**
	 * RFID 标签容量
	 */
	public RFIDTagMemory m_RFIDTagMemory;

}