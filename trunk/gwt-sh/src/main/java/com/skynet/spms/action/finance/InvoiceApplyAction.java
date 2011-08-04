package com.skynet.spms.action.finance;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.finance.InvoiceApplyManager;
import com.skynet.spms.persistence.entity.financeService.salesSettleAccounts.InvoiceApplyTable;
import com.skynet.spms.service.UUIDGeneral;
import com.skynet.spms.service.imp.UUIDGeneralImp;

@Component
public class InvoiceApplyAction  implements DataSourceAction<InvoiceApplyTable> {

	
	private Logger log=LoggerFactory.getLogger(InvoiceApplyAction.class);
	
	@Autowired
	private InvoiceApplyManager invoiceApplyManager;
	
	@Autowired
	private UUIDGeneral uuidGeneral;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"finance_invoiceApply_dataSource"};
	}

	@Override
	public void insert(InvoiceApplyTable item) {
		// TODO Auto-generated method stub
		item.setCreateDate(new Date());
/*		item.setCreateBy("huangyun");
		item.setApplyUser("huangyun");*/
		item.setApplyDate(new Date());
		item.setCheck("0");
		item.setApplyNumber(uuidGeneral.getSequence("IVA"));
		invoiceApplyManager.insertEntity(item);
		
	}

	@Override
	public InvoiceApplyTable update(Map<String, Object> newValues, String itemID) {
		// TODO Auto-generated method stub
		return invoiceApplyManager.updateEntity(newValues, itemID, InvoiceApplyTable.class);
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		invoiceApplyManager.deleteEntity(itemID, InvoiceApplyTable.class);
	}

	@Override
	public List<InvoiceApplyTable> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		// TODO Auto-generated method stub
		log.info("doQuery");
		List criList = (List)values.get("criteria");
        if(criList!=null&&criList.size()!=0){
        	return invoiceApplyManager.queryByCriteriaList(criList);
        }
        InvoiceApplyTable invoiceApplyTable = new InvoiceApplyTable();
        invoiceApplyTable.setDeleted(true);
		return null;
	}

	@Override
	public List<InvoiceApplyTable> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return invoiceApplyManager.list(startRow, endRow,InvoiceApplyTable.class);
	}

}
