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
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodeCargoSpaceItem;

/**
 * 描述：货位补码明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class RepairCodeCsItemDataSourceAction implements DataSourceAction<RepairCodeCargoSpaceItem> {

	private Logger log=LoggerFactory.getLogger(RepairCodeCsItemDataSourceAction.class);
	
	@Autowired
	private RepairCodeCargoSpaceItemManager repairCodeCargoSpaceItemManager;
	
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"repairCodeCargoSpaceItem_dataSource"};
	}

	/**
	 * 新增货位补码明细相关信息
	 * @param item
	 */
	@Override
	public void insert(RepairCodeCargoSpaceItem item) {
		log.info("=============================RepairCodeCsItemDataSourceAction.insert()");
		
	}

	/**
	 * 更新货位补码明细相关信息
	 * @param newValues
	 * @param itemID
	 * @return
	 */
	@Override
	public RepairCodeCargoSpaceItem update(Map<String, Object> newValues, String itemID) {
		log.info("=============================RepairCodeCsItemDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return null;
	}

	/**
	 * 删除货位补码明细相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		log.info("=============================RepairCodeCsItemDataSourceAction.delete()");
		
	}

	/**
	 * 查询货位补码明细相关信息
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 货位补码明细相关信息
	 */
	@Override
	public List<RepairCodeCargoSpaceItem> doQuery(Map<String, Object> map,
			int startRow, int endRow) {
		log.info("=============================RepairCodeCsItemDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : map.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<RepairCodeCargoSpaceItem> items = repairCodeCargoSpaceItemManager.queryByProps(map);
		return items;
	}

	/**
	 * 获取所有货位补码明细信息
	 * @param startRow
	 * @param endRow
	 * @return 货位补码明细信息
	 */
	@Override
	public List<RepairCodeCargoSpaceItem> getList(int startRow, int endRow) {
		log.info("=============================UserInfoAction.getList()");
		List<RepairCodeCargoSpaceItem> list = new ArrayList<RepairCodeCargoSpaceItem>();
		return list;
	}
}
