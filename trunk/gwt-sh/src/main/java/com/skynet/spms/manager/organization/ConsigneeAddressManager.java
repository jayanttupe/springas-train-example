package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ConsigneeAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

public interface ConsigneeAddressManager extends CommonManager<ConsigneeAddress> {

	public List<ConsigneeAddress> queryByProps(Map<String, Object> props);
}
