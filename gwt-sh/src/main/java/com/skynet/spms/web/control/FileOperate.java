package com.skynet.spms.web.control;
import java.io.*;
import org.apache.commons.io.FileUtils;

public class FileOperate {
	public FileOperate() {
	}

	// 新建目录
	public void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			System.out.println("新建目录操作出错");
			e.printStackTrace();
		}
	}

	// 文件夹下面的所有文件
	public static void traversal(File file, File sFileName) { 
		File[] files = file.listFiles();  // 获取文件夹下面的所有文件
		for (File f : files) {
			try {
				FileUtils.moveFileToDirectory(f, sFileName, false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
