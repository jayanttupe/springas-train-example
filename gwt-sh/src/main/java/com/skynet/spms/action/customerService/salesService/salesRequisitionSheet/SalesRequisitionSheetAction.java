package com.skynet.spms.action.customerService.salesService.salesRequisitionSheet;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.CodeManager;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.SalesRequisitionSheetManager;
import com.skynet.spms.manager.organization.UserManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheetItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheet;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 客户订单Action实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Component
public class SalesRequisitionSheetAction implements
		DataSourceAction<SalesRequisitionSheet> {
	
	@Autowired
	private SalesRequisitionSheetManager salesRequisitionSheetManager;

	@Resource
	UUIDGeneral uuidGeneral;
	
	@Resource
	UserManager userManager;
	
	@Autowired
	private ListGridFilterManager<SalesRequisitionSheet> filterManager;
	
	public String[] getBindDsName() {

		return new String[] {DSKey.C_SALESREQUISITIONSHEET_DS};
	}

	
	/**
	 * 添加订单
	 * @param o 待添加的订单
	 */
	public void insert(SalesRequisitionSheet item) {
		//编号
		item.setRequisitionSheetNumber(uuidGeneral.getSequence("CSO"));
		
		//申请日期
		item.setCreateDate(new Date());
		
		//业务状态
		BussinessStatusEntity bse = new BussinessStatusEntity();
		bse.setStatus(EntityStatusMonitor.created);//已创建
		item.setM_BussinessStatusEntity(bse);
		
		//发布状态
		BussinessPublishStatusEntity bpse =new BussinessPublishStatusEntity();
		bpse.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(bpse);
		
		salesRequisitionSheetManager.addSalesRequisitionSheet(item);
		
		
	}

	/**
	 * 删除订单
	 * @param itemID 订单id
	 */
	public void delete(String itemID) {
		this.salesRequisitionSheetManager.deleteSalesRequisitionSheet(itemID);
	}

	/**
	 * 修改订单
	 * @param newValues 新的数据对象
	 * @param itemID 订单Id
	 * @return 订单对象
	 */
	public SalesRequisitionSheet update(Map<String, Object> newValues,
			String itemID) {

		return salesRequisitionSheetManager.updateSalesRequisitionSheet(newValues,
				itemID);
	}

	/***
	 * 查询订单
	 * @param startRow 开始行
	 * @param endRow   结束行
	 * @return 订单列表
	 */
	public List<SalesRequisitionSheet> getList(int startRow, int endRow) {
		List<SalesRequisitionSheet> list = salesRequisitionSheetManager
				.querySalesRequisitionSheetList(startRow, endRow);
		return list;
	}
	
	/**
	 * 列表筛选
	 * @param values 筛选条件
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  订单列表
	 */
	public List<SalesRequisitionSheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(SalesRequisitionSheet.class,
					criteria, startRow, endRow);
		}
		
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
		
		return salesRequisitionSheetManager.doQuery(values, startRow, endRow);
	}

	

}
