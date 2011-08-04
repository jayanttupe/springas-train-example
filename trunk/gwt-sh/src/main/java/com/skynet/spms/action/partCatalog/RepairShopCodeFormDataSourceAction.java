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
import com.skynet.spms.persistence.entity.csdd.r.RepairShopCode;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.DepartmentInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.manufacturers.Manufacturer;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.repairAgencys.RepairAgency;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.CustomsClearanceData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.financeData.FinanceData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.technicalData.PartTechnicalData;
import com.skynet.spms.web.form.ManufacturerCodeForm;
import com.skynet.spms.web.form.RepairShopCodeForm;

@Component
public class RepairShopCodeFormDataSourceAction implements DataSourceAction<RepairShopCodeForm> {
	

	private Logger log=LoggerFactory.getLogger(RepairShopCodeFormDataSourceAction.class);
	
	@Autowired
	EnterpriseManager enterpriseManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"repairShopCodeForm_dataSource"}; 
	}

	@Override
	public void insert(RepairShopCodeForm item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RepairShopCodeForm update(Map<String, Object> newValues,
			String itemID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RepairShopCodeForm> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepairShopCodeForm> getList(int startRow, int endRow) {
		Class cls = RepairAgency.class;
		List<RepairShopCodeForm> mcfList = new ArrayList<RepairShopCodeForm>();
		List<BaseEnterpriseInformation> eiList =enterpriseManager.list(startRow, endRow, cls);
		for(BaseEnterpriseInformation ei : eiList){
			RepairAgency m = (RepairAgency)ei;
			RepairShopCodeForm mform = new RepairShopCodeForm();
			mform.setRepairShopCodeId(m.getM_RepairShopCode().getId());
			mform.setRepairShopCode(m.getM_RepairShopCode().getCode());
			mform.setRepairShopName(m.getAbbreviation());
			
			log.info("RepairShopCodeId : "+mform.getRepairShopCodeId());
			log.info("RepairShopCode : " + mform.getRepairShopCode());
			log.info("RepairShopName : " + mform.getRepairShopName());
			mcfList.add(mform);
		}
		return mcfList;
	}

	

}
