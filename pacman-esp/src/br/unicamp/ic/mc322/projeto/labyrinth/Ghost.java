package br.unicamp.ic.mc322.projeto.labyrinth;

import java.util.List;

public abstract class Ghost extends LabyrinthObject {
	private Direction currentDirection;
	boolean active;

	public Ghost(int x, int y) {
		super(x, y);
		this.currentDirection = Direction.UP;
		active = true;
	}
	
	protected void setActive(boolean active) {
		this.active = active;
	}
	
	protected Direction getCurrentDirection() {
		return this.currentDirection;
	}
	
	protected void setCurrentDirection(Direction direction) {
		this.currentDirection = direction;
	}
	
	protected boolean containsWall(int x, int y, List<Wall> walls) {
		for (Wall wall : walls) {
			if (wall.isSameCoordinates(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	protected Coordinate getDestiny(Direction direction) {
		int destinoX = getX();
		int destinoY = getY();

		switch (direction) {
		case UP:
			destinoY--;
			break;
		case DOWN:
			destinoY++;
			break;
		case LEFT:
			destinoX--;
			break;
		case RIGHT:
			destinoX++;
			break;
		}

		return new Coordinate(destinoX, destinoY);
	}
	
	abstract void move(List<Wall> walls);
	
	public boolean getActive() {
		return this.active;
	}
}
