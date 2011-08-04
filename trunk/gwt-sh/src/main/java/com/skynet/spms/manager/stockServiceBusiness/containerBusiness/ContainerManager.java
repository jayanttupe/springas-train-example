package com.skynet.spms.manager.stockServiceBusiness.containerBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.containerManage.Container;

/**
 * 容器信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ContainerManager extends CommonManager<Container>{
	
	/**
	 * 保存容器信息
	 * @param container
	 * @return 容器信息
	 */
	public Container saveContainer(Container container);
	
	/**
	 * 获取容器信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 容器信息
	 */ 
	public List<Container> getContainer(Map values, int startRow, int endRow);

}

