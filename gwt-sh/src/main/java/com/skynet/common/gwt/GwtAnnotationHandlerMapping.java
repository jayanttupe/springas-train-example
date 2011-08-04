package com.skynet.common.gwt;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.AbstractDetectingUrlHandlerMapping;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Gwt辅助类，gwt action映射管理器
 * 用于将Gwt业务Action与url绑定
 * 
 * 扫描所有以@GwtRpcEndPoint修饰的action
 * 
 * 在默认情况下，将action绑定于对应接口@RemoteServiceRelativePath所声明的url映射
 * 
 * 如果在@GwtRpcEndPoint中指定了url映射,则与本地指定的url绑定
 * 
 * @author jiang
 * @see com.skynet.spms.web.control
 * @see com.skynet.spms.gwt.homepage.client.service
 * @see com.skynet.common.gwt.GwtRpcEndPoint
 * @see com.google.gwt.user.client.rpc.RemoteServiceRelativePath
 */
public class GwtAnnotationHandlerMapping extends
AbstractDetectingUrlHandlerMapping {

	private Logger log = LoggerFactory
			.getLogger(GwtAnnotationHandlerMapping.class);

	private String[] prefixArray = {"portal","spms"};

//	private String suffix = "";

	protected String[] buildUrls(Class<?> handlerType, String beanName) {

		String serviceName = null;
//		List<String> serviceList=new ArrayList<String>();
		
		Class<?>[] interfaces = handlerType.getInterfaces();
		for (Class<?> itf : interfaces) {

			if (itf.isAnnotationPresent(RemoteServiceRelativePath.class)) {

				RemoteServiceRelativePath gwtPath = itf
						.getAnnotation(RemoteServiceRelativePath.class);

				serviceName = gwtPath.value();
//				serviceList.add(serviceName);
//				String packageName = itf.getPackage().getName();
//
//				String moduleName = getModuleName(packageName);
//
//				remoteServiceName = "/" + moduleName + "/" + serviceName;
				log.debug("binding service :" + serviceName + " with "
						+ handlerType.getName());
				break;
			}
		}

		if (serviceName == null) {
			throw new IllegalArgumentException(
					"Unable to generate name for "
							+ handlerType.getName()
							+ "; cannot locate interface that is a subclass of RemoteService");
		}

		String[] urlArray=new String[prefixArray.length];
		for(int i=0;i<prefixArray.length;i++){
			urlArray[i]="/"+prefixArray[i]+"/"+serviceName;
		}
//		StringBuilder sb = new StringBuilder();
//
//		sb.append(prefix);
//
//		sb.append(remoteServiceName);
//
//		sb.append(suffix);
		return urlArray;
	}

//	private String getModuleName(String packageName) {
//		String moduleName = "";
//		int idxEnd = packageName.indexOf(".client");
//		int idxStart = packageName.lastIndexOf(".", idxEnd - 1);
//
//		moduleName = packageName.substring(idxStart + 1, idxEnd);
//		return moduleName;
//	}

	@Override
	protected final String[] determineUrlsForHandler(String beanName) {
		String[] urls = new String[0];
		Class<?> handlerType = getApplicationContext().getType(beanName);
		log.debug("oper cls:"+handlerType);
		
		if (handlerType.isAnnotationPresent(GwtRpcEndPoint.class)
				&&RemoteService.class.isAssignableFrom(handlerType)) {
			log.debug("found gwt annot:" + handlerType.getName());
			GwtRpcEndPoint endPointAnnotation = handlerType
					.getAnnotation(GwtRpcEndPoint.class);
			if (StringUtils.isNotBlank(endPointAnnotation.value())) {
				urls = new String[] { endPointAnnotation.value() };
			} else {
				urls = buildUrls(handlerType, beanName);
			}
			log.debug("urls:" + Arrays.asList(urls));
		} 
		
		return urls;
	}

	public final void setPrefixs(String[] prefix) {
		this.prefixArray = prefix;
	}

//	public final void setSuffix(String suffix) {
//		this.suffix = suffix;
//	}
}
