package br.unicamp.ic.mc322.projeto.game;

import br.unicamp.ic.mc322.projeto.labyrinth.ChaserGhost;
import br.unicamp.ic.mc322.projeto.labyrinth.Checkpoint;
import br.unicamp.ic.mc322.projeto.labyrinth.EvasiveGhost;
import br.unicamp.ic.mc322.projeto.labyrinth.JumperGhost;
import br.unicamp.ic.mc322.projeto.labyrinth.Player;
import br.unicamp.ic.mc322.projeto.labyrinth.RandomGhost;
import br.unicamp.ic.mc322.projeto.labyrinth.SuperCheckpoint;
import br.unicamp.ic.mc322.projeto.labyrinth.Wall;

public interface LabyrinthObjectVisitor {

	public void visit(Player player);

	public void visit(Wall wall);

	public void visit(Checkpoint checkpoint);
	
	public void visit(SuperCheckpoint superCheckpoint);
	
	public void visit(RandomGhost fantasma);
	
	public void visit(ChaserGhost fantasma);
	
	public void visit(JumperGhost fantasma);
	
	public void visit(EvasiveGhost fantasma);
	
}
