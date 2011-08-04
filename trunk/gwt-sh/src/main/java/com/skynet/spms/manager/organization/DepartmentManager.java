package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.DepartmentInformation;

public interface DepartmentManager extends CommonManager<DepartmentInformation> {

	public List<DepartmentInformation> queryTree(Map<String, Object> props);
}
