package com.skynet.spms.action.supplierSupport.leaseService.leasecontract;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.lease.LeaseContract.SSLeaseAttachmentManager;
import com.skynet.spms.persistence.entity.base.Attachment;

/**
 * 供应商租赁合同附件控制器
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 */
@Component
public class SSLeaseAttachmentAction implements DataSourceAction<Attachment> {

	@Autowired
	private SSLeaseAttachmentManager manager;

	public String[] getBindDsName() {
		return new String[] { DSKey.S_LEASEATTACHMENT_DS };
	}

	/**
	 * 添加供应商合同附件的方法
	 */
	public void insert(Attachment item) {
		manager.addSheet(item);
	}

	/**
	 * 分页查询供应商合同附件的方法
	 */
	public List<Attachment> getList(int startRow, int endRow) {
		return manager.getList(startRow, endRow);
	}

	/**
	 * 修改供应商合同附件的方法
	 */
	public Attachment update(Map<String, Object> newValues, String itemID) {
		return manager.update(newValues, itemID);
	}

	/**
	 * 删除供应商合同附件的方法
	 */
	public void delete(String itemID) {
		manager.delete(itemID);
	}

	/**
	 * 根据条件查询供应商合同附件的方法
	 */
	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		return manager.doQuery(values, startRow, endRow);
	}

}
