package br.unicamp.ic.mc322.projeto.labyrinth;

import java.util.List;
import java.util.Random;
import br.unicamp.ic.mc322.projeto.game.LabyrinthObjectVisitor;

public class RandomGhost extends Ghost {

	RandomGhost(int x, int y) {
		super(x, y);
	}

	private Direction randomDirection() {
		Random rand = new Random();
		int n = rand.nextInt(4);
		n++;
		switch (n) {
		case 1:
			return Direction.UP;
		case 2:
			return Direction.LEFT;
		case 3:
			return Direction.DOWN;
		case 4:
			return Direction.RIGHT;
		}
		return null;
	}
	void move(List<Wall> walls) {
		// verificar direção. se for uma parede, mudar. caso contrario, continuar.
		Coordinate destinyCheck = getDestiny(this.getCurrentDirection());
		while (containsWall(destinyCheck.getX(), destinyCheck.getY(), walls)) {
			Direction direction = randomDirection();
			this.setCurrentDirection(direction);
			destinyCheck = getDestiny(direction);
		}
		if (!containsWall(destinyCheck.getX(), destinyCheck.getY(), walls)) {
			Coordinate destiny = getDestiny(this.getCurrentDirection());
			getCoordinate().changeCoordinates(destiny.getX(), destiny.getY());
		}
	}

	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}
}
