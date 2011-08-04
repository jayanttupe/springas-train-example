package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.DepartmentManager;
import com.skynet.spms.manager.organization.EnterpriseManager;
import com.skynet.spms.manager.partCatalog.PartIndexManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.DepartmentInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.manufacturers.Manufacturer;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.CustomsClearanceData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.financeData.FinanceData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.technicalData.PartTechnicalData;
import com.skynet.spms.web.form.ManufacturerCodeForm;

@Component
public class ManufacturerCodeFormDataSourceAction implements DataSourceAction<ManufacturerCodeForm> {
	

	private Logger log=LoggerFactory.getLogger(ManufacturerCodeFormDataSourceAction.class);
	
	@Autowired
	EnterpriseManager enterpriseManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"manufacturerCodeForm_dataSource"}; 
	}

	@Override
	public void insert(ManufacturerCodeForm item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ManufacturerCodeForm update(Map<String, Object> newValues,
			String itemID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ManufacturerCodeForm> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("===============ManufacturerCodeFormDataSourceAction.doQuery");
		return getList(startRow,endRow);
	}

	@Override
	public List<ManufacturerCodeForm> getList(int startRow, int endRow) {
		log.info("===============ManufacturerCodeFormDataSourceAction.getList");
		Class cls = Manufacturer.class;
		List<ManufacturerCodeForm> mcfList = new ArrayList<ManufacturerCodeForm>();
		List<BaseEnterpriseInformation> eiList =enterpriseManager.list(startRow, endRow, cls);
		for(BaseEnterpriseInformation ei : eiList){
			Manufacturer m = (Manufacturer)ei;
			ManufacturerCodeForm mform = new ManufacturerCodeForm();
			mform.setManufacturerCodeId(m.getM_ManufacturerCode().getId());
			mform.setManufacturerCode(m.getM_ManufacturerCode().getCode());
			mform.setManufacturerName(m.getAbbreviation());
			
			log.info("ManufacturerCodeId : "+mform.getManufacturerCodeId());
			log.info("ManufacturerCode : " + mform.getManufacturerCode());
			log.info("ManufacturerName : " + mform.getManufacturerName());
			mcfList.add(mform);
		}
		return mcfList;
	}

	

}
