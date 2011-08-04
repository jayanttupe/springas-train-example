package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.credit.customerCredit.CustomerCredit;

public interface CustomerCreditManager extends CommonManager<CustomerCredit> {

	public List<CustomerCredit> queryByProps(Map<String, Object> props);
}
