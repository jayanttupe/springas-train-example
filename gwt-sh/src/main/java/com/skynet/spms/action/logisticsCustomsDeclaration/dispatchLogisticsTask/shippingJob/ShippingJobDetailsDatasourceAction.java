package com.skynet.spms.action.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJobDetailsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJobDetails;

/**
 * 描述：物流运单明细相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ShippingJobDetailsDatasourceAction implements DataSourceAction<ShippingJobDetails>{
	@Autowired
	private ShippingJobDetailsManager shippingJobDetailsManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"shippingJobDetails_dataSource"};
	}
	
	/**
	 * 新增物流运单明细相关信息
	 * @param item
	 */
	@Override
	public void insert(ShippingJobDetails item) {
		shippingJobDetailsManager.insertEntity(item);
	}

	/**
	 * 更新物流运单明细相关信息
	 * @param newValues
	 * @param itemID
	 * @return 物流运单明细相关信息
	 */
	@Override
	public ShippingJobDetails update(Map<String, Object> newValues, String itemID) {
		return shippingJobDetailsManager.updateEntity(newValues, itemID, ShippingJobDetails.class);
	}
	
	/**
	 * 删除物流运单明细相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		shippingJobDetailsManager.deleteEntity(itemID, ShippingJobDetails.class);
	}

	/**
	 * 查询物流运单明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流运单明细相关信息
	 */
	@Override
	public List<ShippingJobDetails> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		return shippingJobDetailsManager.getShippingJobDetails(values, 0, -1);
	}

	/**
	 * 获取所有物流运单明细信息
	 * @param startRow
	 * @param endRow
	 * @return 物流运单明细信息
	 */
	@Override
	public List<ShippingJobDetails> getList(int startRow, int endRow) {
		return shippingJobDetailsManager.getShippingJobDetails(null, 0, -1);
	}

}
