package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.PartIndexManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
import com.skynet.spms.web.form.PartIndexForm;

@Component
public class PartIndexDataSourceAction implements DataSourceAction<PartIndexForm> {
	

	private Logger log=LoggerFactory.getLogger(PartIndexDataSourceAction.class);
	
	@Autowired
	PartIndexManager partIndexManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"partIndex_dataSource"}; 
	}

	@Override
	public void insert(PartIndexForm item) {
		log.info("===============PartIndexDataSourceAction.insert()");
		log.info("ManufacturerCodeId: " + item.getManufacturerCodeId());
		PartIndex partIndex = transfer(item);
		
		partIndexManager.insertCascade(partIndex,item.getManufacturerCodeId());
		
		transfer(partIndex, item);
		
	}

	@Override
	public PartIndexForm update(Map<String, Object> newValues, String itemID) {
		log.info("===============PartIndexDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		log.info(itemID);
		PartIndex partIndex = partIndexManager.updateCascade(newValues, itemID);
		PartIndexForm form = new PartIndexForm();
		transfer(partIndex, form);
		log.info("===============PartIndexDataSourceAction.end");
		return form;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============PartIndexDataSourceAction.delete()");
		partIndexManager.deleteEntity(itemID, PartIndex.class);
		
	} 

	@Override
	public List<PartIndexForm> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============PartIndexDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List criteria = (List)values.remove("criteria");
		List<PartIndex> list = new ArrayList<PartIndex>();
		if(criteria != null){
			list = partIndexManager.queryFilter(criteria,startRow,endRow);
		}else{
			list = partIndexManager.queryByProps(values,startRow,endRow);
		}
		List<PartIndexForm> formList = new ArrayList<PartIndexForm>();
		for(PartIndex pi : list){
			PartIndexForm form = new PartIndexForm();
			transfer(pi, form);
			formList.add(form);
		}
		return formList;
	}

	@Override
	public List<PartIndexForm> getList(int startRow, int endRow) {
		log.info("===============PartIndexDataSourceAction.getList()");
		List<PartIndex> list = partIndexManager.list(startRow, endRow, PartIndex.class);
		List<PartIndexForm> formList = new ArrayList<PartIndexForm>();
		for(PartIndex pi : list){
			PartIndexForm form = new PartIndexForm();
			transfer(pi, form);
			formList.add(form);
		}
		return formList;
	}
	
	private void transfer(PartIndex partIndex,PartIndexForm form){
	
		form.setId(partIndex.getId());
		form.setCreateBy(partIndex.getCreateBy());
		form.setCreateDate(partIndex.getCreateDate());
		form.setDeleted(partIndex.isDeleted());
		form.setKeyword(partIndex.getKeyword());
		form.setLockVersion(partIndex.getLockVersion());
		form.setM_BasicInformation(partIndex.getM_BasicInformation());
		form.setM_BussinessPublishStatusEntity(partIndex.getM_BussinessPublishStatusEntity());
		form.setM_CustomsClearanceData(partIndex.getM_CustomsClearanceData());
		//form.setM_FinanceData(partIndex.getM_FinanceData());
		//form.setM_GeneralOperateLogEntity(partIndex.getM_GeneralOperateLogEntity());
		form.setM_ManufacturerCode(partIndex.getM_ManufacturerCode());
		form.setM_OptionalPart(partIndex.getM_OptionalPart());
		form.setM_PartApplicationData(partIndex.getM_PartApplicationData());
		form.setM_PartTechnicalData(partIndex.getM_PartTechnicalData());
		if(partIndex.getM_ManufacturerCode()!= null){
			form.setManufacturerCodeId(partIndex.getM_ManufacturerCode().getId());
		}
		
		form.setManufacturerPartNumber(partIndex.getManufacturerPartNumber());
		form.setOverlengthPartNumber(partIndex.getOverlengthPartNumber());
		form.setUniqueComponentIdentificationNumber(partIndex.getUniqueComponentIdentificationNumber());
		form.setPartNumber(partIndex.getPartNumber());
		form.setKeyword_zh(partIndex.getKeyword_zh());
		form.setKeyword_en(partIndex.getKeyword_en());
		form.setVersion(partIndex.getVersion());
	}
	
	private PartIndex transfer(PartIndexForm form){
		PartIndex partIndex = new PartIndex();
		partIndex.setManufacturerPartNumber(form.getManufacturerPartNumber());
		partIndex.setOverlengthPartNumber(form.getOverlengthPartNumber());
		partIndex.setUniqueComponentIdentificationNumber(form.getUniqueComponentIdentificationNumber());
		partIndex.setPartNumber(form.getPartNumber());
		partIndex.setKeyword_zh(form.getKeyword_zh());
		partIndex.setKeyword_en(form.getKeyword_en());
		
		return partIndex;
	}
	
}
