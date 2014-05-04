package edu.cdu.fpt.io;

import java.util.List;
import java.util.Properties;

/**
 * This class work for getting input from database
 * 
 * @author : Kai Wang
 * 
 * 
 * 
 */
public class InputDatabase implements IInput {
	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.io.IInput#getAdjacencyInfo()
	 */
	public void getAdjacencyInfo() {

	}

	public void setProperties(Properties properties) {

	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.io.IInput#initialization()
	 */
	public void initialization() {

	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.io.IInput#getNumOfVertex()
	 */
	public int getNumOfVertex() {
		return 0;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cdu.fpt.io.IInput#getAdjacencyMatrix()
	 */
	public List<String[]> getAdjacencyMatrix() {
		return null;
	}
}
