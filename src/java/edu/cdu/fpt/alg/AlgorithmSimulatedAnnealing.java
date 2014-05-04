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
 * This class implements simulated annealing algorithm
 * 
 * @author Kai Wang
 * 
 */
@Component("algorithmSimulatedAnnealing")
public class AlgorithmSimulatedAnnealing extends AbstractAlgorithm {

	private static java.util.Random random = new java.util.Random();

	private double decayRate = 0.99;

	private static Logger log;
	static {
		log = Logger.getLogger(AlgorithmSimulatedAnnealing.class.getName());

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

		simulatedAnnealing(g);
	}

	/**
	 * implement simulated annealing algorithm
	 * 
	 * @param g
	 *            ,Graph
	 */
	private void simulatedAnnealing(Graph<String, Integer> g) {

		State state = getState();
		Set<List<String>> dominatingSetSet = new TreeSet<List<String>>(
				new ListSizeComparator());

		State minState = (State) state.clone();
		int numOfVertex = this.getNumOfVertex();

		int minEnergy = AlgorithmUtil.energy(g, minState, numOfVertex);
		int energy = minEnergy;

		double temperature = numOfVertex;
		// int iterations = 10*numOfVertex;
		int iterations = 10000;
		for (int i = 0; i < iterations; i++) {
			step(g, state);
			int tempEnergy = AlgorithmUtil.energy(g, state, numOfVertex);

			double randomDouble = random.nextDouble();
			int gapEnergy = energy - tempEnergy;
			double annealingMeasure = Math.exp(gapEnergy / temperature);
			if (tempEnergy <= energy || randomDouble < annealingMeasure) {

				energy = tempEnergy;
				if (tempEnergy < minEnergy) {
					minState = (State) state.clone();
					minEnergy = tempEnergy;

					dominatingSetSet.add(minState.getDs());

					List<String> stateDs = state.getDs();
					state.setPrevDs(stateDs);
					state.setPrevCplDs(state.getComplementaryDs());
				}

			} else {
				undo(state);
				temperature = temperature * decayRate;
			}

		}

		setDominatingSetSet(dominatingSetSet);
		setState(state);
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
			log.info("By using 'Simulated Annealing', it could find solutions whose size is less than or equal to parameter k ("
					+ k + "). (the mininum size is " + minDSLen + ")");

		}
	}

	/**
	 * step out to get new solution
	 * 
	 * @param g
	 *            , graph
	 * @param state
	 *            , State
	 */
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

	/**
	 * Undo last step, go back to last position
	 * 
	 * @param state
	 *            , State
	 */
	private void undo(State state) {

		List<String> prevDs = state.getPrevDs();
		List<String> prevCplDs = state.getPrevCplDs();

		state.setDs(prevDs);
		state.setComplementaryDs(prevCplDs);
	}

}
