package com.ashtav.dangerousdave;

public class DaveKeyboard extends GameKeyboard {
	private DangerousDave game;

	public static final Integer VELOCITY = 10;

	public DaveKeyboard(DangerousDave game) {
		super();
		this.game = game;
	}

	@Override
	public void update(double second) {
		if (game.level == null)
			return;
		Dave player = game.level.player;
		if (player == null) {
			return;
		}
		if (!player.exploding) {
			if (!player.usingJetpack) {
				if (pressedKeys.get(21) != null && pressedKeys.get(21)) {
					player.move(-VELOCITY);
				}
				if (pressedKeys.get(22) != null && pressedKeys.get(22)) {
					player.move(+VELOCITY);
				}
				if (pressedKeys.get(19) != null && pressedKeys.get(19)) {
					player.jump();
				}
				// atira se o player apertou crtl
				if (pressedKeys.get(129) != null && pressedKeys.get(129)) {
					player.shoot();
				}
				// toogla jetpack se o player apertar espaço e tiver jetpack
				if (toogledKeys.get(62) != null && toogledKeys.get(62)) {
					toogledKeys.put(62, false);
					if (DaveStatus.GetInstance().hasJetpack) {
						player.toggleJetpack();
					}
				}
			} else {
				if (pressedKeys.get(21) != null && pressedKeys.get(21)) {
					player.jetpackMove(-VELOCITY, 0);
				}
				if (pressedKeys.get(22) != null && pressedKeys.get(22)) {
					player.jetpackMove(+VELOCITY, 0);
				}
				if (pressedKeys.get(19) != null && pressedKeys.get(19)) {
					player.jetpackMove(0, -VELOCITY);
				}
				if (pressedKeys.get(20) != null && pressedKeys.get(20)) {
					player.jetpackMove(0, +VELOCITY);
				}
				// atira se o player apertou crtl
				if (pressedKeys.get(129) != null && pressedKeys.get(129)) {
					player.shoot();
				}
				if (toogledKeys.get(62) != null && toogledKeys.get(62)) {
					toogledKeys.put(62, false);
					player.toggleJetpack();
				}
			}
		}
	}

}
