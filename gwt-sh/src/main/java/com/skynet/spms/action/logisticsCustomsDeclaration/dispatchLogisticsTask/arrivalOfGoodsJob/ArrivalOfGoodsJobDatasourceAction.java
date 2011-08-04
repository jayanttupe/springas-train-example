/**
 * 
 */
package com.skynet.spms.action.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob.ArrivalOfGoodsJobManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob.ArrivalOfGoodsJob;

/**
 * 描述：物流到达相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ArrivalOfGoodsJobDatasourceAction implements
		DataSourceAction<ArrivalOfGoodsJob> {
	@Autowired
	private ArrivalOfGoodsJobManager arrivalOfGoodsJobManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "arrivalOfGoodsJob_dataSource" };
	}

	/**
	 * 新增物流到达相关信息
	 * @param item
	 */
	@Override
	public void insert(ArrivalOfGoodsJob item) {
		arrivalOfGoodsJobManager.insertEntity(item);
	}

	/**
	 * 更新相应物流到达信息
	 * @param newValues
	 * @param itemID
	 * @return 物流到达相关信息
	 */
	@Override
	public ArrivalOfGoodsJob update(Map newValues, String itemID) {
		if (newValues.containsKey("setStatus")
				&& newValues.get("setStatus").equals("arrivalStatus")) {
			arrivalOfGoodsJobManager.setWorkStatus(newValues.get("orderID")
					.toString());
			return null;
		} else {
			return (ArrivalOfGoodsJob) arrivalOfGoodsJobManager.updateEntity(
					newValues, itemID, ArrivalOfGoodsJob.class);
		}
	}

	/**
	 *  删除物流到达相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		arrivalOfGoodsJobManager.deleteEntity(itemID, ArrivalOfGoodsJob.class);
	}

	/**
	 * 查询物流到达相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流到达相关信息
	 */ 
	@Override
	public List<ArrivalOfGoodsJob> doQuery(Map values, int startRow, int endRow) {
		return arrivalOfGoodsJobManager.getArrivalOfGoodsJob(values, 0, -1);
	}

	/**
	 * 获取所有物流到达信息
	 * @param startRow
	 * @param endRow
	 * @return 物流到达信息
	 */
	@Override
	public List<ArrivalOfGoodsJob> getList(int startRow, int endRow) {
		
		return arrivalOfGoodsJobManager.getArrivalOfGoodsJob(null, 0, -1);
	}

}
