package com.skynet.spms.action.customerService.guaranteeClaimService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheet.GuaranteeClaimRequisitionSheetManager;
import com.skynet.spms.persistence.entity.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheet.GuaranteeClaimRequisitionSheet;

/**
 * 
 * 描述：担保索赔申请单
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Component
public class GuaranteeClaimSheetAction implements
		DataSourceAction<GuaranteeClaimRequisitionSheet> {

	@Autowired
	private GuaranteeClaimRequisitionSheetManager guaranteeClaimRequisitionSheetManager;
	@Autowired
	private ListGridFilterManager<GuaranteeClaimRequisitionSheet> filterManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { DSKey.C_GUARANTEESHEET_DS };
	}
	/**
	 * 
	 * 描述：添加一条记录
	 * 
	 * @param sheet
	 *            担保申请单实体
	 */
	@Override
	public void insert(GuaranteeClaimRequisitionSheet item) {
		guaranteeClaimRequisitionSheetManager.addSheet(item);
	}
	/**
	 * 
	 * 描述： 修改
	 * 
	 * @param newValues
	 *            待修改的值集合
	 * @param itemID
	 *            被修改的实体的uuid
	 * @return 返回修改后的实体
	 */
	@Override
	public GuaranteeClaimRequisitionSheet update(Map<String, Object> newValues,
			String itemID) {
		return guaranteeClaimRequisitionSheetManager.update(newValues, itemID);
	}
	/**
	 * 描述： 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	@Override
	public void delete(String itemID) {
		guaranteeClaimRequisitionSheetManager.delete(itemID);
	}
	/**
	 * 描述： 分页查询
	 * 
	 * @param values
	 *            查询条件集合,各个条件为and的关系
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询结果集合
	 */
	@Override
	public List<GuaranteeClaimRequisitionSheet> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(
					GuaranteeClaimRequisitionSheet.class, criteria, startRow,
					endRow);
		}
		return guaranteeClaimRequisitionSheetManager.doQuery(values, startRow,
				endRow);
	}
	/**
	 * 描述： 分页查询
	 * 
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询的结果集合
	 */
	@Override
	public List<GuaranteeClaimRequisitionSheet> getList(int startRow, int endRow) {
		return guaranteeClaimRequisitionSheetManager.getList(startRow, endRow);
	}

}
