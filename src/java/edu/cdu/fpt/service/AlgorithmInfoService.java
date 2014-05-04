package edu.cdu.fpt.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.springframework.stereotype.Service;

import edu.cdu.fpt.model.AlgorithmInfo;

/**
 * services related to algorithms
 * 
 * @author Kai Wang
 * 
 */
@Service
public class AlgorithmInfoService {
	Map<String, String> algPreAlgMap = new HashMap<String, String>();

	public List<AlgorithmInfo> getAlgorithmInfoList() {
		List<AlgorithmInfo> list = new ArrayList<AlgorithmInfo>();
		AlgorithmInfo ai1 = new AlgorithmInfo();
		ai1.setFilePath("AlogrithmGreedy.java");
		ai1.setLog("AlogrithmGreedy.log");
		ai1.setName("AlgorithmGreedy");
		ai1.setUploadDate("2013-08-24 19:20:34");
		list.add(ai1);

		AlgorithmInfo ai2 = new AlgorithmInfo();
		ai2.setFilePath("AlgorithmHillClimbing.java");
		ai2.setLog("AlgorithmHillClimbing.log");
		ai2.setName("AlgorithmHillClimbing");
		ai2.setUploadDate("2013-08-14 09:02:23");
		list.add(ai2);

		AlgorithmInfo ai3 = new AlgorithmInfo();
		ai3.setFilePath("AlgorithmHillClimbingGreedy.java");
		ai3.setLog("AlgorithmHillClimbingGreedy.log");
		ai3.setName("AlgorithmHillClimbingGreedy");
		ai3.setUploadDate("2013-08-14 09:02:23");
		list.add(ai3);

		AlgorithmInfo ai4 = new AlgorithmInfo();
		ai4.setFilePath("AlgorithmSimulatedAnnealing.java");
		ai4.setLog("AlgorithmSimulatedAnnealing.log");
		ai4.setName("AlgorithmSimulatedAnnealing");
		ai4.setUploadDate("2013-08-14 09:02:23");
		list.add(ai4);

		AlgorithmInfo ai5 = new AlgorithmInfo();
		ai5.setFilePath("AlgorithmSimulatedAnnealingGreedy.java");
		ai5.setLog("AlgorithmSimulatedAnnealingGreedy.log");
		ai5.setName("AlgorithmSimulatedAnnealingGreedy");
		ai5.setUploadDate("2013-08-14 09:02:23");
		list.add(ai5);

		AlgorithmInfo ai6 = new AlgorithmInfo();
		ai6.setFilePath("AlgorithmStochasticLocalSearch.java");
		ai6.setLog("AlgorithmStochasticLocalSearch.log");
		ai6.setName("AlgorithmStochasticLocalSearch");
		ai6.setUploadDate("2013-08-14 09:02:23");
		list.add(ai6);

		AlgorithmInfo ai7 = new AlgorithmInfo();
		ai7.setFilePath("AlgorithmStochasticLocalSearchGreedy.java");
		ai7.setLog("AlgorithmStochasticLocalSearchGreedy.log");
		ai7.setName("AlgorithmStochasticLocalSearchGreedy");
		ai7.setUploadDate("2013-08-14 09:02:23");
		list.add(ai7);

		return list;
	}

	/**
	 * invoke ant tasks
	 * 
	 * @param contextPath
	 *            , context path
	 * @param algorithmName
	 *            , algorithm name
	 */
	public void runAlgorithm(String contextPath, String algorithmName) {
		Logger log = Logger.getLogger(this.getClass());
		log.info("runAlgorithm start");
		algPreAlgMap.put("AlgorithmHillClimbing",
				"edu.cdu.fpt.alg.AlgorithmGreedy");
		algPreAlgMap.put("AlgorithmSimulatedAnnealing",
				"edu.cdu.fpt.alg.AlgorithmSimulatedAnnealingGreedy");
		algPreAlgMap.put("AlgorithmStochasticLocalSearch",
				"edu.cdu.fpt.alg.AlgorithmStochasticLocalSearchGreedy");

		String buildXmlFilePath = contextPath + "WEB-INF\\classes\\build.xml";
		File buildFile = new File(buildXmlFilePath);
		PrintStream logstream = null;
		DefaultLogger cl = new DefaultLogger();
		// String algorithmName="AlgorithmHillClimbing";

		Project p = new Project();
		try {
			String logFilePath = contextPath + "log\\" + algorithmName + ".log";
			File logFile = null;

			logFile = new File(logFilePath);

			if (!logFile.exists()) {
				logFile.createNewFile();
			}

			logstream = new PrintStream(new FileOutputStream(logFilePath),
					false);
			cl.setErrorPrintStream(logstream);
			cl.setOutputPrintStream(logstream);

			p.fireBuildStarted();
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			helper.parse(p, buildFile);

			// cl.setMessageOutputLevel(Project.MSG_VERBOSE);
			p.addBuildListener(cl);

			p.setProperty("algorithm.class.name", algorithmName);
			String testFile = contextPath + "resources\\testcase400.csv";
			p.setProperty("algorithm.test.file", testFile);

			int k = 11;
			p.setProperty("algorithm.k", Integer.toString(k));

			String preAlgName = algPreAlgMap.get(algorithmName);
			if (preAlgName != null) {
				p.setProperty("algorithm.preAlg", preAlgName);
			}

			String dftTgt = p.getDefaultTarget();

			p.executeTarget(dftTgt);
			p.fireBuildFinished(null);
		} catch (BuildException e) {
			p.fireBuildFinished(e);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			logstream.close();
		}

	}

	/**
	 * compare algorithms
	 */
	public void compareAlg() {

	}

}
