package com.skynet.spms.web.servlet;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.service.FileService;

public class FileUploadServlet extends UploadAction {

	private Logger log = LoggerFactory.getLogger(FileUploadServlet.class);

	private static final long serialVersionUID = 1L;
	
//	private Map<String,String> fileMap=new ConcurrentHashMap<String,String>();

	private ApplicationContext getContext() {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(super.getServletContext());
		return ctx;
	}

	/**
	 * Override executeAction to save the received files in a custom place and
	 * delete this items from session.
	 */
	@Override
	public String executeAction(HttpServletRequest request,
			List<FileItem> sessionFiles) throws UploadActionException {

		List<Map<String,Object>> fileInfos=new ArrayList<Map<String,Object>>();
		
		for (FileItem item : sessionFiles) {
			if (item.isFormField()) {
				continue;
			}
			try {							// / Create a new file based on the remote file name in the
				// client
				
				Attachment file = getFileService().saveFile(item);
								
				fileInfos.add(file.getJsonFileInfo());
			} catch (Exception e) {
				throw new UploadActionException(e);
			}
			
		}

		// / Remove files from session because we have a copy of them
		removeSessionFileItems(request);
		
		// / Send information of the received files to the client.
		return JSONValue.toJSONString(fileInfos);
	}

	private FileService getFileService() {
		FileService fileService = getContext().getBean(FileService.class);
		return fileService;
	}
	 

	/**
	 * Get the content of an uploaded file.
	 */
	@Override
	public void getUploadedFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		throw new UnsupportedOperationException();
//		String fieldName = request.getParameter(PARAM_SHOW);
//		String fileKey=fileMap.get(fieldName);
//		log.info("get file:" + fieldName);
//
//		try {
//			InputStream stream=getFileService().getFileStream(fileKey);
//			copyFromInputStreamToOutputStream(stream, response.getOutputStream());
//		} catch (FileNotFoundException e) {
//			renderXmlResponse(request, response, ERROR_ITEM_NOT_FOUND);
//		}	
	}

	/**
	 * Remove a file when the user sends a delete request.
	 */
	@Override
	public void removeItem(HttpServletRequest request, String fieldName)
			throws UploadActionException {
		throw new UnsupportedOperationException();
//		log.info("delete file:"+fieldName);
//		
//		String fileKey=fileMap.get(fieldName);
//		
//		getFileService().removeFile(fileKey);
	}
}