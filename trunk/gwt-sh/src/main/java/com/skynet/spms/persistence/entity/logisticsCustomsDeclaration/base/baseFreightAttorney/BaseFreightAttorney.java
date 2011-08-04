package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseFreightAttorney;
import java.util.List;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.s.SpecifiedShippingMethodCode;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.ConsigneeDetails;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.ShipperDetails;
import com.skynet.spms.persistence.entity.spmsdd.Payment;
import com.skynet.spms.persistence.entity.spmsdd.ShippingServiceType;
import com.skynet.spms.persistence.entity.spmsdd.TradeMethods;
import com.skynet.spms.persistence.entity.spmsdd.TransportationCode;

/**
 * 运代委托书主要是客户订舱业务人员与运代公司之间就运输内容的确定。
 * @author 曹宏炜
 * @version 1.0
 * @created 16-三月-2011 17:21:03
 */
public class BaseFreightAttorney extends BaseEntity {

	private int billOfLadingContainerCount;
	/**
	 * 由托运人向承运人申报的货物价值，目的是要决定运费或设定承运人对损失、损害或延误所承担责任的限制。     
	 */
	private float declaredValueForCarriage;
	private String forwardingOrderNumber;
	private String note;
	private String numberOfRuns;
	private String remarkText;
	private float valueOfInsurance;
	public ConsigneeDetails m_ConsigneeDetails;
	public ShipperDetails m_ShipperDetails;
	public List m_LogisticsGoodsBasicInformation;
	public Payment m_Payment;
	public TransportationCode m_TransportationCode;
	public ShippingServiceType m_ShippingServiceType;
	public TradeMethods  m_TradeMethods ;
	public SpecifiedShippingMethodCode m_SpecifiedShippingMethodCode;
	public List m_Attachment;
	public List m_GeneralJobStatusEntity;

}