package com.skynet.spms.action.customerService.buybackService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.BuybackService.BuybackContract.IBuyBackAttachmentManager;
import com.skynet.spms.persistence.entity.base.Attachment;

/**
 * 
 * 描述：回购合同 附件数据源
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Component
public class BuyBackAttachmentDatasourceAction implements
		DataSourceAction<Attachment> {

	@Autowired
	private IBuyBackAttachmentManager manager;

	/**
	 * 绑定回购附件数据源
	 * 
	 * @return 返回数据源名称数组
	 */
	public String[] getBindDsName() {
		return new String[] { DSKey.C_BUYBACK_ATTACHMENT_DS };
	}

	/**
	 * 描述： 添加一条记录
	 * 
	 * @param item
	 *            附件实体
	 */
	public void insert(Attachment item) {
		manager.addSheet(item);
	}

	/**
	 * 描述： 分页查询
	 * 
	 *@param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询的结果集合
	 */
	public List<Attachment> getList(int startRow, int endRow) {
		return manager.getList(startRow, endRow);
	}

	/**
	 * 
	 * 描述： 修改附件信息
	 * 
	 * @param newValues
	 *            待修改的值集合
	 * @param itemID
	 *            被修改的附件实体的uuid
	 * @return 返回修改后的附件实体
	 */
	public Attachment update(Map<String, Object> newValues, String itemID) {
		return manager.update(newValues, itemID);
	}
	/**
	 * 描述： 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	public void delete(String itemID) {
		manager.delete(itemID);
	}
	/**
	 * 描述： 分页查询
	 * 
	 * @param values
	 *            查询条件集合
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询结果集合
	 */
	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		return manager.doQuery(values, startRow, endRow);
	}

}
