package edu.cdu.fpt.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class work for getting input from files
 * 
 * @author : Kai
 * 
 */
public class InputFile implements IInput {
	/*
	 * the input file path and name
	 */
	private String inputFile;
	/*
	 * number of vertices shown in the input file, generally in the first line
	 */
	private int numOfVertex;
	/*
	 * the adjacency matrix shown in the input file
	 */
	private List<String[]> adjacencyMatrix;
	private Properties properties;

	private static final String GRAPH_ADJACENCY_MATRIX_CSV = "graph_adjacency_matrix_csv";

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.io.IInput#initialization()
	 */
	public void initialization() {

		String inputFile = properties.getProperty(GRAPH_ADJACENCY_MATRIX_CSV);
		this.inputFile = inputFile;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.io.IInput#getAdjacencyInfo()
	 */
	public void getAdjacencyInfo() {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(this.inputFile));

			String line = null;

			// the first line is number of vertex
			line = reader.readLine();
			numOfVertex = Integer.parseInt(line);
			// the following lines from the 2nd line are adjacency matrix
			adjacencyMatrix = new ArrayList<String[]>();

			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				adjacencyMatrix.add(item);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.io.IInput#getNumOfVertex()
	 */
	public int getNumOfVertex() {
		return numOfVertex;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.io.IInput#getAdjacencyMatrix()
	 */
	public List<String[]> getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
