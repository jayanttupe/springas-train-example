package com.skynet.spms.action.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob.DeliverTheGoodsJobManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob.DeliverTheGoodsJob;

/**
 * 描述：物流交货计划相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class DeliverTheGoodsJobDatasourceAction implements DataSourceAction<DeliverTheGoodsJob>{
	@Autowired
	private DeliverTheGoodsJobManager deliverTheGoodsJobManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"deliverTheGoodsJob_dataSource"};
	}

	/**
	 * 新增物流交货计划相关信息
	 * @param item
	 */
	@Override
	public void insert(DeliverTheGoodsJob item) {
		
	}

	/**
	 * 更新物流交货计划相关信息
	 * @param newValues
	 * @param itemID
	 * @return 物流交货计划相关信息
	 */
	@Override
	public DeliverTheGoodsJob update(Map<String, Object> newValues, String itemID) {
		if (newValues.containsKey("setStatus") && newValues.get("setStatus").equals("deliverStatus")) {
			deliverTheGoodsJobManager.setWorkStatus(newValues.get("orderID").toString());
			return null;
		} else {
			return deliverTheGoodsJobManager.updateEntity(newValues, itemID, DeliverTheGoodsJob.class);	
		}
	}

	/**
	 * 删除物流交货计划相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		
	}

	/**
	 * 查询物流交货计划相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流交货计划相关信息
	 */
	@Override
	public List<DeliverTheGoodsJob> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		return deliverTheGoodsJobManager.getDeliverTheGoodsJob(values, 0, -1);
	}

	/**
	 * 获取所有物流交货计划信息
	 * @param startRow
	 * @param endRow
	 * @return 物流交货计划信息
	 */
	@Override
	public List<DeliverTheGoodsJob> getList(int startRow, int endRow) {
		return deliverTheGoodsJobManager.getDeliverTheGoodsJob(null, 0, -1);
	}


}
