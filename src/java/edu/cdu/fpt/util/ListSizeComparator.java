/**
 * @author : Kai
 * @Time : 11:15:27 AM
 */
package edu.cdu.fpt.util;

import java.util.Comparator;
import java.util.List;

/**
 * a comparator for lists
 * 
 * @author Kai Wang
 * 
 */
public class ListSizeComparator implements Comparator<List<String>> {
	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(List<String> l1, List<String> l2) {
		return l2.size() - l1.size();
	}
}
