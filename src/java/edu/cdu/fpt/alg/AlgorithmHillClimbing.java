package edu.cdu.fpt.alg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import edu.cdu.fpt.util.ListSizeComparator;
import edu.cdu.fpt.util.LogUtil;
import edu.uci.ics.jung.graph.Graph;

/**
 * This class implements hill climbing algorithm
 * 
 * @author Kai Wang
 * 
 * 
 */

@Component("algorithmHillClimbing")
public class AlgorithmHillClimbing extends AbstractAlgorithm {

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
			Logger log = LogUtil.getLogger(AlgorithmHillClimbing.class);
			log.info("By using 'HILL CLIMBING ', it could find solutions whose size is less than or equal to parameter k ("
					+ k + "). (the mininum size is " + minDSLen + ")");

		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cdu.fpt.alg.AbstractAlgorithm#preComputing(edu.cdu.fpt.alg.IAlgorithm
	 * , edu.uci.ics.jung.graph.Graph, java.util.List)
	 */
	protected void preComputing(IAlgorithm ag, Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList) {
		if (ag == null) {
			State state = AlgorithmUtil.generateARandomSolution(this
					.getNumOfVertex());
			this.setState(state);
		} else {
			ag.computing(g, vertexDegreeList);
			this.setState(ag.getState());
		}

	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cdu.fpt.alg.AbstractAlgorithm#computing(edu.uci.ics.jung.graph.Graph,
	 * java.util.List)
	 */
	public final void computing(Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList) {

		hillClimbing(g);
	}

	/**
	 * implement the hill climbing algorithm
	 * 
	 * @param g
	 *            , graph
	 */
	private void hillClimbing(Graph<String, Integer> g) {
		State state = getState();
		Set<List<String>> dominatingSetSet = new TreeSet<List<String>>(
				new ListSizeComparator());

		List<String> certainDS = state.getDs();
		dominatingSetSet.add(certainDS);

		int certainDSLen = certainDS.size();

		if (certainDSLen <= getK()) {
			dominatingSetSet.add(certainDS);
		}

		// take use of hill climbing idea based on the generated dominating set
		int minDSLen = certainDSLen;

		List<String> complementaryDS = state.getComplementaryDs();
		int complementaryDSLen = complementaryDS.size();

		for (int i = 0; i < complementaryDSLen; i++) {
			List<String> certainDSBk = new ArrayList<String>(certainDSLen);

			List<String> complementaryDSBk = new ArrayList<String>(
					complementaryDSLen);
			certainDSBk.addAll(certainDS);
			complementaryDSBk.addAll(complementaryDS);
			String temp = complementaryDS.get(i);
			certainDSBk.add(temp);
			complementaryDSBk.remove(temp);
			Collection<String> neighborsOfI = g.getNeighbors(temp);
			Iterator<String> nIIt = neighborsOfI.iterator();
			while (nIIt.hasNext()) {

				String nIItNextStr = nIIt.next();
				if (certainDSBk.contains(nIItNextStr)) {
					complementaryDSBk.add(nIItNextStr);
					certainDSBk.remove(nIItNextStr);
				}

			}

			// verify certainDSBk is a dominating set
			boolean isDS = AlgorithmUtil
					.isDS(g, certainDSBk, complementaryDSBk);
			if (isDS) {
				int certainDSBkLen = certainDSBk.size();
				if (minDSLen > certainDSBkLen) {

					minDSLen = certainDSBkLen;

					// dominatingSetSet.add(certainDSBk);
					List<String> existingDs = state.getDs();
					List<String> existingCplDs = state.getComplementaryDs();
					state.setPrevDs(existingDs);
					state.setPrevCplDs(existingCplDs);
					state.setDs(certainDSBk);
					state.setComplementaryDs(complementaryDSBk);

				}
			}
		}
		dominatingSetSet.add(state.getDs());
		setDominatingSetSet(dominatingSetSet);
		setState(state);
	}

}
