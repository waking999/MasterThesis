package edu.cdu.fpt.alg;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import edu.cdu.fpt.util.LogUtil;
import edu.uci.ics.jung.graph.Graph;

/**
 * implement the combination of greedy and simulated annealing algorithm
 * 
 * @author Kai Wang
 * 
 */
@Component("algorithmSimulatedAnnealingGreedy")
public class AlgorithmSimulatedAnnealingGreedy extends AbstractAlgorithm {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cdu.fpt.alg.AbstractAlgorithm#preComputing(edu.cdu.fpt.alg.IAlgorithm
	 * , edu.uci.ics.jung.graph.Graph, java.util.List)
	 */
	protected final void preComputing(IAlgorithm ag, Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList) {
		ag.computing(g, vertexDegreeList);

	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.AbstractAlgorithm#postComputing()
	 */
	protected void postComputing() {
		State state = getState();
		List<String> ds = state.getDs();
		int minDSLen = ds.size();
		int k = getK();
		if (minDSLen <= k) {
			Logger log = LogUtil.getLogger(AlgorithmHillClimbingGreedy.class);
			log.info("By using 'Simulated Annealing with Greedy', it could find solutions whose size is less than or equal to parameter k ("
					+ k + "). (the mininum size is " + minDSLen + ")");

		}
	}

}
