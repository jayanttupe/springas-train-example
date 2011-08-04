package com.skynet.spms.datasource;



import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;

//依据smartgwt的DataSource封装的数据操作方法，定义以下接口方法，可以在实现类中实现具体的方法
public interface DataSourceAdapte {

	void fetch(DSRequest dsRequest, DSResponse dsResponse);
	
	void insert(DSRequest dsRequest,DSResponse dsResponse);

	void update(DSRequest dsRequest,DSResponse dsResponse);

	void delete(DSRequest dsRequest,DSResponse dsResponse);
	
	void custom(DSRequest dsRequest,DSResponse dsResponse,String customType);
}
