package br.unicamp.ic.mc322.projeto.labyrinth;

import br.unicamp.ic.mc322.projeto.game.LabyrinthObjectVisitor;

public class SuperCheckpoint extends Checkpoint{

	SuperCheckpoint(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}
}
