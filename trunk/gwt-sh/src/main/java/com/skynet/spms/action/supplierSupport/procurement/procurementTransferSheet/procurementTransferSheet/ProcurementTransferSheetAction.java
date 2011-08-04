package com.skynet.spms.action.supplierSupport.procurement.procurementTransferSheet.procurementTransferSheet;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheet.IProcurementTransferSheetManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheet;
import com.skynet.spms.service.UUIDGeneral;
/**
 * 采购调拨单
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class ProcurementTransferSheetAction implements
		DataSourceAction<ProcurementTransferSheet> {

	@Resource
	IProcurementTransferSheetManager manager;

	@Resource
	UUIDGeneral uuidGeneral;
	
	@Autowired
	private ListGridFilterManager<ProcurementTransferSheet> filterManager;
	
	public String[] getBindDsName() {
		return new String[] { "transferSheet_datasource" };
	}

	/**
	 * 添加调拨单
	 * @param o 待添加的调拨单
	 */
	public void insert(ProcurementTransferSheet item) {
		//业务状态
		BussinessStatusEntity bse = new BussinessStatusEntity();
		bse.setStatus(EntityStatusMonitor.created);//已创建
		item.setM_BussinessStatusEntity(bse);
		
	
		
		//发布状态
		BussinessPublishStatusEntity bpse =new BussinessPublishStatusEntity();
		bpse.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(bpse);
		
		//调拨编号
		item.setTransferSheetNumber(uuidGeneral.getSequence("AT"));
		
		//创建日期
		item.setCreateDate(new Date());
		manager.addProcurementTransferSheet(item);
	}

	/**
	 * 删除调拨单
	 * @param itemID 调拨单id
	 */
	public void delete(String itemID) {
		manager.deleteProcurementTransferSheet(itemID);
	}	

	/**
	 * 修改调拨单
	 * @param newValues 新的数据对象
	 * @param itemID 调拨单Id
	 * @return 调拨单对象
	 */
	public ProcurementTransferSheet update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateProcurementTransferSheet(newValues, itemID);
	}

	
	/**
	 * 列表查询
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  调拨单列表
	 */
	public List<ProcurementTransferSheet> getList(int startRow, int endRow) {
		return manager.queryProcurementTransferSheetList(startRow, endRow);
	}
	
	/**
	 * 列表筛选
	 * @param values 筛选条件
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  调拨单列表
	 */
	public List<ProcurementTransferSheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(ProcurementTransferSheet.class,
					criteria, startRow, endRow);
		}
		
		return manager.queryProcurementTransferSheetList(startRow, endRow);
	}

}
