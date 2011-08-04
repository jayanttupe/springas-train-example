package com.skynet.spms.aop.i18nconvert.convertpile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.aop.i18nconvert.ConvertPile;

public class CollectionPile implements ConvertPile {

	Logger log = LoggerFactory.getLogger(CollectionPile.class);

	private final ColType colType;
	
	private final boolean isAnno;
	
	private enum ColType {
		Collection, Map;
	}

	public static ConvertPile getInstanceByMap(boolean isAnno) {
		return new CollectionPile(isAnno, ColType.Map);
	}

	public static ConvertPile getInstanceByCol(boolean isAnno) {
		return new CollectionPile(isAnno, ColType.Collection);
	}

	private CollectionPile(boolean isAnno,ColType type) {
		
		this.colType = type;
		this.isAnno=isAnno;
	}

	@Override
	public void doConvert(Object param, PropertyEntity prop) {
		
		try {
			switch (colType) {
			case Map:
				Map<?,?> map=(Map<?,?>)param;
				doConvertToCol(map.values(),prop);
				break;
			case Collection:
				doConvertToCol(param,prop);
				break;			
			default:
				throw new IllegalStateException("ill coltype:" + colType);
			}
		} catch (Exception e) {
			log.error("do convert fail", e);
		}
	}
	
	private void doConvertToCol(Object param,PropertyEntity prop){
		Collection<?> col = (Collection<?>) param;
		if(col.isEmpty()){
			return;
		}
		Object elem=col.iterator().next();
		Class<?> cls=elem.getClass();
		
		if(cls.equals(String.class)&&isAnno){
			fillForString(col,prop);
		}else{
			fillForObj(col, prop, cls);
		}
	}

	private void fillForObj(Collection<?> col, PropertyEntity prop, Class<?> cls) {
		ConvertPile subPile=PileFactory.getConvertInst(cls,isAnno);
		for (Object val : col) {
			subPile.doConvert(val, prop);
		}
	}
	
	private void fillForString(Collection<?> objCol, PropertyEntity prop){
		Collection<String> col = (Collection<String>) objCol;
		List<String> tmpStore = new ArrayList<String>();
		for (String val : col) {
			col.remove(val);
			tmpStore.add(prop.getProperty(val));
		}
		col.addAll(tmpStore);
	}
	
	
	
	
}
