package com.skynet.spms.action.supplierSupport.repairClaim;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.repairClaim.InspectOutlayRecord.IInspectOutlayRecordManager;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.InspectOutlayRecord.InspectOutlayRecord;

/**
 * 供应商修理记录
 * 
 * @author tqc
 */
@Component
public class InspectOutlayRecordAction implements
		DataSourceAction<InspectOutlayRecord> {

	@Resource
	IInspectOutlayRecordManager manager;
	/**
	 * 绑定数据源
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "supplierInspectOutlayRecord_datasource" };
	}
	/**
	 * 插入
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(InspectOutlayRecord item) {
		manager.addInspectOutlayRecord(item);
	}
	/**
	 * 分页查询
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<InspectOutlayRecord> getList(int startRow, int endRow) {
		return manager.queryInspectOutlayRecordList(startRow, endRow);
	}
	/**
	 * 更新
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map, java.lang.String)
	 */
	public InspectOutlayRecord update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateInspectOutlayRecord(newValues, itemID);
	}
	/**
	 * 删除
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deleteInspectOutlayRecord(itemID);
	}
	/**
	 * 高级查询
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map, int, int)
	 */
	public List<InspectOutlayRecord> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if("getByID".equals(values.get("key"))){
			String contractId=(String)values.get("contractId");
			return manager.getInspectOutlayRecordByContractId(contractId) ;
		}
		return manager.queryInspectOutlayRecordList(startRow, endRow);
	}

}
