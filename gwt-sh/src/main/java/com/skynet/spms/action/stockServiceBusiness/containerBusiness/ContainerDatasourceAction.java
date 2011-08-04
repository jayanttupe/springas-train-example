package com.skynet.spms.action.stockServiceBusiness.containerBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.containerBusiness.ContainerManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.containerManage.Container;

/**
 * 描述：容器管理相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ContainerDatasourceAction implements DataSourceAction<Container>{
	@Autowired
	private ContainerManager containerManager;

	
	@Override
	public String[] getBindDsName() {
		return new String[]{"container_dataSource"};
	}

	/**
	 * 新增容器相关信息
	 * @param item
	 */
	@Override
	public void insert(Container item) {
		containerManager.saveContainer(item);
	}
	
	/**
	 * 更新容器相关信息
	 * @param newValues
	 * @param itemID
	 * @return 容器相关信息
	 */
	@Override
	public Container update(Map newValues, String itemID) {
		Container container = new Container();
		BeanPropUtil.fillEntityWithMap(container, newValues);
		return containerManager.saveContainer(container);
	}

	/**
	 * 删除容器相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		containerManager.deleteEntity(itemID,Container.class);
	}
	
	/**
	 * 查询容器相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 容器相关信息
	 */
	@Override
	public List<Container> doQuery(Map values,
			int startRow, int endRow) {
		return containerManager.getContainer(values, 0, -1);
	}

	/**
	 * 获取所有容器信息
	 * @param startRow
	 * @param endRow
	 * @return 容器信息
	 */
	@Override
	public List<Container> getList(int startRow, int endRow) {
		
		return containerManager.getContainer(null, 0, -1);
	}

}
