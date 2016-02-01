package com.jmr.ge.path;

import java.util.Comparator;

import com.badlogic.gdx.utils.Array;

public class PathFinder {

	private Array<Node> open = new Array<Node>();
	private Array<Node> closed = new Array<Node>();
	
	private final Node[][] nodeMap;
	private final int width, height;
	
	public PathFinder(int width, int height) {
		this.width = width;
		this.height = height;
		nodeMap = new Node[width][height];
		initNodeMap();
	}
	
	//Sets blank nodes
	private void initNodeMap() {
		for (int j = 0; j < height; j++)
			for (int i = 0; i < width; i++)
				nodeMap[i][j] = new Node(i, j);
	}
	
	public void printMap() {
		for (int y = 0; y < nodeMap.length; y++) {
			for (int x = 0; x < nodeMap[0].length; x++) {
				System.out.print((nodeMap[x][y].isWalkable() ? 0 : 1) + " ");
			}
			System.out.println("");
		}
	}
	
	//Sets all of the tiles to see if they are walkable according to map
	public void setWalkables(boolean[][] mapWalkables) {
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				nodeMap[i][j].setWalkable(mapWalkables[i][j]); //If it collides, its not walkable
			}
		}
	}
	
	//Checks if the closed list contains the node
	private boolean onClosedList(Node node) {
		return closed.contains(node, true);
	}
	
	//Checks if the open list contains the node
	private boolean onOpenedList(Node node) {
		return open.contains(node, true);
	}
	
	//Gets all nodes around the node
	private Array<Node> getAdjacent(Node node, Node goal) {
		Array<Node> ret = new Array<Node>();
		
		int x = node.getX();
		int y = node.getY();
		
		//Check the top, left, right bottom. See if they are walkable, arent on closed/open list, and are within width and height
		if (x >= 0 && x < width && y + 1 >= 0 && y + 1 < height && nodeMap[x][y + 1].isWalkable() && !onClosedList(nodeMap[x][y + 1]) && !onOpenedList(nodeMap[x][y + 1]))
			ret.add(nodeMap[x][y + 1]);
		if (x >= 0 && x < width && y - 1 >= 0 && y - 1 < height && nodeMap[x][y - 1].isWalkable() && !onClosedList(nodeMap[x][y - 1]) && !onOpenedList(nodeMap[x][y - 1]))
			ret.add(nodeMap[x][y - 1]);
		if (x + 1 >= 0 && x + 1 < width && y >= 0 && y < height && nodeMap[x + 1][y].isWalkable() && !onClosedList(nodeMap[x + 1][y]) && !onOpenedList(nodeMap[x + 1][y]))
			ret.add(nodeMap[x + 1][y]);
		if (x - 1 >= 0 && x - 1 < width && y >= 0 && y < height && nodeMap[x - 1][y].isWalkable() && !onClosedList(nodeMap[x - 1][y]) && !onOpenedList(nodeMap[x - 1][y]))
			ret.add(nodeMap[x - 1][y]);
		
		//Recalculate all g and h values to be used when sorting
		for (Node n : ret) {
			n.calculateAll(goal);
			n.setParent(node); //set the parent to the node being tested on
		}
		
		return ret; //return all nodes
	}
	
	//Finds a path from the starting node to the goal node
	public Array<Node> findPath(Node start, Node goal, boolean[][] mapWalkables) {
		setWalkables(mapWalkables);
		Array<Node> ret = new Array<Node>();
		
		open.clear();
		closed.clear();
		
		Node finished = null;
		
		//Add to start and set parent to null to know its the beginning
		open.add(start);
		start.setParent(null);
		
		while (open.size > 0) { //While there are nodes in the open list
			open.sort(sortF); //Sort the open list by the lowest f (g + h) value
			
			Node current = open.first(); //get the first node in the array (lowest f value)
			
			//if the current node equals the goal node, break out and set finished node
			if (current.getX() == goal.getX() && current.getY() == goal.getY()) {
				finished = current;
				break;
			}
			
			open.removeValue(current, false); //Remove from the open list since we just checked it
			closed.add(current); //Add it to the closed list so we dont go back to it
			open.addAll(getAdjacent(current, goal)); //Add all adjacent tiles to the open list to be tested 
			
			//start from beginning
		}
		
		if (finished != null) { //if a finished node was set (aka a path was found)
			ret.add(finished); //Add the finished node to the array
			Node n = finished; 
			while (n.getParent() != null) { //Go through each node and add the parent node to the array to create the path
				ret.add(n.getParent());
				n = n.getParent();
			}
		}
		
		return ret; //return the path
	}
	
	//Compares the sorted array so that the f value goes from decreasing to increasing
	private Comparator<Node> sortF = new Comparator<Node>() {
		@Override
		public int compare(Node one, Node two) {
			if (one.getF() == two.getF()) {
				return 0; // doesn't move
			} else if (one.getF() > two.getF()) {
				return 1; // higher in array
			}
			return -1; // lower in array
		}
	};
	
}

