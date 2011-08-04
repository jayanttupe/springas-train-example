package com.skynet.spms.client.feature.data;


import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.service.TableInfoService;
import com.skynet.spms.client.service.TableInfoServiceAsync;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.rpc.RPCManager;

public class DataSourceTool {
	
	private Logger log = Logger.getLogger("Data Source");

	private TableInfoServiceAsync service = GWT.create(TableInfoService.class);
	
	

	public void onCreateDataSourceWithoutI18n(String moduleName,String dsName,final PostDataSourceInit initOper){
		
		service.getFieldListWithouti18n(moduleName, dsName,CreateWithFieldList(initOper));

	}
	
	public void onCreateSimpleDataSource(String className,String dsName,final PostDataSourceInit initOper){
		service.getFieldListByClsName(className, 
				CreateWithSimpDs(initOper,dsName));

	}
	
	public void onInitDataSource(String moduleName,String dsName,final PostDataSourceInit initOper){
		
		service.getFieldList(moduleName, dsName,CreateWithoutFieldList(initOper));
		
	}
	
	public void onCreateDataSource(String moduleName,String dsName,final PostDataSourceInit initOper){
		service.getFieldList(moduleName,dsName, CreateWithFieldList(initOper));
	}

	public void onCreateDataSource(String moduleName,final PostDataSourceInit initOper){
		service.getFieldList(moduleName, CreateWithFieldList(initOper));
	}

	
	private AsyncCallback<DataInfo> CreateWithoutFieldList(
			final PostDataSourceInit initOper){
		return new AsyncCallback<DataInfo>(){

			@Override
			public void onFailure(Throwable caught) {
				log.log(Level.WARNING,caught.getMessage());
				
			}

			@Override
			public void onSuccess(DataInfo result) {
				DataSource dataSource = new DataSource();
			
				String url=GWT.getModuleBaseURL() + "ds/common.ds";
				dataSource.setDataURL(url);				
				dataSource.setID(result.getDsDefineName());
				
				log.info("dataSource url:"+url+" id:"+dataSource.getID());
								
				initOper.doPostOper(dataSource,result);
			}
			
		};
		
		
	}
 	private AsyncCallback<DataInfo> CreateWithFieldList(
			final PostDataSourceInit initOper) {
		return new AsyncCallback<DataInfo>(){

			@Override
			public void onFailure(Throwable caught) {
				log.log(Level.WARNING,caught.getMessage());
				
			}

			@Override
			public void onSuccess(DataInfo result) {
				DataSource dataSource = new DataSource();

				String url=GWT.getModuleBaseURL() + "ds/common.ds";
				dataSource.setDataURL(url);				
				dataSource.setID(result.getDsDefineName());
				
				log.info("dataSource url:"+url+" id:"+dataSource.getID());
							
				for(DataSourceField field:result.getDsFieldList()){
					dataSource.addField(field);
				}
				initOper.doPostOper(dataSource,result);
			}
			
		};
	}
 	
 	public DataSource  getDataSource(String dsName){
 		
		DataSource dataSource = new DataSource();

 		String url=GWT.getModuleBaseURL() + "ds/common.ds";
 		
		dataSource.setDataURL(url);				
		dataSource.setID(dsName);
		
		return dataSource;
 	}
 	
 	public DataSource createDataSource(String dsName){
 		DataSource dataSource=DataSource.getDataSource(dsName);
 		
 		return dataSource;
 	}
	
	private AsyncCallback<DataInfo> CreateWithSimpDs(
			final PostDataSourceInit initOper,final String dsName) {
		return new AsyncCallback<DataInfo>(){

			@Override
			public void onFailure(Throwable caught) {
				log.log(Level.WARNING,caught.getMessage());
				
			}

			@Override
			public void onSuccess(DataInfo result) {
				DataSource dataSource = new DataSource();

				String url=GWT.getModuleBaseURL() + "ds/simple.ds";
				dataSource.setDataURL(url);				
				dataSource.setID(dsName);
				
				log.info("dataSource url:"+url+" id:"+dataSource.getID());
				for(DataSourceField field:result.getDsFieldList()){
					dataSource.addField(field);
				}			
				
				initOper.doPostOper(dataSource,result);
			}
			
		};
	}
	
	
	public interface PostDataSourceInit {

		public void doPostOper(DataSource dataSource,DataInfo dataInfo);
		
	}
	
}
