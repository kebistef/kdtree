package a05;

import java.awt.RenderingHints.Key;

import javax.xml.soap.Node;

/**
 * @author Kyle Ottman
 * @author Jasmin Stefanussen
 * Date: April 11, 2015
 * Date last modified: 
 * 
 * sources:
 *
 */
public class KdTreeST<Value> {
	
	private Node root;		//root of KdTree
	
	private class Node {
		private Key key;
		private Value val;
		private Node left;
		private Node right;
		private int N;
	}
	private int N;
	
	/**
	 * construct an empty symbol table KdTrees 
	 */
	public KdTreeST() {
		
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
		return x.N;
	}
	
	/**
	 * associate the value val with point p
	 * @param p
	 * @param val
	 */
	public void put(Point2D p, Value val)  {
		
	}
	
	/**
	 * value associated with point p 
	 * @param p
	 * @return
	 */
	public Value get(Point2D p) {
		return null;
	}
	
	/**
	 * does the symbol table contain point p? 
	 * @param p
	 * @return boolean
	 */
	public boolean contains(Point2D p) {
		return false;
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
		// unit testing of the methods (not graded) 
	}
}
