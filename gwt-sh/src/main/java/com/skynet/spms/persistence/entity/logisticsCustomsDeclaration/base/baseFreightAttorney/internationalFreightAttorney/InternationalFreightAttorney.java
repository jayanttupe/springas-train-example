package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseFreightAttorney.internationalFreightAttorney;
import java.util.List;

import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseFreightAttorney.BaseFreightAttorney;
import com.skynet.spms.persistence.entity.spmsdd.ClearanceAgent;
import com.skynet.spms.persistence.entity.spmsdd.DeliveryType;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 16-三月-2011 17:21:06
 */
public class InternationalFreightAttorney extends BaseFreightAttorney {

	private String certificateOfOrigin;
	private String contractNumber;
	private String countryOfOrigin;
	/**
	 * 适用于，为核定关税金额而向海关申报的货物价值。
	 * 海关申报价值针对货物的价值向海关部门的申报，是正规的进出口公司，要办理退税的话，到时还会跟外管局核对你们的外汇进账金额，能进行退税。一般以货物的原值进行申报较好
	 * ，否则海关在核对价格的时候出现价格较大偏差，导致货物扣留情况。
	 */
	private float declaredValueForCustoms;
	private String productsPermitLicense ;
	public DeliveryType m_DeliveryType;
	public ClearanceAgent m_ClearanceAgent;
	public InternationalCurrencyCode m_InternationalCurrencyCode;
	public List AirAttachment;

}