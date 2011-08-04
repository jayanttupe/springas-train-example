package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

public interface ShippingAddressManager extends CommonManager<ShippingAddress> {

	public List<ShippingAddress> queryByProps(Map<String, Object> props);
}
