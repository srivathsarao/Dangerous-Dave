package com.ashtav.dangerousdave;

import com.ashtav.dangerousdave.framework.Level;
import com.ashtav.dangerousdave.framework.Sprite;
import com.ashtav.dangerousdave.physics.CollisionResult;
import com.ashtav.dangerousdave.physics.ICollisionListener;

public class DaveCollisionListener implements ICollisionListener {

	// override
	public void onCollision(CollisionResult result) {
		Boolean pickup = true;
		Dave player = (Dave) result.moving;
		DaveStatus daveStatus = DaveStatus.GetInstance();
		// não pode fazer pickup se estiver exploding
		if (result.other instanceof Tile) {
			Tile tile = (Tile) result.other;
			Level level = tile.level;
			DangerousDave main = level.game;
			// if (this.bla == false) {
			switch (tile.id) {
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
				Sounds.pickup.play();
				level.tiles[tile.getTileY()][tile.getTileX()] = null;
				daveStatus.score += Levels.scores[tile.id];
				break;
			case 23: // gun
				DaveStatus.GetInstance().hasGun = true;
				level.tiles[tile.getTileY()][tile.getTileX()] = null;
				break;
			case 24: // jetpack
				DaveStatus.GetInstance().hasJetpack = true;
				level.tiles[tile.getTileY()][tile.getTileX()] = null;
				break;
			case 14:
				Sounds.trophy.play();
				level.tiles[tile.getTileY()][tile.getTileX()] = null;
				daveStatus.score += Levels.scores[tile.id];
				main.portaAberta = true;
				break;
			case 15:
			case 16:
			case 17:
				pickup = false;

				player.explode();
				break;
			default:
				pickup = false;
				break;
			}

		} else if (result.other instanceof Explosion) {
			return;

		} else if (result.other instanceof Sprite) {
			pickup = false;
			player.explode();
		}

		if (player.exploding) {
			player.land();
		} else if (!pickup && player.getAnimation() != Dave.ANIMATION_BLINKING) {
			player.setX(player.getX() + result.offsetX);
			player.setY(player.getY() + result.offsetY);

			if (result.getDirection() == CollisionResult.SOUTH) {
				player.land();
			}
			if (result.getDirection() == CollisionResult.NORTH) {
				player.speedY = 0;
			}
		}
	}

}
