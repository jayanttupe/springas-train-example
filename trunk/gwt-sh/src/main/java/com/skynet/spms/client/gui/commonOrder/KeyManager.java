package com.skynet.spms.client.gui.commonOrder;

import java.util.HashMap;
import java.util.Map;

public class KeyManager {

	private static Map<String, String> BKMap = new HashMap<String, String>();
	private static Map<String, String> KBMap = new HashMap<String, String>();
	private static Map<String, String> BIZ2EntityMap = new HashMap<String, String>();

	static {
		ContractIndexKey[] keys = ContractIndexKey.values();
		DirectiveBusinessType[] types = DirectiveBusinessType.values();
		ContractEntityName[] entityNames=ContractEntityName.values();
		
		for (int i = 0; i < types.length; i++) {
			BKMap.put(types[i].name(), keys[i].name());
			KBMap.put(keys[i].name(), types[i].name());
			BIZ2EntityMap.put(types[i].name(), entityNames[i].name());
		}
	}

	public static String businessType2ContractKey(String key) {
		return BKMap.get(key);
	}

	public static String contractKey2BusinessType(String key) {
		return KBMap.get(key);
	}

	public static String contractBusinessType2EntityName(String key) {
		return BIZ2EntityMap.get(key);
	}
	
}
