package com.skynet.common.gwt;

import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * GWT 辅助类 gwt action分发管理器
 * 用于将GWT前端发起的request分发方法函数绑定至业务action的方法函数上执行
 * 将执行结果按GWT规范打包后返回前台
 * 
 * 并通过GwtActionHelper处理用户信息等相关上下文的初始化
 * @author jiang
 * @see com.skynet.common.gwt.GwtActionHelper
 */
public class GwtRcpEndPointHandlerAdapter extends RemoteServiceServlet
		implements HandlerAdapter ,ServletContextAware{
	
	private Logger log=LoggerFactory.getLogger(GwtRcpEndPointHandlerAdapter.class);

	private static ThreadLocal<Object> handlerHolder = new ThreadLocal<Object>();

	private static final long serialVersionUID = -7421136737990135393L;

	public long getLastModified(HttpServletRequest request, Object handler) {
		return -1;
	}
		
	private ServletContext servletContext;
	
	public void setServletContext(ServletContext servletContext){
		this.servletContext=servletContext;
	}

	public ServletContext getServletContext(){
		return servletContext;
	}
	
	@Override
	public ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		try {
			log.debug("been call by web:"+handler.getClass());
			
			
			// store the handler for retrieval in processCall()
			handlerHolder.set(handler);
			doPost(request, response);
			
		} finally {
			// clear out thread local to avoid resource leak
			handlerHolder.set(null);
		}

		return null;
	}

	protected Object getCurrentHandler() {
		return handlerHolder.get();
	}

	@Override
	//校验当前的映射处理类是否匹配
	public boolean supports(Object handler) {
		return handler instanceof RemoteService
				&& handler.getClass().isAnnotationPresent(GwtRpcEndPoint.class);
	}

	@Override
	//根据客户端请求的方法函数映射绑定服务器端映射处理类的方法函数
	public String processCall(String payload) throws SerializationException {

		log.debug("call by rpc servlet:"+payload);
		try {
			RPCRequest rpcRequest = RPC.decodeRequest(payload,
					getCurrentHandler().getClass());
			
			Object entity=getCurrentHandler();
			Method method=rpcRequest.getMethod();			
//			Method impMethod=entity.getClass().getMethod(method.getName(), method.getParameterTypes());
						
			GwtActionHelper.init(getThreadLocalRequest());
			
			String retVal = RPC.invokeAndEncodeResponse(
					entity,method,rpcRequest.getParameters());

			return retVal;

		} catch (Throwable t) {
			log.error("rpc call fail",t);
			return RPC.encodeResponseForFailure(null, t);
		}
	}
}
