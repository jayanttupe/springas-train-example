package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.PartSaleReleaseManager;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.PartSaleRelease;
import com.skynet.spms.web.form.PartSaleReleaseForm;

@Component
public class PartSaleReleaseDataSourceAction implements DataSourceAction<PartSaleReleaseForm> {
	

	private Logger log=LoggerFactory.getLogger(PartSaleReleaseDataSourceAction.class);
	
	@Autowired
	PartSaleReleaseManager partSaleReleaseManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"partSaleRelease_dataSource"}; 
	}

	@Override
	public void insert(PartSaleReleaseForm item) {
		log.info("===============PartSaleReleaseDataSourceAction.insert()");
		log.info("id: " + item.getId());
		log.info("partindexid: " + item.getPartIndexId());
		log.info("releaseVersion: " + item.getReleaseVersion());
		log.info("edition: " + item.getEdition());
		log.info("m_ExchangeUnitAvailableIndicator: " + item.getM_ExchangeUnitAvailableIndicator());
		PartSaleRelease partSaleRelease = new PartSaleRelease();
		transfer(item,partSaleRelease);
		partSaleReleaseManager.insertCascade(partSaleRelease, item.getPartIndexId());
		transfer(partSaleRelease,item);
		
	}
	
	private void transfer(PartSaleReleaseForm from,PartSaleRelease to){
		to.setReleaseVersion(from.getReleaseVersion());
		to.setRemark(from.getRemark());
		to.setM_ExchangeUnitAvailableIndicator(from.getM_ExchangeUnitAvailableIndicator());
		
	}
	private void transfer(PartSaleRelease from,PartSaleReleaseForm to){
		to.setId(from.getId());
		to.setPartIndexId(from.getM_PartIndex().getId());
		to.setCreateBy(from.getCreateBy());
		to.setCreateDate(from.getCreateDate());
		to.setDeleted(from.isDeleted());
		to.setReleaseVersion(from.getReleaseVersion());
		to.setEdition(from.getEdition());
		to.setVersion(from.getVersion());
		to.setRemark(from.getRemark());
		to.setKeyword(from.getKeyword());
		to.setLockVersion(from.getLockVersion());
		to.setM_ExchangeUnitAvailableIndicator(from.getM_ExchangeUnitAvailableIndicator());
		to.setM_BussinessPublishStatusEntity(from.getM_BussinessPublishStatusEntity());
		to.setM_ChangeCode(from.getM_ChangeCode());
		to.setM_EditionsInformation(from.getM_EditionsInformation());
		to.setM_SalesPrice(from.getM_SalesPrice());
		to.setM_PartIndex(from.getM_PartIndex());
	}

	@Override
	public PartSaleReleaseForm update(Map<String, Object> newValues, String itemID) {
		log.info("===============PartSaleReleaseDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		PartSaleRelease pad = partSaleReleaseManager.updateCascade(newValues, itemID);
		PartSaleReleaseForm form = new PartSaleReleaseForm();
		transfer(pad,form);
		return form;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============PartSaleReleaseDataSourceAction.delete()");
		partSaleReleaseManager.deleteEntity(itemID, PartSaleRelease.class);
		
	} 

	
	@Override
	public List<PartSaleReleaseForm> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============PartSaleReleaseDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List criteria = (List)values.remove("criteria");
		List<PartSaleRelease> list = null;
		if(criteria != null){
			list = partSaleReleaseManager.queryFilter(criteria);
		}else{
			list = partSaleReleaseManager.queryByProps(values);
		}
		List<PartSaleReleaseForm> psrList = new ArrayList<PartSaleReleaseForm>();
		for(PartSaleRelease pr : list){
			PartSaleReleaseForm form = new PartSaleReleaseForm();
			transfer(pr,form);
			psrList.add(form);
		}
		return psrList;
	}
	
	@Override
	public List<PartSaleReleaseForm> getList(int startRow, int endRow) {
		log.info("===============PartSaleReleaseDataSourceAction.getList()");
		List<PartSaleRelease> list = partSaleReleaseManager.list(startRow, endRow, PartSaleRelease.class);
		List<PartSaleReleaseForm> psrList = new ArrayList<PartSaleReleaseForm>();
		for(PartSaleRelease pr : list){
			PartSaleReleaseForm form = new PartSaleReleaseForm();
			transfer(pr,form);
			psrList.add(form);
		}
		return psrList;	
	}
	
}
