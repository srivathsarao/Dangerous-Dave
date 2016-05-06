package com.ashtav.dangerousdave;

import com.ashtav.dangerousdave.framework.Sprite;

	
	public class Explosion extends Sprite
	{

		public static Integer[] ANIMATION_FRAMES = new Integer[4];
		public static Integer[] ANIMATION_TIMES = new Integer[350];
		
		public Explosion(DaveLevel level, Integer y, Integer x) 
		{
			super(level, Images.explosion, ANIMATION_FRAMES, ANIMATION_TIMES);
			this.setX(x);
			this.setY(y);
			this.setAnimation(0);
			this.playAnimation = true;
		}
		
		
	}
