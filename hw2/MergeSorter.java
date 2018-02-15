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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 * in the array. 
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "merge sort";
		outputFileName = "merge.txt";
	}
	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public MergeSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		super(inputFileName);
		algorithm = "merge sort";
		outputFileName = "merge.txt";
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order)
	{
		//if not a 1 or 2 throws exception
		if(order < 1 || order > 2)
		{
			throw new IllegalArgumentException();
		}
		setComparator(order);
		
		
		long begin = System.nanoTime(); //starts timer for sorting method
		mergeSortRec(points); //performs sorting operation
		sortingTime = System.nanoTime() - begin; //ends timer
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int length = pts.length;
		if(length <= 1)
		{
			return;
		}
		//middle element of array
		int mid = length/2; 
		Point[] left = new Point[mid]; 
		Point[] right = new Point[length-mid];
	
		//copies elements 0-mid
		for(int i=0;i<mid;i++){
			left[i] = pts[i];
		}
		
		//copies elements mid-length
		for(int i=mid;i<length;i++)
		{
			right[i-mid] = pts[i];
		}
		mergeSortRec(left);
		mergeSortRec(right); 
		
		//
		Point[] mergeArr = merge(left,right);
		int i = 0;
		for(Point pt : mergeArr)
		{
			pts[i++] = pt;
		}
	}
	
	//helper method
	/**
	 * Sorts and merges the two given arrays into one array.
	 * @param left		left subarray
	 * @param right		right subarray
	 * @return res		merged/sorted array
	 */
	private Point[] merge(Point[] left, Point[] right){
		int sizeL = left.length;
		int sizeR = right.length;
		Point[] res = new Point[sizeL+sizeR];
		int i=0; int j=0; int k=0;
		
		while(i < sizeL && j < sizeR)
		{
			if(pointComparator.compare(left[i], right[j]) != 1)//left[i]<= right[j]
			{ 
				res[k++] = left[i++];//append left[i] to result array
			}
			else //append right[j] to result array
			{ 
				res[k++] = right[j++];
			}
		}
		if(i >= sizeL)
		{
			while(j < sizeR)
			{
				res[k++] = right[j++]; //append right[j],...,right[rightSize-1] to result
			}
		}
		else
		{
			while(i < sizeL)
			{
				res[k++] = left[i++]; //append left[i],...left[leftSize-1] to result
			}
		}
		return res;
	}
}





