package br.unicamp.ic.mc322.projeto.labyrinth;

import java.util.List;
import java.lang.Math;
import br.unicamp.ic.mc322.projeto.game.LabyrinthObjectVisitor;

public class ChaserGhost extends Ghost {

	ChaserGhost(int x, int y) {
		super(x, y);
	}
	
	private double calculateDistance(Coordinate coordinate) {
		Coordinate destiny = getDestiny(this.getCurrentDirection());
		double cat1 = Math.abs(coordinate.getY() - destiny.getY());
		double cat2 = Math.abs(coordinate.getX() - destiny.getX());
		return Math.sqrt(cat1*cat1 + cat2*cat2);
	}

	private Direction chasingDirection(List<Wall> walls, Coordinate coordinate) {
		setCurrentDirection(Direction.UP);
		double distanceUp = calculateDistance(coordinate);
		setCurrentDirection(Direction.LEFT);
		double distanceLeft = calculateDistance(coordinate);
		setCurrentDirection(Direction.DOWN);
		double distanceDown = calculateDistance(coordinate);
		setCurrentDirection(Direction.RIGHT);
		double distanceRight = calculateDistance(coordinate);
		
		if (distanceUp == distanceLeft) {
			if (!containsWall(getDestiny(Direction.UP).getX(), getDestiny(Direction.UP).getY(), walls)) {
				return Direction.UP;
			}
			else if (!containsWall(getDestiny(Direction.LEFT).getX(), getDestiny(Direction.LEFT).getY(), walls)) {
				return Direction.LEFT;
			}
		}
		if (distanceUp == distanceRight) {
			if (!containsWall(getDestiny(Direction.UP).getX(), getDestiny(Direction.UP).getY(), walls)) {
				return Direction.UP;
			}
			else if (!containsWall(getDestiny(Direction.RIGHT).getX(), getDestiny(Direction.RIGHT).getY(), walls)) {
				return Direction.RIGHT;
			}
		}
		if (distanceDown == distanceRight) {
			if (!containsWall(getDestiny(Direction.DOWN).getX(), getDestiny(Direction.DOWN).getY(), walls)) {
				return Direction.DOWN;
			}
			else if (!containsWall(getDestiny(Direction.RIGHT).getX(), getDestiny(Direction.RIGHT).getY(), walls)) {
				return Direction.RIGHT;
			}
		}
		
		if (distanceDown == distanceLeft) {
			if (!containsWall(getDestiny(Direction.DOWN).getX(), getDestiny(Direction.DOWN).getY(), walls)) {
				setCurrentDirection(Direction.DOWN);
				return Direction.DOWN;
			}
			else if (!containsWall(getDestiny(Direction.LEFT).getX(), getDestiny(Direction.LEFT).getY(), walls)) {
				setCurrentDirection(Direction.LEFT);
				return Direction.LEFT;
			}
		}
		if (distanceUp < distanceLeft && distanceUp < distanceDown && distanceUp < distanceRight) {
			setCurrentDirection(Direction.UP);
			return Direction.UP;
		}
		else if (distanceLeft < distanceUp && distanceLeft < distanceDown && distanceLeft < distanceRight) {
			setCurrentDirection(Direction.LEFT);
			return Direction.LEFT;
		}
		else if (distanceRight < distanceUp && distanceRight < distanceDown && distanceRight < distanceUp) {
			setCurrentDirection(Direction.RIGHT);
			return Direction.RIGHT;
		}
		else {
			setCurrentDirection(Direction.DOWN);
			return Direction.DOWN;
		}
		
	}

	void move(List<Wall> walls) {
	}

	void move(List<Wall> walls, Coordinate coordinate) {
		Coordinate destinyCheck = getDestiny(chasingDirection(walls, coordinate));
		if (!containsWall(destinyCheck.getX(), destinyCheck.getY(), walls)) {
			getCoordinate().changeCoordinates(destinyCheck.getX(), destinyCheck.getY());
		}
	}
	
	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}
}
