package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.takeDeliveryPlan;
import java.util.List;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.spmsdd.TransportationCode;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 16-三月-2011 17:21:09
 */
public class TakeDeliveryPlan extends BaseEntity {

	private String addressOfTakeDelivery;
	private String linkman;
	private String numberOfRuns;
	private String remarkText;
	private String takeDeliveryDate;
	public TransportationCode m_TransportationCode;
	public CarrierName m_CarrierName;
	public List m_GeneralJobStatusEntity;
	public List m_MailEntity;

}