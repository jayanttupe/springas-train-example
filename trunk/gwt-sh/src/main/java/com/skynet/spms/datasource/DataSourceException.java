package com.skynet.spms.datasource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.isomorphic.datasource.DSResponse;
import com.isomorphic.util.ErrorMessage;


public class DataSourceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2990070730522049287L;

	public DataSourceException(){
		super();
	}
	
	public DataSourceException(Exception e){
		super(e);
	}
	
	public DataSourceException(String msg){
		super(msg);
	}
	
	private int errorCode=-1;

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
	private Map<String,ErrorMessage> errorMap=new HashMap<String,ErrorMessage>();
	
	public void addError(String fieldName,String errorMsg){

		ErrorMessage errorMessage=new ErrorMessage();
		errorMessage.setErrorString(errorMsg);
		errorMap.put(fieldName,errorMessage);
	}
	
	
	public void fillErrorRecords(DSResponse response){
		
		for(Map.Entry<String, ErrorMessage> entry:errorMap.entrySet()){
			String fieldName=entry.getKey();
			ErrorMessage errorMessage=entry.getValue();
			response.addError(fieldName, errorMessage);
			
		}
		response.setProperty("error", this.getMessage());
		response.setStatus(errorCode);
	}
	
}
