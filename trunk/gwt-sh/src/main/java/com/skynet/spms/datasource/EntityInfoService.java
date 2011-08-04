package com.skynet.spms.datasource;

import com.skynet.spms.datasource.entity.EntityMetaInfo;

public interface EntityInfoService {

	public EntityMetaInfo getEntityInfoByClsName(String className);

}
