package com.ashtav.dangerousdave;

import com.ashtav.dangerousdave.framework.Sprite;
import com.ashtav.dangerousdave.physics.Physics;
import com.ashtav.dangerousdave.physics.Rectangle;

public class Dave extends Sprite implements IBulletListener {

	public static final Integer[] ANIMATION_FRAMES = { 1, 4, 1, 2 };
	public static final Integer[] ANIMATION_TIMES = { 0, 100, 0, 100 };

	public static final Integer ANIMATION_STANDING = 0;
	public static final Integer ANIMATION_WALKING = 1;
	public static final Integer ANIMATION_JUMPING = 2;
	public static final Integer ANIMATION_BLINKING = 3;

	// Attributes

	public double speedX = 0;
	public double speedY = 0;

	public Boolean jumping = false;
	public Boolean falling = false;
	public Boolean exploding = false;
	public Boolean usingJetpack = false;

	public Boolean hasBulletOnScreen = false;

	// Constructors

	public Dave(DaveLevel level, Integer y, Integer x) {
		super(level, Images.dave, ANIMATION_FRAMES, ANIMATION_TIMES);

		// this.x = x * level.tileWidth;
		// this.y = y * level.tileHeight;
		// substituição do código comentado acima
		this.restart(y, x);

		this.collisionListener = new DaveCollisionListener();
	}

	// Properties

	@Override
	public Rectangle getCollisionRectangle() {
		return new Rectangle(getX() + 3, getY() + 1, width - 6, height - 1);
	}

	// Methods

	public void move(Integer velocity) {
		if (velocity < 0) {
			flipX = true;
		} else {
			flipX = false;
		}
		speedX = velocity;
		if (!jumping && !falling) {
			Sounds.walking.loop();
			playAnimation = true;
			this.setAnimation(ANIMATION_WALKING);
		}
	}

	public void jump() {
		if (!jumping && !falling) {
			Sounds.jump.play();
			Sounds.walking.stop();
			this.setAnimation(ANIMATION_JUMPING);
			speedY = -90;
			jumping = true;
		}
	}

	public void land() {
		speedY = 0;
		if (jumping) {
			Sounds.jump.stop();
			jumping = false;
			this.setAnimation(ANIMATION_STANDING);
		}
		if (falling) {
			Sounds.falling.stop();
			falling = false;
		}
	}

	public void fall() {
		Sounds.walking.stop();
		Sounds.falling.play();
		falling = true;
		this.setAnimation(ANIMATION_WALKING);
		playAnimation = false;
		speedY = 90;
	}

	public void restart(Integer y, Integer x) {
		this.setX(x * level.tileWidth);
		this.setY(y * level.tileHeight);
		this.setAnimation(ANIMATION_BLINKING);
		playAnimation = true;
	}

	// Overrides

	@Override
	public void update(double seconds) {
		super.update(seconds);
		if (this.getAnimation() != ANIMATION_BLINKING) {
			if (!usingJetpack) {
				if (speedX == 0) {
					Sounds.walking.stop();
					playAnimation = false;
				}
				if (speedY > 0 && !jumping && !falling) {
					fall();
				}
//				if (!falling) {
//					double gravity = 120 * seconds;
//					speedY += gravity;
//					speedY = Math.min(speedY, 90);
//				}

				Physics.move((int) (speedX * seconds), (int) (speedY * seconds), this, level);
				speedX = 0;
			} else {
				// mover utilizando o jetpack
				DaveStatus status = DaveStatus.GetInstance();
				status.jetpackFuel -= seconds * 10;
				if (status.jetpackFuel <= 0) {
					status.hasJetpack = false;
					toggleJetpack();
				}
				Physics.move((int) (speedX * seconds), (int) (speedY * seconds), this, level);
				speedX = 0;
				speedY = 0;
			}
		}

	}

	public void explode() {
		if (this.exploding == false) {
			if (this.usingJetpack) {
				this.toggleJetpack();
			}

			final Explosion explosion = new Explosion((DaveLevel) this.level, this.getY(), this.getX());
			this.level.addSprite(explosion);
			this.exploding = true;
			this.visible = false; // variável adicionada à sprite
			Sounds.death.play();
			level.game.execute(3, new Runnable() {

				@Override
				public void run() {
					((DaveLevel) level).SetPlayerToInitialPosition();
					level.removeSprite(explosion); // mt louco, isso pega!!!
					exploding = false;
					visible = true;
				}
			});

			// TODO: modificar esse código para não acessar diretamente a
			// variável
			DaveStatus.GetInstance().lives--;
		}
	}

	public void shoot() {
		DaveStatus daveStatus = DaveStatus.GetInstance();
		if (daveStatus.hasGun && !this.hasBulletOnScreen) {
			Integer bulletX = this.getX() / this.level.tileWidth;
			Integer bulletY = this.getY() / this.level.tileHeight;
			Integer direction = ((this.flipX) ? -1 : 1);
			Bullet b = new Bullet((DaveLevel) this.level, bulletY, bulletX + direction, direction, true);
			b.bulletListener = this;
			this.level.addSprite(b);
			this.hasBulletOnScreen = true;
		}
	}

	public void onBulletRemoved() {
		// aplicando um delay
		this.level.game.execute(1, new Runnable() {

			@Override
			public void run() {
				hasBulletOnScreen = false;
			}
		});
	}

	// toogla o jetpack (desliga se estiver ligado e liga caso contrário)
	// seta a variável usingjetpack pra true
	public void toggleJetpack() {
		// apenas executa se o dave tiver o item jetpack
		if (DaveStatus.GetInstance().hasJetpack) {
			if (this.usingJetpack) {
				this.usingJetpack = false;
				Sounds.jetpack.stop();

				playAnimation = true;
				image = Images.dave;
				animationFrames = ANIMATION_FRAMES;
				animationTimes = ANIMATION_TIMES;
				this.setAnimation(0);

			} else {
				this.usingJetpack = true;
				Sounds.jetpack.loop();
				Sounds.toogleJetpack.play();

				playAnimation = true;
				image = Images.davejetpack;

				animationFrames = new Integer[4];
				animationTimes = new Integer[50];
				this.setAnimation(0);

				falling = false;
				jumping = false;
			}
		}
	}

	public void jetpackMove(Integer velocityX, Integer velocityY) {
		if (velocityX < 0) {
			flipX = true;
		} else {
			flipX = false;
		}
		speedX = velocityX;
		speedY = velocityY;
	}
}