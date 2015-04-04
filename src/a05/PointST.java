package a05;

import java.awt.RenderingHints.Key;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.introcs.In;

/**
 * @author Kyle Ottman
 * @author Jasmin Stefanussen
 * Date: April 11, 2015
 * Date last modified: 
 * 
 * sources:
 *
 */

public class PointST<Value> {
	
	private RedBlackBST<Point2D, Value> redBlackTree;
	
	/**
	 * construct an empty symbol table of points 
	 */
	public PointST() {
		 redBlackTree = new RedBlackBST<>();
	}

	/**
	 * is the symbol table empty? 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return redBlackTree.isEmpty();
	}

	/**
	 * number of points 
	 * @return int size
	 */
	public int size() {
		return redBlackTree.size();
	}

	/**
	 * associate the value val with point p
	 * @param p
	 * @param val
	 */
	public void put(Point2D p, Value val)  {
		redBlackTree.put(p, val);
	}
	

	/**
	 * value associated with point p 
	 * @param p
	 * @return
	 */
	public Value get(Point2D p) {
		return redBlackTree.get(p);
	}
	
	/**
	 * does the symbol table contain point p? 
	 * @param p
	 * @return boolean
	 */
	public boolean contains(Point2D p) {
		return redBlackTree.contains(p);
	}
	
	/**
	 * all points in the symbol table 
	 * @return Iterable<Point2D>
	 */
	public Iterable<Point2D> points() {
		return redBlackTree.keys(); 
	}
	
	/**
	 * all points that are inside the rectangle 
	 * @param rect
	 * @return
	 */
	public Iterable<Point2D> range(RectHV rect) {
		Point2D bottomLeft = new Point2D(rect.xmin(), rect.ymin());
		Point2D topRight = new Point2D(rect.xmax(), rect.ymax());
		return redBlackTree.keys(bottomLeft, topRight);
	}
	
	/**
	 * nearest neighbor to point p; null if the symbol table is empty 
	 * @param p
	 * @return
	 */
	public Point2D nearest(Point2D p) {
		Point2D nearestPoint = null;
		double nearestDistance = 0;
		double distance;
		
		for (Point2D point : redBlackTree.keys()) {
			distance = p.distanceSquaredTo(point);
			if (nearestPoint == null) { // only runs once, on the first iteration
				nearestPoint = point;
				nearestDistance = distance;
			}
			else if (distance < nearestDistance) {
				nearestPoint = point;
				nearestDistance = distance;
			}
		}
		
		return nearestPoint;
	}
	
	public static void main(String[] args) {
		
		String inputFile = "/a05/input5.txt";
		In in = new In(inputFile);
		PointST<Point2D> points = new PointST<>();
		
		double x = 0;
		double y = 0;
		try {
			do {
				x = in.readDouble();
				y = in.readDouble();
				Point2D point = new Point2D(x, y);
				points.put(point, point);
			} while (true);
		} catch (Exception e) {}
		
		Point2D myPoint = new Point2D(3, 5);
		Point2D nearest = points.nearest(myPoint);
		System.out.println(nearest);
 	}
}
