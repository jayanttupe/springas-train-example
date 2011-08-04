package com.skynet.spms.action.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJobManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob.DeliverTheGoodsJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJob;

/**
 * 描述：物流运单相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ShippingJobDatasourceAction implements DataSourceAction<ShippingJob>{
	@Autowired
	private ShippingJobManager shippingJobManager;

	
	@Override
	public String[] getBindDsName() {
		return new String[]{"shippingJob_dataSource"};
	}
	
	/**
	 * 新增物流运单相关信息
	 * @param shippingJob
	 */
	@Override
	public void insert(ShippingJob shippingJob) {
		shippingJobManager.saveShippingJob(shippingJob);
	}

	/**
	 * 更新物流运单相关信息
	 * @param newValues
	 * @param itemID
	 * @return 物流运单相关信息
	 */
	@Override
	public ShippingJob update(Map<String, Object> newValues, String itemID) {
		if (newValues.containsKey("setStatus") && newValues.get("setStatus").equals("shippingStatus")) {
			shippingJobManager.setWorkStatus(newValues.get("orderID").toString());
			return null;
		} else {
			ShippingJob shippingJob = new ShippingJob();
			BeanPropUtil.fillEntityWithMap(shippingJob, newValues);
			
			return shippingJobManager.saveShippingJob(shippingJob);	
		}
	}
	
	/**
	 * 删除物流运单相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		shippingJobManager.deleteEntity(itemID, ShippingJob.class);
	}

	/**
	 * 查询物流运单相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流运单相关信息
	 */
	@Override
	public List<ShippingJob> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		return shippingJobManager.getShippingJob(values, 0, -1);
	}

	/**
	 * 获取所有物流运单相关信息
	 * @param startRow
	 * @param endRow
	 * @return 物流运单相关信息
	 */
	@Override
	public List<ShippingJob> getList(int startRow, int endRow) {
		return shippingJobManager.getShippingJob(null, 0, -1);
	}

}
