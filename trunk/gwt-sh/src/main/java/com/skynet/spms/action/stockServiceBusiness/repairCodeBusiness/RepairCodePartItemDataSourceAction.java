package com.skynet.spms.action.stockServiceBusiness.repairCodeBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.repairCodeBusiness.RepairCodeCargoSpaceItemManager;
import com.skynet.spms.manager.stockServiceBusiness.repairCodeBusiness.RepairCodePartItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodeCargoSpaceItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodePartItem;

/**
 * 描述：航材补码备件相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class RepairCodePartItemDataSourceAction implements DataSourceAction<RepairCodePartItem> {

	private Logger log=LoggerFactory.getLogger(RepairCodePartItemDataSourceAction.class);
	
	@Autowired
	private RepairCodePartItemManager repairCodePartItemManager;
	
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"repairCodePartItem_dataSource"};
	}

	/**
	 * 新增航材补码备件相关信息
	 * @param item
	 */
	@Override
	public void insert(RepairCodePartItem item) {
		log.info("=============================RepairCodePartItemDataSourceAction.insert()");
		
	}

	/**
	 * 更新航材补码备件相关信息
	 * @param newValues
	 * @param itemID
	 * @return 航材补码备件相关信息
	 */
	@Override
	public RepairCodePartItem update(Map<String, Object> newValues, String itemID) {
		log.info("=============================RepairCodePartItemDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return null;
	}

	/**
	 * 删除航材补码备件相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		log.info("=============================RepairCodePartItemDataSourceAction.delete()");
		
	}

	/**
	 * 查询航材补码备件相关信息
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 航材补码备件相关信息
	 */
	@Override
	public List<RepairCodePartItem> doQuery(Map<String, Object> map,
			int startRow, int endRow) {
		log.info("=============================RepairCodePartItemDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : map.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<RepairCodePartItem> items = repairCodePartItemManager.queryByProps(map);
		return items;
	}

	/**
	 * 获取所有航材补码备件信息
	 * @param startRow
	 * @param endRow
	 * @return 航材补码备件信息
	 */
	@Override
	public List<RepairCodePartItem> getList(int startRow, int endRow) {
		log.info("=============================RepairCodePartItemDataSourceAction.getList()");
		List<RepairCodePartItem> list = new ArrayList<RepairCodePartItem>();
		return list;
	}
}
