package br.unicamp.ic.mc322.projeto.labyrinth;

import br.unicamp.ic.mc322.projeto.game.LabyrinthObjectVisitor;

public class Wall extends LabyrinthObject {
	private boolean visible;

	Wall(int x, int y) {
		super(x, y);
		this.visible = true;
	}

	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}
	
	public void setInv() {
		this.visible = false;
	}
	
	public boolean getVis() {
		return this.visible;
	}

}
