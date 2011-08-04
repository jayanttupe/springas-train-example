package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.discountMatrix.DiscountItem;

public interface DiscountMatrixManager extends CommonManager<DiscountItem> {

	public List<DiscountItem> queryByProps(Map<String, Object> values);

}
