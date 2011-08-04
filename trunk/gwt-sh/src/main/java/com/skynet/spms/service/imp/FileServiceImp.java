package com.skynet.spms.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.skynet.spms.manager.FileStoreManager;
import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.service.FileService;

@Component
public class FileServiceImp implements FileService {
	
	private Logger log=LoggerFactory.getLogger(FileServiceImp.class);
	@Value("${file.store.basepath}")
	private String basePath;
	

	@Autowired
	private FileStoreManager fileManager;
	
	
	@Override
	public Attachment saveFile(FileItem fileItem) {

		String saveName = fileItem.getName().replaceAll(
				"[\\\\/><\\|\\s\"'{}()\\[\\]]+", "_");
		
		String fileName=StringEscapeUtils.unescapeHtml(saveName);

		log.info("saveName:" + fileName);

		Attachment fileEntity=fileManager.saveFileInfo(fileItem,fileName);
		String key=fileItem.getFieldName();
		key=StringUtils.substringBefore(key, "-");
		fileEntity.setRelatedBussinessId(key);
		
		File file=getFile(fileEntity.getId());
		InputStream stream=null;
		try{
			stream=fileItem.getInputStream();
			FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(fileItem.getInputStream()));
		}catch (IOException e) {
			throw new IllegalArgumentException(e);
		}finally{
			IOUtils.closeQuietly(stream);
		}
		
		return fileEntity;
	}
	
	
	
	private File getFile(String name){
		
		String seq = getSeqFromName(name); 
				
		String one=seq.substring(0,2);
		
		String two=seq.substring(2,4);
		
		String fileName=seq.substring(4);
		
		File path= new File(basePath+"/"+one+"/"+two);
		if(!path.exists()){
			path.mkdirs();
		}
		return new File(path,fileName);
		
	}



	private String getSeqFromName(String name) {
		//48808033 2e5b8285 012e 5b828f02  0003
		name=StringUtils.reverse(name);
				
		String start=StringUtils.substring(name,0,4);
		String midSeq=StringUtils.substring(name, 4, 12);
		String tail=StringUtils.substring(name,12,24);
		
		String seq=midSeq+start+tail;
		return seq;
	}
	
	/* (non-Javadoc)
	 * @see com.skynet.spms.service.imp.FileService#saveFile(java.lang.String, java.io.InputStream)
	 */
//	@Override
//	public String saveFile(String fileName,InputStream stream) {
//		
//		String subDir=new SimpleDateFormat("MM/dd/").format(new Date());
//		
//		File workPath=new File(basePath+"/"+subDir);
//				
//		try{
//			if(!workPath.exists()){
//				workPath.mkdirs();
//			}
//			File newFile=File.createTempFile("upload-", "."+fileName, workPath);
//
//			FileUtils.writeByteArrayToFile(newFile, IOUtils.toByteArray(stream));
//			
//			subDir=subDir.replaceAll("/", "_");
//			return subDir+newFile.getName();
//
//		} catch (IOException e) {
//			throw new IllegalArgumentException(e);
//		}finally{
//			IOUtils.closeQuietly(stream);
//		}
//		
//	}
//	
	@Override
	public InputStream getFileStream(String fileIndex) throws FileNotFoundException {
	
		return new FileInputStream(getFile(fileIndex));
	}
	
	@Override
	public void removeFile(String fileIndex){

		FileUtils.deleteQuietly(getFile(fileIndex));
		
	}

	
	

}
