/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclaration;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclaration;

/**
 * 出口报关单Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ExportCustomsDeclarationManager extends CommonManager<ExportCustomsDeclaration>{
	
	/**
	 * 保存出口报关单相关信息
	 * @param exportCustomsDeclaration
	 * @return 出口报关单相关信息
	 */
	public ExportCustomsDeclaration saveExportCustomsDeclaration(ExportCustomsDeclaration exportCustomsDeclaration);

	/**
	 * 获取出口报关单相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 出口报关单相关信息
	 */
	public List<ExportCustomsDeclaration> getExportCustomsDeclaration(Map values, int startRow, int endRow);

	/**
	 * 设置工作状态
	 * @param orderID
	 */
	public void setWorkStatus(String orderID);
}
