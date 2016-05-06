package com.ashtav.dangerousdave.framework;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageGrid {

	public Texture bitmap;
	public Integer width;
	public Integer height;

	public ImageGrid(Texture bitmap, Integer width, Integer height) {
		this.bitmap = bitmap;
		this.width = width;
		this.height = height;
	}

	public void render(SpriteBatch graphics, Integer x, Integer y, Integer animationX, Integer animationY, Boolean flipX, Boolean flipY) {
		TextureRegion[][] tmp = TextureRegion.split(bitmap, width, height);
		TextureRegion region = tmp[animationY][animationX];
		region.flip(flipX, flipY);
		graphics.draw(region, x, 200 - y, width, height);
	}
}
