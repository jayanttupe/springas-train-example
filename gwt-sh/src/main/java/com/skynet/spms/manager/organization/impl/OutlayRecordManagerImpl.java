package com.skynet.spms.manager.organization.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.organization.OutlayRecordManager;
import com.skynet.spms.persistence.entity.base.baseOutlayRecordEntity.BaseOutlayRecord;

@Service
@Transactional
public class OutlayRecordManagerImpl extends CommonManagerImpl<BaseOutlayRecord> implements OutlayRecordManager {

}
