package com.ashtav.dangerousdave;

import com.ashtav.dangerousdave.framework.ImageGrid;
import com.ashtav.dangerousdave.framework.Sprite;
import com.ashtav.dangerousdave.physics.Rectangle;

public class Tile extends Sprite {

	public static final Integer PINK_FLOOR = 7;
	public static final Integer ID_TROOPHY = 14;

	public static final Integer[] ANIMATION_FRAMES = { 1, 1, 1, 1,  1, 1, 1, 1,  1, 1, 1, 1,  1, 1, 6, 4,  4, 5, 1, 1,  1, 1, 1, 1,  1, 1 };
	public static final Integer[] ANIMATION_TIMES = { 0, 0, 0, 0,  0, 0, 0, 0,  0, 0, 0, 0,  0, 0, 90, 90,  90, 90, 0, 0,  0, 0, 0, 0,  0, 0 };

	public Integer id;

	public Tile(DaveLevel level, ImageGrid image, Integer id, Integer y, Integer x) {
		super(level, image, ANIMATION_FRAMES, ANIMATION_TIMES);
		this.setX(x * level.tileWidth);
		this.setY(y * level.tileHeight);
		this.id = id;
		this.animationX = 0;
		this.animationY = id;
	}

	public Integer getTileX() {
		return getX() / level.tileWidth;
	}

	public Integer getTileY() {
		return getY() / level.tileHeight;
	}

	@Override
	public Rectangle getCollisionRectangle() {
		if (id == 8 || id == 9 || id == 10 || id == 11 || id == 12 || id == 13 || id == 14 || id == 23 || id == 24) {
			return new Rectangle(getX() + 2, getY() + 2, getX() - 4, getY() - 4);
		}
		if (id == PINK_FLOOR) {
			return new Rectangle(getX(), getY(), width, height - 5);
		}
		else {
			return super.getCollisionRectangle();
		}
	}

}
