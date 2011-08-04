package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.GTAManage.EnterpriseGTA;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.airworthinessInformation.AirworthinessInformationEntity;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BankInformation;

public interface AirworthinessManager extends CommonManager<AirworthinessInformationEntity> {

	public List<AirworthinessInformationEntity> queryByProps(Map<String, Object> props);
}
