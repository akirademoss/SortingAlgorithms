package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;



/**
 *  
 * @author Akira DeMoss
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points. 
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "insertion sort";
		outputFileName = "insert.txt";
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public InsertionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		super(inputFileName);
		algorithm = "insertion sort";
		outputFileName = "insert.txt";
	}
	
	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 */
	@Override 
	public void sort(int order) {
		if(order < 1 || order > 2)
		{
			throw new IllegalArgumentException();
		}
		if (order == 2)
		{
			sortByAngle = true;
		}
		setComparator(order);
		long begin = System.nanoTime();
		for (int i = 1; i < points.length; i++) 
		{
			int j = i;
			while(j > 0 && pointComparator.compare(points[j-1], points[j]) == 1)
			{
				swap(j,j-1);
				j--;
			}
		}
		sortingTime = System.nanoTime() - begin; //end time - start time
	}
}
