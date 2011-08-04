package com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport.NonconformingReport;

/**
 * 不合格品报告Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface NonconformingReportManager extends CommonManager<NonconformingReport>{

	/**
	 * 获取不合格品报告相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 不合格品报告相关信息
	 */
	public List<NonconformingReport> getNonconformingReport(Map values, int startRow, int endRow);

	/**
	 * 保存不合格品报告相关信息
	 * @param nonconformingReport
	 * @return 不合格品报告相关信息
	 */
	public NonconformingReport saveNonconformingReport(NonconformingReport nonconformingReport);
	
}

