package com.skynet.spms.manager.imp;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.FileStoreManager;
import com.skynet.spms.persistence.entity.base.Attachment;


@Service
@Transactional
public class FileStoreManagerImpl implements FileStoreManager {

	@Autowired
	private SessionFactory factory;
	
	
	
	@Override
	public Attachment saveFileInfo(FileItem fileInfo,String fileName) {
		Attachment attachment=new Attachment();
		attachment.setFileName(fileName);
		attachment.setFileSize((int)fileInfo.getSize());
		attachment.setFileType(fileInfo.getContentType());
		
		factory.getCurrentSession().save(attachment);
		
		return attachment;
	}

	@Override
	public Attachment getFileStoreInfo(String fileID) {
		return (Attachment) factory.getCurrentSession().get(Attachment.class, fileID);
		
	}

}
