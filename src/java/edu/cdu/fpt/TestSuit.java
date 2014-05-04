package edu.cdu.fpt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.cdu.fpt.alg.AlgorithmGreedyTest;
import edu.cdu.fpt.alg.AlgorithmHillClimbingGreedyTest;
import edu.cdu.fpt.alg.AlgorithmHillClimbingTest;
import edu.cdu.fpt.alg.AlgorithmSimulatedAnnealingGreedyTest;
import edu.cdu.fpt.alg.AlgorithmSimulatedAnnealingTest;
import edu.cdu.fpt.alg.AlgorithmStochasticLocalSearchGreedyTest;
import edu.cdu.fpt.alg.AlgorithmStochasticLocalSearchTest;

/**
 * A suite to accommodate all available tests, which will be called in test
 * runner
 * 
 * @author Kai Wang
 * 
 * 
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ AlgorithmGreedyTest.class,
		AlgorithmHillClimbingTest.class, AlgorithmHillClimbingGreedyTest.class,
		AlgorithmStochasticLocalSearchTest.class,
		AlgorithmStochasticLocalSearchGreedyTest.class,
		AlgorithmSimulatedAnnealingTest.class,
		AlgorithmSimulatedAnnealingGreedyTest.class })
public class TestSuit {

}
