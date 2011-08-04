package com.skynet.spms.web.control;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.common.prop.PropManager;
import com.skynet.spms.client.service.EnumDicService;

/**
 * 枚举类型字典加载
 * 
 * @author tqc
 * 
 */
@Controller
@GwtRpcEndPoint
public class EnumDicAction implements EnumDicService {

	@Autowired
	PropManager propManager;

	public LinkedHashMap<String, String> get(String className) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		if (className != null && !"".equals(className)) {
			Class enumClass;
			try {
				enumClass = Class.forName(className);
				String[][] arr = propManager.getDisplayMapByEnum(enumClass,
						GwtActionHelper.getLocale());
				for (int i = 0; i < arr[0].length; i++) {
					map.put(arr[0][i], arr[1][i]);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		return map;
	}

}
