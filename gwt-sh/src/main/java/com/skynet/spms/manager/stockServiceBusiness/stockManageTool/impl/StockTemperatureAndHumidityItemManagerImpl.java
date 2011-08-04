package com.skynet.spms.manager.stockServiceBusiness.stockManageTool.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockManageTool.StockTemperatureAndHumidityItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.stockTemperatureAndHumidity.StockTemperatureAndHumidityItem;

@Service
@Transactional
public class StockTemperatureAndHumidityItemManagerImpl extends CommonManagerImpl<StockTemperatureAndHumidityItem> implements StockTemperatureAndHumidityItemManager{

}