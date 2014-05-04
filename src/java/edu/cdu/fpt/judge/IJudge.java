package edu.cdu.fpt.judge;

import java.util.List;

import edu.uci.ics.jung.graph.Graph;

/**
 * this class declares the api of judges
 * 
 * @author Kai Wang
 * 
 */
public interface IJudge {
	/**
	 * judge if it is a solution of dominating set
	 * 
	 * @param g
	 *            , graph
	 * @param ds
	 *            , dominating set
	 * @param complementaryDS
	 *            , the complementary set of dominating set
	 * @return boolean, is dominating set or not
	 */
	public boolean isDS(Graph<String, Integer> g, List<String> ds,
			List<String> complementaryDS);

}
