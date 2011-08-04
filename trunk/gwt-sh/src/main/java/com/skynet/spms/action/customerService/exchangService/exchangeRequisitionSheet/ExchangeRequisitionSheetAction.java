package com.skynet.spms.action.customerService.exchangService.exchangeRequisitionSheet;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.exchangService.exchangeRequisitionSheet.*;
import com.skynet.spms.manager.organization.UserManager;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.customerService.sales.exchangeRequisitionSheet.ExchangeRequisitionSheet;
/**
 * 交换申请单控制层
 * @author  lining
 *
 */
@Component
public class ExchangeRequisitionSheetAction implements
		DataSourceAction<ExchangeRequisitionSheet> {

	@Resource
	IExchangeRequisitionSheetManager manager;

	
	@Resource
	UserManager userManager;
	public String[] getBindDsName() {
		return new String[] { "exchangeRequisition_dataSource" };
	}

	public void insert(ExchangeRequisitionSheet item) {
		manager.addExchangeRequisitionSheet(item);
	}

	public List<ExchangeRequisitionSheet> getList(int startRow, int endRow) {
		return manager.queryExchangeRequisitionSheetList(startRow, endRow);
	}

	public ExchangeRequisitionSheet update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateExchangeRequisitionSheet(newValues, itemID);
	}

	public void delete(String itemID) {
		manager.deleteExchangeRequisitionSheet(itemID);
	}

	public List<ExchangeRequisitionSheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
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
		return manager.doQuery(values, startRow, endRow);
	}

}
