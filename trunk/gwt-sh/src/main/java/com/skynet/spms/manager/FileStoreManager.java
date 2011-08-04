package com.skynet.spms.manager;

import org.apache.commons.fileupload.FileItem;

import com.skynet.spms.persistence.entity.base.Attachment;


public interface FileStoreManager {


	public Attachment saveFileInfo(FileItem fileInfo,String fileName);

	public Attachment getFileStoreInfo(String fileID);

}