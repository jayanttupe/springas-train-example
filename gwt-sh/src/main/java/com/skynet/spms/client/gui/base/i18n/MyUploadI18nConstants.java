package com.skynet.spms.client.gui.base.i18n;

import gwtupload.client.IUploader.UploaderConstants;

/**
 * 文件上传国际化
 */
public interface MyUploadI18nConstants extends UploaderConstants {
	
	@DefaultStringValue("文件正在上传，请稍后再传.")
	String uploaderActiveUpload();

	@DefaultStringValue("该文件已经存上传过了")
	String uploaderAlreadyDone();

	@DefaultStringValue("系统出错")
	String uploaderBlobstoreError();

	@DefaultStringValue("上传文件")
	String uploaderBrowse();

	@DefaultStringValue("不允许的文件格式.\n仅仅允许:\n")
	String uploaderInvalidExtension();

	@DefaultStringValue("上传")
	String uploaderSend();

	@DefaultStringValue("服务器出错")
	String uploaderServerError();

	@DefaultStringValue("不能连接服务器: ")
	String uploaderServerUnavailable();

	@DefaultStringValue("上传超时了:\n perhaps your browser does not send files correctly,\n your session has expired,\n or there was a server error.\nPlease try again.")
	String uploaderTimeout();

}
