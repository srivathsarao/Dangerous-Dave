package com.ashtav.dangerousdave.framework;

public class Camera {

	public Integer offsetX;
	public Integer offsetY;

	public Integer x;
	public Integer y;
	public Integer width;
	public Integer height;

	public Camera(Integer offsetX, Integer offsetY, Integer x, Integer y, Integer width, Integer height) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}