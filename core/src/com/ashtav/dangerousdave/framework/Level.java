package com.ashtav.dangerousdave.framework;

import java.util.ArrayList;
import java.util.List;

import com.ashtav.dangerousdave.DangerousDave;
import com.ashtav.dangerousdave.Tile;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {

	public DangerousDave game;
	public Camera camera;

	public Integer tileWidth;
	public Integer tileHeight;
	public Integer sizeX;
	public Integer sizeY;

	public Tile[][] tiles;
	public List<Sprite> sprites;

	public Boolean playerFired = false;

	public Level(DangerousDave game, Camera camera) {
		this.game = game;
		this.camera = camera;
		sprites = new ArrayList<Sprite>();
	}

	public Integer getWorldWidth() {
		return sizeX * tileWidth;
	}

	public Integer getWorldHeight() {
		return sizeY * tileHeight;
	}

	public void update(Double seconds) {
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				if (tiles[y][x] != null) {
					Tile tile = tiles[y][x];
					tile.update(seconds);
				}
			}
		}
		for (Sprite sprite : sprites) {
			sprite.update(seconds);
		}
	}

	public void render(SpriteBatch graphics) {
		Integer offsetX = camera.offsetX - camera.x;
		Integer offsetY = camera.offsetY - camera.y;

		for (Integer y = 0; y < tiles.length; y++) {
			for (Integer x = 0; x < tiles[y].length; x++) {
				if (tiles[y][x] != null) {
					Tile tile = tiles[y][x];
					tile.render(graphics, offsetX, offsetY);
				}
			}
		}
		for (Sprite sprite : sprites) {
			sprite.render(graphics, offsetX, offsetY);
		}
	}

	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
		sprite.level = this;
	}

	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}
}
