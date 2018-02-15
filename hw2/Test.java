package edu.iastate.cs228.hw2;
import java.io.FileNotFoundException;

//Quick Test
public class Test {
	


	 public static void main(String[] args) throws FileNotFoundException
	  {
		 
		  Point p1 = new Point(6,2);
		  Point p2 = new Point(1,2);
		  Point p3 = new Point(20,-5);
		  Point p4 = new Point(9,2);
		  Point p5 = new Point(1,-39);
		  Point p6 = new Point(1,15);
		  Point p7 = new Point(39,2);
		  Point p8 = new Point(7,-18);
		  Point p9 = new Point(7,-28);
		  Point p10 = new Point(7,23);
		  Point p11 = new Point(16,2);
		  Point p12 = new Point(12,2);
		  Point p13 = new Point(42,-5);
		  Point p14 = new Point(49,2);
		  Point p15 = new Point(25,-39);
		  Point p16 = new Point(37,15);
		  Point p17 = new Point(32,2);
		  Point p18 = new Point(18,-18);
		  Point p19 = new Point(15,-28);
		  Point p20 = new Point(10,23);
		  Point p21 = new Point(36,2);
		  Point p22 = new Point(26,2);
		  Point p23 = new Point(21,-5);
		  Point p24 = new Point(23,2);
		  Point p25 = new Point(45,-39);
		  Point p26 = new Point(41,15);
		  Point p27 = new Point(44,2);
		  Point p28 = new Point(22,-18);
		  Point p29 = new Point(13,-28);
		  Point p30 = new Point(11,23);
			  
		  Point[] sort = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10};
		  Point[] sort2 = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20};
		  Point[] sort3 = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, 
				  		   p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,};
		  
		  //Testing InsertionSorter  
		  InsertionSorter s = new InsertionSorter(sort);
		  s.sort(1);
		  System.out.println(s.stats());
		  s.writePointsToFile();
		  //Testing InsertionSorter sort 2
		  InsertionSorter s2 = new InsertionSorter(sort2);
		  s2.sort(1);
		  System.out.println("InsertionSorter 2 stats:"+ s2.stats());
		  s2.writePointsToFile();
		  //Testing InsertionSorter sort 3
		  InsertionSorter s3 = new InsertionSorter(sort3);
		  s3.sort(1);
		  System.out.println("InsertionSorter 2 stats:"+ s3.stats() + "\n");
		  s3.writePointsToFile();
		  
		  //Testing MergeSorter
		  MergeSorter m = new MergeSorter(sort);
		  m.sort(1);
		  System.out.println(m.stats());
		  m.writePointsToFile();
		  //Testing MergeSorter sort 2
		  InsertionSorter m2 = new InsertionSorter(sort2);
		  m2.sort(1);
		  System.out.println("MergeSorter sort 2 stats:"+ m2.stats());
		  m2.writePointsToFile();
		  //Testing MergeSorter sort 3
		  InsertionSorter m3 = new InsertionSorter(sort3);
		  m3.sort(1);
		  System.out.println("MergeSorter sort 3 stats:"+ m3.stats() + "\n");
		  m3.writePointsToFile();
		  
		  //Testing QuickSorter
		  QuickSorter q = new QuickSorter(sort);
		  q.sort(1);
		  System.out.println(q.stats());
		  q.writePointsToFile();
		  //Testing QuickSorter sort 2
		  InsertionSorter q2 = new InsertionSorter(sort2);
		  q2.sort(1);
		  System.out.println("QuickSorter sort 2 stats:"+ q2.stats());
		  q2.writePointsToFile();
		  //Testing QuickSorter sort 3
		  InsertionSorter q3 = new InsertionSorter(sort3);
		  q3.sort(1);
		  System.out.println("QuickSorter sort 3 stats:"+ q3.stats() + "\n");
		  q3.writePointsToFile();
		  
		  //Testing SelectionSorter
		  SelectionSorter sl = new SelectionSorter(sort);
		  sl.sort(1);
		  System.out.println(sl.stats());
		  sl.writePointsToFile();
		  //Testing SelectionSorter sort 2
		  InsertionSorter sl2 = new InsertionSorter(sort2);
		  sl2.sort(1);
		  System.out.println("SelectionSorter sort 2 stats:"+ sl2.stats());
		  sl2.writePointsToFile();
		  //Testing SelectionSorter sort 3
		  InsertionSorter sl3 = new InsertionSorter(sort3);
		  sl3.sort(1);
		  System.out.println("SelectionSorter sort 3 stats:"+ sl3.stats() + "\n");
		  sl3.writePointsToFile();
		  
	  }
		  
}



