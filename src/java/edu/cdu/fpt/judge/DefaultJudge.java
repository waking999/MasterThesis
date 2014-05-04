package edu.cdu.fpt.judge;

import java.util.List;

import edu.cdu.fpt.alg.AlgorithmUtil;
import edu.uci.ics.jung.graph.Graph;

/**
 * This is the default judge implementing the basic judge jobs
 * 
 * @author Kai Wang
 * 
 */
public class DefaultJudge implements IJudge {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.judge.IJudge#isDS(edu.uci.ics.jung.graph.Graph,
	 * java.util.List, java.util.List)
	 */
	public boolean isDS(Graph<String, Integer> g, List<String> ds,
			List<String> complementaryDS) {
		return AlgorithmUtil.isDS(g, ds, complementaryDS);
	}
}
