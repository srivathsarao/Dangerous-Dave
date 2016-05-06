package com.ashtav.dangerousdave;

import com.ashtav.dangerousdave.framework.ImageGrid;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Images {
	private static Texture Bar = new Texture(Gdx.files.internal("images/bar.png"));
	private static Texture Dave = new Texture(Gdx.files.internal("images/dave.png"));;
	private static Texture Explosion = new Texture(Gdx.files.internal("images/explosion.png"));;
	private static Texture Tiles = new Texture(Gdx.files.internal("images/tiles.png"));;
	private static Texture GameFont = new Texture(Gdx.files.internal("images/gamefont.png"));;
	private static Texture BrownCircle = new Texture(Gdx.files.internal("images/browncircle.png"));;
	private static Texture DaveBullet = new Texture(Gdx.files.internal("images/bullet.png"));;
	private static Texture BrownCircleBullet = new Texture(Gdx.files.internal("images/enemyray.png"));;
	private static Texture Gun = new Texture(Gdx.files.internal("images/gun.png"));;
	private static Texture JetpackBar = new Texture(Gdx.files.internal("images/jetpackbar.png"));;
	private static Texture JetpackFill = new Texture(Gdx.files.internal("images/jetpackfill.png"));;
	private static Texture DaveJetpack = new Texture(Gdx.files.internal("images/davejetpack.png"));;
	
	public static ImageGrid bar = new ImageGrid(Bar, 320, 2);
	public static ImageGrid dave = new ImageGrid(Dave, 16, 16);
	public static ImageGrid explosion = new ImageGrid(Explosion, 16, 16);
	public static ImageGrid tiles = new ImageGrid(Tiles, 16, 16);
	public static ImageGrid gamefont = new ImageGrid(GameFont, 12, 12);
	public static ImageGrid browncircle = new ImageGrid(BrownCircle, 27, 15);
	public static ImageGrid davebullet = new ImageGrid(DaveBullet, 8, 3);
	public static ImageGrid browncirclebullet = new ImageGrid(BrownCircleBullet, 18, 3);
	public static ImageGrid gun = new ImageGrid(Gun, 18, 11);
	public static ImageGrid jetpackbar = new ImageGrid(JetpackBar, 128, 12);
	public static ImageGrid jetpackfill = new ImageGrid(JetpackFill, 2, 4);
	public static ImageGrid davejetpack = new ImageGrid(DaveJetpack, 18, 16);
}
