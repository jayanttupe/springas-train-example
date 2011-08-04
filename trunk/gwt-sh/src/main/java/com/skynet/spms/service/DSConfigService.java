package com.skynet.spms.service;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

import com.isomorphic.datasource.DataSource;

public interface DSConfigService {


	String getCompleteDsXml(String dsName, String rule, Locale locale);

	String getCompleteDsJson(String dsName, String rule, Locale locale);
	
	DataSource getDataSourceByName(String dsName,String rule,Locale locale) throws Exception;

	String[] getDataSourceList();
}
