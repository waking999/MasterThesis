package edu.cdu.fpt.util;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * format java util log
 * 
 * @author Kai Wang
 * 
 */
public class CustFormatter extends SimpleFormatter {
	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.util.logging.SimpleFormatter#format(java.util.logging.LogRecord)
	 */
	public synchronized String format(LogRecord record) {
		return record.getMessage() + "\r\n";
	}
}
