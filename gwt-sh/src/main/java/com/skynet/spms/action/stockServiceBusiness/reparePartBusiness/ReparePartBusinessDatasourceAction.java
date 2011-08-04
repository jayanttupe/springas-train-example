package com.skynet.spms.action.stockServiceBusiness.reparePartBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.reparePartBusiness.ReparePartBusinessManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.reparePartBusiness.ReparePartBusiness;

/**
 * 描述：备件修理相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ReparePartBusinessDatasourceAction implements DataSourceAction<ReparePartBusiness>{
	@Autowired
	private ReparePartBusinessManager reparePartBusinessManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"reparePartBusiness_dataSource"};
	}

	/**
	 * 新增备件修理相关信息
	 * @param reparePartBusiness
	 */
	@Override
	public void insert(ReparePartBusiness reparePartBusiness) {
		reparePartBusinessManager.insertEntity(reparePartBusiness);
	}

	/**
	 * 更新备件修理相关信息
	 * @param newValues
	 * @param number
	 * @return 备件修理相关信息
	 */
	@Override
	public ReparePartBusiness update(Map newValues, String number) {
		return (ReparePartBusiness) reparePartBusinessManager.updateEntity(newValues, number, ReparePartBusiness.class);
	}

	/**
	 * 删除备件修理相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		reparePartBusinessManager.deleteEntity(number, ReparePartBusiness.class);
	}

	/**
	 * 查询备件修理相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件修理相关信息
	 */
	@Override
	public List<ReparePartBusiness> doQuery(Map values, int startRow, int endRow) {
		return reparePartBusinessManager.getReparePartBusiness(values, 0, -1);
	}

	/**
	 * 获取所有备件修理信息
	 * @param startRow
	 * @param endRow
	 * @return 备件修理信息
	 */
	@Override
	public List<ReparePartBusiness> getList(int startRow, int endRow) {
		return reparePartBusinessManager.getReparePartBusiness(null, 0, -1);
	}
}