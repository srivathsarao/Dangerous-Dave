package com.ashtav.dangerousdave;

import java.util.Map;

import com.ashtav.dangerousdave.framework.Camera;
import com.ashtav.dangerousdave.framework.Level;

public class DaveLevel extends Level {

	// Attributes

	public Dave player;

	private Integer xLowerBound;
	private Integer xUpperBound;

	private Integer pan = 0;
	private Integer count = 0;

	private Integer playerInitialX;
	private Integer playerInitialY;

	// Constructors

	public DaveLevel(DangerousDave game, Map<String, Object> level, Camera camera) {
		super(game, camera);

		this.tileWidth = 16;
		this.tileHeight = 16;

		Integer[][] data = ((Integer[][]) level.get("data"));

		this.sizeY = data.length;
		tiles = new Tile[sizeY][];
		for (Integer y = 0; y < data.length; y++) {
			this.sizeX = data[y].length;
			tiles[y] = new Tile[sizeX];
			for (Integer x = 0; x < data[y].length; x++) {
				Integer id = data[y][x];
				if (id == 1) {
					player = new Dave(this, y, x);
					playerInitialX = x;
					playerInitialY = y;
					this.addSprite(player);
				} else if (id == 25) {
					BrownCircle brownCircle = new BrownCircle(this, y, x);
					this.addSprite(brownCircle);
				} else if (id != 0) {
					tiles[y][x] = new Tile(this, Images.tiles, id, y, x);
				}
			}
		}

		this.xLowerBound = 0;
		this.xUpperBound = this.getWorldWidth() - camera.width;
	}

	// Override

	@Override
	public void update(Double seconds) {
		super.update(seconds);

		if (pan != 0) {
			if (pan > 0) {
				incrementCameraX(pan * tileWidth);
				count += 1;
				if (count == 15) {
					pan = 0;
				}
			}
			if (pan < 0) {
				incrementCameraX(pan * tileWidth);
				count -= 1;
				if (count == -15) {
					pan = 0;
				}
			}
		} else {
			if (camera.x + camera.width - player.getX() < 48) {
				if (camera.x >= xUpperBound)
					return;
				count = 0;
				pan = 1;
			}
			if (player.getX() - camera.x < 16) {
				if (camera.x <= xLowerBound)
					return;
				count = 0;
				pan = -1;
			}
		}
	}

	private void incrementCameraX(Integer value) {
		if (value > 0) {
			if (camera.x + value > xUpperBound) {
				camera.x = xUpperBound;
			} else {
				camera.x += value;
			}
		}
		if (value < 0) {
			if (camera.x + value < xLowerBound) {
				camera.x = xLowerBound;
			} else {
				camera.x += value;
			}
		}
	}

	public void SetPlayerToInitialPosition() {
		player.restart(this.playerInitialY, this.playerInitialX);
	}
}
