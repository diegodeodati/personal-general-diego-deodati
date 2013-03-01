package it.betplus.database.connector.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadSettings {

//	private static final Log log = LogFactory.getLog(LoadSettings.class);
	
	public static String getSetting(String confResourceName, String settingName) {
		
		Properties settings = null;
		String settingValue = null;

		// try to load resource
	//	log.debug(String.format("Loading resource %s from file system", confResourceName));
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(confResourceName);		
		settings = new Properties();
		if (in != null) {
			
			try {
				settings.load(in);
			} catch (IOException e) {
				e.printStackTrace();
		//		log.error(String.format("ERROR loading properties from configuration resource %s", confResourceName), e);
			}
			
		}

		if (settings != null && settings.containsKey(settingName)) {
			settingValue = settings.getProperty(settingName);
		}


		return settingValue;
	}
	
	public static InputStream getResourceAsStream(String confResourceName) {
		
		// try to load resource stream
	//	log.debug(String.format("Loading resource %s from file system", confResourceName));
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(confResourceName);		
		
		return in;
		
	}
	
}
