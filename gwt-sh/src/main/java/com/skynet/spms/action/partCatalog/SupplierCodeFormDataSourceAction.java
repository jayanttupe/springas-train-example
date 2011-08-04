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
import com.skynet.spms.persistence.code.SupplierCode;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.DepartmentInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.manufacturers.Manufacturer;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.suppliers.Supplier;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.CustomsClearanceData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.financeData.FinanceData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.technicalData.PartTechnicalData;
import com.skynet.spms.web.form.ManufacturerCodeForm;
import com.skynet.spms.web.form.SupplierCodeForm;

@Component
public class SupplierCodeFormDataSourceAction implements DataSourceAction<SupplierCodeForm> {
	

	private Logger log=LoggerFactory.getLogger(SupplierCodeFormDataSourceAction.class);
	
	@Autowired
	EnterpriseManager enterpriseManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"supplierCodeForm_dataSource"}; 
	}
	
	@Override
	public void insert(SupplierCodeForm item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SupplierCodeForm update(Map<String, Object> newValues,
			String itemID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SupplierCodeForm> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SupplierCodeForm> getList(int startRow, int endRow) {
		Class cls = Supplier.class;
		List<SupplierCodeForm> scfList = new ArrayList<SupplierCodeForm>();
		List<BaseEnterpriseInformation> eiList =enterpriseManager.list(startRow, endRow, cls);
		for(BaseEnterpriseInformation ei : eiList){	
			Supplier s = (Supplier)ei;
			SupplierCodeForm scForm = new SupplierCodeForm();
			scForm.setSupplierCodeId(s.getM_SupplierCode().getId());
			scForm.setSupplierCode(s.getM_SupplierCode().getCode());
			scForm.setSupplierName(s.getAbbreviation());
			log.info("SupplierCodeId : "+scForm.getSupplierCodeId());
			log.info("SupplierCode : " + scForm.getSupplierCode());
			log.info("SupplierName : " + scForm.getSupplierName());
			scfList.add(scForm);
		}
		return scfList;
	}

	

}
