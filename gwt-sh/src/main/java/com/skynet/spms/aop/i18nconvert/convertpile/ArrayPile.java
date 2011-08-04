package com.skynet.spms.aop.i18nconvert.convertpile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.aop.i18nconvert.ConvertPile;

public class ArrayPile implements ConvertPile {

	Logger log = LoggerFactory.getLogger(CollectionPile.class);

	private final ConvertPile subPile;
	
	private final boolean isString;

	ArrayPile(Class<?> arrayCls,boolean isAnno){		
				
		Class<?> cls = arrayCls.getComponentType();
		isString=cls.equals(String.class);
		
		if(!isAnno&&isString){
			subPile=null;
			return;
		}
		
		if (!isString) {
			subPile = PileFactory.getConvertInst(cls,isAnno);
		}else{
			subPile = null;
		}
	}	

	@Override
	public void doConvert(Object param, PropertyEntity prop) {
				
		if (isString) {
			String[] col = (String[])param;
			for(int i=0;i<col.length;i++){				
				col[i]=prop.getProperty(col[i]);
			}			
		} else if(subPile!=null) {
			Object[] col = (Object[])param;
			for (Object val : col) {
				subPile.doConvert(val, prop);
			}
		}		
	}

}
