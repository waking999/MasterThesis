package edu.cdu.fpt;

import java.util.List;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.junit.Assert;

import edu.cdu.fpt.alg.IAlgorithm;
import edu.cdu.fpt.alg.State;
import edu.cdu.fpt.io.InputFile;
import edu.cdu.fpt.judge.IJudge;
import edu.cdu.fpt.util.Util;
import edu.uci.ics.jung.graph.Graph;

/**
 * 
 * @author Kai Wang
 * 
 *         The class is a customized ant task to run algorithms and to make
 *         judge
 */
public class AlgorithmTask extends Task {

	private String algClass;

	private String preAlgClass;

	public void setPreAlgClass(String preAlgClass) {
		this.preAlgClass = preAlgClass;
	}

	public void setAlgClass(String algClass) {
		this.algClass = algClass;
	}

	private String testFilePath;

	public void setTestFilePath(String testFilePath) {
		this.testFilePath = testFilePath;
	}

	private int k;

	public void setK(int k) {
		this.k = k;
	}

	private IJudge judge;
	private String judgeClass;

	public void setJudgeClass(String judgeClass) {
		this.judgeClass = judgeClass;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.tools.ant.Task#execute()
	 */
	public void execute() throws BuildException {

		try {
			// get the test data file dynamically by configuration
			InputFile input = new InputFile();
			input.setInputFile(this.testFilePath);
			input.getAdjacencyInfo();

			// get the algorithm run before this
			IAlgorithm preAlg = null;
			if (!Util.isStrNull(preAlgClass)) {
				Class preAlgClazz;
				try {
					preAlgClazz = Class.forName(preAlgClass);
					preAlg = (IAlgorithm) preAlgClazz.newInstance();
				} catch (Exception e) {
					preAlg = null;
				}

			}
			// get current algorithm dynamically by configuration
			Class algClazz = Class.forName(algClass);
			IAlgorithm alg = (IAlgorithm) algClazz.newInstance();

			alg.setPreAlg(preAlg);

			// initialization parameters;
			alg.initialization(input, k);
			// run the major logic of algorithm
			alg.generateDominatingSet();

			Set<List<String>> dsSet = alg.getDominatingSetSet();

			// get the judge class and do judge
			Class judgeClazz = Class.forName(judgeClass);
			judge = (IJudge) judgeClazz.newInstance();
			Graph<String, Integer> g = alg.getG();
			State state = alg.getState();

			boolean isDS = judge.isDS(g, state.getDs(),
					state.getComplementaryDs());
			Assert.assertTrue(isDS);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
