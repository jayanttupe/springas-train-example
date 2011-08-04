package com.skynet.spms.aop.i18nconvert.convertpile;

import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.aop.i18nconvert.ConvertPile;

class NullPile implements ConvertPile{

	@Override
	public void doConvert(Object obj, PropertyEntity prop) {
		return;		
	}

}
