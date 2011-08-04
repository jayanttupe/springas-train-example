/**
 * 
 */
package com.skynet.spms.action.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob.TakeDeliveryOfJobManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob.TakeDeliveryOfJob;

/**
 * 描述：物流取货计划相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class TakeDeliveryOfJobDatasourceAction implements DataSourceAction<TakeDeliveryOfJob>{
	@Autowired
	private TakeDeliveryOfJobManager taskDeliveryOfJobManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"takeDeliveryOfJob_dataSource"};
	}

	/**
	 * 新增物流取货计划相关信息
	 * @param item
	 */
	@Override
	public void insert(TakeDeliveryOfJob item) {
	}
	
	/**
	 * 更新物流取货计划相关信息
	 * @param newValues
	 * @param itemID
	 * @return 物流取货计划相关信息
	 */
	@Override
	public TakeDeliveryOfJob update(Map newValues, String itemID) {
		if (newValues.containsKey("setStatus") && newValues.get("setStatus").equals("takeDeliveryStatus")) {
			taskDeliveryOfJobManager.setWorkStatus(newValues.get("orderID").toString());
			return null;
		} else {
			TakeDeliveryOfJob takeDeliveryOfJob = new TakeDeliveryOfJob();		
			BeanPropUtil.fillEntityWithMap(takeDeliveryOfJob, newValues);
			
			return taskDeliveryOfJobManager.saveTaskDeliveryOfJob(takeDeliveryOfJob);
		}
	}

	/**
	 * 删除物流取货计划相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		taskDeliveryOfJobManager.deleteEntity(itemID,TakeDeliveryOfJob.class);
	}
	
	/**
	 * 查询相关物流取货计划信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流取货计划相关信息
	 */
	@Override
	public List<TakeDeliveryOfJob> doQuery(Map values,
			int startRow, int endRow) {
		return taskDeliveryOfJobManager.getTaskDeliveryOfJob(values, 0, -1);
	}

	/**
	 * 获取所有物流取货计划信息
	 * @param startRow
	 * @param endRow
	 * @return 物流取货计划信息
	 */
	@Override
	public List<TakeDeliveryOfJob> getList(int startRow, int endRow) {
		
		return taskDeliveryOfJobManager.getTaskDeliveryOfJob(null, 0, -1);
	}
	

}
