package com.skynet.spms.manager.finance;

import java.util.List;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.financeService.buySettleAccounts.PayApplyTable;

public interface PayApplyManager extends CommonManager<PayApplyTable> {

	List<PayApplyTable> queryByCriteriaList(List criList);

}
