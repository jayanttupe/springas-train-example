package com.skynet.spms.action.customerService.salesService.salesInquirySheet;

import java.util.Date;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.salesService.salesInquirySheet.SalesInquirySheetManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.BuybackService.BuybackRequisitionSheet.BuybackRequisitionSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheet;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;
import com.skynet.spms.service.UUIDGeneral;
import com.skynet.spms.action.organization.UserDatasourceAction;
import com.skynet.spms.manager.organization.UserManager;

/**
 * 询价单Action实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class SalesInquirySheetDatasourceAction implements
		DataSourceAction<SalesInquirySheet> {

	@Autowired
	SalesInquirySheetManager salesInquirySheetManager;

	@Resource
	UUIDGeneral uuidGeneral;

	@Resource
	UserManager userManager;
	
	@Autowired
	private ListGridFilterManager<SalesInquirySheet> filterManager;

	public String[] getBindDsName() {
		return new String[] { "salesInquirySheet_dataSource",
				"doQuotation_dataSource" };
	}

	/**
	 * 添加询价单
	 * @param o 待添加的询价单
	 */
	public void insert(SalesInquirySheet item) {
		// hidden1.setValue(new BussinessStatusEntity().status.created+"");
		BussinessStatusEntity bse = new BussinessStatusEntity();
		bse.setStatus(EntityStatusMonitor.created);
		Date date = new Date();
		bse.setActionDate(date);
		item.setM_BussinessStatusEntity(bse);

		QuotationStatusEntity qse = new QuotationStatusEntity();
		qse.setM_QuotationStatus(QuotationStatus.didNotOffer);
		qse.setActionDate(date);
		item.setM_QuotationStatusEntity(qse);

		// 发布状态
		BussinessPublishStatusEntity bpse = new BussinessPublishStatusEntity();
		bpse.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(bpse);
		
		item.setInquirySheetNumber(uuidGeneral.getSequence("CIQ"));

		item.setCreateDate(new Date());
		salesInquirySheetManager.addSalesInquirySheet(item);
	}
	
	/**
	 * 删除询价单
	 * @param itemID 询价单id
	 */
	public void delete(String itemID) {
		this.salesInquirySheetManager.deleteSalesInquirySheet(itemID);
	}


	/**
	 * 修改询价单
	 * @param newValues 新的数据对象
	 * @param itemID 询价单Id
	 * @return 询价单对象
	 */
	public SalesInquirySheet update(Map<String, Object> newValues, String itemID) {
		return salesInquirySheetManager.updateSalesInquirySheet(newValues,
				itemID);
	}


	/***
	 * 查询询价单
	 * @param startRow 开始行
	 * @param endRow   结束行
	 * @return 询价单列表
	 */
	public List<SalesInquirySheet> getList(int startRow, int endRow) {
		List<SalesInquirySheet> result = salesInquirySheetManager
				.querySalesInquirySheetList(startRow, endRow);
		return result;
	}
	
	/**
	 * 列表筛选
	 * @param values 筛选条件
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  询价单列表
	 */
	public List<SalesInquirySheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List criteria = (List) values.remove("criteria");
		
		//根据传递过来的客户名称 先获得客户
		if (values.get("userName")!=null) {
			CustomerIdentificationCode customerCode= userManager.queryCustomerCodeByUsername(values.get("userName")+"");
			values.remove("userName");
			if(customerCode!=null){
				values.put("m_CustomerIdentificationCode.id", customerCode.getId());
			}else{
				values.put("m_CustomerIdentificationCode.id", "");
			}
			
			
		}
		
		
		if (criteria != null) {
			return filterManager.doQueryFilter(SalesInquirySheet.class,
					criteria, startRow, endRow);
		}
		
		
		return salesInquirySheetManager.doQuery(values, startRow, endRow);
	}
}
