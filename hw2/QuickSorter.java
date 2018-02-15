package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.lang.IllegalArgumentException; 


/**
 *  
 * @author Akira DeMoss
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		algorithm = "quick sort";
		outputFileName = "quick.txt";
	}
		

	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public QuickSorter(String inputFileName) throws InputMismatchException, FileNotFoundException
	{
		super(inputFileName);
		algorithm = "quick sort";
		outputFileName = "quick.txt";
	}


	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order)
	{
		if(order < 1 || order > 2)
		{
			throw new IllegalArgumentException();
		}
		setComparator(order);
		
		long begin = System.nanoTime(); //start time
		int last = points.length-1; 
		quickSortRec(0,last);
		sortingTime = System.nanoTime() - begin; //end time - start time
	}
	
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if(first >= last)
		{ 
			return;
		}
		int p = partition(first, last);
		quickSortRec(first,p-1);
		quickSortRec(p+1,last);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		//Uses last element as pivot
		Point piv = points[last];
		int low = first-1;
		for(int j=first;j<last;j++)
		{
			if(pointComparator.compare(points[j], piv) == -1) //points[j] <= pivot
			{ 
				low++;
				swap(low,j); //swap points[i] and points[j]
			}
		}
		swap(low+1,last); //swap points[i+1] and points[last]
		return low+1; 
	}		
}
