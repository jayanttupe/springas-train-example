package com.skynet.spms.action.stockServiceBusiness.discardServiceBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.discardServiceBusiness.DiscardServiceBusinessManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.discardServiceBusiness.DiscardServiceBusiness;

/**
 * 备件报废相关信息处理
 * @author Administrator
 *
 */
@Component
public class DiscardServiceBusinessDatasourceAction implements DataSourceAction<DiscardServiceBusiness>{
	@Autowired
	private DiscardServiceBusinessManager discardServiceBusinessManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"discardServiceBusiness_dataSource"};
	}

	/**
	 * 新增备件报废相关信息
	 * @param discardServiceBusiness
	 */
	@Override
	public void insert(DiscardServiceBusiness discardServiceBusiness) {
		discardServiceBusinessManager.saveDiscardServiceBusiness(discardServiceBusiness);
	}

	/**
	 * 更新备件报废相关信息
	 * @param newValues
	 * @param number
	 * @return 备件报废相关信息
	 */
	@Override
	public DiscardServiceBusiness update(Map newValues, String number) {
		DiscardServiceBusiness discardServiceBusiness = new DiscardServiceBusiness();
		BeanPropUtil.fillEntityWithMap(discardServiceBusiness, newValues);
//		return (DiscardServiceBusiness) discardServiceBusinessManager.updateEntity(newValues, number, DiscardServiceBusiness.class);
		return discardServiceBusiness;
	}

	/**
	 * 删除备件报废相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		discardServiceBusinessManager.deleteEntity(number, DiscardServiceBusiness.class);
	}

	/**
	 * 查询备件报废相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件报废相关信息
	 */
	@Override
	public List<DiscardServiceBusiness> doQuery(Map values, int startRow, int endRow) {
		return discardServiceBusinessManager.getDiscardServiceBusiness(values, 0, -1);
	}

	/**
	 * 获取所有备件报废信息
	 * @param startRow
	 * @param endRow
	 * @return 备件报废信息
	 */
	@Override
	public List<DiscardServiceBusiness> getList(int startRow, int endRow) {
		return discardServiceBusinessManager.getDiscardServiceBusiness(null, 0, -1);
	}
}