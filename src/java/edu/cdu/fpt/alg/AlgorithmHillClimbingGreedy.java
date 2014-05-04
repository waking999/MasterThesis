package edu.cdu.fpt.alg;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import edu.cdu.fpt.util.LogUtil;

/**
 * This class work for the combination of greedy and hill climbing algorithm
 * 
 * @author Kai Wang
 * 
 * 
 */
@Component("algorithmHillClimbingGreedy")
public class AlgorithmHillClimbingGreedy extends AlgorithmHillClimbing {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.AlgorithmHillClimbing#postComputing()
	 */
	protected void postComputing() {
		State state = getState();
		List<String> ds = state.getDs();
		int minDSLen = ds.size();
		int k = getK();
		if (minDSLen <= k) {
			Logger log = LogUtil.getLogger(AlgorithmHillClimbing.class);
			log.info("By using 'HILL CLIMBING WITH GREEDY', it could find solutions whose size is less than or equal to parameter k ("
					+ k + "). (the mininum size is " + minDSLen + ")");

		}
	}

}
