package br.unicamp.ic.mc322.projeto.labyrinth;

import java.util.List;
import java.util.Random;
import br.unicamp.ic.mc322.projeto.game.LabyrinthObjectVisitor;

public class JumperGhost extends Ghost {

	JumperGhost(int x, int y) {
		super(x, y);
	}
	
	//Obtem o tamanho do mapa
	private Coordinate lastCoordinate(List<Wall> walls) {
		int lastX = 0, lastY = 0;
		for (Wall wall : walls) {
			if (wall.getX() >= lastX) {
				lastX = wall.getX();
			}
			if (wall.getY() >= lastY) {
				lastY = wall.getY();
			}
		}
		return new Coordinate(lastX, lastY);
	}
	
	private Coordinate getRandomCoordinate(List<Wall> walls) {
		Random rand = new Random();
		int x;
		x = rand.nextInt((int)lastCoordinate(walls).getX());
		int y;
		y = rand.nextInt((int)lastCoordinate(walls).getY());
		
		while(containsWall(x, y, walls)) {
			x = rand.nextInt((int)lastCoordinate(walls).getX());
			y = rand.nextInt((int)lastCoordinate(walls).getY());
		}
		return new Coordinate(x, y);
		
	}
	
	//se teletransporta numa posição aleatória do mapa
		void move(List<Wall> walls) {
			Coordinate coordinate = getRandomCoordinate(walls);
			getCoordinate().changeCoordinates(coordinate.getX(), coordinate.getY());
		}
	
	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}
}
