package com.ashtav.dangerousdave.framework;

import com.ashtav.dangerousdave.physics.ICollisionListener;
import com.ashtav.dangerousdave.physics.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sprite {

	private Integer _x;
	private Integer _y;
	public Integer width;
	public Integer height;

	public Level level;
	public ImageGrid image;
	public ICollisionListener collisionListener;

	public Integer animationX = 0;
	public Integer animationY = 0;

	public Integer[] animationFrames;
	public Integer[] animationTimes;

	public Boolean playAnimation = true;
	public Boolean flipX = false;;
	public Boolean flipY = false;;

	private double animationCounter = 0;

	public Boolean visible;

	public Sprite(Level level, ImageGrid image, Integer[] animationFrames, Integer[] animationTimes) {
		this.level = level;
		this.setX(0);
		this.setY(0);
		this.width = image.width;
		this.height = image.height;

		this.image = image;
		this.animationFrames = (animationFrames != null) ? animationFrames : new Integer[1];
		this.animationTimes = (animationTimes != null) ? animationTimes : new Integer[0];

		visible = true;
	}

	public Integer getX() {
		return _x;
	}

	public void setX(Integer value) {
		this._x = (int) (Math.round(value * 100) / 100.0);
	}

	public Integer getY() {
		return _y;
	}

	public void setY(Integer value) {
		this._y = (int) (Math.round(value * 100) / 100.0);
	}

	public Rectangle getCollisionRectangle() {
		return new Rectangle(getX(), getY(), width, height);
	}

	public Integer getAnimation() {
		return animationY;
	}

	public void setAnimation(Integer animation) {
		if (animation != animationY) {
			animationX = 0;
			animationY = animation;
		}
	}

	public void render(SpriteBatch graphics, Integer offsetX, Integer offsetY) {
		if (visible) {
			image.render(graphics, getX() + offsetX, getY() + offsetY, animationX, animationY, flipX, flipY);
		}
	}

	public void update(double seconds) {
		if (!playAnimation || animationFrames[animationY] <= 1)
			return;

		animationCounter += seconds * 1000;
		if (animationCounter > animationTimes[animationY]) {
			animationCounter = 0;
			animationX = (animationX + 1) % animationFrames[animationY];
		}
	}

	@Override
	public String toString() {
		return "Sprite[x=" + getX() + ",y=" + getY() + "]";
	}
}
