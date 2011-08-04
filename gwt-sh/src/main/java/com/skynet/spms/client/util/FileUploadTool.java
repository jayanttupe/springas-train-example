package com.skynet.spms.client.util;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import com.skynet.spms.client.service.AttachmentService;
import com.skynet.spms.client.service.AttachmentServiceAsync;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.WidgetCanvas;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnChangeUploaderHandler;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.IUploader.OnStartUploaderHandler;
import gwtupload.client.IUploader.Utils;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;


public class FileUploadTool {
	
	private Logger log = Logger.getLogger("file upload");

	private SpmsUploaderConstants constant=GWT.create(SpmsUploaderConstants.class);
	
	private AttachmentServiceAsync attachmentService=GWT.create(AttachmentService.class);
	
	private MultiUploader defaultUploader;
	
	private String refBusinessID;
	
	public FileUploadTool(){
		defaultUploader = new MultiUploader();
				
		defaultUploader.getFileInput().setLength(0);
		
		defaultUploader.setI18Constants(constant);
		defaultUploader.getFileInput().setText(constant.uploadBtnName());
			
		defaultUploader.addOnFinishUploadHandler(new OnFinishHandler());
		
		defaultUploader.addOnStartUploadHandler(new OnStartUploaderHandler(){

			@Override
			public void onStart(IUploader uploader) {
				uploader.setFileInputPrefix(refBusinessID);
			}
			
		});
		defaultUploader.setFileInputPrefix("test");
	}
	
	public void setWidth(String width){
		defaultUploader.setWidth(width);
	}
	
	public void setHeight(String height){
		defaultUploader.setHeight(height);
	}

	public void setLength(int length){
		defaultUploader.getFileInput().setLength(length);
	}
		
	public void setEnabled(boolean enable){
		defaultUploader.setEnabled(enable);
	}
	
	public boolean isEnabled(){
		return defaultUploader.isEnabled();
	}
	
	public void setVisible(boolean visible){
		defaultUploader.setVisible(visible);
	}
	
	public boolean isVisible(){
		return defaultUploader.isVisible();
	}
	
	public Canvas getWidget(){
		WidgetCanvas upLoadWidgetCanvas = new WidgetCanvas(defaultUploader);	
		upLoadWidgetCanvas.setTop("40px");
		return upLoadWidgetCanvas;
	}
	
	public void setBusinessID(String id){
		defaultUploader.setFileInputPrefix(id);
	}
	
	private OnUploadFinishHandler finishHandler;
	
	public void addOnFinishHandler(OnUploadFinishHandler handler){
		this.finishHandler=handler;
	}
	
	public void addOnStartHandler(OnStartUploaderHandler handler){
		defaultUploader.addOnStartUploadHandler(handler);
	}
	
	public void addOnChoiceHandler(OnChangeUploaderHandler handler){
		defaultUploader.addOnChangeUploadHandler(handler);
	}
	
	public void setFileNameExt(String... ext){
		defaultUploader.setValidExtensions(ext);
	}
	
	public void deleteFile(String fileIndex){
		attachmentService.deleteFile(fileIndex,new AsyncCallback<Void>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public static interface OnUploadFinishHandler{
		public void onFinish(FileInfo fileInfo);
	}
	
	private class OnFinishHandler implements OnFinishUploaderHandler {
		public void onFinish(final IUploader uploader) {
						
			if (uploader.getStatus() == Status.SUCCESS) {

				String response = uploader.getServerResponse();
				
				
				Document doc = XMLParser.parse(response);
				String json = Utils.getXmlNodeValue(doc, "message");
				log.info(json);
				
				JsArray<FileInfo> fileInfoArray = FileInfo
						.asArrayOfFileInfo(json);
				
				defaultUploader.reset();
				
				for (int i = 0; i < fileInfoArray.length(); i++) {
					
					FileInfo info = fileInfoArray.get(i);
					finishHandler.onFinish(info);				

				}				

				

			}
		}
	}

	
}
