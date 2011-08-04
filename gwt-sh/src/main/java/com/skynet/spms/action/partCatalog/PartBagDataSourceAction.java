package com.skynet.spms.action.partCatalog;
/**
 * Description: TODO
 *
 * @author   Administrator
 * @version  Ver 1.0
 * @Date	 2011-7-25
 *
 */

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.PartBagItemDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.partBagItem.PartBagItem;

@Component
public class PartBagDataSourceAction implements DataSourceAction<PartBagItem> {
	

	private Logger log=LoggerFactory.getLogger(PartBagDataSourceAction.class);
	
	@Autowired
	PartBagItemDataManager partBagItemDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"partBagItem_dataSource"}; 
	}

	@Override
	public void insert(PartBagItem item) {
		log.info("==============PartBagDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("itemPartIndex : " + item.getItemPartIndex());
		log.info("requireCount: " + item.getRequireCount());
		/*log.info("optionalPartNumberText" + item.getOptionalPartNumberText());
		log.info("m_InterchangeabilityCode : " + item.getM_InterchangeabilityCode());*/
	
		partBagItemDataManager.insertEntity(item);
	}

	@Override
	public PartBagItem update(Map<String, Object> newValues, String itemID) {
		log.info("===============PartBagDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		PartBagItem pad = partBagItemDataManager.updateEntity(newValues, itemID, PartBagItem.class);
		
		return pad;
	}
	@Override
	public void delete(String itemID) {
		log.info("===============PartBagDataSourceAction.delete()");
		log.info("itemPartIndex：" + itemID);
		partBagItemDataManager.deleteEntity(itemID, PartBagItem.class);
	}

	@Override
	public List<PartBagItem> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============PartBagDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<PartBagItem> list = partBagItemDataManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<PartBagItem> getList(int startRow, int endRow) {
		log.info("===============PartBagDataSourceAction.getList()");
		List<PartBagItem> list = partBagItemDataManager.list(startRow, endRow, PartBagItem.class);
		
		return list;
	}

	
	
	
}
