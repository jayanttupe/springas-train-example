package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.partBagItem.PartBagItem;

/**
 * Description: TODO
 *
 * @author   Administrator
 * @version  Ver 1.0
 * @Date	 2011-7-25
 *
 */

public interface PartBagItemDataManager extends CommonManager<PartBagItem> {

	public List<PartBagItem> queryByProps(Map<String, Object> values);
	
}
