/**
 * ModulePartIndexManager.java
 * com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet
 *
 * TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 Jul 25, 2011 		Administrator
 *
 * Copyright (c) 2011, Tocersoft.
 */

package com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet;

import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;

/**
 * TODO
 * 
 * @author Administrator
 * @version 1.0
 * @Date Jul 25, 2011
 * 
 */
public interface ModulePartIndexManager {

	/**
	 * 根据件号查询件信息
	 * 
	 * @param partNumber
	 * @return PartIndex
	 */
	PartIndex getPartIndexByPartNumber(String partNumber);

	/**
	 * 查询某个件的库存数量
	 * 
	 * @param partNumber
	 * @return Long
	 */
	Long getPartIndexNumberFromInventoryRecord(String partNumber);

}
