package com.skynet.spms.action.supplierSupport.consignment;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.supplierSupport.consignment.ConsignmentContract.IConsignProtocolManager;
import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.consignmentAgreementTemplate.ConsignmentAgreementTemplate;

/**
 * 
 * 描述：寄售协议
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Component
public class ConsignProtocolAction implements
		DataSourceAction<ConsignmentAgreementTemplate> {
	@Autowired
	private IConsignProtocolManager manager;
	@Autowired
	private ListGridFilterManager<ConsignmentAgreementTemplate> filterManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { DSKey.S_CONSIGNPROTOCOL_DS };
	}
	/**
	 * 
	 * 描述：添加一条记录
	 * 
	 * @param item
	 *            寄售协议实体
	 */
	@Override
	public void insert(ConsignmentAgreementTemplate item) {
		manager.addSheet(item);
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
	public ConsignmentAgreementTemplate update(Map<String, Object> newValues,
			String itemID) {
		return manager.update(newValues, itemID);
	}
	/**
	 * 描述： 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	@Override
	public void delete(String itemID) {
		manager.delete(itemID);
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
	public List<ConsignmentAgreementTemplate> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(
					ConsignmentAgreementTemplate.class, criteria, startRow,
					endRow);
		}
		return manager.doQuery(values, startRow, endRow);
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
	public List<ConsignmentAgreementTemplate> getList(int startRow, int endRow) {
		return manager.getList(startRow, endRow);
	}

}
