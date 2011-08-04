package com.skynet.spms.action.finance;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.finance.PayApplyManager;
import com.skynet.spms.persistence.entity.financeService.buySettleAccounts.PayApplyTable;
import com.skynet.spms.service.UUIDGeneral;


@Component
public class PayApplyAction implements DataSourceAction<PayApplyTable> {

	
	private Logger log=LoggerFactory.getLogger(PayApplyAction.class);
	
	@Autowired
	private PayApplyManager payApplyManager;
	@Autowired
	private UUIDGeneral uuidGeneral;
	@Override
	public String[] getBindDsName() {

		return new String[]{"finance_payApply_dataSource"};
	}

	@Override
	public void insert(PayApplyTable item) {

		item.setCreateDate(new Date());
		item.setApplyDate(new Date()); 
		item.setCheck("0");
		item.setApplyNumber(uuidGeneral.getSequence("PYA"));
		payApplyManager.insertEntity(item);
	}

	@Override
	public PayApplyTable update(Map<String, Object> newValues, String itemID) {
		// TODO Auto-generated method stub
		log.info("update.......................................");
		return payApplyManager.updateEntity(newValues, itemID, PayApplyTable.class);
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		payApplyManager.deleteEntity(itemID, PayApplyTable.class);
	}

	@Override
	public List<PayApplyTable> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("doQuery.........................................");
		List criList = (List)values.get("criteria");
        if(criList!=null&&criList.size()!=0){
        	return payApplyManager.queryByCriteriaList(criList);
        }
		PayApplyTable query = new PayApplyTable();
		return payApplyManager.queryByBean(query, startRow, endRow);
	}

	@Override
	public List<PayApplyTable> getList(int startRow, int endRow) {
		log.info("do List ..........................................");
		return payApplyManager.list(startRow, endRow, PayApplyTable.class);
	}

}
