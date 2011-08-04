package com.skynet.spms.datasource;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.AbstractDetectingUrlHandlerMapping;

import com.skynet.spms.datasource.annotation.DataSourceAdapteAnno;
//DataSource映射处理绑定
public class DataSourceAnnotationHandlerMapping extends
		AbstractDetectingUrlHandlerMapping {

	private Logger log = LoggerFactory
			.getLogger(DataSourceAnnotationHandlerMapping.class);

	private String prefix = "";

	private String suffix = "";

	@Override
	//在ApplicationContext找到实现HandlerMapping接口的类，并将客户端url请求与该类建立映射处理绑定
	protected final String[] determineUrlsForHandler(String beanName) {
		Class<?> handlerType = getApplicationContext().getType(beanName);
		log.debug("oper cls:" + handlerType);

		if (handlerType.isAnnotationPresent(DataSourceAdapteAnno.class)
				&& DataSourceAdapte.class.isAssignableFrom(handlerType)) {
			String[] urls = new String[1];

			log.debug("found datasource annot:" + handlerType.getName());

			DataSourceAdapteAnno dsAnnotation = handlerType
					.getAnnotation(DataSourceAdapteAnno.class);
            //根据注释的name属性值绑定url
			String dsName = dsAnnotation.name();
			urls[0] = prefix + "/" + dsName + suffix;
			log.info("register urls:" + Arrays.asList(urls));
			return urls;
		} else {
			return new String[0];
		}
	}

	public final void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public final void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
