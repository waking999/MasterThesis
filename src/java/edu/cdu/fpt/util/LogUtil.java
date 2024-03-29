package edu.cdu.fpt.util;

import org.apache.log4j.Logger;

/**
 * a util for log
 * 
 * @author Kai Wang
 * 
 */
public class LogUtil {

	/**
	 * get log according to class
	 * 
	 * @param clazz
	 *            , class
	 * @return logger
	 */
	public static Logger getLogger(@SuppressWarnings("rawtypes") Class clazz) {
		Logger log = Logger.getLogger(clazz);
		log = setLog(log);
		return log;
	}

	/**
	 * set log
	 * 
	 * @param log
	 */
	private static Logger setLog(Logger log) {

		return log;
	}

	/**
	 * get root logger
	 * 
	 * @return logger
	 */
	public static Logger getLogger() {
		Logger log = Logger.getRootLogger();
		log = setLog(log);
		return log;
	}
}
