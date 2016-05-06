package com.ashtav.dangerousdave;

import com.ashtav.dangerousdave.framework.Sprite;
import com.ashtav.dangerousdave.physics.CollisionResult;
import com.ashtav.dangerousdave.physics.ICollisionListener;
import com.ashtav.dangerousdave.physics.Physics;
import com.ashtav.dangerousdave.physics.Rectangle;

public class Bullet extends Sprite implements ICollisionListener {
	// Static

	// info do davebullet
	private static Integer[] ANIMATION_FRAMES_BULLET = new Integer[0];
	private static Integer[] ANIMATION_TIMES_BULLET = new Integer[0];
	// info do browncirclebullet
	private static Integer[] ANIMATION_FRAMES_BLAST = new Integer[3];
	private static Integer[] ANIMATION_TIMES_BLAST = new Integer[50];

	private static Integer VELOCITY = 120;

	// Attributes
	private Integer _speedX = 120;
	private Boolean isDaveBullet = false;

	public IBulletListener bulletListener = null;

	// Constructors

	public Bullet(DaveLevel level, Integer y, Integer x, Integer direction, Boolean isDaveBullet) {
		// TODO: mudar o construtor de bullet: speed, e direção não estão
		// sendo contemplados no construtor
		super(level, Images.davebullet, ANIMATION_FRAMES_BULLET, ANIMATION_TIMES_BULLET);
		this.level = level;
		this.isDaveBullet = isDaveBullet;
		if (isDaveBullet) {
			this.animationFrames = ANIMATION_FRAMES_BULLET;
			this.animationTimes = ANIMATION_TIMES_BULLET;
			this.image = Images.davebullet;
		} else {
			this.animationFrames = ANIMATION_FRAMES_BLAST;
			this.animationTimes = ANIMATION_TIMES_BLAST;
			this.image = Images.browncirclebullet;
		}
		this.collisionListener = this;

		this.setX(x * level.tileWidth);
		this.setY(y * level.tileHeight + ((level.tileHeight - this.image.height) / 2));
		// this.animation = 0;

		if (direction < 0) {
			flipX = true;
			_speedX = -_speedX;
		}
	}

	// Properties
	@Override
	public Rectangle getCollisionRectangle() {
		return new Rectangle(getX() + 3, getY() + 1, width - 6, height - 1);
	}

	// Methods
	/*
	 * public function move(velocity :Number) :void { if (velocity < 0) { flipX
	 * = true; } else { flipX = false; } speedX = velocity; }
	 */
	// Overrides

	@Override
	public void update(double seconds) {
		super.update(seconds);
		Physics.move((int)(_speedX * seconds), 0, this, level);
		// verifica se saiu da área visível
		if (this.getX() - level.camera.x > level.camera.width - this.width || this.getX() - level.camera.x < 0) {
			removeBullet();
		}
	}

	// override
	public void onCollision(CollisionResult result) {
		// var bu :Bullet = result.moving as Bullet;
		if (result.other instanceof Tile) {
			Tile tile = (Tile) result.other;

			if (tile.id == 2 || tile.id == 3) {
				// wall
				if (result.getDirection() == CollisionResult.EAST) {
					removeBullet();
				}
			}
		}
		// remover o bullet se acertar um sprite?
		// TODO: ajustar o BrownCircle pra ter seu próprio método de colisão
		else if (result.other instanceof BrownCircle) {
			((BrownCircle) result.other).explode();
		}
	}

	private void removeBullet() {
		this.level.removeSprite(this);

		if (this.bulletListener != null) {
			this.bulletListener.onBulletRemoved();
		}
	}

}
