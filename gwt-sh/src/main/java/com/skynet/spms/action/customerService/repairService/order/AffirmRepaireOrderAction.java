package com.skynet.spms.action.customerService.repairService.order;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.order.affirmRepaireOrder.IAffirmRepaireOrderManager;
import com.skynet.spms.persistence.entity.customerService.order.affirmRepaireOrder.AffirmRepaireOrder;

/**
 * 客户确认修理指令控制层
 * @author  tqc
 *
 */
@Deprecated
@Component
public class AffirmRepaireOrderAction implements
		DataSourceAction<AffirmRepaireOrder> {

	@Resource
	IAffirmRepaireOrderManager manager;
	
	/**
	 * 定义数据源名称
	 */
	@Deprecated
	public String[] getBindDsName() {
		return new String[] { "AffirmRepaireOrder_datasource" };
	}
	@Deprecated
	public void insert(AffirmRepaireOrder item) {
		manager.addAffirmRepaireOrder(item);
	}
	@Deprecated
	public List<AffirmRepaireOrder> getList(int startRow, int endRow) {
		return manager.queryAffirmRepaireOrderList(startRow, endRow);
	}
	@Deprecated
	public AffirmRepaireOrder update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateAffirmRepaireOrder(newValues, itemID);
	}
	@Deprecated
	public void delete(String itemID) {
		manager.deleteAffirmRepaireOrder(itemID);
	}
	@Deprecated
	public List<AffirmRepaireOrder> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		return manager.queryAffirmRepaireOrderList(startRow, endRow);
	}

}
