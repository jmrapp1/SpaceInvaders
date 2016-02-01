package com.jmr.ge.path;

import com.jmr.ge.setting.Settings;

public class Node {

	private final int x, y;
	private int g, h;
	private boolean walkable;
	private Node parent;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void calculateH(Node goal) {
		h = (Math.abs(x - goal.x) + Math.abs(y - goal.y));
	}
	
	public void calculateG() {
		if (parent != null)
			g += parent.getG() + 1;
		else
			g = 0;
	}
	
	public void calculateAll(Node goal) {
		calculateH(goal);
		calculateG();
	}
	
	public void setParent(Node node) {
		parent = node;
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public int getG() {
		return g;
	}
	
	public int getH() {
		return h;
	}
	
	public int getF() {
		return g + h;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getTileX() {
		return x * Settings.getTileSize();
	}
	
	public int getTileY() {
		return y * Settings.getTileSize();
	}
	
}
