package com.skynet.spms.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;

import com.skynet.spms.persistence.entity.base.Attachment;

public interface FileService {


	Attachment saveFile(FileItem fileItem);
	
	InputStream getFileStream(String fileIndex) throws FileNotFoundException;

	void removeFile(String fileIndex);

}