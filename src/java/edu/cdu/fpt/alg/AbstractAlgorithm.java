package edu.cdu.fpt.alg;

import java.util.List;
import java.util.Set;

import edu.cdu.fpt.io.IInput;
import edu.uci.ics.jung.graph.Graph;

/**
 * AbstractAlgorithm works as the base class of algorithms to implement an
 * interface and to implement some basic operations
 * 
 * @author Kai Wang
 * 
 */

public abstract class AbstractAlgorithm implements IAlgorithm {
	private State state;

	private Graph<String, Integer> g;

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.IAlgorithm#getState()
	 */
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	private IAlgorithm preAlg;

	public IAlgorithm getPreAlg() {
		return preAlg;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.IAlgorithm#setPreAlg(edu.cdu.fpt.alg.IAlgorithm)
	 */
	public void setPreAlg(IAlgorithm preAlg) {
		this.preAlg = preAlg;
	}

	/*
	 * number of vertex in the graph
	 */
	protected int numOfVertex;
	/*
	 * the adjacency matrix of a graph
	 */
	private List<String[]> adjacencyMatrix;
	/*
	 * the parameter that the size of dominatingSet should less than or equal to
	 * it.
	 */
	private int k;
	/*
	 * an array containing several dominating sets
	 */
	private Set<List<String>> dominatingSetSet;

	protected void setDominatingSetSet(Set<List<String>> dominatingSetSet) {
		this.dominatingSetSet = dominatingSetSet;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.IAlgorithm#initialization(edu.cdu.fpt.io.IInput,
	 * int)
	 */
	public final void initialization(IInput input, int k) {
		int numOfVertex = input.getNumOfVertex();
		List<String[]> adjacencyMatrix = input.getAdjacencyMatrix();

		initialization(numOfVertex, adjacencyMatrix, k);

	}

	public final int getNumOfVertex() {
		return numOfVertex;
	}

	public final List<String[]> getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	public final int getK() {
		return k;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.IAlgorithm#setK(int)
	 */
	public void setK(int k) {
		this.k = k;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.IAlgorithm#initialization(int, java.util.List, int)
	 */
	public void initialization(int numOfVertex, List<String[]> adjacencyMatrix,
			int k) {
		this.numOfVertex = numOfVertex;
		this.adjacencyMatrix = adjacencyMatrix;
		this.k = k;

		if (this.preAlg != null) {
			preAlg.initialization(numOfVertex, adjacencyMatrix, k);
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.IAlgorithm#generateDominatingSet()
	 */
	public final void generateDominatingSet() {

		Graph<String, Integer> g = prepareGraph();
		List<VertexDegree> vertexDegreeList = sortVertexAccordingToDegree(g);
		// take use of template method design pattern to maintain submethod
		// sequence
		preComputing(preAlg, g, vertexDegreeList);

		computing(g, vertexDegreeList);

		postComputing();

	}

	/**
	 * do something before the computing
	 * 
	 * @param ag
	 *            , algorithm
	 * @param g
	 *            , graph
	 * @param vertexDegreeList
	 *            , a list of vertices with degree
	 */
	protected void preComputing(IAlgorithm ag, Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList) {

	}

	/**
	 * do something after computing
	 */
	protected void postComputing() {

	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.IAlgorithm#computing(edu.uci.ics.jung.graph.Graph,
	 * java.util.List)
	 */
	public void computing(Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList) {
	}

	/**
	 * get a list of sorted vertices with their degrees from a graph
	 * 
	 * @param g
	 *            , an instance of Graph,
	 * @return List<VertexDegree>, a list of vertices with their degrees
	 */
	protected final List<VertexDegree> sortVertexAccordingToDegree(
			Graph<String, Integer> g) {
		List<VertexDegree> vertexDegreeList = AlgorithmUtil
				.sortVertexAccordingToDegree(g, getNumOfVertex());
		return vertexDegreeList;
	}

	/**
	 * generate an instance of Graph with internal parameters
	 * 
	 * @return Graph<String, Integer>, an instance of Graph
	 */
	protected final Graph<String, Integer> prepareGraph() {
		Graph<String, Integer> g = AlgorithmUtil.prepareGraph(getNumOfVertex(),
				getAdjacencyMatrix());
		this.g = g;
		return g;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.IAlgorithm#getG()
	 */
	public Graph<String, Integer> getG() {
		return g;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.IAlgorithm#setNumOfVertex(int)
	 */
	public void setNumOfVertex(int numOfVertex) {
		this.numOfVertex = numOfVertex;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.IAlgorithm#setAdjacencyMatrix(java.util.List)
	 */
	public void setAdjacencyMatrix(List<String[]> adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.alg.IAlgorithm#getDominatingSetSet()
	 */
	public Set<List<String>> getDominatingSetSet() {
		return dominatingSetSet;
	}
}
