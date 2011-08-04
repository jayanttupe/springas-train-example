package com.skynet.spms.action.finance;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.finance.ClassificationItemManager;
import com.skynet.spms.manager.finance.PurposeVoucherPayManager;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.ClassificationItem;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.PurposeVoucher;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.Subjects;
import com.skynet.spms.persistence.entity.spmsdd.PurposeVoucherType;
import com.skynet.spms.service.UUIDGeneral;
import com.skynet.spms.tools.OneToManyTools;

@Component
public class PurposeVoucherPayAction implements DataSourceAction<PurposeVoucher> {


	private Logger log = LoggerFactory.getLogger(PurposeVoucherPayAction.class);

	@Autowired
	private PurposeVoucherPayManager purposeVoucherManager;
	
	@Autowired
	private ClassificationItemManager classificationItemManager;
	
	@Autowired
	private UUIDGeneral uuidGeneral;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[] { "finance_payVoucher_dataSource" };
	}

	@Override
	public void insert(PurposeVoucher item) {
		// TODO Auto-generated method stub

		List<ClassificationItem> classificationItemList = null;
		for(Map map : item.getClassificMapItems()){
			String subject = (String)map.get("subject");
			Subjects sub = classificationItemManager.querySubjectsById(subject);
			map.put("subject", sub);
		}
		if (item.getClassificMapItems() != null) {
			classificationItemList = OneToManyTools.ConvertToEntity(
					item.getClassificMapItems(), ClassificationItem.class);
			item.setClassificationItem(classificationItemList);
		}

		item.setCreateCertificateUser(GwtActionHelper.getCurrUser());
		log.info(""+item.getM_VoucherWord());
		if(item.getM_VoucherWord().toString().equals("pay"))
		{
			item.setCertificateNumber(uuidGeneral.getSequence("PZP"));
			item.setPurposeVoucherType(PurposeVoucherType.pay);
		}	
		else if(item.getM_VoucherWord().toString().equals("gathering")){
			item.setCertificateNumber(uuidGeneral.getSequence("PZG"));
			item.setPurposeVoucherType(PurposeVoucherType.gathering);
		}
		
		purposeVoucherManager.insertEntity(item);

	}

	@Override
	public PurposeVoucher update(Map<String, Object> newValues, String itemID) {
		// TODO Auto-generated method stub
		
	
		PurposeVoucher purposeVoucher = purposeVoucherManager.queryById(itemID, PurposeVoucher.class);
		  List listItems = (List)newValues.get("classificationItem");
	        for(Object item:listItems){
	        	Map map = (Map)item;
				String subject = (String)map.get("subject");
				Subjects sub = classificationItemManager.querySubjectsByName(subject);
				map.put("subject", sub);
	        }
		BeanPropUtil.fillEntityWithMap(purposeVoucher, newValues);
		List<ClassificationItem> newClassificationItemList = OneToManyTools
				.ConvertToEntity(purposeVoucher.getClassificationItem(),
						ClassificationItem.class);	
		purposeVoucher.setClassificationItem(newClassificationItemList);		
		return purposeVoucherManager.saveOrUpdate(purposeVoucher);
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		purposeVoucherManager.deleteEntity(itemID, PurposeVoucher.class);
	}

	@Override
	public List<PurposeVoucher> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		
		// TODO Auto-generated method stub
		log.info("mode===================="+(String)values.get("mode"));

		if(values.get("mode")==null||"".equals(values.get("mode"))){
			List criList = (List)values.get("criteria");
			if(criList!=null&&criList.size()!=0){
	        	return purposeVoucherManager.queryByCriteriaList(criList);
	        }
		}
			
		return purposeVoucherManager.listPurposeVouchersByMode((String)values.get("mode"));
	}

	@Override
	public List<PurposeVoucher> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return purposeVoucherManager.listAllPurposeVouchers();
	}
}
