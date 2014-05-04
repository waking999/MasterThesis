package edu.cdu.fpt.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * a util for general purpose
 * 
 * @author Kai Wang
 * 
 */
public class Util {
	public static final String CONNECTED = "1";
	public static final String UNCONNECTED = "0";

	/**
	 * generate a random graph
	 * 
	 * @param numOfVertex
	 *            , the number of vertices in the graph
	 * @return adjancy matrix
	 */
	public static List<String[]> generateRandGraph(int numOfVertex) {
		List<String[]> adjacencyMatrix = null;
		float adjacentRatio = 0.4f; // the purpose of this ratio is to reduce
									// the number of neighbors of a vertex in
									// order to increase the number of elements
									// in ds. otherwise, a vertex may link to
									// all other vertices and become the only
									// element in the ds.

		adjacencyMatrix = new ArrayList<String[]>(numOfVertex);
		for (int i = 0; i < numOfVertex; i++) {
			String[] row = new String[numOfVertex];
			Arrays.fill(row, UNCONNECTED);

			int adjacentNum = (int) Math.round(Math.random() * numOfVertex);
			adjacentNum = Math.round(adjacentRatio * adjacentNum);
			for (int j = 0; j < adjacentNum; j++) {
				int position;

				position = (int) Math.floor(Math.random() * numOfVertex);

				if (CONNECTED.equals(row[position])) {
					j--;
				} else {
					row[position] = CONNECTED;
				}
			}

			row[i] = CONNECTED;
			adjacencyMatrix.add(row);
		}

		return adjacencyMatrix;

	}

	/**
	 * save the adjacency matrix to file
	 * 
	 * @param adjacencyMatrix
	 *            , adjacency matrix
	 */
	public static void saveToFile(List<String[]> adjacencyMatrix) {
		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			String fileName = System.currentTimeMillis() + ".csv";
			File csvFile = new File(fileName);
			out = new FileOutputStream(csvFile);
			osw = new OutputStreamWriter(out);
			bw = new BufferedWriter(osw);

			int numOfVertex = adjacencyMatrix.size();
			bw.write(numOfVertex + "\r\n");
			for (int i = 0; i < numOfVertex; i++) {
				String[] row = adjacencyMatrix.get(i);
				StringBuffer sb = new StringBuffer();
				for (int j = 0; j < numOfVertex; j++) {
					sb.append(row[j]).append(",");
				}
				bw.write(sb.subSequence(0, sb.length() - 1) + "\r\n");

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				bw.close();
				osw.close();
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	/**
	 * if the str is null or empty
	 * 
	 * @param str
	 *            , string
	 * @return,if the str is null or empty, true; else, false;
	 */
	static public boolean isStrNull(String str) {
		return (str == null || "".equals(str.trim()));
	}
}
