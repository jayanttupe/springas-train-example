package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.creditLine.customerCreditLine.CustomerCreditLine;

public interface CustomerCreditLineManager extends CommonManager<CustomerCreditLine> {

	public List<CustomerCreditLine> queryByProps(Map<String, Object> props);
}
