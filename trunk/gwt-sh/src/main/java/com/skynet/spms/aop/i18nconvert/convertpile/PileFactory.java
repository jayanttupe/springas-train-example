package com.skynet.spms.aop.i18nconvert.convertpile;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.skynet.spms.aop.i18nconvert.ConvertPile;
import com.skynet.spms.aop.i18nconvert.NoNeedi18n;
import com.skynet.spms.client.entity.GwtFoo;

public class PileFactory {
	
	
	public static ConvertPile getConvertInst(Class<?> cls,boolean isAnno){
		
		if(cls.isAnnotationPresent(NoNeedi18n.class)){
			return null;
		}
		
		if(cls.isPrimitive()){
			return null;
		}
		
		Set<Class<?>> interSet=new HashSet<Class<?>>();			
		interSet.addAll(Arrays.asList(cls.getInterfaces()));
		
		if(cls.isArray()){
			return new ArrayPile(cls,isAnno);
		}else if(interSet.contains(Collection.class)){
			return CollectionPile.getInstanceByCol(isAnno);
		}else if(interSet.contains(Map.class)){
			return CollectionPile.getInstanceByMap(isAnno);
		}
				
		if(cls.getPackage().equals(GwtFoo.class.getPackage())){			
			return getPileInstance(cls);
		}
		
		throw new IllegalStateException("unknow cls:"+cls.getName());
	}

	private static final ConcurrentMap<Class<?>,ObjectPile> GlobeCache=new ConcurrentHashMap<Class<?>,ObjectPile>();
	
	private static ObjectPile getPileInstance(Class<?> cls){
		if(!cls.getPackage().equals(GwtFoo.class.getPackage())){
			throw new IllegalArgumentException();
		}
		
		ObjectPile inst=GlobeCache.get(cls);
		if(inst!=null){
			return inst;
		}
		
		return GlobeCache.putIfAbsent(cls, new ObjectPile(cls));
	}
	
}
