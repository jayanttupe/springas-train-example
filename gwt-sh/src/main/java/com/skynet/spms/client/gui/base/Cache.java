package com.skynet.spms.client.gui.base;

import java.util.LinkedHashMap;

public class Cache {

	private static Cache pool;

	private LinkedHashMap<String, Object> cache = new LinkedHashMap<String, Object>();

	private Cache() {
	}

	public static Cache getInstance() {
		if (pool == null) {
			pool = new Cache();
		}
		return pool;
	}

	public Object getObject(String key) {
		return cache.get(key);
	}

	public void putObject(String key,Object o) {
		cache.put(key,o);
	}

}
