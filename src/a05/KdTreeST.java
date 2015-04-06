package a05;

import java.awt.RenderingHints.Key;

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
	private int N;			//size
	
	private class Node {
		private Key key;
		private Value val;
		private Node lb;
		private Node rb;
		private RectHV rect;
		private Point2D p;
		private int N;
		
		public Node(Point2D point, Value value, RectHV rectangle)
		{
			p = point;
			val = value;
			rect = rectangle;
		}
	}
	
	/**
	 * construct an empty symbol table KdTrees 
	 */
	public KdTreeST() {
		root = null;
		N = 0;
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
		if (val == null) {
			throw new IllegalArgumentException ("null value cannot be inserted");
		}
				
		root = put(root, p, val, true);
	}
	
	/**
	 * Helper method for put.
	 * @param node
	 * @param point
	 * @param val
	 * @param orient
	 * @param rect
	 * @return node
	 */
	private Node put(Node node, Point2D point, Value val, boolean orient) {
		RectHV rect;
		
		if (node == null) {
			rect = new RectHV(1, 1, 1, 1);
			return new Node(point, val, rect);				
		}
		
		Point2D parentNode = node.p;

		if (orient && parentNode.x() > point.x()) { // Horizontal left
			node.lb = put(node.lb, point, val, !orient);
		}
		else if (orient) { // Horizontal right
			node.rb = put(node.rb, point, val, !orient);
		}
		else if (parentNode.y() > point.y()) { // Vertical below
			node.lb = put(node.lb, point, val, orient);
		}
		else { // Vertical above
			node.rb = put(node.rb, point, val, orient);
		}
		
		N++;
		return node;
	}
	
	/**
	 * value associated with point p 
	 * @param p
	 * @return
	 */
	public Value get(Point2D p) {
		return get(root, p);
	}
	
	/**
	* Helper method for get. 
	* @param node
	* @param point
	* @return Value
	*/
	private Value get(Node node, Point2D point) {
	        if (node == null) return null;
	        
	        int cmp = point.compareTo(node.p);
	        
	        if      (cmp < 0) return get(node.lb, point);
	        else if (cmp > 0) return get(node.rb, point);
	        else              return node.val;
	}
	
	/**
	 * does the symbol table contain point p? 
	 * @param p
	 * @return boolean
	 */
	public boolean contains(Point2D p) {
		//TODO
		return false;
	}
	
	/**
	 * all points in the symbol table 
	 * @return Iterable<Point2D>
	 */
	public Iterable<Point2D> points() {
		//TODO:
		return null;
	}
	
	/**
	 * all points that are inside the rectangle 
	 * @param rect
	 * @return
	 */
	public Iterable<Point2D> range(RectHV rect) {
		//TODO
		return null;
	}
	
	/**
	 * nearest neighbor to point p; null if the symbol table is empty 
	 * @param p
	 * @return
	 */
	public Point2D nearest(Point2D p) {
		//TODO 
		return null;
	}
	
	public static void main(String[] args) {
		KdTreeST<Point2D> myKdTree = new KdTreeST<Point2D>();
		
		Point2D p1 = new Point2D(0.7, 0.2);
		myKdTree.put(p1, p1);
		
		Point2D p2 = new Point2D(0.5, 0.4);
		myKdTree.put(p2, p2);

		Point2D p3 = new Point2D(0.2, 0.3);
		myKdTree.put(p3, p3);
		
		Point2D p4 = new Point2D(0.4, 0.7);
		myKdTree.put(p4, p4);
		
		Point2D p5 = new Point2D(0.9, 0.6);
		myKdTree.put(p5, p5);
		
		System.out.println("done");
	}
}
