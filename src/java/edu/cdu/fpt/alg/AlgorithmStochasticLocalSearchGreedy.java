package edu.cdu.fpt.alg;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import edu.cdu.fpt.util.LogUtil;

/**
 * implement the combination of greedy and local search algorithm
 * 
 * @author Kai Wang
 * 
 */
@Component("algorithmStochasticLocalSearchGreedy")
public class AlgorithmStochasticLocalSearchGreedy extends
		AlgorithmStochasticLocalSearch {

	private static Logger log = LogUtil
			.getLogger(AlgorithmStochasticLocalSearchGreedy.class);

}
