package com.skynet.spms.web.control;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.config.GlobalConfig;
import com.skynet.spms.config.Utils;
import com.skynet.spms.manager.attachment.IAttachmentManager;
import com.skynet.spms.persistence.entity.base.Attachment;

public class UploadServlet extends UploadAction {

	private static final long serialVersionUID = 1L;

	Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();

	Hashtable<String, File> receivedFiles = new Hashtable<String, File>();

	Hashtable<String, String> filesMap = new Hashtable<String, String>();
	
	public String executeAction(HttpServletRequest request,
			List<FileItem> sessionFiles) throws UploadActionException {
		// get the bussiness uuid
		String uuid = request.getParameter("uuid");
		int cont = 0;
		String response = "";
		
		for (FileItem item : sessionFiles) {
			if (false == item.isFormField()) {
				cont++;
				try {
					String tarPath = hashing(request);
					File file = new File(tarPath + "\\"
							+ Utils.renameFile(item.getName()));
					item.write(file);
					Attachment o = saveAttachement(uuid, cont, item);
					receivedFiles.put(item.getFieldName(), file);
					filesMap.put(item.getFieldName(), o.getId());
					receivedContentTypes.put(item.getFieldName(),
							item.getContentType());
					response += "<file-" + cont + "-field>"
							+ item.getFieldName() + "</file-" + cont
							+ "-field>\n";
					response += "<file-" + cont + "-name>" + item.getName()
							+ "</file-" + cont + "-name>\n";
					response += "<file-" + cont + "-size>" + item.getSize()
							+ "</file-" + cont + "-size>\n";
					response += "<file-" + cont + "-type>"
							+ item.getContentType() + "</file-" + cont
							+ "type>\n";
				} catch (Exception e) {
					throw new UploadActionException(e);
				}
			}
		}
		removeSessionFileItems(request);
		return "<response>\n" + response + "</response>\n";
	}

	private ApplicationContext getContext() {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(super.getServletContext());
		return ctx;
	}

	private IAttachmentManager getAttachmentManager() {
		IAttachmentManager attachmentManager = getContext().getBean(
				IAttachmentManager.class);
		return  attachmentManager;
	}

	private Attachment saveAttachement(String uuid, int cont, FileItem item) {
		Attachment o = new Attachment();
		o.setUuid(uuid);
		o.setTitle(item.getName());
		o.setDescription(item.getName());
		o.setFileName(item.getName());
		o.setFileSize((int) item.getSize());
		o.setFileType(item.getContentType());
		o.setOperator(GwtActionHelper.getCurrUser());
		o.setItemNumber(cont);
		return getAttachmentManager().addAttachment(o);
	}

	private String hashing(HttpServletRequest request) {
		String base = GlobalConfig.getValueWithKey("attachment.base");
		if (base == null || "".equals(base)) {
			base = "\\static";
		}
		String floderName = "\\" + Utils.getNowShortDate();
		String desPath = request.getSession().getServletContext()
				.getRealPath(base)
				+ floderName;
		if (!Utils.fileExists(desPath)) {
			Utils.createFolder(desPath);
		}
		return desPath;
	}

	/**
	 * Get the content of an uploaded file.
	 */
	public void getUploadedFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String fieldName = request.getParameter(PARAM_SHOW);
		File f = receivedFiles.get(fieldName);
		if (f != null) {
			response.setContentType(receivedContentTypes.get(fieldName));
			FileInputStream is = new FileInputStream(f);
			copyFromInputStreamToOutputStream(is, response.getOutputStream());
		} else {
			renderXmlResponse(request, response, ERROR_ITEM_NOT_FOUND);
		}
	}

	/**
	 * Remove a file when the user sends a delete request.
	 */
	public void removeItem(HttpServletRequest request, String fieldName)
			throws UploadActionException {
		File file = receivedFiles.get(fieldName);
		receivedFiles.remove(fieldName);
		getAttachmentManager().deleteAttachment(filesMap.get(fieldName));
		filesMap.remove(fieldName);
		receivedContentTypes.remove(fieldName);
		if (file != null) {
			file.delete();
		}
	}

}