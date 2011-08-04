package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.SalesPriceDataManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.AlternateSupplyLocationText;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.BasePrice;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice.SalesPrice;
import com.skynet.spms.tools.OneToManyTools;

@Component
public class SalesPriceDataSourceAction implements DataSourceAction<SalesPrice> {
	

	private Logger log=LoggerFactory.getLogger(SalesPriceDataSourceAction.class);
	
	@Autowired
	SalesPriceDataManager salesPriceDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"salesPrice_dataSource"}; 
	}

	@Override
	public void insert(SalesPrice item) {
		log.info("===============SalesPriceDataSourceAction.insert()");
		

	} 

	@Override
	public SalesPrice update(Map<String, Object> newValues, String itemID) {
		log.info("===============SalesPriceDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		
		SalesPrice ptd = salesPriceDataManager.updateCascade(newValues, itemID);
		return ptd;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============SalesPriceDataSourceAction.delete()");
		
	}

	@Override
	public List<SalesPrice> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============SalesPriceDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<SalesPrice> list = salesPriceDataManager.queryByProps(values);
		log.info("===============doQuery end");
		return list;
	}

	@Override
	public List<SalesPrice> getList(int startRow, int endRow) {
		log.info("===============SalesPriceDataSourceAction.getList()");
		List<SalesPrice> list = new ArrayList<SalesPrice>();
		return list;
	}

}
