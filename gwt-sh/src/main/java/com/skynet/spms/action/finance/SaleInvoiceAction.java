package com.skynet.spms.action.finance;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.finance.InvoiceApplyManager;
import com.skynet.spms.manager.finance.SaleInvoiceManager;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.Subjects;
import com.skynet.spms.persistence.entity.financeService.salesSettleAccounts.InvoiceApplyTable;
import com.skynet.spms.persistence.entity.financeService.salesSettleAccounts.SaleInvoice;
import com.skynet.spms.service.UUIDGeneral;

@Component
public class SaleInvoiceAction  implements DataSourceAction<SaleInvoice> {

	
	private Logger log=LoggerFactory.getLogger(SaleInvoiceAction.class);
	
	@Autowired
	private SaleInvoiceManager saleInvoiceManager;
	
	@Autowired
	private InvoiceApplyManager invoiceApplyManager;
	@Autowired
	private UUIDGeneral uuidGeneral;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"finance_saleInvoice_dataSource"};
	}

	@Override
	public void insert(SaleInvoice item) {
		// TODO Auto-generated method stub
		InvoiceApplyTable invoiceApplyTable = invoiceApplyManager.queryById(item.getInvoiceApplyId(), InvoiceApplyTable.class);
		item.setInvoiceApplyTable(invoiceApplyTable);
		item.setInvoiceDate(new Date());
		item.setInvoiceNumber(uuidGeneral.getSequence("SVN"));
		item.setIsChecked(new Byte("0"));
		saleInvoiceManager.insertEntity(item);
		
	}

	@Override
	public SaleInvoice update(Map<String, Object> newValues, String itemID) {
		// TODO Auto-generated method stub
		return saleInvoiceManager.updateEntity(newValues, itemID, SaleInvoice.class);
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		saleInvoiceManager.deleteEntity(itemID, SaleInvoice.class);
	}

	@Override
	public List<SaleInvoice> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		// TODO Auto-generated method stub
		log.info("doQuery");
		List criList = (List)values.get("criteria");
        if(criList!=null&&criList.size()!=0){
        	return saleInvoiceManager.queryByCriteriaList(criList);
        }
        
        SaleInvoice saleInvoice = new SaleInvoice();
        BeanPropUtil.fillEntityWithMap(saleInvoice, values);
		return saleInvoiceManager.queryByBean(saleInvoice, startRow, endRow);
	}

	@Override
	public List<SaleInvoice> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		log.info("do List............................");
		return saleInvoiceManager.list(startRow, endRow, SaleInvoice.class);
	}

}
