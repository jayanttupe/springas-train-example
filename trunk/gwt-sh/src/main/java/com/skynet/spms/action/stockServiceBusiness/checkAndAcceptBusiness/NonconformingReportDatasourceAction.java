package com.skynet.spms.action.stockServiceBusiness.checkAndAcceptBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.NonconformingReportManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport.NonconformingReport;

/**
 * 描述：航材证书管理相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class NonconformingReportDatasourceAction implements DataSourceAction<NonconformingReport>{

	@Autowired
	private NonconformingReportManager nonconformingReportManage;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"nonconformingReport_dataSource"};
	}
	
	/**
	 * 新增航材证书管理相关信息
	 * @param nonconformingReport
	 */
	@Override
	public void insert(NonconformingReport nonconformingReport) {
		nonconformingReportManage.saveNonconformingReport(nonconformingReport);
	}

	/**
	 * 更新航材证书管理相关信息
	 * @param newValues
	 * @param number
	 * @return 航材证书管理相关信息
	 */
	@Override
	public NonconformingReport update(Map newValues, String number) {
		NonconformingReport nonconformingReport = new NonconformingReport();
		BeanPropUtil.fillEntityWithMap(nonconformingReport, newValues);
		return nonconformingReportManage.saveNonconformingReport(nonconformingReport);
	}

	/**
	 * 删除航材证书管理相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		nonconformingReportManage.deleteEntity(number, NonconformingReport.class);
	}

	/**
	 * 查询航材证书管理相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 航材证书管理相关信息
	 */
	@Override
	public List<NonconformingReport> doQuery(Map values, int startRow, int endRow) {
		return nonconformingReportManage.getNonconformingReport(values, 0, -1);
	}

	/**
	 * 获取所有航材证书管理信息
	 * @param startRow
	 * @param endRow
	 * @return 航材证书管理相关信息
	 */
	@Override
	public List<NonconformingReport> getList(int startRow, int endRow) {
		return nonconformingReportManage.getNonconformingReport(null ,0, -1);
	}
}