package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.PartSupplierPriceManager;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.PartSupplierPriceIndex;
import com.skynet.spms.web.form.PartSupplierPriceForm;

@Component
public class PartSupplierPriceDataSourceAction implements DataSourceAction<PartSupplierPriceForm> {
	

	private Logger log=LoggerFactory.getLogger(PartSupplierPriceDataSourceAction.class);
	
	@Autowired
	PartSupplierPriceManager partSupplierPriceManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"partSupplierPrice_dataSource"}; 
	}

	@Override
	public void insert(PartSupplierPriceForm item) {
		log.info("===============PartSaleReleaseDataSourceAction.insert()");
		log.info("id: " + item.getId());
		log.info("PartIndexId: " + item.getPartIndexId());
		log.info("SupplierCodeId: " + item.getSupplierCodeId());
		log.info("releaseVersion: " + item.getReleaseVersion());
		log.info("edition: " + item.getEdition());
		
		PartSupplierPriceIndex partSupplierPriceIndex = new PartSupplierPriceIndex();
		transfer(item,partSupplierPriceIndex);
		partSupplierPriceManager.insertCascade(partSupplierPriceIndex, item.getPartIndexId(),item.getSupplierCodeId());
		transfer(partSupplierPriceIndex,item);
		
		
	}
	
	private void transfer(PartSupplierPriceForm from,PartSupplierPriceIndex to){
		to.setReleaseVersion(from.getReleaseVersion());
		to.setRemark(from.getRemark());
		
	}
	private void transfer(PartSupplierPriceIndex from,PartSupplierPriceForm to){
		to.setId(from.getId());
		to.setPartIndexId(from.getM_PartIndex().getId());
		to.setSupplierCodeId(from.getM_SupplierCode().getId());
		to.setCreateBy(from.getCreateBy());
		to.setCreateDate(from.getCreateDate());
		to.setDeleted(from.isDeleted());
		to.setReleaseVersion(from.getReleaseVersion());
		to.setEdition(from.getEdition());
		to.setVersion(from.getVersion());
		to.setRemark(from.getRemark());
		to.setKeyword(from.getKeyword());
		to.setLockVersion(from.getLockVersion());
		
		to.setM_ChangeCode(from.getM_ChangeCode());
		to.setM_BussinessPublishStatusEntity(from.getM_BussinessPublishStatusEntity());
		to.setM_EditionsInformation(from.getM_EditionsInformation()); 
		to.setM_SupplierSalesPrice(from.getM_SupplierSalesPrice());
		to.setM_PartIndex(from.getM_PartIndex());
		to.setM_SupplierCode(from.getM_SupplierCode());
		
		
	}

	@Override
	public PartSupplierPriceForm update(Map<String, Object> newValues, String itemID) {
		log.info("===============PartSaleReleaseDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		PartSupplierPriceIndex psd = partSupplierPriceManager.updateCascade(newValues, itemID);
		PartSupplierPriceForm form = new PartSupplierPriceForm();
		transfer(psd,form);
		return form;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============PartSaleReleaseDataSourceAction.delete()");
		partSupplierPriceManager.deleteEntity(itemID, PartSupplierPriceIndex.class);
		
	} 

	
	@Override
	public List<PartSupplierPriceForm> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============PartSaleReleaseDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List criteria = (List)values.remove("criteria");
		List<PartSupplierPriceIndex> list = null;
		if(criteria != null){
			list = partSupplierPriceManager.queryFilter(criteria);
		}else{
			list = partSupplierPriceManager.queryByProps(values);
		}
		List<PartSupplierPriceForm> psrList = new ArrayList<PartSupplierPriceForm>();
		for(PartSupplierPriceIndex pr : list){
			PartSupplierPriceForm form = new PartSupplierPriceForm();
			transfer(pr,form);
			psrList.add(form);
		}
	
		return psrList;
	}

	@Override
	public List<PartSupplierPriceForm> getList(int startRow, int endRow) {
		log.info("===============PartSaleReleaseDataSourceAction.getList()");
		List<PartSupplierPriceIndex> list = partSupplierPriceManager.list(startRow, endRow, PartSupplierPriceIndex.class);
		List<PartSupplierPriceForm> psrList = new ArrayList<PartSupplierPriceForm>();
		for(PartSupplierPriceIndex pr : list){
			PartSupplierPriceForm form = new PartSupplierPriceForm();
			transfer(pr,form);
			psrList.add(form);
		}
		return psrList;	
	}
	
}
