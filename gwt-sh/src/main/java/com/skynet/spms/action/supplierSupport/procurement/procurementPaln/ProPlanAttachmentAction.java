package com.skynet.spms.action.supplierSupport.procurement.procurementPaln;

import java.util.List;
import java.util.Map;

import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.procurement.ProcurementPaln.ProPlanAttachmentManager;
import com.skynet.spms.persistence.entity.base.Attachment;

/**
 * 备件需求 附件数据源
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 */
// @Component
public class ProPlanAttachmentAction implements DataSourceAction<Attachment> {

	// @Autowired
	private ProPlanAttachmentManager manager;

	public String[] getBindDsName() {
		return new String[] { DSKey.S_PROCUREMENTPLAN_ATTACHMENT_DS };
	}

	/**
	 * 添加附件的方法
	 */
	public void insert(Attachment item) {
		manager.addSheet(item);
	}

	/**
	 * 分页查询所有附件的方法
	 */
	public List<Attachment> getList(int startRow, int endRow) {
		return manager.getList(startRow, endRow);
	}

	/**
	 * 修改附件的方法
	 */
	public Attachment update(Map<String, Object> newValues, String itemID) {
		return manager.update(newValues, itemID);
	}

	/**
	 * 删除附件的方法
	 */
	public void delete(String itemID) {
		manager.delete(itemID);
	}

	/**
	 * 根据条件查询附件的方法
	 */
	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		return manager.doQuery(values, startRow, endRow);
	}

}
