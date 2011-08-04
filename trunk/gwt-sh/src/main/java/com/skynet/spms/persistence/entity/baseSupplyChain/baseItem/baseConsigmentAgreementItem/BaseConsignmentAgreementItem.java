package com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseConsigmentAgreementItem;
import java.util.List;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseItem.BasePartItem;
import com.skynet.spms.persistence.entity.csdd.c.CertificateType;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.p.PartStatusCode;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:00
 */
public class BaseConsignmentAgreementItem extends BasePartItem {

	private String batchNumber;
	/**
	 * The sell price for one unit of the subject part conforming to the Currency Code,
	 * Unit of  Measure, and when applicable, Price Break Quantity range.
	 */
	private float unitPriceAmount;
	private String consignmentSite;
	private ManufacturerCode spl;
	public List<CertificateType> m_CertificateType;
	public InternationalCurrencyCode m_InternationalCurrencyCode;
	private PartStatusCode spc;

}