package com.ashtav.dangerousdave.physics;

import com.ashtav.dangerousdave.framework.Sprite;

public class CollisionResult {

	public static final Integer NORTH = 1;
	public static final Integer NORTHEAST = 2;
	public static final Integer EAST = 3;
	public static final Integer SOUTHEAST = 4;
	public static final Integer SOUTH = 5;
	public static final Integer SOUTHWEST = 6;
	public static final Integer WEST = 7;
	public static final Integer NORTHWEST = 8;

	public static final String[] DESCRIPTIONS = { "NONE", "NORTH", "NORTHEAST", "EAST", "SOUTHEAST", "SOUTH", "SOUTHWEST", "WEST", "NORTHWEST" };

	public Sprite moving;
	public Sprite other;
	public Integer offsetX;
	public Integer offsetY;

	public CollisionResult(Sprite moving, Sprite other, Integer offsetX, Integer offsetY) {
		this.moving = moving;
		this.other = other;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public String getDirectionString() {
		return DESCRIPTIONS[getDirection()];
	}

	public Integer getDirection() {
		if (offsetX == 0 && offsetY < 0) {
			return CollisionResult.SOUTH;
		}
		if (offsetX > 0 && offsetY < 0) {
			return CollisionResult.SOUTHWEST;
		}
		if (offsetX > 0 && offsetY == 0) {
			return CollisionResult.WEST;
		}
		if (offsetX > 0 && offsetY > 0) {
			return CollisionResult.NORTHWEST;
		}
		if (offsetX == 0 && offsetY > 0) {
			return CollisionResult.NORTH;
		}
		if (offsetX < 0 && offsetY > 0) {
			return CollisionResult.NORTHEAST;
		}
		if (offsetX < 0 && offsetY == 0) {
			return CollisionResult.EAST;
		}
		if (offsetX < 0 && offsetY < 0) {
			return CollisionResult.SOUTHEAST;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "CollisionResult[direction=" + getDirectionString() + ", offsetX=" + offsetX + ", offsetY=" + offsetY + "]";
	}
}