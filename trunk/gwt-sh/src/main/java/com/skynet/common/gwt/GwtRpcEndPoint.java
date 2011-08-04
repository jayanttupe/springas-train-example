package com.skynet.common.gwt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * gwt action标志声明
 * 用以修饰gwt后台业务功能类
 * 使其能自动映射到指定url并处理gwt前端的调用请求
 *   
 * @author jiang
 * @see com.skynet.spms.gwt.homepage.client.service
 * @see com.google.gwt.user.client.rpc.RemoteServiceRelativePath

 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GwtRpcEndPoint {
	/**
	 * 指定业务类所绑定的url
	 * 默认情况下，业务类会与其对应接口声明的url绑定
	 * @return
	 */
	String value() default "";
}