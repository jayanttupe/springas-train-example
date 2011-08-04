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
import com.skynet.spms.manager.finance.ClassificationItemManager;
import com.skynet.spms.manager.finance.PurchaseInvoiceManager;
import com.skynet.spms.persistence.entity.financeService.buySettleAccounts.PurchaseInvoice;
import com.skynet.spms.service.UUIDGeneral;

@Component
public class PurchaseInvoiceAction implements DataSourceAction<PurchaseInvoice> {

	private Logger log=LoggerFactory.getLogger(PurchaseInvoiceAction.class);
	
	@Autowired
	private PurchaseInvoiceManager purchaseInvoiceManager;
	@Autowired
	private UUIDGeneral uuidGeneral;
	
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"finance_purchaseInvoice_dataSource"};
	}

	@Override
	public void insert(PurchaseInvoice item) {
		// TODO Auto-generated method stub
		item.setInvoiceDate(new Date());
		item.setInvoiceNumber(uuidGeneral.getSequence("PVN"));
		item.setIsChecked(new Byte("0"));
		purchaseInvoiceManager.insertEntity(item);
	}

	@Override
	public PurchaseInvoice update(Map<String, Object> newValues, String itemID) {
		// TODO Auto-generated method stub

		return purchaseInvoiceManager.updateEntity(newValues, itemID, PurchaseInvoice.class);
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		purchaseInvoiceManager.deleteEntity(itemID,PurchaseInvoice.class);
		
	}

	@Override
	public List<PurchaseInvoice> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		// TODO Auto-generated method stub
		log.info("doQuery");
		List criList = (List)values.get("criteria");
        if(criList!=null&&criList.size()!=0){
        	return purchaseInvoiceManager.queryByCriteriaList(criList);
        }
        
        PurchaseInvoice purchaseInvoice = new PurchaseInvoice();
        BeanPropUtil.fillEntityWithMap(purchaseInvoice, values);
		return purchaseInvoiceManager.queryByBean(purchaseInvoice, startRow, endRow);
	}

	@Override
	public List<PurchaseInvoice> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return purchaseInvoiceManager.list(startRow, endRow, PurchaseInvoice.class);
	}

}
