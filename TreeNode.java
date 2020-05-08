package application;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tree {
	private Node root; // the root node of the tree

	/******************************** Implement Here: *****************************************/
	/*Finish the two basic functions below "insertNode(Node node)" and "deleteNode(Node node)*/

	/*
	 * The function below is to insert a new node into the binary search tree. You need to
	 * implement the code: if a node is successfully
	 * inserted, it returns "true"; if the node to be inserted has the value
	 * already exist in the tree, it should not be inserted and return "false".
	 * So you need to modify the code to forbid nodes with repeating values to
	 * be inserted.
	 */
	public boolean insertNode(Node node) {


        //Implementation Here...
		
		if(root == null)
			root = node;
		else
		{
			Node p = root;
			
			while(p != null) 
			{
				if(node.getValue() == p.getValue())
					return false;
				
				if(node.getValue() < p.getValue())
				{
					if(p.getLeft() != null)
					{
						p = p.getLeft();
					}
					else
					{
						p.setLeft(node);
						node.setParent(p);
						p = null;
//						System.out.println("Node Value: "+node.getValue());
//						System.out.println("Parent Value: "+node.getParent().getValue());
					}
				}
				else
				{
					if(p.getRight() != null)
					{
						p = p.getRight();
					}
					else
					{
						p.setRight(node);
						node.setParent(p);
						p = null;
					}
				}
			}
		}
		
		return true;
	}

	/*
	 * The function below is to delete a selected node from the tree. You need
	 * to finish the implementation. 
	 */
	public boolean deleteNode(Node node) {		

		
        //Implementation Here...
		
//		System.out.println(node.getValue());
//		System.out.println("Left Value: "+node.getLeft().getValue());
//		System.out.println("Right Value: "+node.getRight().getValue());
		
		if(node.getLeft() == null && node.getRight() == null)
		{
//			System.out.println("Case1");
			
			if(node == root)
				root = null;
			else
			{
				if(node.isLeft_child_of_parent())
				{
					node.getParent().setLeft(null);
				}
				else
				{
					node.getParent().setRight(null);
//					System.out.println("I am here");
				}
			}
		}
		else if(node.getLeft()!=null || node.getRight()!= null)
		{
//			System.out.println("Case2");
			
			if(node.getLeft()!=null && node.getRight()==null)
			{
				if(root == node)
				{
					root = root.getLeft();
					return true;
				}
				
//				System.out.println("Sub-Case1");
				
				Node p = node;
				node = node.getLeft();
//				System.out.println("Previous Node Value: "+p.getValue());
//				System.out.println("New Value: "+node.getValue());
				node.setParent(p.getParent());
				
				if(p.getValue() > p.getParent().getValue())
				{
					p.getParent().setRight(node);
				}
				else
				{
					p.getParent().setLeft(node);
				}
				
				
//				node = node.getLeft();
			}
			else if(node.getLeft()==null && node.getRight()!=null)
			{
				if(root == node)
				{
					root = root.getRight();
					return true;
				}
				
//				System.out.println("Sub-Case2");
				
				Node p = node;
				node = node.getRight();		
				
//				System.out.println("Previous Node Value: "+p.getValue());
//				System.out.println("New Value: "+node.getValue());
				
				node.setParent(p.getParent());
				
				if(p.getValue() < p.getParent().getValue())
				{
					p.getParent().setLeft(node);
				}
				else
				{
					p.getParent().setRight(node);
				}
				
//				node.getRight().setParent(node.getParent());
//				node = node.getRight();
			}
		}
		
		// Case 3 when the node has both left and right childr
		if(node.getLeft() != null && node.getRight() != null)
		{
//			System.out.println("Case3");
			
			Node p1 = node;
			Node p = node.getLeft();
			while(p.getRight()!=null)
			{
//				System.out.println("While");
				p = p.getRight();
			}
			if(p.getLeft() != null)
			{
//				System.out.println("Sub-Case 3-1");
				
				Node p2 = new Node();
				p2 = p;
				
				Node p3 = new Node();
				p3 = p.getLeft();
				
				Node p4 = new Node();
				p4 = p1;
				
				
				if(p.isLeft_child_of_parent())
				{
					p.getParent().setLeft(null);
					p2.getParent().setLeft(p3);
				}
				else
				{
					p.getParent().setRight(null);
					p2.getParent().setRight(p3);
				}
				
				p2.setLeft(p4.getLeft());
				p2.setRight(p4.getRight());
				
				if(p1.isLeft_child_of_parent())
				{
					if(root == node)
					{
						root = p2;
						return true;
					}
					p1.getParent().setLeft(null);
					p4.getParent().setLeft(p2);
				}
				else
				{
					if(root == node)
					{
						root = p2;
						return true;
					}
					p1.getParent().setRight(null);
					p4.getParent().setRight(p2);
				}
								
			}
			else
			{
//				System.out.println("Sub-Case 3-2");
				
				Node p2 = new Node();
				p2 = p;
				
				if(p.isLeft_child_of_parent())
					p.getParent().setLeft(null);
				else
					p.getParent().setRight(null);
				
//				p2.setParent(p1.getParent());
				// setting the new value's left and right values
				p2.setLeft(p1.getLeft());
				p2.setRight(p1.getRight());
				
				Node p3 = new Node();
				p3 = p1;
				
				if(p1.isLeft_child_of_parent())
				{
					if(root == node)
					{
						root = p2;
						return true;
					}
					p1.getParent().setLeft(null);
					p3.getParent().setLeft(p2);					
				}
				// if it is the right child
				else
				{
					// If it is the root
					if(root == node)
					{
						root = p2;
						return true;
					}
					p1.getParent().setRight(null);
					p3.getParent().setRight(p2);
				}
				
			}
		}
		
		//The two variables below are for GUI use only. You don't need to use them.
		select_node_value = -1;	
		selected_node = null;
		return true;		
	}
	
	
	
	/*
	 * The function below is to insert a new node into a red-black tree. It may first call the
	 * "insertNode(Node node) function above to perform binary search tree insertion. Then according to this new
	 * node situation, following the four cases rules and do the corresponding updating.
	 * 
	 * Attention: to test this function, you need to do a little change on the "MainGUI.java"
	 * 			  (1) Comment the line 173
	 * 			  (2) Uncomment the line 174
	 * 
	 * In this way, new node will be inserted following Red-black tree rule.
	 */
	public boolean insertNodeRedBlack(Node node) {


        //Implementation Here...
		
		System.out.println("Red Black Insertion Node: "+node.getValue());
		
//		Node current_node = root;
//		Node parent = node.getParent();
//		Node uncle = null;
//		Node grand_parent = node.getParent().getParent();
		
		if(root == null)
		{
			insertNode(node);
			root = node;
			node.setColor(true);
//			current_node = node;
			return true;
		}
		else
		{
			insertNode(node);
			node.setColor(false);
			
//			left rotation testing code
//			if(node.getValue() == 3)
//			{
//				rightRotate(node.getParent().getParent());
//				return true;
//			}
			
			if(node.getParent().getColor() == true)
			{
				System.out.println("I am case 2");
				return true;
			}
			else if(node.getParent().getColor() == false)
			{
				checkNode(node);
			}
		}
			
		return true;
	}
	
	public boolean checkNode(Node node)
	{
		if(node.getParent().getParent() == null)
		{
			return true;
		}
		
		Node parent = node.getParent();
		Node grand_parent = node.getParent().getParent();
		Node uncle;
		
		if(parent.isLeft_child_of_parent())
			uncle = node.getParent().getParent().getRight();
		else
			uncle = node.getParent().getParent().getLeft();
		
		// LL -	Working	; LR(Done) - Working(Done); RR - Working(Done); RL - Working(Done)
		if(uncle == null) 
		{
			System.out.println(parent.isLeftChildOfparent(parent));
			System.out.println(node.isLeftChildOfparent(node));
			
			System.out.println("Node Value: "+node.getValue());
			System.out.println("Node's Parent Value: "+parent.getValue());
			
			if(parent.isLeftChildOfparent(parent) && node.isLeftChildOfparent(node))
			{
				System.out.println("I am in 1st Null Uncle condition");
				leftLeft(node);
				System.out.println("aaaaaaaaa"+node.getColor());
				System.out.println("bbbbbbbbb"+parent.getColor());
				System.out.println("ccccccccc"+grand_parent.getColor());
			}						
			else if(parent.isLeftChildOfparent(parent) && !(node.isLeftChildOfparent(node)))
			{
				System.out.println("I am in 2nd Null Uncle condition");
				leftRight(node);
			}
			else if(!(parent.isLeftChildOfparent(parent)) && !(node.isLeftChildOfparent(node)))
			{
				System.out.println("I am in 3rd Null Uncle condition");
				rightRight(node);											
			}						
			else if(!(parent.isLeftChildOfparent(parent)) && node.isLeftChildOfparent(node))
			{
				System.out.println("I am in 4th Null Uncle condition");
				rightLeft(node);
			}
			
			return true;
		}
		
		if(uncle.getColor() == false)
		{
			System.out.println("I am case 3");
			
//			System.out.println("Node Value: "+node.getValue());
//			System.out.println("Parent Value: "+parent.getValue());
//			System.out.println("GP Value: "+grand_parent.getValue());
			
			if(node != null)
			{			
				
				if(parent.getColor() == true)
				{
					return true;
				}
				else
				{					
					parent.setColor(true);							
					uncle.setColor(true);
				}
				if(grand_parent == root || grand_parent == null)
				{
					return true;
				}
				else
				{
					grand_parent.setColor(false);
					node = grand_parent;
					checkNode(node);
				}
				
				System.out.println("In While");
				System.out.println("Node Value: "+node.getValue()+" Color: "+node.getColor());
				System.out.println("Parent Value: "+parent.getValue()+" Color: "+parent.getColor());
				System.out.println("Uncle Value: "+uncle.getValue()+" Color: "+uncle.getColor());
				System.out.println("GP Value: "+grand_parent.getValue()+" Color: "+grand_parent.getColor());
				System.out.println();
				
//				if(grand_parent != null)
//				{
//					node = grand_parent;
//					checkNode(node);
//				}
//				else 
//					return true;
			}
			
		}
		else if(uncle.getColor() == true)
		{
			
			System.out.println(parent.isLeftChildOfparent(parent));
			System.out.println(node.isLeftChildOfparent(node));
			
			System.out.println("Node Value: "+node.getValue());
			System.out.println("Node's Parent Value: "+parent.getValue());
			
			if(parent.isLeftChildOfparent(parent) && node.isLeftChildOfparent(node))
			{
				System.out.println("I am in 1st Black Uncle condition");
				leftLeft(node);
			}						
			else if(parent.isLeftChildOfparent(parent) && !(node.isLeftChildOfparent(node)))
			{
				System.out.println("I am in 2nd Black Uncle condition");
				leftRight(node);
			}
			else if(!(parent.isLeftChildOfparent(parent)) && !(node.isLeftChildOfparent(node)))
			{
				System.out.println("I am in 3rd Black Uncle condition");
				rightRight(node);											
			}						
			else if(!(parent.isLeftChildOfparent(parent)) && node.isLeftChildOfparent(node))
			{
				System.out.println("I am in 4th Black Uncle condition");
				rightLeft(node);
			}
			
//			if(parent.isLeftChildOfparent(parent) && node.isLeftChildOfparent(node))
//				leftLeft(node);
//			else if(parent.isLeftChildOfparent(parent) && !(node.isLeftChildOfparent(node)))
//				leftRight(node);
//			else if(!(parent.isLeftChildOfparent(parent)) && !(node.isLeftChildOfparent(node)))
//				rightRight(node);
//			else if(!(parent.isLeftChildOfparent(parent)) && node.isLeftChildOfparent(node))
//				rightLeft(node);
		}
		return true;
	}
	
	public void swapColors(Node node)
	{
		if(node.getColor() == false)
			node.setColor(true);
		else 
			node.setColor(false);
	}
	
	public void leftLeft(Node node)
	{
		Node parent = node.getParent();
		Node grand_parent = node.getParent().getParent();
		
		rightRotate(grand_parent);
		swapColors(grand_parent);
		swapColors(parent);
	}
	
	public void leftRight(Node node)
	{
		Node parent = node.getParent();
		Node grand_parent = node.getParent().getParent();
		
		leftRotate(parent);
		rightRotate(grand_parent);
		swapColors(grand_parent);
		swapColors(node);
	}
	
	public void rightRight(Node node)
	{
		Node parent = node.getParent();
		Node grand_parent = node.getParent().getParent();
		
		leftRotate(grand_parent);
		swapColors(grand_parent);
		swapColors(parent);
	}
	
	public void rightLeft(Node node)
	{
		Node parent = node.getParent();
		Node grand_parent = node.getParent().getParent();
		
		rightRotate(parent);
		leftRotate(grand_parent);
		swapColors(grand_parent);
		swapColors(node);
	}
	
	public void rightRotate(Node node)
	{
//		node = node.getParent().getParent();
//		System.out.println("Right Check of "+node.getValue());
		
		Node parent_of_node = node.getParent();
		Node left_child_of_node = node.getLeft();
		Node right_child_of_left_subtree = node.getLeft().getRight();
		
//		check if the left child of node is null
		if(left_child_of_node == null)
		{
			System.out.println("Right Rotation is not possible");
			return;
		}
		
//		Step 1 : Connect left child of right subtree to node
		node.setLeft(right_child_of_left_subtree);
		
//		Step 2 : Connect the node's parent to the node's right child parent
		if(right_child_of_left_subtree != null)
		{
			right_child_of_left_subtree.setParent(node);
		}
		
		left_child_of_node.setParent(parent_of_node);
		
		if(parent_of_node == null)
		{
			root = left_child_of_node;
		}
		else if(node.isLeft_child_of_parent())
		{
			parent_of_node.setLeft(left_child_of_node);
		}
		else
		{
			parent_of_node.setRight(left_child_of_node);
		}
		
//		Step 3: Connect node as the left child of node's right child
		left_child_of_node.setRight(node);
		node.setParent(left_child_of_node);
		
	}
	
	public void leftRotate(Node node)
	{
//		node = node.getParent().getParent();
//		System.out.println("Left Check of "+node.getValue());
		
		Node parent_of_node = node.getParent();
		Node rightchild_of_node = node.getRight();
		Node leftchild_of_right_subtree = node.getRight().getLeft();
		
//		check if the right child of node is null 
		if(rightchild_of_node == null)
		{
			System.out.println("Left Rotation is not possible");
			return;
		}
			
		
//		Step 1 : Connect left child of right subtree to node
		node.setRight(leftchild_of_right_subtree);
//		leftchild_of_right_subtree.setParent(node);
		
//		Step 2 : Connect the node's parent to the node's right child parent
		if(parent_of_node == null)
		{
			root = rightchild_of_node;
		}
		else if(node.isLeft_child_of_parent())
		{
			parent_of_node.setLeft(rightchild_of_node);
		}
		else
		{
			parent_of_node.setRight(rightchild_of_node);
		}
		
//		Step 3: Connect node as the left child of node's right child
		rightchild_of_node.setLeft(node);
		
	}
	

	/************************************************** End of Implementation **************************************************************/

	
	
	/***********************************************
	 * Below are read-only! You don't need to make any changes
	 ******************************/
	/***********************************************
	 * The data members and functions below are just for the GUI Part
	 ******************************************/

	/* Below are the GUI part you don't need to use */
	private int tree_max_height; // record the maximum height of the tree
	private int tree_max_width; // record the maximum width of the tree
	private Canvas canvas; // the canvas where a node is drawn
	private GraphicsContext gc; // the brush used to draw on canvas
	private int canvas_width = 640;
	private int canvas_height = 480;
	private Vector<Vector<Node>> layer_nodes = new Vector<Vector<Node>>(); // store
																			// each
																			// node
																			// into
																			// the
																			// corresponding
																			// layer
	private Node new_node; // the newly inserted node
	private double old_dragging_x;
	private double old_dragging_y;
	private boolean dragging = false;
	private Node selected_node;
	private double delta_x;
	private double delta_y;
	private int radius = 30; // the size of the node
	private int select_node_value; // indicate which node is selected

	public Tree() {

	}

	public Tree(Canvas c, GraphicsContext gc) {
		canvas = c;
		this.gc = gc;
	}

	/*
	 * The function below is for GUI use to make sure the tree fits in the
	 * canvas: (1) according to the current tree distribution, determine the
	 * tree height and width; (2) Check whether there is any overlap between
	 * tree nodes
	 */
	private void organizeTree() {
		// Reset several variables
		layer_nodes.clear(); // clear the layer

		// Set positions on Canvas

	}

	private void setNewProp(Node node) {
		if(node == null)
			return;
		setNewNodeProperties(node);
		if (node.getLeft() != null)
			setNewProp(node.getLeft());
		if (node.getRight() != null)
			setNewProp(node.getRight());
	}

	// Draw the tree on canvas
	public void showTree(boolean insertion_occur) {

		if (insertion_occur) {
			// Set the basic properties for then new node
			setNewProp(root);
		}

		// Traverse the tree and draw all the nodes onto canvas
		bfsTreeDraw(this);

	}

	/* For mouse dragging use (update the sub-tree) */
	private void updateTreePos(Node moving_node, double delta_x, double delta_y) {

		Queue<Node> queue = new LinkedList<Node>();
		Node current_node;

		queue.add(moving_node); // push the root node into queue
		while (!queue.isEmpty()) {
			current_node = queue.remove();

			// Check left child
			if (current_node.getLeft() != null) {
				Point2D pos = new Point2D(current_node.getLeft().getPosition().getX() + delta_x,
						current_node.getLeft().getPosition().getY() + delta_y);
				current_node.getLeft().setPosition(pos);
				queue.add(current_node.getLeft());

			}

			// Check right child
			if (current_node.getRight() != null) {
				Point2D pos = new Point2D(current_node.getRight().getPosition().getX() + delta_x,
						current_node.getRight().getPosition().getY() + delta_y);
				current_node.getRight().setPosition(pos);
				queue.add(current_node.getRight());

			}
		}

	}

	/* Apply Breath First Search Tree to render all the node */
	private void bfsTreeDraw(Tree tree) {
		Queue<Node> queue; // store the retrieved nodes from edges
		Node current_node; // point to the current node processing on

		if (tree.getRoot() == null) {
			return;
		}

		queue = new LinkedList<Node>();

		queue.add(tree.getRoot()); // push the root node into queue
		while (!queue.isEmpty()) {
			current_node = queue.remove();
			current_node.showNode(select_node_value); // draw the node on the
														// canvas

			// Check left child
			if (current_node.getLeft() != null) {

				// Draw the edge between current node the the left child
				double start_x = current_node.getPosition().getX() + radius / 2;
				double start_y = current_node.getPosition().getY() + radius / 2;
				double end_x = current_node.getLeft().getPosition().getX() + radius / 2;
				double end_y = current_node.getLeft().getPosition().getY() + radius / 2;

				// Draw the edge
				gc.setStroke(Color.BLACK);
				gc.strokeLine(start_x, start_y, end_x, end_y);

				// Show current node again to cover the edge
				current_node.showNode(select_node_value);

				queue.add(current_node.getLeft());
			}

			// Check right child
			if (current_node.getRight() != null) {
				// Draw the edge between current node the the right child
				double start_x = current_node.getPosition().getX() + radius / 2;
				double start_y = current_node.getPosition().getY() + radius / 2;
				double end_x = current_node.getRight().getPosition().getX() + radius / 2;
				double end_y = current_node.getRight().getPosition().getY() + radius / 2;

				// Draw the edge
				gc.setStroke(Color.BLACK);
				gc.strokeLine(start_x, start_y, end_x, end_y);

				// Show current node again to cover the edge
				current_node.showNode(select_node_value);

				queue.add(current_node.getRight());
			}
		}

	}

	/*
	 * Apply Breath First Search Tree to (1) find the parent of the target_node
	 * and setup connection between them (2) set the GUI properties for the node
	 */
	private void setNewNodeProperties(Node target_node) {

		Queue<Node> queue; // store the retrieved nodes from edges
		Node current_node; // point to the current node processing on
		layer_nodes.clear(); // clear all the layer

		if (target_node == root) {

			target_node.setDepth(0); // set the root depth to 0
			target_node.setParent(null);
			Point2D pos = new Point2D(320, 5); // do the setting for the root
			target_node.setPosition(pos);
			target_node.setDepth(0);

			Vector<Node> layer = new Vector<Node>();
			layer.add(target_node);
			layer_nodes.add(layer);
			target_node.setLayer_idx(0);

			return;
		}

		// Start the BFS search
		queue = new LinkedList<Node>();
		queue.add(getRoot()); // push the root node into queue

		while (!queue.isEmpty()) {
			current_node = queue.remove();

			/* If current node is in a new layer, then create a new layer */
			if (layer_nodes.size() - 1 < current_node.getDepth()) {
				Vector<Node> layer = new Vector<Node>();
				layer.add(current_node);
				layer_nodes.add(layer);
				current_node.setLayer_idx(0);
			} else {
				layer_nodes.get(current_node.getDepth()).add(current_node);
				current_node.setLayer_idx(layer_nodes.get(current_node.getDepth()).size() - 1);
			}

			if (current_node.getLeft() == target_node) {

				/* Set the depth and parent information */
				target_node.setDepth(current_node.getDepth() + 1);
				target_node.setParent(current_node);
				target_node.setLeft_child_of_parent(true);
			} else if (current_node.getRight() == target_node) {
				/* Set the depth and parent information */
				target_node.setDepth(current_node.getDepth() + 1);
				target_node.setParent(current_node);
				target_node.setLeft_child_of_parent(false);

			}

			/*
			 * If the current node does not have the target node as one of its
			 * children, then keep finding it
			 */
			if (current_node.getLeft() != null)
				queue.add(current_node.getLeft());
			if (current_node.getRight() != null)
				queue.add(current_node.getRight());

		}

		/* Set the 2D position on Canvas */
		setNodePosition(target_node);
	}

	/*
	 * Set the target_node position. This new node may affect the other node's
	 * current positions
	 */
	void setNodePosition(Node target_node) {
		double x_left_offset = -(double) radius * 6.0 / (double) target_node.getDepth() + 2.0; // the
																								// left
																								// child
																								// x
																								// relative
																								// position
																								// to
																								// the
																								// current
																								// node's
																								// x
																								// position
																								// (maximum
																								// offset)
		double x_right_offset = (double) radius * 6.0 / (double) target_node.getDepth() + 2.0;
		int y_offset = radius * 2; // the left child x position relative
									// (maximum offset)

		// Below are the two indicator whether need to adjust the tree size
		boolean layer_crowded = false;
		boolean tree_too_high = false;

		// Set position for the target_node
		Point2D pos = new Point2D(0, 0); // the position will be assigned to the
											// target_node
		if (target_node.isLeft_child_of_parent()) {
			pos = new Point2D(target_node.getParent().getPosition().getX() + x_left_offset,
					target_node.getParent().getPosition().getY() + y_offset);
		} else {
			pos = new Point2D(target_node.getParent().getPosition().getX() + x_right_offset,
					target_node.getParent().getPosition().getY() + y_offset);
		}
		target_node.setPosition(pos);

		// Check this new node has overlapping with its left or right neighbors
		if (target_node.getLayer_idx() > 0) // check overlapping with left
											// neighbor
		{
			int left_neighbor = target_node.getLayer_idx() - 1;
			Point2D left_pos = layer_nodes.get(target_node.getDepth()).get(left_neighbor).getPosition();
			if (pos.getX() - left_pos.getX() < radius) {
				layer_crowded = true;
				System.out.println("Overlapping with left! "
						+ layer_nodes.get(target_node.getDepth()).get(left_neighbor).getValue());
			}
		}

	}

	/* Tracking the mouse event to see whether a node is being dragged. */
	public void finishNodeDragging(double x, double y) {
		dragging = false;
		delta_x = 0;
		delta_y = 0;
	}

	/* Tracking the mouse event to see whether a node is being dragged. */
	public void checkNodeDragging(double x, double y) {
		for (int j = 0; j < layer_nodes.size(); j++) {
			for (int i = 0; i < layer_nodes.get(j).size(); i++) {
				double node_x = layer_nodes.get(j).get(i).getPosition().getX();
				double node_y = layer_nodes.get(j).get(i).getPosition().getY();
				if (((node_x + radius / 2 - x) * (node_x + radius / 2 - x)
						+ (node_y + radius / 2 - y) * (node_y + radius / 2 - y)) < radius * radius / 4) {
					dragging = true;
					selected_node = layer_nodes.get(j).get(i);
					old_dragging_x = x;
					old_dragging_y = y;
					select_node_value = selected_node.getValue();

					gc.clearRect(0, 0, canvas_width, canvas_height);
					bfsTreeDraw(this);
					break;
				}
			}
			if (dragging == true)
				break;
		}

		// update the selection
		if (dragging == false) {
			select_node_value = -1;
			gc.clearRect(0, 0, canvas_width, canvas_height);
			bfsTreeDraw(this);
		}

	}

	/*
	 * If a node is being dragged, update its position and its children's
	 * positions
	 */
	public void doNodeDragging(double x, double y) {
		// update dragged node position
		if (dragging == true) {

			delta_x = x - old_dragging_x;
			delta_y = y - old_dragging_y;
			Point2D pos = new Point2D(selected_node.getPosition().getX() + delta_x,
					selected_node.getPosition().getY() + delta_y);
			selected_node.setPosition(pos);
			old_dragging_x = x;
			old_dragging_y = y;
			updateTreePos(selected_node, delta_x, delta_y);
			gc.clearRect(0, 0, canvas_width, canvas_height);
			bfsTreeDraw(this);
		}

	}

	/* Adjust the tree width according to the layer value */
	// layer_idx stores the layer index that has issue with the new node
	void adjustTreeWidth(int layer_idx) {
		int x_left_offset = -radius * 3; // the left child x relative position
											// to the current node's x position
											// (maximum offset)
		int x_right_offset = radius * 3;
		int y_offset = radius * 2; // the left child x position relative
									// (maximum offset)

		// Calculate the width of problem layer = # * (node size + node gap)
		int target_layer_width = layer_nodes.get(layer_idx).size() * radius * 2
				+ (layer_nodes.get(layer_idx).size() - 1) * x_right_offset * 2;
		Point2D layer_center = new Point2D(canvas_width / 2, new_node.getPosition().getY());

		// If the problem layer width is smaller than the canvas width, then no
		// need to adjust the node size but change the current layer's position
		if (target_layer_width < canvas_width) {
			// If there are even number of nodes
			if (layer_nodes.get(layer_idx).size() % 2 == 0) {
				int left_node_besides_center = layer_nodes.get(layer_idx).size() / 2;
				int right_node_besides_center = layer_nodes.get(layer_idx).size() / 2 + 1;
				System.out.println("center: " + layer_center.getX());
				// Update the nodes positions on the left side
				Point2D pos = new Point2D(layer_center.getX() + x_left_offset, layer_center.getY());
				System.out.println("left: " + pos.getX());
				layer_nodes.get(layer_idx).get(left_node_besides_center).setPosition(pos);
				for (int i = left_node_besides_center - 1; i >= 0; i--) {
					pos = new Point2D(layer_nodes.get(layer_idx).get(i + 1).getPosition().getX() + x_left_offset * 2,
							layer_center.getY());
					layer_nodes.get(layer_idx).get(i).setPosition(pos);
				}

				// Update the nodes positions on the left side
				pos = new Point2D(layer_center.getX() + x_right_offset, layer_center.getY());
				System.out.println("Right: " + pos.getX());
				layer_nodes.get(layer_idx).get(left_node_besides_center).setPosition(pos);
				for (int i = right_node_besides_center + 1; i < layer_nodes.get(layer_idx).size(); i++) {
					pos = new Point2D(layer_nodes.get(layer_idx).get(i - 1).getPosition().getX() + x_right_offset * 2,
							layer_center.getY());
					layer_nodes.get(layer_idx).get(i).setPosition(pos);
				}

			}
			// If there are odd number of nodes
			else {
				int node_at_center = (layer_nodes.get(layer_idx).size() + 1) / 2;

				Point2D pos = new Point2D(layer_center.getX(), layer_center.getY());
				layer_nodes.get(layer_idx).get(node_at_center).setPosition(pos);

				// Update the nodes positions on the left side
				for (int i = node_at_center - 1; i >= 0; i--) {
					pos = new Point2D(layer_nodes.get(layer_idx).get(i + 1).getPosition().getX() + x_left_offset * 2,
							layer_center.getY());
					layer_nodes.get(layer_idx).get(i).setPosition(pos);
				}

				// Update the nodes positions on the left side
				for (int i = node_at_center + 1; i < layer_nodes.get(layer_idx).size(); i++) {
					pos = new Point2D(layer_nodes.get(layer_idx).get(i - 1).getPosition().getX() + x_right_offset * 2,
							layer_center.getY());
					layer_nodes.get(layer_idx).get(i).setPosition(pos);
				}
			}
		}

	}

	/*
	 * Apply Breath First Search Tree to set basic tree properties for each
	 * node: (left or right) parent, children, depth, push node to corresponding
	 * layer
	 */
	private void setBasicTreeProperties(Tree tree) {

		Queue<Node> queue; // store the retrieved nodes from edges
		Node current_node; // point to the current node processing on

		if (tree.getRoot() == null) {
			return;
		}

		tree.getRoot().setDepth(0); // set the root depth to 0
		tree.getRoot().setParent(null);

		// Start the BFS search
		queue = new LinkedList<Node>();
		queue.add(tree.getRoot()); // push the root node into queue
		while (!queue.isEmpty()) {
			current_node = queue.remove();

			/* If current node is in a new layer, then create a new layer */
			if (layer_nodes.size() - 1 < current_node.getDepth()) {
				Vector<Node> layer = new Vector<Node>();
				layer.add(current_node);
				layer_nodes.add(layer);
			} else {
				layer_nodes.get(current_node.getDepth()).add(current_node);
			}

			// Check left child
			if (current_node.getLeft() != null) {
				current_node.getLeft().setParent(current_node); // let the left
																// child point
																// to the
																// current node
				current_node.getLeft().setLeft_child_of_parent(true); // let the
																		// left
																		// child
																		// have
																		// left
																		// parent
				current_node.getLeft().setDepth(current_node.getDepth() + 1); // assign
																				// the
																				// depth
																				// to
																				// the
																				// left
																				// child

				queue.add(current_node.getLeft());
			}

			// Check right child
			if (current_node.getRight() != null) {
				current_node.getRight().setParent(current_node); // let the left
																	// child
																	// point to
																	// the
																	// current
																	// node
				current_node.getRight().setLeft_child_of_parent(false); // let
																		// the
																		// left
																		// child
																		// have
																		// left
																		// parent
				current_node.getRight().setDepth(current_node.getDepth() + 1); // assign
																				// the
																				// depth
																				// to
																				// the
																				// left
																				// child
				queue.add(current_node.getRight());
			}
		}

	}

	/*
	 * Because of the position change of one layer of nodes, this information is
	 * propagated to other layers
	 */
	void changePropogation() {

		Node node = root; // node is just a temporary node holder

		Point2D pos = new Point2D(320, 0); // do the setting for the root
		root.setPosition(pos);

		int x_left_offset = -radius * 3; // the left child x relative
											// position
											// to the current node's x position
											// (maximum offset)
		int x_right_offset = radius * 3;
		int y_offset = radius * 2; // the left child x position relative
									// (maximum offset)

		/* Preset all the nodes' positions; some of them maybe changed later */
		for (int i = 1; i < layer_nodes.size(); i++) {
			for (int j = 0; j < layer_nodes.get(i).size(); j++) {
				if (layer_nodes.get(i).get(j).isLeft_child_of_parent())
					pos = new Point2D(layer_nodes.get(i).get(j).getParent().getPosition().getX() + x_left_offset,
							layer_nodes.get(i).get(j).getParent().getPosition().getY() + y_offset);
				else
					pos = new Point2D(layer_nodes.get(i).get(j).getParent().getPosition().getX() + x_right_offset,
							layer_nodes.get(i).get(j).getParent().getPosition().getY() + y_offset);

				layer_nodes.get(i).get(j).setPosition(pos);
			}
		}

		/*
		 * Traverse all the layers to see if any layer has node overlapping or
		 * out of the range issue
		 */
		for (int y = layer_nodes.size() - 1; y >= 0; y--) // start from the
															// bottom layer
		{

			boolean width_issue = false; // an indicator that the tree suffers
											// width problem
			boolean height_issue = false; // an indicator the tree suffers
											// height problem

			// Below is to process each layer
			for (int x = 0; x < layer_nodes.get(y).size(); x++) {
				// If the tree is too wide
				if (layer_nodes.get(y).get(x).getPosition().getX() < radius / 2
						|| layer_nodes.get(y).get(x).getPosition().getX() > (canvas_width - radius / 2)) {
					width_issue = true;
				}

				// If the tree is too high
				else if (layer_nodes.get(y).get(x).getPosition().getY() > (canvas_height - radius / 2)) {

				}

			}
		}

	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Node getNew_node() {
		return new_node;
	}

	public void setNew_node(Node new_node) {
		this.new_node = new_node;
	}

	public int getSelect_node_value() {
		return select_node_value;
	}

	public void setSelect_node_value(int select_node_value) {
		this.select_node_value = select_node_value;
	}

	public Node getSelectedNode() {
		return selected_node;
	}

	public void setSelectedNode(Node dragged_node) {
		this.selected_node = dragged_node;
	}
}
