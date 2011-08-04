package com.skynet.spms.service;

import java.io.InputStream;

public interface ReportService {
	
	public String getHtmlReport(String raqName) throws ReportException;
	
	public byte[] getPdfReport(String raqName)throws ReportException;

}
