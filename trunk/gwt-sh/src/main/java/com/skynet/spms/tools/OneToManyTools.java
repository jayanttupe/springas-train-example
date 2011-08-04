package com.skynet.spms.tools;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;

public class OneToManyTools {

	
	public static List ConvertToEntity(List objList,Class<?> cls)
	{
		
		List newList = new ArrayList();
		if(objList==null)
			return null;
		for (int i = 0; i < objList.size(); i++)
		{
			Map map = (Map)objList.get(i);
			Set set = map.entrySet();
			Object obj;
			try {
				obj = cls.newInstance();
				BeanPropUtil.fillEntityWithMap(obj, map);     
				newList.add(obj);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return newList;
	}

}
