package com.skynet.spms.action.customerService.leaseService.leaseRequisitionSheet;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.LeaseService.LeaseContract.LeaseAttachmentManager;
import com.skynet.spms.persistence.entity.base.Attachment;

/**
 * 回购合同 附件数据源
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 */
@Component
public class LeaseAttachmentAction implements DataSourceAction<Attachment> {

	@Autowired
	private LeaseAttachmentManager manager;

	public String[] getBindDsName() {
		return new String[] { DSKey.C_LEASEATTACHMENT_DS };
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
