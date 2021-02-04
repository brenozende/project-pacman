package br.unicamp.ic.mc322.projeto.labyrinth;

import br.unicamp.ic.mc322.projeto.game.LabyrinthObjectVisitor;

public abstract class LabyrinthObject {
	private Coordinate coordinate;

	LabyrinthObject(int x, int y) {
		Coordinate coordinate = new Coordinate(x, y);
		this.coordinate = coordinate;
	}
	
	protected Coordinate getCoordinate() {
		return this.coordinate;
	}

	public int getX() {
		return this.coordinate.getX();
	}

	public int getY() {
		return this.coordinate.getY();
	}

	public boolean isSameCoordinates(int x, int y) {
		return coordinate.isSameCoordinates(x, y);
	}

	public boolean isSameCoordinates(LabyrinthObject obj) {
		return isSameCoordinates(obj.getX(), obj.getY());
	}
	
	public abstract void accept(LabyrinthObjectVisitor visitor);
}
