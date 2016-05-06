package com.ashtav.dangerousdave.physics;

public class Rectangle {

	public Integer x;
	public Integer y;
	public Integer width;
	public Integer height;

	public Rectangle(Integer x, Integer y, Integer width, Integer height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Integer getEndX() {
		return x + width;
	}

	public Integer getEndY() {
		return y + height;
	}

	public boolean intersects(Rectangle other) {
		if ((this.x + this.width) <= other.x) {
			return false;
		}
		if (this.x >= (other.x + other.width)) {
			return false;
		}
		if ((this.y + this.height) <= other.y) {
			return false;
		}
		if (this.y >= (other.y + other.height)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Rectangle[x=" + x + ",y=" + y + ",width=" + width + ",height=" + height + "]";
	}
}