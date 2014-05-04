package edu.cdu.fpt.util;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

/**
 * the test for the general util class
 * 
 * @author Kai Wang
 * 
 */
public class UtilTest {
	@Ignore
	@Test
	/**
	 * test for generate random graph
	 */
	public void testGenerateRandGraph() {
		int numOfVertex = 30;

		List<String[]> adjacencyMatrix = Util.generateRandGraph(numOfVertex);

		for (int i = 0; i < numOfVertex; i++) {
			String[] row = adjacencyMatrix.get(i);
			for (int j = 0; j < numOfVertex; j++) {
				System.out.print(row[j] + ",");
			}
			System.out.println();
		}
	}

	@Ignore
	@Test
	/**
	 * test save to file
	 */
	public void saveToFile() {
		int numOfVertex = 400;

		List<String[]> adjacencyMatrix = Util.generateRandGraph(numOfVertex);
		Util.saveToFile(adjacencyMatrix);
	}

}
