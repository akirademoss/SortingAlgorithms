package edu.iastate.cs228.hw2;

/**
 *  
 * @author Akira DeMoss
 *
 */

import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later on the sorted) sequence and records the employed sorting algorithm, 
 * the comparison method, and the time spent on sorting. 
 *
 */


public abstract class AbstractSorter
{
	
	protected Point[] points;    // Array of points operated on by a sorting algorithm. 
	                             // The number of points is given by points.length.
	
	protected String algorithm = null; // "selection sort", "insertion sort",  
	                                   // "merge sort", or "quick sort". Initialized by a subclass 
									   // constructor.
	protected boolean sortByAngle;     // true if last sort was done by polar angle and false 
									   // if by x-coordinate 
	
	protected String outputFileName;   // "select.txt", "insert.txt", "merge.txt", or "quick.txt"
	
	protected long sortingTime; 	   // execution time in nanoseconds. 
	 
	protected Comparator<Point> pointComparator;  // comparator which compares polar angle if 
												  // sortByAngle == true and x-coordinate if 
												  // sortByAngle == false 
	
	private Point lowestPoint; 	    // lowest point in the array, or in case of a tie, the
									// leftmost of the lowest points. This point is used 
									// as the reference point for polar angle based comparison.

	
	// Add other protected or private instance variables you may need. 
	
	protected AbstractSorter()
	{
		// No implementation needed. Provides a default super constructor to subclasses. 
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	}
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * Sets the instance variable lowestPoint.
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		if (pts == null || pts.length == 0) 
		{
			throw new IllegalArgumentException();
		}
		points = new Point[pts.length];
		lowestPoint = pts[0];
		for (int i = 0; i < pts.length; i++) 
		{
			points[i] = pts[i];
			if (lowestPoint.getY() > points[i].getY()) 
			{
				lowestPoint = points[i];
			} else if (lowestPoint.getY() == points[i].getY()) 
			{
				if (lowestPoint.getX() > points[i].getX()) 
				{
					lowestPoint = points[i];
				}
			}
		}
		
		if (algorithm == "selection sort") 
		{
			outputFileName = "select.txt";
		}
		if (algorithm == "insertion sort") 
		{
			outputFileName = "insert.txt";
		}
		if (algorithm == "merge sort") 
		{
			outputFileName = "merge.txt";
		}
		if (algorithm == "quick sort") 
		{
			outputFileName = "quick.txt";
		}
	}
		

	

	
	/**
	 * This constructor reads points from a file. Sets the instance variables lowestPoint and 
	 * outputFileName.
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   when the input file contains an odd number of integers
	 */
	protected AbstractSorter(String inputFileName) throws FileNotFoundException, InputMismatchException
	{
		try{
			if(inputFileName == "" || inputFileName == null){
				throw new IllegalArgumentException();
			}
			File file = new File(inputFileName);
			Scanner scanner = new Scanner(file);
			int i = 0;
			int minIndex = 0;

			while(scanner.hasNextInt())
			{
				i++;
				scanner.nextInt();
			}
			scanner.close();
			
			if(i % 2 != 0)
			{
				throw new InputMismatchException();
			}

			points = new Point[i / 2];
			i = 0;
			scanner = new Scanner(file);

			while(scanner.hasNextInt()){
				points[i] = new Point(scanner.nextInt(), scanner.nextInt());
				i++;
			}

			for(int j=0;j<points.length;j++){
				if (points[j].getY() < points[minIndex].getY()){
					minIndex = j;
				}
			}
			scanner.close();
			
			lowestPoint = points[minIndex];
		}
		catch(FileNotFoundException e)
		{
			throw e;
		}
		
		if (algorithm == "selection sort") 
		{
			outputFileName = "select.txt";
		}
		if (algorithm == "insertion sort") 
		{
			outputFileName = "insert.txt";
		}
		if (algorithm == "merge sort") 
		{
			outputFileName = "merge.txt";
		}
		if (algorithm == "quick sort") 
		{
			outputFileName = "quick.txt";
		}
	}
	

	/**
	 * Sorts the elements in points[]. 
	 * 
	 *     a) in the non-decreasing order of x-coordinate if order == 1
	 *     b) in the non-decreasing order of polar angle w.r.t. lowestPoint if order == 2 
	 *        (lowestPoint will be at index 0 after sorting)
	 * 
	 * Sets the instance variable sortByAngle based on the value of order. Calls the method 
	 * setComparator() to set the variable pointComparator and use it in sorting.    
	 * Records the sorting time (in nanoseconds) using the System.nanoTime() method. 
	 * (Assign the time to the variable sortingTime.)  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle w.r.t lowestPoint 
	 *
	 * @throws IllegalArgumentException if order is less than 1 or greater than 2
	 */
	public abstract void sort(int order) throws IllegalArgumentException; 
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the assignment description. 
	 */
	public String stats()
	{
		if(algorithm == "merge sort")
		{
			return "" + algorithm + "  \t " + points.length + "\t" + sortingTime;
		}
		if(algorithm == "quick sort")
		{
			return "" + algorithm + "  \t " + points.length + "\t" + sortingTime;
		}
		else
		{
		return "" + algorithm + "   " + points.length + "\t" + sortingTime;
		}
	}
	
	
	/**
	 * Write points[] to a string.  When printed, the points will appear in order of increasing
	 * index with every point occupying a separate line.  The x and y coordinates of the point are 
	 * displayed on the same line with exactly one blank space in between. 
	 */
	@Override
	public String toString() 
	{
		String pts = "";
		for (int i=0;i<points.length;i++) 
		{
			pts = pts + "(" + points[i].getX() + ", " + points[i].getY() + ")" + "\n";
		}
		return pts;
	}

	
	/**
	 *  
	 * This method, called after sorting, writes point data into a file by outputFileName. It will 
	 * be used for Mathematica plotting to verify the sorting result.  The data format depends on 
	 * sortByAngle.  It is detailed in Section 4.1 of the assignment description assn2.pdf. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writePointsToFile() throws FileNotFoundException
	{
		PrintWriter printer = new PrintWriter(outputFileName);
		if (!sortByAngle) 
		{
			for (int i=0;i<points.length;i++) 
			{
				printer.write("" + points[i].getX() + " " + points[i].getY() + System.lineSeparator());
			}
		}
		else if (sortByAngle) 
		{
			printer.write("" + lowestPoint.getX() + " " + lowestPoint.getY() + System.lineSeparator());
			for (int i=0;i<points.length;i++) 
			{
				printer.write("" + points[i].getX() + " " + points[i].getY() + " " + lowestPoint.getX() + " " + lowestPoint.getY() + " " + points[i].getX() + " " + points[i].getY() + " " + System.lineSeparator());
			}
		}
		printer.close();
	}	

	
	/**
	 * Generates a comparator on the fly that compares by polar angle if sortByAngle == true
	 * and by x-coordinate if sortByAngle == false. Set the protected variable pointComparator
	 * to it. Need to create an object of the PolarAngleComparator class and call the compareTo() 
	 * method in the Point class, respectively for the two possible values of sortByAngle.  
	 * 
	 * 
	 */
	protected void setComparator(int order) 
	{

		if(order == 1){
			pointComparator = new Comparator<Point>(){
				@Override
				public int compare(Point point1, Point point2){
					return point1.compareTo(point2);
				}
			};
			sortByAngle = false;
		}
		else if(order == 2){
			sortByAngle = true;
			pointComparator = new PolarAngleComparator(lowestPoint){};
		}
		
	}

	
	/**
	 * Swap the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}	
}
