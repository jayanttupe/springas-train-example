package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BankInformation;

public interface BankInformationManager extends CommonManager<BankInformation> {

	public List<BankInformation> queryByProps(Map<String, Object> props);
}
