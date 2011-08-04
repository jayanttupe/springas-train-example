package com.skynet.spms.manager.finance;

import java.util.List;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.PurposeVoucher;

public interface PurposeVoucherManager extends CommonManager<PurposeVoucher> {

	
	public PurposeVoucher saveOrUpdate(PurposeVoucher purposeVoucher);
	
	public List<PurposeVoucher> listAllPurposeVouchers();
	
	public List<PurposeVoucher> listPurposeVouchersByMode(String mode);

	public List<PurposeVoucher> queryByCriteriaList(List criList);
}
