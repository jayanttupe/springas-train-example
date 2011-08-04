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
import com.skynet.spms.manager.partCatalog.SupplierPriceDataManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.AlternateSupplyLocationText;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.BasePrice;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice.SalesPrice;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.salesPrice.SupplierSalesPrice;
import com.skynet.spms.tools.OneToManyTools;

@Component
public class SupplierPriceDataSourceAction implements DataSourceAction<SupplierSalesPrice> {
	

	private Logger log=LoggerFactory.getLogger(SupplierPriceDataSourceAction.class);
	
	@Autowired
	SupplierPriceDataManager supplierPriceDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"supplierSalesPrice_dataSource"}; 
	}

	@Override
	public void insert(SupplierSalesPrice item) {
		log.info("===============SupplierPriceDataSourceAction.insert()");
		

	} 

	@Override
	public SupplierSalesPrice update(Map<String, Object> newValues, String itemID) {
		log.info("===============SupplierPriceDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		
		SupplierSalesPrice ptd = supplierPriceDataManager.updateCascade(newValues, itemID);
		return ptd;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============SupplierPriceDataSourceAction.delete()");
		
	}

	@Override
	public List<SupplierSalesPrice> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============SupplierPriceDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<SupplierSalesPrice> list = supplierPriceDataManager.queryByProps(values);
		log.info("===============doQuery end");
		return list;
	}

	@Override
	public List<SupplierSalesPrice> getList(int startRow, int endRow) {
		log.info("===============SupplierPriceDataSourceAction.getList()");
		List<SupplierSalesPrice> list = new ArrayList<SupplierSalesPrice>();
		return list;
	}

}
