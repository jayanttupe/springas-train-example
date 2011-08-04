package com.skynet.spms.persistence.entity.customerService.GuaranteeClaimService.Other;

import java.util.Date;

/**
 * @author 乔海龙
 * @version 1.0
 * @created 03-四月-2011 11:32:51
 */
public class GuaranteeClaimContract {

	public int ContractNumbers;
	public int Customer;
	public int Linkman;
	public int HandlingCodeTNC;
	public Date CreationDate;
	public int CustomerContractNumber;
	public int ContractAccording;
	public int Priority;
	public int AircraftNumber;
	public int Type;
	public GuaranteeClaimSpareParts m_GuaranteeClaimSpareParts;
	public Guarantee m_Guarantee;
	public FreightInvoices m_FreightInvoices;
	public ComponentFailureSafetyData m_ComponentFailureSafetyData;
	public Accessories m_Accessories;

}