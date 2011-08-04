package com.skynet.spms.web.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.helpers.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.common.strtemplate.SimpleTmpTool;
import com.skynet.spms.client.service.AttachmentService;
import com.skynet.spms.manager.FileStoreManager;
import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.service.FileService;

@Controller
@GwtRpcEndPoint
public class FileDownloadCtrl implements AttachmentService{
	
	private Logger log=LoggerFactory.getLogger(FileDownloadCtrl.class);
	
	@Autowired
	private FileStoreManager fileManager;
	
	@Autowired
	private FileService fileService;
	
	
	@RequestMapping(value="/file/{fileID}",method = RequestMethod.GET)
	public void redirect(@PathVariable("fileID") String fileID ,HttpServletRequest request,HttpServletResponse  response) throws IOException{
		
		log.info("file:"+fileID);
		
		Attachment attach=fileManager.getFileStoreInfo(fileID);
		
		String fileName=attach.getFileName();
		
		String encodeFileName=URLEncoder.encode(fileName, "UTF-8");
//		String encodeFileName=fileName;
		String servletPath=request.getServletPath();
		
		String url=SimpleTmpTool.generByTmp(URL_TMP, fileID,encodeFileName,servletPath);
		
		log.info(url);

		response.setContentType("text/plain;charset=UTF-8");
		response.sendRedirect("../"+url);
		
		
	}
	
	private static String URL_TMP="file/${0}/name/${1}";
		
	
	@RequestMapping(value="/file/{fileID}/name/*",method = RequestMethod.GET)
	public void download(@PathVariable("fileID") String fileID,HttpServletResponse  response) {
		
		log.info("file id:"+fileID);
		
		try{
			InputStream stream=fileService.getFileStream(fileID);
			IOUtils.copy(stream, response.getOutputStream());
						
			Attachment fileInfo=fileManager.getFileStoreInfo(fileID);
			
			MediaType type=MediaType.APPLICATION_OCTET_STREAM;
			try{
				type=MediaType.parseMediaType(fileInfo.getFileType());			
				
			}catch(IllegalArgumentException  e){
				log.warn("invalid type:"+fileInfo.getFileType(),e);
			}
			
			
			
			String encodeFileName=URLEncoder.encode(fileInfo.getFileName().trim(),
					"UTF-8");
						

			String fullCtx=SimpleTmpTool.generByTmp(TMP, 
					encodeFileName,String.valueOf(fileInfo.getFileSize()));
			
			String fileType=type.toString();
			
			log.info("full info:"+fullCtx);
			log.info("context type:"+type.toString());
			response.setContentType(fileType);
			response.setContentLength(fileInfo.getFileSize());
			
//			response.addHeader("Content-Disposition",fullCtx);
			
		}catch(FileNotFoundException e){
			response.setStatus(404);
		} catch (IOException e) {
			response.setStatus(500);
		}
	}
	
	private static final String TMP="attachment; filename=${0}; size=${1}";


	@Override
	public void deleteFile(String fileIdx) {
		fileService.removeFile(fileIdx);
	}

}
