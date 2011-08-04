package com.skynet.spms.manager;

import org.hibernate.Session;

public interface GenerateNumberManager{

	 public String GenerateNumber(Session session, String strTableName, String strColumnName, String strMark);
	
}
