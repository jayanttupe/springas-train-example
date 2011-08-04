package com.skynet.spms.action.partCatalog;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.DiscountMatrixManager;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.discountMatrix.DiscountItem;

@Component
public class DiscountMatrixDataSourceAction implements DataSourceAction<DiscountItem> {
	

	private Logger log=LoggerFactory.getLogger(DiscountMatrixDataSourceAction.class);
	
	@Autowired
	 DiscountMatrixManager discountMatrixManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"discountItem_dataSource"}; 
	}

	@Override
	public void insert(DiscountItem item) {
		log.info("===============DiscountMatrixDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("partSaleReleaseId : " + item.getPartSaleReleaseId());
		/*log.info("m_CustomerCategoryCode : " + item.getRecommendedQuantity());
		log.info("m_ProductCategoryCode" + item.isQuickEngineChange());
		log.info("m_DiscountPercentCode : " + item.getM_ETOPSFlightIndicator());
		log.info("itemDetail" + item.getM_EngineLevelOfMaintenanceCode());*/
		
		discountMatrixManager.insertEntity(item);
	}

	@Override
	public DiscountItem update(Map<String, Object> newValues, String itemID) {
		log.info("===============DiscountMatrixDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		DiscountItem pad = discountMatrixManager.updateEntity(newValues, itemID, DiscountItem.class);
		
		return pad;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============DiscountMatrixDataSourceAction.delete()");
		log.info("DiscountMatrixId ：" + itemID);
		discountMatrixManager.deleteEntity(itemID, DiscountItem.class);
	}

	@Override
	public List<DiscountItem> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============DiscountMatrixDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<DiscountItem> list = discountMatrixManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<DiscountItem> getList(int startRow, int endRow) {
		log.info("===============DiscountMatrixDataSourceAction.getList()");
		List<DiscountItem> list = discountMatrixManager.list(startRow, endRow, DiscountItem.class);
		
		return list;
	}
	
	
}
