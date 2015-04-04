package a05;

import java.awt.RenderingHints.Key;

import edu.princeton.cs.algs4.ST;

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
	
	private Node root;
	
	private class Node {
		private Value val;
		private Node left;
		private Node right;
		private Point2D point;
		private int size;
		
		public Node(Point2D point, Value val, int size) {
			this.point = point;
			this.val = val;
			this.size = size;
 		}
		
	}
	
	/**
	 * construct an empty symbol table of points 
	 */
	public PointST() {
		ST<Point2D, Value> symbolTable = new ST<>();
	}

	/**
	 * is the symbol table empty? 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * number of points 
	 * @return int size
	 */
	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null) return 0;
		else return x.size;
	}
	/**
	 * associate the value val with point p
	 * @param p
	 * @param val
	 */
	public void put(Point2D p, Value val)  {

	}
	
	private Node put(Node x, Point2D p, Value val) {
		if (x == null) return new Node(p, val, 1);
		int compare = p.compareTo(x.point);
		if (compare < 0) 
			x.left = put(x.left, p, val);
		else if (compare > 0) 
			x.right = put(x.right, p, val);
		else x.val = val;
		return x;
	}

	/**
	 * value associated with point p 
	 * @param p
	 * @return
	 */
	public Value get(Point2D p) {
		return get(root, p);
	}
	
	private Value get(Node x, Point2D p) {
		if (x == null) return  null;
		int compare = p.compareTo(x.point);
		if (compare < 0) 
			return get(x.left, p);
		else if (compare > 0) 
			return get(x.right, p);
		else return x.val;
	}
	
	/**
	 * does the symbol table contain point p? 
	 * @param p
	 * @return boolean
	 */
	public boolean contains(Point2D p) {
		return get(p) != null;
	}
	
	/**
	 * all points in the symbol table 
	 * @return Iterable<Point2D>
	 */
	public Iterable<Point2D> points() {
		
		return null;
	}
	
	/**
	 * all points that are inside the rectangle 
	 * @param rect
	 * @return
	 */
	public Iterable<Point2D> range(RectHV rect) {
		return null;
	}
	
	/**
	 * nearest neighbor to point p; null if the symbol table is empty 
	 * @param p
	 * @return
	 */
	public Point2D nearest(Point2D p) {
		return null;
	}
	
	public static void main(String[] args) {
		Point2D point1 = new Point2D(3, 4);
		Point2D point2 = new Point2D(5, 6);
		Point2D point3 = new Point2D(8, 2);
		
		PointST test = new PointST();
		
		
 	}
}
