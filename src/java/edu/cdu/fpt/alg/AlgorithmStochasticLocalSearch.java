package edu.cdu.fpt.alg;

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
 * implement local search algorithm
 * 
 * @author Kai Wang
 * 
 */
@Component("algorithmStochasticLocalSearch")
public class AlgorithmStochasticLocalSearch extends AbstractAlgorithm {

	private static Logger log = LogUtil
			.getLogger(AlgorithmStochasticLocalSearch.class);

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

		localSearch(g);
	}

	/**
	 * implement local search algorithm
	 * 
	 * @param g
	 *            , graph
	 */
	private void localSearch(Graph<String, Integer> g) {
		Set<List<String>> dominatingSetSet = new TreeSet<List<String>>(
				new ListSizeComparator());
		State state = this.getState();
		State minState = (State) state.clone();
		int numOfVertex = this.getNumOfVertex();
		int k = this.getK();
		int minEnergy = AlgorithmUtil.energy(g, minState, numOfVertex);

		int iterations = Math.max(10 * numOfVertex, DEFAULTITERATIONS);
		for (int i = 0; i < iterations; i++) {
			step(g, state);
			int tempEnergy = AlgorithmUtil.energy(g, state, numOfVertex);

			if (tempEnergy == numOfVertex) {
				undo(state);
				continue;
			} else {
				if (tempEnergy <= minEnergy) {
					List<String> stateDs = state.getDs();
					if (!dominatingSetSet.contains(stateDs)) {
						minState = (State) state.clone();
						minEnergy = tempEnergy;

						dominatingSetSet.add(minState.getDs());
						state.setPrevDs(stateDs);
						state.setPrevCplDs(state.getComplementaryDs());

					}
				}

			}
		}

		setDominatingSetSet(dominatingSetSet);
		setState(state);

		if (minEnergy <= k) {
			log.info("By using 'STOCHASTIC LOCAL SEARCH', it could find solutions whose size is less than or equal to parameter k ("
					+ k + "). (the mininum size is " + minEnergy + ")");
		}
	}

	private void step(Graph<String, Integer> g, State state) {
		// search local area and change to a new ds
		List<String> ds = state.getDs();
		List<String> cplDs = state.getComplementaryDs();

		int cplDsLen = cplDs.size();
		int position = (int) Math.floor(Math.random() * cplDsLen);

		String randomVertex = cplDs.get(position);

		ds.add(randomVertex);
		cplDs.remove(randomVertex);
		Collection<String> neighborsOfR = g.getNeighbors(randomVertex);
		Iterator<String> nRIt = neighborsOfR.iterator();
		while (nRIt.hasNext()) {
			String nIItNextStr = nRIt.next();
			if (ds.contains(nIItNextStr)) {
				cplDs.add(nIItNextStr);
				ds.remove(nIItNextStr);
			}

		}

	}

	private void undo(State state) {

		List<String> prevDs = state.getPrevDs();
		List<String> prevCplDs = state.getPrevCplDs();

		state.setDs(prevDs);
		state.setComplementaryDs(prevCplDs);
	}

}
