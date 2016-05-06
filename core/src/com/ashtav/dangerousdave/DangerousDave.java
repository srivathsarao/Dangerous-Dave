package com.ashtav.dangerousdave;

import com.ashtav.dangerousdave.framework.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DangerousDave extends DaveGame {
	public DaveLevel level;
	public Integer score;
	public Integer lives;
	public Boolean portaAberta;

	private DaveStatus daveHud = DaveStatus.GetInstance();

	@Override
	public void render(SpriteBatch batch) {
		if (level != null) {
			level.render(batch);
			Images.bar.render(batch, 0, 13, 0, 0, false, false);
			Images.bar.render(batch, 0, 172, 0, 0, false, false);
			// colocar jetpack e gun se ela for visivel
			if (daveHud.hasJetpack) {
				double fuel = DaveStatus.GetInstance().jetpackFuel;
				Images.jetpackbar.render(batch, 12 * 8, 174, 0, 0, false, false);
				for (int i = 0; i < 60 * fuel / 100; i++) {
					Images.jetpackfill.render(batch, (12 * 8) + 4 + (2 * i), 174 + 4, 0, 0, false, false);
				}
			}
			if (daveHud.hasGun) {
				Images.gun.render(batch, 12 * 25, 174, 0, 0, false, false);
			}
		}
	}

	@Override
	public void update(double seconds) {
		if (level != null) {
			level.update(seconds);
		}
	}

	@Override
	public void load() {
		this.keyboard = new DaveKeyboard(this);

		this.level = new DaveLevel(this, Levels.level1, new Camera(0, 16, 0, 0, 320, 200));

		portaAberta = false;
	}
}
