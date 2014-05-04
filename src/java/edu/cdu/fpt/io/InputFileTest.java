package edu.cdu.fpt.io;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * This test class works for test of InputFile
 * 
 * @author : Kai Wang
 * 
 * 
 */
public class InputFileTest {

	@Test
	public void testGetAdjacencyMatrix() {
		// testcase1.csv
		/*
		 * String[][] adjacencyMatrix = { {1,1,1,1,0,0}, {1,1,0,1,0,0},
		 * {1,0,1,1,0,0}, {1,1,1,1,1,1}, {0,0,0,1,1,1}, {0,0,0,1,1,1}
		 * 
		 * }
		 */
		InputFile input = new InputFile();
		input.setInputFile("resources/testcase1.csv");
		input.getAdjacencyInfo();
		List<String[]> adjacencyMatrix = input.getAdjacencyMatrix();
		Assert.assertArrayEquals(new String[] { "1", "1", "1", "1", "0", "0" },
				adjacencyMatrix.get(0));
	}
}
