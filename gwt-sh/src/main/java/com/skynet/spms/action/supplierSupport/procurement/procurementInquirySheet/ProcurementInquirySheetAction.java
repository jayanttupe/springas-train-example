package com.skynet.spms.action.supplierSupport.procurement.procurementInquirySheet;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.supplierSupport.procurement.procurementInquirySheet.IProcurementInquirySheetManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheetItem;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheet;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 采购询价单Action实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class ProcurementInquirySheetAction implements
		DataSourceAction<ProcurementInquirySheet> {

	@Resource
	IProcurementInquirySheetManager manager;

	@Resource
	UUIDGeneral uuidGeneral;
	
	@Autowired
	private ListGridFilterManager<ProcurementInquirySheet> filterManager;
	
	public String[] getBindDsName() {
		return new String[] { "procurementInquirySheet_datasource" };
	}

	/**
	 * 添加询价单
	 * @param o 待添加的询价单
	 */
	public void insert(ProcurementInquirySheet item) {
		// 业务状态
		BussinessStatusEntity bse = new BussinessStatusEntity();
		bse.setStatus(EntityStatusMonitor.created);// 已创建
		item.setM_BussinessStatusEntity(bse);

		// 报价状态
		QuotationStatusEntity qsEntity = new QuotationStatusEntity();
		qsEntity.setM_QuotationStatus(QuotationStatus.didNotOffer);// 未报价
		item.setM_QuotationStatusEntity(qsEntity);

		// 发布状态
		BussinessPublishStatusEntity bpse = new BussinessPublishStatusEntity();
		bpse.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(bpse);

		// 询价编号
		item.setInquirySheetNumber(uuidGeneral.getSequence("PIQ"));

		// 创建日期
		item.setCreateDate(new Date());

	
		manager.addProcurementInquirySheet(item);
	}

	/**
	 * 列表查询
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  询价单列表
	 */
	public List<ProcurementInquirySheet> getList(int startRow, int endRow) {
		return manager.queryProcurementInquirySheetList(startRow, endRow);
	}
	/**
	 * 更新询价单
	 * @param newValues 新的数据对象
	 * @param itemID 询价单Id
	 * @return 询价单对象
	 */
	public ProcurementInquirySheet update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateProcurementInquirySheet(newValues, itemID);
	}
	
	/**
	 * 删除询价单
	 * @param itemID 询价单id
	 */
	public void delete(String itemID) {
		manager.deleteProcurementInquirySheet(itemID);
	}

	/**
	 * 列表筛选
	 * @param values 筛选条件
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  询价单列表
	 */
	public List<ProcurementInquirySheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(ProcurementInquirySheet.class,
					criteria, startRow, endRow);
		}
		
		return manager.queryProcurementInquirySheetList(startRow, endRow);
	}

	
}
