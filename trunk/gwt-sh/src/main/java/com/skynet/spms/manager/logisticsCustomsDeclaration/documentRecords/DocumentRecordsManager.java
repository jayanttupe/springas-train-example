/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.documentRecords;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords.DocumentRecords;

/**
 * 单证记录Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface DocumentRecordsManager extends CommonManager<DocumentRecords>{

	/**
	 * 获取单证记录相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 单证记录相关信息
	 */
	public List<DocumentRecords> getDocumentRecords(Map values, int startRow, int endRow);

	/**
	 * 
	 * @param documentRecords
	 * @return
	 */
	DocumentRecords saveDocumentRecords(DocumentRecords documentRecords);
	

}
