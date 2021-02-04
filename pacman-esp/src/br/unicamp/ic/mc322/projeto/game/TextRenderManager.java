package br.unicamp.ic.mc322.projeto.game;

import br.unicamp.ic.mc322.projeto.labyrinth.ChaserGhost;
import br.unicamp.ic.mc322.projeto.labyrinth.Checkpoint;
import br.unicamp.ic.mc322.projeto.labyrinth.EvasiveGhost;
import br.unicamp.ic.mc322.projeto.labyrinth.JumperGhost;
import br.unicamp.ic.mc322.projeto.labyrinth.LabyrinthMap;
import br.unicamp.ic.mc322.projeto.labyrinth.LabyrinthObject;
import br.unicamp.ic.mc322.projeto.labyrinth.Player;
import br.unicamp.ic.mc322.projeto.labyrinth.RandomGhost;
import br.unicamp.ic.mc322.projeto.labyrinth.SuperCheckpoint;
import br.unicamp.ic.mc322.projeto.labyrinth.Wall;

class TextRenderManager implements LabyrinthObjectVisitor {
	private char[][] charMap;

	TextRenderManager(int mapWidth, int mapHeight) {
		this.charMap = new char[mapWidth][mapHeight];
	}

	private void clearMap() {
		for (int i = 0; i < charMap[0].length; i++) {
			for (int j = 0; j < charMap.length; j++) {
				charMap[j][i] = ' ';
			}
		}
	}

	private void printMap() {
		for (int i = 0; i < charMap[0].length; i++) {
			for (int j = 0; j < charMap.length; j++) {
				System.out.print(charMap[j][i] + " ");
			}
			System.out.println();
		}
	}

	public void render(LabyrinthMap labyrinthMap) {
		clearMap();
		labyrinthMap.accept(this);
		printMap();
		//printScore();
		System.out.printf("\n");
	}

	private void setSymbol(LabyrinthObject obj, char character) {
		charMap[obj.getX()][obj.getY()] = character;
	}

	@Override
	public void visit(Player player) {
		setSymbol(player, 'G');
	}

	@Override
	public void visit(Wall wall) {
		if (wall.getVis()) {
			setSymbol(wall, 'H');
		}
		else {
			setSymbol(wall, ' ');
		}
	}

	@Override
	public void visit(Checkpoint checkpoint) {
		if (checkpoint.isConquered()) {
			setSymbol(checkpoint, ' ');
		} else {
			setSymbol(checkpoint, '-');
		}
	}
	
	@Override
	public void visit(SuperCheckpoint superCheckpoint) {
		if (superCheckpoint.isConquered()) {
			setSymbol(superCheckpoint, ' ');
		} else {
			setSymbol(superCheckpoint, '@');
		}
	}
	
	@Override
	public void visit(RandomGhost fantasma) {
		if (fantasma.getActive()) {
			setSymbol(fantasma, 'M');
		} else {
			setSymbol(fantasma, 'W');
		}
	}
	
	@Override
	public void visit(ChaserGhost fantasma) {
		if (fantasma.getActive()) {
			setSymbol(fantasma, 'M');
		} else {
			setSymbol(fantasma, 'W');
		}
	}
	
	@Override
	public void visit(JumperGhost fantasma) {
		if (fantasma.getActive()) {
			setSymbol(fantasma, 'M');
		} else {
			setSymbol(fantasma, 'W');
		}
	}
	
	@Override
	public void visit(EvasiveGhost fantasma) {
		if (fantasma.getActive()) {
			setSymbol(fantasma, 'M');
		} else {
			setSymbol(fantasma, 'W');
		}
	}
}
