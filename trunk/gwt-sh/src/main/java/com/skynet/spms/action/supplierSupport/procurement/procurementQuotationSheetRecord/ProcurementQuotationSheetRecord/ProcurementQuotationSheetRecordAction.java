package com.skynet.spms.action.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.supplierSupport.procurement.procurementQuotationSheetRecord.IProcurementQuotationSheetRecordManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecord;
import com.skynet.spms.service.UUIDGeneral;
/**
 * 采购报价单Action实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class ProcurementQuotationSheetRecordAction implements
		DataSourceAction<ProcurementQuotationSheetRecord> {

	@Resource
	IProcurementQuotationSheetRecordManager manager;

	@Resource
	UUIDGeneral uuidGeneral;
	
	@Autowired
	private ListGridFilterManager<ProcurementQuotationSheetRecord> filterManager;
	
	public String[] getBindDsName() {
		return new String[] { "procurementQuotationSheet_datasource" };
	}

	/**
	 * 添加报价单
	 * @param o 待添加的报价单
	 */
	public void insert(ProcurementQuotationSheetRecord item) {
		//业务状态
		BussinessStatusEntity bse = new BussinessStatusEntity();
		bse.setStatus(EntityStatusMonitor.created);//已创建
		item.setM_BussinessStatusEntity(bse);
		
		//报价状态
		QuotationStatusEntity qsEntity= new QuotationStatusEntity();
		qsEntity.setM_QuotationStatus(QuotationStatus.didNotOffer);//未报价
		item.setM_QuotationStatusEntity(qsEntity);
		
		//发布状态
		BussinessPublishStatusEntity bpse =new BussinessPublishStatusEntity();
		bpse.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(bpse);
		
		//报价编号
		item.setQuotationSheetNumber(uuidGeneral.getSequence("PQ"));
		//创建日期
		item.setCreateDate(new Date());
		
		if(item.getTotalAmount()==null){
			item.setTotalAmount(0.00f);
		}
		manager.addProcurementQuotationSheetRecord(item);
	}

	/**
	 * 删除报价单
	 * @param itemID 报价单id
	 */
	public void delete(String itemID) {
		manager.deleteProcurementQuotationSheetRecord(itemID);
	}

	/**
	 * 修改报价单
	 * @param newValues 新的数据对象
	 * @param itemID 报价单Id
	 * @return 报价单对象
	 */
	public ProcurementQuotationSheetRecord update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateProcurementQuotationSheetRecord(newValues, itemID);
	}

	

	/**
	 * 列表查询
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  报价单列表
	 */
	public List<ProcurementQuotationSheetRecord> getList(int startRow, int endRow) {
		return manager.queryProcurementQuotationSheetRecordList(startRow, endRow);
	}
	
	/**
	 * 列表筛选
	 * @param values 筛选条件
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  报价单列表
	 */
	public List<ProcurementQuotationSheetRecord> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(ProcurementQuotationSheetRecord.class,
					criteria, startRow, endRow);
		}
		
		return manager.queryProcurementQuotationSheetRecordList(startRow, endRow);
	}

}
