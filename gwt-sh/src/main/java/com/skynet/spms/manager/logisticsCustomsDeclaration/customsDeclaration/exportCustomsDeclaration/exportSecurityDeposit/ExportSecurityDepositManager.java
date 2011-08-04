/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit.ExportSecurityDeposit;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit.ImportSecurityDeposit;

/**
 * 出口保证金业务Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ExportSecurityDepositManager extends CommonManager<ExportSecurityDeposit>{
	
	/**
	 * 获取出口保证金相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 出口保证金信息
	 */
	 public List<ExportSecurityDeposit> getExportSecurityDeposit(Map values, int startRow, int endRow);
	 
	 /**
	  * 保存出口保证金相关信息
	  * @param exportSecurityDeposit
	  * @return 出口保证金信息
	  */
	 public ExportSecurityDeposit saveExportSecurityDeposit(ExportSecurityDeposit exportSecurityDeposit);

}
