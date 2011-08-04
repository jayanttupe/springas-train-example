package com.skynet.spms.manager.stockServiceBusiness.printCodeBusiness.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.printCodeBusiness.CargoSpace1DBarCodeManager;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.CargoSpaceManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.barCodeEntity.base1DBarCode.CargoSpace1DBarCode;

@Service
@Transactional
public class CargoSpace1DBarCodeManagerImpl extends CommonManagerImpl<CargoSpace1DBarCode> implements CargoSpace1DBarCodeManager{

	@Autowired
	private CargoSpaceManager cargoSpaceManager; 
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CargoSpace1DBarCode saveCargoSpace1DBarCode(CargoSpace1DBarCode cargoSpace1DBarCode) {
		Session session = getSession();
		cargoSpace1DBarCode.setCreateDate(new Date());
		session.saveOrUpdate(cargoSpace1DBarCode);		
		return cargoSpace1DBarCode;
	}

}
