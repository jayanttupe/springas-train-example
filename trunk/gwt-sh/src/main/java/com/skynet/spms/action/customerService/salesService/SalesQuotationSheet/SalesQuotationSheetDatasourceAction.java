package com.skynet.spms.action.customerService.salesService.SalesQuotationSheet;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.CodeManager;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.salesService.SalesQuotationSheet.SalesQuotationSheetManager;
import com.skynet.spms.manager.organization.UserManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.RequisitionStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheet;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;
import com.skynet.spms.persistence.entity.spmsdd.RequisitionStatus;
import com.skynet.spms.service.UUIDGeneral;
/**
 * 销售报价Action实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class SalesQuotationSheetDatasourceAction implements
		DataSourceAction<SalesQuotationSheet> {

	@Autowired
	private SalesQuotationSheetManager salesQuotationSheetManager;

	@Autowired
	UUIDGeneral uuidGeneral;
	
	@Autowired
	UserManager userManager;
	
	
	
	
	@Autowired
	private ListGridFilterManager<SalesQuotationSheet> filterManager;
	
	public String[] getBindDsName() {
		return new String[] { "salesQuotationSheet_dataSource" };
	}
	
	
	/**
	 * 添加报价单
	 * @param o 待添加的报价单
	 */
	public void insert(SalesQuotationSheet item) {
		// 业务状态
		BussinessStatusEntity bse = new BussinessStatusEntity();
		bse.setStatus(EntityStatusMonitor.created);// 已创建
		item.setM_BussinessStatusEntity(bse);

		// 报价状态
		QuotationStatusEntity qsEntity = new QuotationStatusEntity();
		qsEntity.setM_QuotationStatus(QuotationStatus.didNotOffer);// 未报价
		item.setM_QuotationStatusEntity(qsEntity);

		// 采购状态
		RequisitionStatusEntity rse = new RequisitionStatusEntity();
		rse.setM_RequisitionStatus(RequisitionStatus.didNotOffer);// 未采购
		item.setM_RequisitionStatusEntity(rse);

		// 发布状态
		BussinessPublishStatusEntity bpse = new BussinessPublishStatusEntity();
		bpse.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(bpse);

		// 报价编号
		item.setQuotationSheetNumber(uuidGeneral.getSequence("CQ"));

		// 创建日期
		Date nowDate= new Date();
		item.setCreateDate(nowDate);
		item.setQuotationDate(nowDate);

		if(item.getTotalAmount()==null){
			item.setTotalAmount(0.00f);
		}
		
		salesQuotationSheetManager.addSalesQuotationSheet(item);
		//salesQuotationSheetManager.updateBussinessState("SalesInquirySheet", item.getSalesInquirySheet().getId(),EntityStatusMonitor.processing.toString());
		//manager.updateBussinessState("SalesInquirySheet", item.getSalesInquirySheet().getId(),EntityStatusMonitor.processing.toString());
	}
	
	/**
	 * 删除报价单
	 * @param itemID 报价单id
	 */
	public void delete(String itemID) {
		this.salesQuotationSheetManager.deleteSalesQuotationSheet(itemID);
	}

	
	/**
	 * 修改报价单
	 * @param newValues 新的数据对象
	 * @param itemID 报价单Id
	 * @return 报价单对象
	 */
	public SalesQuotationSheet update(Map<String, Object> newValues,
			String itemID) {
		return salesQuotationSheetManager.updateSalesQuotationSheet(newValues,
				itemID);
	}
	
	
	/***
	 * 查询报价单
	 * @param startRow 开始行
	 * @param endRow   结束行
	 * @return 报价单列表
	 */
	public List<SalesQuotationSheet> getList(int startRow, int endRow) {

		List<SalesQuotationSheet> result = salesQuotationSheetManager
				.querySalesQuotationSheetList(startRow, endRow);
		return result;
	}

	/**
	 * 列表筛选
	 * @param values 筛选条件
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  报价单列表
	 */
	public List<SalesQuotationSheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(SalesQuotationSheet.class,
					criteria, startRow, endRow);
		}
		
		//根据传递过来的客户名称 先获得客户
//		if (values.get("userName")!=null) {
//			CustomerIdentificationCode customerCode= userManager.queryCustomerCodeByUsername(values.get("userName")+"");
//			values.remove("userName");
//			if(customerCode!=null){
//				values.put("m_CustomerIdentificationCode.id", customerCode.getId());
//			}else{
//				values.put("m_CustomerIdentificationCode.id", "");
//			}
//		}
		return salesQuotationSheetManager.doQuery(values, startRow, endRow);
	}

	
	

}
