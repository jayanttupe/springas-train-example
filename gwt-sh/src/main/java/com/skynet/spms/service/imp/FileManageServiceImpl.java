package com.skynet.spms.service.imp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.skynet.spms.schedule.ScheduledJob;
import com.skynet.spms.service.FileManageService;
import com.skynet.spms.web.control.FileOperate;

@Component
public class FileManageServiceImpl implements FileManageService {

	@Value("${manualFile.scanUrl}")
	private String scanPath;

	@Value("${manualFile.scanRootUrl}")
	private String scanRootPath;
	
	@Value("${manualFile.credentialsUrl}")
	private String credentialPath;

	@Value("${manualFile.credentials2Url}")
	private String credential2Path;

	// 设置定时器,每30分钟运行一次 cron
	// http://blog.csdn.net/ja_II_ck/article/details/6441830
	@ScheduledJob(jobName = "FileManagement", cron = "0 0/30 0-23 * * ?")
	public void FileOperate() throws IOException {
		// 创建主目录
		String folderPath = credentialPath;
		FileOperate fileOperate = new FileOperate();
		fileOperate.newFolder(folderPath);

		// 获取系统时间，转换为yyyyMM格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String datestr = sdf.format(new Date());
		System.out.println("现在的年月为" + datestr);

		// 在主目录下生成相应的年份文件夹
		String sFileName = new String(credential2Path);
		sFileName += datestr;
		FileOperate fileOperate123 = new FileOperate();
		fileOperate123.newFolder(sFileName);
		System.out.println("年份文件夹为" + sFileName);

		
		
		//如果没有这个目录则进行创建
		FileOperate fileOperate1234=new FileOperate();
		fileOperate1234.newFolder(scanRootPath);
		FileOperate fileOperate12345=new FileOperate();
		fileOperate12345.newFolder(scanPath);
		// 获取扫描仪目录
		File file = new File(scanPath);
		System.out.println("扫描仪目录为" + file);
		File dest = new File(sFileName);
		FileOperate.traversal(file, dest);

	}

}
