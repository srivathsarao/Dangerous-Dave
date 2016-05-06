package com.ashtav.dangerousdave;

/**
 * class that keeps global information about dave implemented as a singleton
 * keeps information needed in "Game.as" and "Dave.as" methods
 * 
 * @author farinha
 */
public class DaveStatus {

	public Boolean hasGun;
	public Boolean hasJetpack;
	public Integer lives;
	public double jetpackFuel;
	public Integer score;
	private static DaveStatus instance;

	public DaveStatus() {
		hasGun = false;
		hasJetpack = false;
		lives = 3;
		jetpackFuel = 100;
		score = 0;
	}

	public static DaveStatus GetInstance() {
		if (instance == null) {
			instance = new DaveStatus();
		}
		return instance;
	}
}
