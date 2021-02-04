package br.unicamp.ic.mc322.projeto.labyrinth;

import java.util.List;
import br.unicamp.ic.mc322.projeto.game.LabyrinthObjectVisitor;

public class EvasiveGhost extends Ghost {

	EvasiveGhost(int x, int y) {
		super(x, y);
	}

	private double distance(int x1, int x2) {
		return Math.round(x2 - x1);
	}

	private Coordinate averageCd(Coordinate A, Coordinate B, Coordinate C) {
		double averageX = (A.getX() + B.getX() + C.getX()) / 3;
		double averageY = (A.getY() + B.getY() + C.getY()) / 3;
		return new Coordinate((int) averageX, (int) averageY);
	}

	private Direction calculateDirection(List<Wall> walls, Coordinate cd1, Coordinate cd2, Coordinate cd3) {
		if (cd1.getX() == cd2.getX() && cd1.getX() == cd3.getX()) {
			if (distance(this.getX(), averageCd(cd1, cd2, cd3).getX()) < 0) {
				return Direction.RIGHT;
			} else {
				return Direction.LEFT;
			}
		} else if (cd1.getY() == cd2.getY() && cd1.getY() == cd3.getY()) {
			if (distance(this.getY(), averageCd(cd1, cd2, cd3).getY()) < 0) {
				return Direction.DOWN;
			} else {
				return Direction.UP;
			}
		} else {
			if (Math.abs(distance(this.getX(), averageCd(cd1, cd2, cd3).getX())) < Math
					.abs(distance(this.getY(), averageCd(cd1, cd2, cd3).getY()))) {
				if (distance(this.getX(), averageCd(cd1, cd2, cd3).getX()) < 0) {
					return Direction.RIGHT;
				}
				else {
					return Direction.LEFT;
				}
			}
			else if (Math.abs(distance(this.getX(), averageCd(cd1, cd2, cd3).getX())) > Math
					.abs(distance(this.getY(), averageCd(cd1, cd2, cd3).getY()))) {
				if (distance(this.getY(), averageCd(cd1, cd2, cd3).getY()) < 0) {
					return Direction.DOWN;
				}
				else {
					return Direction.UP;
				}
			}
		}
		return Direction.UP; //padrão estabelecido
	}

	// escolhe uma posição que seja em média mais longe dos outros fantasmas
	void move(List<Wall> walls) {
	}

	void move(List<Wall> walls, Coordinate cd1, Coordinate cd2, Coordinate cd3) {
		setCurrentDirection(calculateDirection(walls, cd1, cd2, cd3));
		Coordinate destinyCheck = getDestiny(getCurrentDirection());
		if (!containsWall(destinyCheck.getX(), destinyCheck.getY(), walls)) {
			getCoordinate().changeCoordinates(destinyCheck.getX(), destinyCheck.getY());
		}
	}

	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}
}
