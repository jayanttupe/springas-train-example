package com.skynet.common.datadictory;

import java.util.Locale;

public interface DataDictionaryManager {
	
//	String[][] getDisplayDataDictoryByName(String ddName,Locale locale);
//	
//	DataDictEntity getDataDictoryByName(String ddName,String key,Locale locale);

	String[][] getDisplayDataArrayByName(String ddName);
	
	DataDictEntity getDataDictMapByName(String ddName,String key);

	
}
