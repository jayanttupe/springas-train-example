package com.skynet.spms.web.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.datasource.DataSourceAdapte;
import com.skynet.spms.datasource.DataSourceRoute;
import com.skynet.spms.datasource.EntityInfoService;
import com.skynet.spms.datasource.annotation.DataSourceAdapteAnno;
import com.skynet.spms.datasource.entity.EntityMetaInfo;
import com.skynet.spms.datasource.entity.FieldMetaInfo;
import com.skynet.spms.datasource.entity.ViewDataInfo;

@Controller
@DataSourceAdapteAnno(name = "simple")
public class SimpleDataSourceAdapte implements DataSourceAdapte{

	private static final int PAGE_SIZE = 79;

	private Logger log = LoggerFactory.getLogger(SimpleDataSourceAdapte.class);

	@Autowired
	private DataSourceRoute actionRoute;
	
	@Autowired
	private EntityInfoService entityInfoService;
	
	
	@Override
	public void fetch(DSRequest dsRequest, DSResponse dsResponse) {
		// DataSource protocol: get filter criteria


		String dsName=dsRequest.getDataSourceName();
		
		DataSourceAction service=actionRoute.getDataSourceAction(dsName);
		
		log.debug("val:" + dsRequest.getValues());

		// DataSource protocol: get requested row range
		long startRow = dsRequest.getStartRow();
		long endRow = dsRequest.getEndRow();
		log.info("start:" + startRow + " end:" + endRow);
		List matchingItems = null;
		if (dsRequest.getValues().isEmpty()) {
			matchingItems = service.getList((int) startRow, (int) endRow);
		} else {
			// bean storage specific: lookup matching item beans
			matchingItems = service.doQuery(dsRequest.getValues(),
					(int) startRow, (int) endRow);
		}

		log.info("get record list:" + matchingItems.size());

//		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//		for(Object obj:matchingItems){
//			list.add(getDataMap(obj));
//		}
		dsResponse.setData(matchingItems);
		// tell client what rows are being returned, and what's available
		dsResponse.setStartRow(startRow);
		dsResponse.setEndRow(matchingItems.size()+startRow);
		long totalNow=startRow+matchingItems.size();
		if(totalNow>=endRow){
			totalNow+=PAGE_SIZE;
		}
		dsResponse.setTotalRows(totalNow);
	}
	
//	private Map getDataMap(Object entity)  {
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//
//		EntityMetaInfo entityInfo=entityInfoService.getEntityInfoByClsName(entity.getClass().getName());
//		
//		for (FieldMetaInfo field : entityInfo.getFieldSet()) {
//
//			String name = field.getName();
//			try {
//				Object val = PropertyUtils.getProperty(entity, name);
//				dataMap.put(name, val);
//			} catch (Exception e) {
//				log.error("field read fail:" + name);
//			}
//
//		}
//		return dataMap;
//	}

	@Override
	public void insert(DSRequest dsRequest, DSResponse dsResponse) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void update(DSRequest dsRequest, DSResponse dsResponse) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void delete(DSRequest dsRequest, DSResponse dsResponse) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void custom(DSRequest dsRequest, DSResponse dsResponse,
			String customType) {
		throw new UnsupportedOperationException();
		
	}



}
