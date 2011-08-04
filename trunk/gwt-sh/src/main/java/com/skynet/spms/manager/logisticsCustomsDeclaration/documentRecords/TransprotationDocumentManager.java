package com.skynet.spms.manager.logisticsCustomsDeclaration.documentRecords;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords.TransprotationDocument;

/**
 * 运输单证相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface TransprotationDocumentManager extends CommonManager<TransprotationDocument>{
	
	/**
	 * 获取运输单证相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 运输单证相关信息
	 */
	 public List<TransprotationDocument> getTransprotationDocument(Map values, int startRow, int endRow);
}
