package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.aircraftConfigCatalog.AircraftRegistration;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.AlternateSupplyLocationText;

public interface AlternateSupplyLocationManager extends CommonManager<AlternateSupplyLocationText> {

	public List<AlternateSupplyLocationText> queryByProps(Map<String, Object> values);

}
