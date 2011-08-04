package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.qualityAssurance.supplierQAManage.SupplierQAEntity;

public interface SupplierQAManager extends CommonManager<SupplierQAEntity> {

	public List<SupplierQAEntity> queryByProps(Map<String, Object> props);
}
