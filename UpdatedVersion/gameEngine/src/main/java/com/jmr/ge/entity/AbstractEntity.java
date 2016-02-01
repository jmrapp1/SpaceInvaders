package com.jmr.ge.entity;

import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jmr.ge.Timer;
import com.jmr.ge.camera.OrthoCamera;
import com.jmr.ge.image.Drawable;
import com.jmr.ge.path.Node;
import com.jmr.ge.path.PathFinder;
import com.jmr.ge.setting.Settings;

public abstract class AbstractEntity extends WorldObject {

	protected Drawable drawable;
	
	protected float speed;
	protected Direction dir;
	
	protected Array<Node> path = new Array<Node>();
	protected Node goalNode;	
	private boolean walkingPath = false, loopPath = false, goingBackwards = false;
	private int currentNode = 0;
	private Rectangle collisionTestRect = new Rectangle();
	
	public AbstractEntity(Drawable drawable, float speed, Rectangle boundOffset, float x, float y) {
		super(x, y, boundOffset);
		this.drawable = drawable;
		this.speed = speed;
		this.boundOffset = boundOffset;
		dir = Direction.DOWN;
	}
	
	public AbstractEntity(Drawable drawable, float speed, float x, float y) {
		super(x, y, drawable != null ? drawable.getWidth() : 0, drawable != null ? drawable.getHeight() : 0 );
		this.drawable = drawable;
		this.speed = speed;
		dir = Direction.DOWN;
	}
	
	public AbstractEntity(Drawable drawable, float speed) {
		this(drawable, speed, 0, 0);
	}
	
	public AbstractEntity(Drawable drawable, float speed, Rectangle boundOffset) {
		this(drawable, speed, boundOffset, 0, 0);
	}
	
	public AbstractEntity(Drawable drawable, float speed, float x, float y, Node goalNode) {
		this(drawable, speed, x, y);
		this.goalNode = goalNode;
	}
	
	public AbstractEntity(Drawable drawable, float speed, Rectangle boundOffset, float x, float y, Node goalNode) {
		this(drawable, speed, boundOffset, x, y);
		this.goalNode = goalNode;
	}
	
	public void update() {
		walkPath();
		updatePos();
	}
	
	public void updatePos() {
		pos.set(desired);
		last.set(pos);
	}
	
	public void render(SpriteBatch sb) {
		if (drawable != null) {
			drawable.render(sb, pos);
		}
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	
	public void setPath(PathFinder finder, Node goal, boolean[][] walkables) {
		if (goal != null) {
			path.clear();
			path = finder.findPath(new Node((int)(pos.x / Settings.getTileSize()), (int)(pos.y / Settings.getTileSize())), goal, walkables);
			goalNode = goal;
		} else {
			goalNode = null;
			path.clear();
		}
	}
	
	protected void walkPath() {
		if (walkingPath && path.size > 0) {
			if (!goingBackwards) { //going forwards
				if (path.size - (currentNode + 1) >= 0) { //check for paths that dont loop. If it is less than 0 it means the path is done.
					Node next = path.get(path.size - (currentNode + 1));
					walkToNode(next);
				}
			} else { //going backwards
				Node next = path.get(path.size - (currentNode - 1));
				walkToNode(next);
			}
		}
	}
	
	private void walkToNode(Node next) {
		float xOffset = (Settings.getTileSize() - width) / 2; //xOffset centers the entity in the middle of the tile
		float yOffset = (Settings.getTileSize() - height) / 2;
		
		float xDif = (int) Math.abs(pos.x - (next.getTileX() + xOffset));
		float yDif = (int) Math.abs(pos.y - (next.getTileY() + yOffset));
		
		if (xDif <= 3)
			pos.x = next.getTileX() + xOffset;
		if (yDif <= 3)
			pos.y = next.getTileY() + yOffset;
		
		if (pos.x < next.getTileX() + xOffset) {
			moveRight();
			return;
		} else if (pos.x > next.getTileX() + xOffset) {
			moveLeft();
			return;
		}
		
		if (pos.y < next.getTileY() + yOffset) {
			moveUp();
		} else if (pos.y > next.getTileY() + yOffset){
			moveDown();
		}
		
		if (pos.y == next.getTileY() + yOffset && pos.x == next.getTileX() + xOffset) {
			if (!goingBackwards) {
				if (currentNode + 1 == path.size) {
					if (loopPath) {
						goingBackwards = true;
					}
				}
				currentNode++;
			} else if (goingBackwards) {
				if (currentNode - 1 == 1) {
					if (loopPath) {
						goingBackwards = false;
					}
				}
				currentNode--;
			}
		}
	}
	
	public int getPathSize() {
		return path.size;
	}

	public boolean isWalkingPath() {
		return walkingPath;
	}
	
	public void shouldWalkPath(boolean walkPath, boolean loop) {
		currentNode = 0;
		walkingPath = walkPath;
		loopPath = loop;
		goingBackwards = false;
	}
	
	public boolean raycast(Array<WorldObject> objects, WorldObject wo, boolean[][] mapWalkables, float distance, float fov) {
		if (getPos().cpy().dst(wo.getPos()) > distance)
			return false;
		Array<WorldObject> moveArray = new Array<WorldObject>();
		moveArray.addAll(objects);
		//moveArray.add(center);
		moveArray.removeValue(this, false);
		moveArray.sort(distanceSortCF);
		Vector2 subtraction = wo.getPos().cpy().sub(getPos());
		Vector2 normal = subtraction.cpy().nor();
		Vector2 newPos = normal.cpy().add(pos).add(width / 2, height / 2);
		
		int woX = (int)(wo.getPos().x / 50);
		int woY = (int)(wo.getPos().y / 50);
		
		int index = 1;
		while (index < 20) {
			Vector2 nextTile = normal.cpy().scl(index).scl(Settings.getTileSize()).add(newPos);
			int tileX = (int)(nextTile.x / Settings.getTileSize());
			int tileY = (int)(nextTile.y / Settings.getTileSize());
			if (tileX == woX && tileY == woY) {
				float nX = Math.abs(normal.x);
				float nY = Math.abs(normal.y);
				if (dir == Direction.LEFT) {
					if (nX >= 1 - fov)
						return wo.getPos().x < getPos().x;
				} else if (dir == Direction.RIGHT) {
					if (nX >= 1 - fov)
						return wo.getPos().x >= getPos().x;
				} else if (dir == Direction.UP) {
					if (nY >= 1 - fov)
						return wo.getPos().y >= getPos().y;
				} else if (dir == Direction.DOWN) {
					if (nY >= 1 - fov)
						return wo.getPos().y <= getPos().y;
				}
				return false;
			}
			if (tileX > 0 && tileX < width && tileY > 0 && tileY < height) { //in bounds
				if (mapWalkables[tileX][tileY]) {
					return false;
				}
			}
			index++;
		}
		return false;
	}
	
	protected Comparator<WorldObject> distanceSortCF = new Comparator<WorldObject>() {
		@Override
		public int compare(WorldObject one, WorldObject two) {
			Vector2 v2 = new Vector2(pos.x, pos.y);
			Vector2 v1 = new Vector2(one.getPos().x, one.getPos().y);
			Vector2 v3 = new Vector2(two.getPos().x, two.getPos().y);

			float distance = v2.dst(v1);
			float distance2 = v2.dst(v3);
			
			if (distance == distance2) {
				return 0; // doesnt move
			} else if (distance > distance2) {
				return 1; // higher
			}
			return -1; // lower
		}
	};
	
	public void handleCollision(WorldObject wo) {
		collisionTestRect.set(desired.x, desired.y, width, height);
		if (wo.getBounds().overlaps(collisionTestRect)) {
			if (onCollide(wo)) {
				resetLastMove();
			} else if (wo instanceof AbstractEntity) {
				AbstractEntity ent = (AbstractEntity) wo;
				if (ent.onCollide(this)) {
					ent.resetLastMove();
				}
			}
		}
	}
	
	public void resetLastMove() {
		desired.set(last);
	}
	
	public float getSpeed() {
		return speed;
	}

	public void focusCamera(float width, float height, OrthoCamera camera) {
		focusCamera(width, height, camera);
	}
	
	public void focusCamera(int width, int height, OrthoCamera camera) {
		float camX = 0, camY = 0;
		
		if (width * Settings.getTileSize() - pos.x >= (Settings.getWidth() / 2) * camera.zoom)
			camX = pos.x;
		else if (width * Settings.getTileSize() - pos.x <= (Settings.getWidth() / 2) * camera.zoom && pos.x > (Settings.getWidth() / 2) * camera.zoom)
			camX = width * Settings.getTileSize() - (Settings.getWidth() / 2) * camera.zoom;
		
		if (pos.x <= (Settings.getWidth() / 2) * camera.zoom)
			camX = (Settings.getWidth() / 2) * camera.zoom;
		else if (pos.x > (Settings.getWidth() / 2) * camera.zoom && pos.x < width * Settings.getTileSize() - (Settings.getWidth() / 2) * camera.zoom) 
			camX = pos.x;
		
		if (height * Settings.getTileSize() - pos.y > (Settings.getHeight() / 2) * camera.zoom)
			camY = pos.y;
		else if (height * Settings.getTileSize() - pos.y <= (Settings.getHeight() / 2) * camera.zoom)
			camY = height * Settings.getTileSize() - (Settings.getHeight() / 2) * camera.zoom;
		
		if (pos.y <= (Settings.getHeight() / 2) * camera.zoom)
			camY = (Settings.getHeight() / 2) * camera.zoom;
		else if (pos.y > (Settings.getHeight() / 2) * camera.zoom && pos.y < height * Settings.getTileSize() - (Settings.getHeight() / 2) * camera.zoom)
			camY = pos.y;
		
		if (camX != camera.getPos().x || camY != camera.getPos().y)
			camera.setPosition(camX, camY);
	}
	
	public void move(float multX, float multY) {
		addPos(multX * Gdx.graphics.getDeltaTime(), multY * Gdx.graphics.getDeltaTime());
	}

	public void moveLeft() {
		addPos(-speed * Gdx.graphics.getDeltaTime(), 0);
		dir = Direction.LEFT;
	}
	
	public void moveRight() {
		addPos(speed * Gdx.graphics.getDeltaTime(), 0);
		dir = Direction.RIGHT;
	}
	
	public void moveUp() {
		addPos(0, speed * Gdx.graphics.getDeltaTime());
		dir = Direction.UP;
	}
	
	public void moveDown() {
		addPos(0, -speed * Gdx.graphics.getDeltaTime());
		dir = Direction.DOWN;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public Direction getDirection() {
		return dir;
	}
	
	public Node getGoalNode() {
		return goalNode;
	}
	
	public Drawable getDrawable() {
		return drawable;
	}

	public static enum Direction {
		DOWN(0), UP(1), RIGHT(2), LEFT(3);
		
		 private int value;
		 private Direction(int value) {
			 this.value = value;
		 }
		 public int getValue() {
			 return value;
		 }
	}
	
	public abstract boolean onCollide(WorldObject wo);
	
}
