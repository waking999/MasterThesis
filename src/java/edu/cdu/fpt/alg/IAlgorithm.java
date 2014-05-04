package edu.cdu.fpt.alg;

import java.util.List;
import java.util.Set;

import edu.cdu.fpt.io.IInput;
import edu.uci.ics.jung.graph.Graph;

/**
 * This interface work for extension of new algorithms
 * 
 * @author : Kai Wang
 * 
 * 
 * 
 */

public interface IAlgorithm {
	public final static int DEFAULTITERATIONS = 10000;

	/**
	 * the major logic to generate dominating set
	 */
	public void generateDominatingSet();

	/**
	 * return the solutions of dominating set
	 * 
	 * @return the array of dominating set
	 */
	public Set<List<String>> getDominatingSetSet();

	/**
	 * getting graph data from input, initialize the parameters before
	 * generating a graph
	 * 
	 * @param input
	 *            , the source of data
	 * @param k
	 *            , parameter
	 */
	public void initialization(IInput input, int k);

	/**
	 * initialize the parameters before generating a graph
	 * 
	 * @param numOfVertex
	 *            , the number of vertices
	 * @param adjacencyMatrix
	 *            , adjacency matrix of the graph
	 * @param k
	 *            , parameter
	 */
	public void initialization(int numOfVertex, List<String[]> adjacencyMatrix,
			int k);

	/**
	 * the major computing behavior, which is part of generateDominatingSet
	 * 
	 * @param g
	 *            , a graph
	 * @param vertexDegreeList
	 *            , a list of vertices with degree
	 * @return
	 */
	public void computing(Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList);

	/**
	 * set an instance of another algorithm which the current one relies on
	 * 
	 * @param preAlg
	 */
	public void setPreAlg(IAlgorithm preAlg);

	/**
	 * get the state in the algorithm
	 * 
	 * @return state
	 */
	public State getState();

	/**
	 * set parameter k
	 * 
	 * @param k
	 *            , parameter
	 */
	public void setK(int k);

	/**
	 * set number of vertices
	 * 
	 * @param numOfVertex
	 *            , number of vertices
	 */
	public void setNumOfVertex(int numOfVertex);

	/**
	 * set adjacency matrix of a graph
	 * 
	 * @param adjacencyMatrix
	 *            , adjacency matrix of a graph
	 */
	public void setAdjacencyMatrix(List<String[]> adjacencyMatrix);

	/**
	 * get the graph for the algorithm
	 * 
	 * @return
	 */
	public Graph<String, Integer> getG();
}
