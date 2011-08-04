package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.PriceBreakDataManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.PriceBreak;

@Component
public class PriceBreakDataSourceAction implements DataSourceAction<PriceBreak> {
	

	private Logger log=LoggerFactory.getLogger(PriceBreakDataSourceAction.class);
	
	@Autowired
	PriceBreakDataManager priceBreakDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"priceBreak_dataSource"}; 
	}

	@Override
	public void insert(PriceBreak item) {
		log.info("===============PriceBreakDataSourceAction.insert()");
		log.info("id : " + item.getId());
		//log.info("SalesPriceId : " + item.getSalesPriceId());
		log.info("itemNumber : " + item.getItemNumber());
		log.info("QuantityScope : " + item.getQuantityScope());
		log.info("PriceAmount" + item.getPriceAmount());
		log.info("nternationalCurrencyCode : " + item.getM_InternationalCurrencyCode());
		log.info("UnitOfMeasureCode" + item.getM_UnitOfMeasureCode());
		
		priceBreakDataManager.insertEntity(item);
	}

	@Override
	public PriceBreak update(Map<String, Object> newValues, String itemID) {
		log.info("===============PriceBreakDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		PriceBreak pad = priceBreakDataManager.updateEntity(newValues, itemID, PriceBreak.class);
		
		return pad;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============PriceBreakDataSourceAction.delete()");
		log.info("PartApplicationDataId ：" + itemID);
		priceBreakDataManager.deleteEntity(itemID, PriceBreak.class);
	}

	@Override
	public List<PriceBreak> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============PriceBreakDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<PriceBreak> list = priceBreakDataManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<PriceBreak> getList(int startRow, int endRow) {
		log.info("===============PriceBreakDataSourceAction.getList()");
		List<PriceBreak> list = new ArrayList<PriceBreak>();
		
		return list;
	}
	
	
}
