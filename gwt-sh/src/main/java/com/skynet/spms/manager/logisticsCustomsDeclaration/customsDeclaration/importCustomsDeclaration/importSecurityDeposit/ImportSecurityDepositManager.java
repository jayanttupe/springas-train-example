/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecord;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit.ImportSecurityDeposit;

/**
 * 进口报关保证金业务Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ImportSecurityDepositManager extends CommonManager<ImportSecurityDeposit>{

	/**
	 * 获取进口报关保证金相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 进口报关保证金相关信息
	 */
	public List<ImportSecurityDeposit> getImportSecurityDeposit(Map values, int startRow, int endRow);

	/**
	 * 保存进口报关保证金相关信息
	 * @param importSecurityDeposit
	 * @return 进口报关保证金相关信息
	 */
	public ImportSecurityDeposit saveImportSecurityDeposit(ImportSecurityDeposit importSecurityDeposit);
}
