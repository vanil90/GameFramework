package game.geom;

public class Vector2 {
	private float x;
	private float y;

	public Vector2() {
		x = y = 0;
	}

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void add(Vector2 v) {
		add(v.getX(), v.getY());
	}

	public void add(float x, float y) {
		setX(getX() + x);
		setY(getY() + y);
	}

	public float distance(Vector2 v) {
		float distance;

		distance = (float) Math.sqrt(Math.pow(getX() - v.getX(), 2)
				+ Math.pow(getY() - v.getY(), 2));

		return distance;
	}

	public void normalize() {
		double length = Math.sqrt(x * x + y * y);

		x /= length;
		y /= length;
	}
}
