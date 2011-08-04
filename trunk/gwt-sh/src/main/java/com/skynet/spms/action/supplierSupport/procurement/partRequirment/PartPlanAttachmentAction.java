package com.skynet.spms.action.supplierSupport.procurement.partRequirment;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.procurement.PartRequirment.PartPlanAttachmentManager;
import com.skynet.spms.persistence.entity.base.Attachment;

/**
 * 备件需求 附件数据源
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 */
@Component
public class PartPlanAttachmentAction implements DataSourceAction<Attachment> {

	@Autowired
	private PartPlanAttachmentManager manager;

	public String[] getBindDsName() {
		return new String[] { DSKey.S_PARTREQUIREMENT_ATTACHMENT_DS };
	}

	public void insert(Attachment item) {
		manager.addSheet(item);
	}

	public List<Attachment> getList(int startRow, int endRow) {
		return manager.getList(startRow, endRow);
	}

	public Attachment update(Map<String, Object> newValues, String itemID) {
		return manager.update(newValues, itemID);
	}

	public void delete(String itemID) {
		manager.delete(itemID);
	}

	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		return manager.doQuery(values, startRow, endRow);
	}

}
