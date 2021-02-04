package br.unicamp.ic.mc322.projeto.labyrinth;

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void changeCoordinates(int x, int y) {
		this.y = y;
		this.x = x;
	}

	public boolean isSameCoordinates(int x, int y) {
		if (x == this.x && y == this.y) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coordinate) {
			Coordinate pair = (Coordinate) obj;
			return isSameCoordinates(pair.x, pair.y);
		}
		return false;
	}

}
