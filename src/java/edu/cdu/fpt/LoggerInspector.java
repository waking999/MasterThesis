package edu.cdu.fpt;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import edu.cdu.fpt.alg.IAlgorithm;
import edu.cdu.fpt.util.LogUtil;

/**
 * This class work for log in aspect oriented programming
 * 
 * @author Kai Wang
 * 
 * 
 */
@Aspect
@Component
public class LoggerInspector {

	private static Logger log = LogUtil.getLogger(LoggerInspector.class);

	/**
	 * the method to be called around a real method
	 * 
	 * @param pjp
	 *            , the joint point
	 * @throws Throwable
	 */
	@Around("execution(public void edu.cdu.fpt.alg..*.computing())")
	public void aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		// invoke procedure to run the method, in this case is computing method
		long start = System.nanoTime();
		pjp.proceed();
		long end = System.nanoTime();

		log.info(pjp.getTarget() + " spends : " + (end - start) / 1000000000.0
				+ " seconds");

		// print result
		IAlgorithm a = (IAlgorithm) pjp.getThis();
		Set<List<String>> dsSet = a.getDominatingSetSet();
		Iterator<List<String>> dsIt = dsSet.iterator();

		while (dsIt.hasNext()) {
			StringBuffer sb = new StringBuffer();
			List<String> dsRow = dsIt.next();

			int dsLen = dsRow.size();
			sb.append(dsLen).append(" vertices are in this set:");
			for (int i = 0; i < dsLen; i++) {
				sb.append(dsRow.get(i)).append(",");
			}
			log.info(sb.substring(0, sb.length() - 1));

		}

	}

}
