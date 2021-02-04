package br.unicamp.ic.mc322.projeto.labyrinth;

import br.unicamp.ic.mc322.projeto.game.LabyrinthObjectVisitor;

public class Checkpoint extends LabyrinthObject {
	private boolean conquered;

	Checkpoint(int x, int y) {
		super(x, y);
		this.conquered = false;
	}
	
	void setConquered() {
		this.conquered = true;
	}

	public boolean isConquered() {
		return this.conquered;
	}

	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}

}
