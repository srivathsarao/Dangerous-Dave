package com.ashtav.dangerousdave.physics;

import java.util.ArrayList;
import java.util.List;

import com.ashtav.dangerousdave.framework.Level;
import com.ashtav.dangerousdave.framework.Sprite;

public class Physics {

	public static void move(Integer x, Integer y, Sprite sprite, Level level) {
		moveX(x, sprite, level);
		moveY(y, sprite, level);
	}

	public static void moveX(Integer x, Sprite sprite, Level level) {
		Integer oldX = sprite.getX();
		sprite.setX(sprite.getX() + x);
		System.out.println(x);

		List<Sprite> possibleCollisions = collidingTiles(sprite, level);
		for (Sprite levelSprite : level.sprites) {
			possibleCollisions.add(levelSprite);
		}

		// collision results
		Integer offset = Integer.MAX_VALUE;
		Sprite otherSprite = null;

		Rectangle spriteBox = sprite.getCollisionRectangle();
		for (Sprite other : possibleCollisions) {
			if (other == sprite)
				continue;
			Rectangle otherBox = other.getCollisionRectangle();
			if (otherBox == null || !otherBox.intersects(spriteBox))
				continue;

			if (oldX <= other.getX()) { // right collision
				Integer c1 = otherBox.x - spriteBox.getEndX();
				if (Math.abs(c1) < Math.abs(offset)) {
					offset = c1;
					otherSprite = other;
				}
			} else { // left collision
				Integer c2 = otherBox.getEndX() - spriteBox.x;
				if (Math.abs(c2) < Math.abs(offset)) {
					offset = c2;
					otherSprite = other;
				}
			}
		}
		if (offset != Integer.MAX_VALUE && sprite.collisionListener != null) {
			sprite.collisionListener.onCollision(new CollisionResult(sprite, otherSprite, offset, 0));
		}
	}

	public static void moveY(Integer y, Sprite sprite, Level level) {
		Integer oldY = sprite.getY();
		sprite.setY(sprite.getY() + y);

		List<Sprite> possibleCollisions = collidingTiles(sprite, level);
		for (Sprite levelSprite : level.sprites) {
			possibleCollisions.add(levelSprite);
		}

		// collision results
		Integer offset = Integer.MAX_VALUE;
		Sprite otherSprite = null;

		Rectangle spriteBox = sprite.getCollisionRectangle();
		for (Sprite other : possibleCollisions) {

			if (other == sprite)
				continue;
			Rectangle otherBox = other.getCollisionRectangle();
			if (otherBox == null || !otherBox.intersects(spriteBox))
				continue;

			if (oldY <= other.getY()) { // south collision
				Integer c1 = otherBox.y - spriteBox.getEndY();
				if (Math.abs(c1) < Math.abs(offset)) {
					offset = c1;
					otherSprite = other;
				}
			} else { // north collision
				Integer c2 = otherBox.getEndY() - spriteBox.y;
				if (Math.abs(c2) < Math.abs(offset)) {
					offset = c2;
					otherSprite = other;
				}
			}
		}
		if (offset != Integer.MAX_VALUE && sprite.collisionListener != null) {
			sprite.collisionListener.onCollision(new CollisionResult(sprite, otherSprite, 0, offset));
		}
	}

	public static List<Sprite> collidingTiles(Sprite sprite, Level level) {
		List<Sprite> result = new ArrayList<Sprite>();

		Rectangle spriteBox = sprite.getCollisionRectangle();
		Integer startX = (int) (spriteBox.x / level.tileWidth);
		Integer startY = (int) (spriteBox.y / level.tileHeight);
		Integer endX = (int) (spriteBox.x + spriteBox.width) / level.tileWidth;
		Integer endY = (int) (spriteBox.y + spriteBox.height) / level.tileHeight;

		if ((spriteBox.x + spriteBox.width) % level.tileWidth == 0)
			endX--;
		if ((spriteBox.y + spriteBox.height) % level.tileHeight == 0)
			endY--;

		for (Integer x = Math.max(startX, 0); x <= Math.min(endX, level.sizeX - 1); x++) {
			for (Integer y = Math.max(startY, 0); y <= Math.min(endY, level.sizeY - 1); y++) {
				if (level.tiles[y][x] != null) {
					result.add(level.tiles[y][x]);
				}
			}
		}
		return result;
	}
}