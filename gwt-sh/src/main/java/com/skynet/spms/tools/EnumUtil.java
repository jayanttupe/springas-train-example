package com.skynet.spms.tools;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.prop.PropManager;

@Service
public class EnumUtil {

	@Autowired
	PropManager propManager;
	
	/**
	 * 根据枚举里定义的内容取得国际化之后的值
	 * @param <T>
	 * @param enumCls
	 * @param val
	 * @return
	 */
	public <T extends Enum> String getDisplayNameByValue(Class<T> enumCls, Object val) {
		if (val == null || val.toString().equals("")) {
			return "";
		}
		
		String strResult = "";
		Locale locale=Locale.SIMPLIFIED_CHINESE;
		
		String[][] abc = propManager.getDisplayMapByEnum(enumCls, locale);
		for (int i = 0; i < abc[0].length; i++) {
			if (abc[0][i].toString().equals(val.toString())) {
				strResult = abc[1][i].toString();
				break;
			}
		}
		return strResult;
	}
	
	/**
	 * 根据国际化之后的值取得枚举里定义的内容
	 * @param <T>
	 * @param enumCls
	 * @param displayName
	 * @return
	 */
	public <T extends Enum> String getValueByDisplayName(Class<T> enumCls, Object displayName) {
		if (displayName == null || displayName.toString().equals("")) {
			return "";
		}
		
		String strResult = "";
		Locale locale=Locale.SIMPLIFIED_CHINESE;
		
		String[][] abc = propManager.getDisplayMapByEnum(enumCls, locale);
		for (int i = 0; i < abc[0].length; i++) {
			if (abc[1][i].toString().equals(displayName.toString())) {
				strResult = abc[0][i].toString();
				break;
			}
		}
		return strResult;
	}
}
