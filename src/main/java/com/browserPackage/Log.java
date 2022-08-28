package com.browserPackage;


import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

public class Log {
	static Logger log = LogManager.getLogger(Log.class);
	
	public static void LogInfo(String info)
	{
		 log.info(info);
	}
	public static void LogError(String info)
	{
		 log.error(info);
	}
	public static void LogDebug(String info)
	{
		 log.debug(info);
	}
	
}
