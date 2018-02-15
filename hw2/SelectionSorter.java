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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
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
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm = "selection sort";
		outputFileName = "selection.txt";
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public SelectionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException
	{
		super(inputFileName);
		algorithm = "selection sort";
		outputFileName = "selection.txt";
	}
	
	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
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
			
		for(int i=0;i<points.length-1;i++)
		{
			int min = i;
			for(int j = i+1;j<points.length;j++)
			{
				if(pointComparator.compare(points[j], points[min]) == -1)
				{
					min = j;
				}
			}
			swap(min,i);
		}
		sortingTime = System.nanoTime() - begin; //end time - start time
	}	
}
