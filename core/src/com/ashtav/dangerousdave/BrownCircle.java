package com.ashtav.dangerousdave;

import com.ashtav.dangerousdave.framework.Sprite;
import com.ashtav.dangerousdave.framework.Utils;
import com.ashtav.dangerousdave.physics.CollisionResult;
import com.ashtav.dangerousdave.physics.ICollisionListener;

public class BrownCircle extends Sprite implements ICollisionListener, IBulletListener {

	private Boolean hasBulletOnScreen = false;

	// override
	public void onCollision(CollisionResult result) {
		this.level.removeSprite(this);
		// if (result.other is Bullet) {
		// this.level.removeSprite(this);
		// }
	}

	private Integer xCenter;
	private Integer yCenter;
	private double angle = 270;

	public BrownCircle(DaveLevel level, Integer y, Integer x) {
		super(level, Images.browncircle, new Integer[1], new Integer[0]);
		this.setX(x * level.tileWidth);
		this.setY(y * level.tileHeight);
		this.xCenter = this.getX() - 6;
		this.yCenter = this.getY();
		this.collisionListener = this;
	}

	@Override
	public void update(double seconds) {
		angle = (angle + (180 * seconds)) % 360;
		this.setX((int) (xCenter + Math.cos(Utils.degreesToRadians(angle)) * 32));
		this.setY((int) (yCenter + Math.sin(Utils.degreesToRadians(angle)) * 16));

		// TODO: fazer calculo de colisão ou colocar parte desse método em
		// physics

		this.shoot();
	}

	public void explode() {
		final Explosion explosion = new Explosion((DaveLevel) this.level, this.getY(), this.getX());
		this.level.addSprite(explosion);
		this.level.removeSprite(this);

		Sounds.explosion.play();
		level.game.execute(3, new Runnable() {

			@Override
			public void run() {
				level.removeSprite(explosion);

			}
		});
	}

	private void shoot() {
		// TODO: implementar IA (atirar apenas quando o player estiver na mesma
		// linha (mesmo valor de y)
		if (!hasBulletOnScreen && this.xCenter - this.level.camera.x < this.level.camera.width) {
			Integer bulletX = this.getX() / this.level.tileWidth;
			Integer bulletY = this.getY() / this.level.tileHeight;
			Integer direction = -1;
			Bullet b = new Bullet((DaveLevel) this.level, bulletY, bulletX + direction, direction, false);
			b.bulletListener = this;
			this.level.addSprite(b);
			this.hasBulletOnScreen = true;
		}
	}

	public void onBulletRemoved() {
		// aplicando um delay
		this.level.game.execute(1,

		new Runnable() {

			@Override
			public void run() {
				hasBulletOnScreen = false;
			}
		});
	}

}
