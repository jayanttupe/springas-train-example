package com.skynet.spms.service.imp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runqian.report4.model.ReportDefine;
import com.runqian.report4.model.engine.ExtCellSet;
import com.runqian.report4.usermodel.Context;
import com.runqian.report4.usermodel.Engine;
import com.runqian.report4.usermodel.IReport;
import com.runqian.report4.usermodel.PrintSetup;
import com.runqian.report4.util.ReportUtils;
import com.runqian.report4.view.html.HtmlReport;
import com.skynet.spms.service.ReportException;
import com.skynet.spms.service.ReportService;
import com.view.pdf.PdfReport;

/*@Service
@Transactional*/
public class ReportServiceImple implements ReportService{

	
	@Autowired
	private DataSource dataSource;
	
	@Value("${report.license.path}")
	private String licPath;
	
	@Value("${report.raq.path}")
	private String raqPath;
	
	@Autowired
	private ResourceLoader loader;
	
	@PostConstruct
	public void init() throws IOException{
		
		File file=loader.getResource(licPath).getFile();
		
		ExtCellSet.setLicenseFileName(file.getAbsolutePath());
		
	}
	
	private IReport generReport(String raqName) {
		
		if(!raqName.endsWith(".raq")){
			raqName+=".raq";
		}
		String location=raqPath+raqName; 
		ReportDefine define;
		try {
			define = (ReportDefine) ReportUtils.read(loader.getResource(location).getInputStream());
		} catch (IOException e) {
			throw new ReportException(e);
		} catch (Exception e) {
			throw new ReportException(e);
		}
		
		Context context=new Context();
		
		try{
			context.setConnection("spms", dataSource.getConnection());
		}catch(SQLException e){
			throw new ReportException(e);
		}
		
		
		Engine engine=new Engine(define,context);
		
		IReport report=engine.calc();
		
		return report;
	}
	
	private PrintSetup getDefaultPrint(){
		PrintSetup print=new PrintSetup();
		print.setPaperHeight(500.0f);
		return print;
	}
	@Override
	public String getHtmlReport(String raqName) {
		
		IReport report=generReport(raqName);
		
		PrintSetup setup=getDefaultPrint();
		report.setPrintSetup(setup);
		try{
			HtmlReport html=new HtmlReport(report, "spms");
		
			String result=html.generateHtml();
			
			return result;
		}catch(Exception e){
			e.printStackTrace();
			throw new ReportException(e);
		}
	}

	@Override
	public byte[] getPdfReport(String raqName) {

		IReport report=generReport(raqName);
		
		report.setPrintSetup(getDefaultPrint());
		
		ByteArrayOutputStream stream=new ByteArrayOutputStream();
		
		try{
			PdfReport pdf=new PdfReport(stream);
			pdf.export(report);			
			pdf.save();
			
		}catch(Throwable e){
			
			throw new ReportException(e);
		}
		
		return stream.toByteArray();
		
	}

}
