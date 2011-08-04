package com.skynet.spms.web.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataInfoService;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.datasource.DataSourceAdapte;
import com.skynet.spms.datasource.DataSourceRoute;
import com.skynet.spms.datasource.annotation.DataSourceAdapteAnno;
import com.skynet.spms.datasource.entity.ViewDataInfo;

@Controller
@DataSourceAdapteAnno(name = "common")
//通用针对smartgwt的DataSource数据操作适配类
public class CommDataSourceAdapte implements DataSourceAdapte {
	private static final int PAGE_SIZE = 79;

	private Logger log = LoggerFactory.getLogger(CommDataSourceAdapte.class);

	@Autowired
	private DataSourceRoute actionRoute;

	@Autowired
	private DataInfoService entityInfoMang;

	
	private ViewDataInfo getViewDataInfo(DSRequest dsRequest){
		String reqDsName = dsRequest.getDataSourceName();

		ViewDataInfo dataInfo = getDataElem(reqDsName);
		String dsName = dataInfo.getMangName();
		BeanPropUtil.setDsName(dsName);
		return dataInfo;
	}
	
	private DataSourceAction getDataSourceAction(ViewDataInfo dataInfo){
		
		DataSourceAction service = actionRoute.getDataSourceAction(dataInfo
				.getMangName(), dataInfo.getViewFormCls());
		return service;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(DSRequest dsRequest,DSResponse dsResponse) {
		
		ViewDataInfo dataInfo=getViewDataInfo(dsRequest);
		@SuppressWarnings("rawtypes")
		DataSourceAction service=getDataSourceAction(dataInfo);

		// DataSource protocol: get primary keys for update (itemID field in
		// this case)
		String pkName = dataInfo.getPkName();
		
		String itemID = (String) dsRequest.getFieldValue(pkName);
		// DataSource protocol: get new values to be saved
		@SuppressWarnings("rawtypes")
		Map newValues = dsRequest.getValues();
		if (newValues.isEmpty()) {
			dsResponse.setData(dsRequest.getCriteria());
			return;
		}
		
		Object item = service.update(dataInfo.getClearDataMap(newValues), itemID);

		// DataSource protocol: return the committed bean to the client for
		// cache update
		dsResponse.setData(dataInfo.getDataMap(item));
	}

	@Override
	public void insert(DSRequest dsRequest, DSResponse dsResponse) {
		

		ViewDataInfo dataInfo=getViewDataInfo(dsRequest);
		DataSourceAction service=getDataSourceAction(dataInfo);

		// DataSource protocol: get new values to be saved
		Map newValues = dsRequest.getValues();
		if (newValues.isEmpty()) {
			dsResponse.setData(dsRequest.getCriteria());
			return;
		}		
		
		Object item = BeanPropUtil.generEntityByMap(dataInfo.getClearDataMap(newValues),
				dataInfo.getViewFormCls());
				
		service.insert(item);

		// DataSource protocol: return the committed item bean to the client for
		// cache update
		dsResponse.setData(dataInfo.getDataMap(item));
	}

	@Override
	public  void fetch(DSRequest dsRequest, DSResponse dsResponse)  {
		// DataSource protocol: get filter criteria


		ViewDataInfo dataInfo=getViewDataInfo(dsRequest);
		DataSourceAction service=getDataSourceAction(dataInfo);
		
		log.debug("val:" + dsRequest.getValues());

		// DataSource protocol: get requested row range
		long startRow = dsRequest.getStartRow();
		long endRow = dsRequest.getEndRow();
		log.info("start:" + startRow + " end:" + endRow);
		List matchingItems = null;
		if (dsRequest.getValues().isEmpty()) {
			matchingItems = service.getList((int) startRow, (int) endRow);
		} else {
			Map<String,Object> map=dataInfo.getClearDataMap(dsRequest.getValues());
			// bean storage specific: lookup matching item beans
			matchingItems = service.doQuery(map,
					(int) startRow, (int) endRow);
		}
		if(matchingItems!=null){
			log.info("get record list:" + matchingItems.size());

			List<Map> resultList = new ArrayList<Map>();
			for (Object obj : matchingItems) {
				resultList.add(dataInfo.getDataMap(obj));
			}
			dsResponse.setData(resultList);
			// tell client what rows are being returned, and what's available
			dsResponse.setStartRow(startRow);
			dsResponse.setEndRow(startRow+matchingItems.size());
			
			long totalNow=startRow+matchingItems.size();
			if(totalNow>=endRow){
				totalNow+=PAGE_SIZE;
			}
			dsResponse.setTotalRows(totalNow);
		}
		

	}



	@Override
	public void delete(DSRequest dsRequest, DSResponse dsResponse) {

		ViewDataInfo dataInfo=getViewDataInfo(dsRequest);
		
		DataSourceAction service=getDataSourceAction(dataInfo);
		
		String pkName = dataInfo.getPkName();

		String itemID = (String) dsRequest.getFieldValue(pkName);
		if (StringUtils.isNotBlank(itemID)) {
			service.delete(itemID);
		}
		dsResponse.setData(dsRequest.getCriteria());

	}

	@Override
	public void custom(DSRequest dsRequest, DSResponse dsResponse,String customType) {

		dsResponse.setFailure();
		dsResponse.setData("Unknown operationType: " + customType);
	}
	
	private ViewDataInfo getDataElem(String fullDsName) {

		log.info("req ds name:" + fullDsName);
		String ruleName = GwtActionHelper.getCurrRule();

		String modName = StringUtils.substringBeforeLast(fullDsName, ".");

		String dsName = StringUtils.substringAfterLast(fullDsName, ".");

		return entityInfoMang.generDataInfo(ruleName, modName, dsName);

	}

}
