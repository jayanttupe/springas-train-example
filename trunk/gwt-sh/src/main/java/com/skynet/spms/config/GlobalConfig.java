package com.skynet.spms.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Config loader
 * 
 * @author tqc
 * 
 */
public class GlobalConfig{

	private static final Logger logger = Logger.getLogger(GlobalConfig.class);
	
	private static long version;
	
	private static long timer=36000;

	private static String GLOBALCONFIG = "/application.properties";
	
	private static Properties globalConfig = new Properties();
	
	
	static {
		init();
	}

	private static void init() {
		InputStream is = GlobalConfig.class.getResourceAsStream(GLOBALCONFIG);
		try {
			globalConfig.load(is);
			version=System.currentTimeMillis();
		} catch (IOException e) {
			logger.error("处理全局配置 :" + GLOBALCONFIG);
		}
	}

	/**
	 * get value by key
	 * 
	 * @param key
	 * @return
	 */
	public static String getValueWithKey(String key) {
		String res = globalConfig.getProperty(key);
		if (res == null) {
			init();
		}
		//when the version overdue reload config
		if(System.currentTimeMillis()-version>timer){
			init();
		}
		return globalConfig.getProperty(key);
	}
	

}
