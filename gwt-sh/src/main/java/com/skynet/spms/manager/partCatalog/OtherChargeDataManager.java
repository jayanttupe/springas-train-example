package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.OtherCharge;

public interface OtherChargeDataManager extends CommonManager<OtherCharge> {

	public List<OtherCharge> queryByProps(Map<String, Object> values);
   
}
