package a05;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.Queue;


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
		private Value val;
		private Node leftOrBottom;
		private Node rightOrTop;
		private RectHV rect;
		private Point2D p;
//		private int N;
		
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
		return N;
	}

//	private int size(Node x) {
//		if (x == null) return 0;
//		return x.N;
//	}
	
	/**
	 * associate the value val with point p
	 * @param p
	 * @param val
	 */
	public void put(Point2D p, Value val)  {
		if (val == null) {
			throw new IllegalArgumentException ("null value cannot be inserted");
		}
				
		root = put(root, p, val, true, new RectHV(0, 0, 1, 1));
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
	private Node put(Node node, Point2D point, Value val, boolean orient, RectHV rect) {
		if (node == null) {
			return new Node(point, val, rect);				
		}
		
		Point2D parentNode = node.p;

		if (orient && parentNode.x() > point.x()) { // Horizontal left
			node.leftOrBottom = put(node.leftOrBottom, point, val, !orient, new RectHV(rect.xmin(), rect.ymin(), parentNode.x(), rect.ymax()));
		}
		else if (orient) { // Horizontal right
			node.rightOrTop = put(node.rightOrTop, point, val, !orient, new RectHV(parentNode.x(), rect.ymin(), rect.xmax(), rect.ymax()));
		}
		else if (parentNode.y() > point.y()) { // Vertical below
			node.leftOrBottom = put(node.leftOrBottom, point, val, orient, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), parentNode.y()));
		}
		else { // Vertical above
			node.rightOrTop = put(node.rightOrTop, point, val, orient, new RectHV(rect.xmin(), parentNode.y(), rect.xmax(), rect.ymax()));
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
		return get(root, p, true);
	}
	
	/**
	* Helper method for get. 
	* @param node
	* @param point
	* @return Value
	*/
	private Value get(Node parentNode, Point2D point, boolean orient) {
        if (parentNode == null) return null;

        if (parentNode.p.compareTo(point) == 0) {
        	return parentNode.val;
        }
        else if (orient && parentNode.p.x() > point.x()) { // Horizontal left
        	return get(parentNode.leftOrBottom, point, !orient);
		}
		else if (orient) { // Horizontal right
        	return get(parentNode.rightOrTop, point, !orient);
		}
		else if (!orient && parentNode.p.y() > point.y()) { // Vertical below
        	return get(parentNode.leftOrBottom, point, !orient);
		}
		else { // Vertical above
        	return get(parentNode.rightOrTop, point, !orient);
		}
	}
	
	/**
	 * does the symbol table contain point p? 
	 * @param p
	 * @return boolean
	 */
	public boolean contains(Point2D p) {
		return get(root, p, true) != null;
	}

	/**
     * all points in the symbol table 
     * @return Iterable<Point2D>
     */
    public Iterable<Point2D> points() {
        List<Point2D> pointsList = new LinkedList<Point2D>();
        Queue<Node> nodeQueue = new Queue<Node>();
        
        if(root == null) return pointsList;
        
        pointsList.add(root.p);
        nodeQueue.enqueue(root);
        
        while(!nodeQueue.isEmpty()) {
            Node currentNode = nodeQueue.dequeue();
            
            if(currentNode.leftOrBottom != null) {
                pointsList.add(currentNode.leftOrBottom.p);
                nodeQueue.enqueue(currentNode.leftOrBottom);
            }
            
            if(currentNode.rightOrTop != null) {
                pointsList.add(currentNode.rightOrTop.p);
                nodeQueue.enqueue(currentNode.rightOrTop);
            }
        }
        return pointsList;
    }
    
    /**
     * all points that are inside the rectangle 
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> points = new LinkedList<Point2D>();
        range(rect, root, points, true);    
        return points;
    }
    
 
    
    /**
     * Helper method for range.
     * @param rect
     * @param root2
     * @param points
     * @param b
     * @return 
     */
    private void range(RectHV rect, Node node, List<Point2D> points, boolean orient) {    	
        Point2D point = node.p;       
        if(rect.contains(point))
            points.add(point);
        
        if(node.leftOrBottom != null && node.leftOrBottom.rect.intersects(rect))
            range(rect, node.leftOrBottom, points, !orient);
        
        if(node.rightOrTop != null && node.rightOrTop.rect.intersects(rect))
            range(rect, node.rightOrTop, points, !orient);
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
		
//		Point2D p6 = new Point2D(0.1, 0.9);
//		Point2D p7 = new Point2D(0.3, 0.7);
//		Point2D p8 = new Point2D(0.5, 0.5);
//		Point2D p9 = new Point2D(0.7, 0.3);
//		Point2D p10 = new Point2D(0.9, 0.1);
		
		RectHV rect = new RectHV(0.4, 0.2, 0.6, 0.7);
		
		System.out.println(myKdTree.range(rect));
		
	}
}