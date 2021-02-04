package br.unicamp.ic.mc322.projeto.labyrinth;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LabyrinthMapRandom {
	
	private static final LabyrinthMapRandom INSTANCE = new LabyrinthMapRandom();

	private LabyrinthMapRandom() {
	}

	public static LabyrinthMapRandom getInstance() {
		return INSTANCE;
	}
	
	public LabyrinthMap createRandomMap() {
		int i = 27; //linha
		int j = 78; //coluna
		List<Wall> wallList = new LinkedList<Wall>();
		List<Checkpoint> checkpointList = new LinkedList<Checkpoint>();
		List<SuperCheckpoint> superCheckpointList = new LinkedList<SuperCheckpoint>();
		Player player = null;
		RandomGhost random = null;
		EvasiveGhost evasive = null;
		ChaserGhost chaser = null;
		JumperGhost jumper = null;
		
		for (int m = 0; m < i; m++) {
			for (int n = 0; n < j; n++) {
				if (m == 0 || m == 26 || n == 0 || n == 77) {
					Wall wall = new Wall(n, m);
					wallList.add(wall);
				}
				else if (m == 6 && n == 34) {
					random = new RandomGhost(n, m);
				} else if (m == 6 && n == 36) {
					evasive = new EvasiveGhost(n, m);
				} else if (m == 6 && n == 38) {
					chaser = new ChaserGhost(n, m);
				} else if (m == 6 && n == 40) {
					jumper = new JumperGhost(n, m);
				} else if (m == 14 && n == 39) {
					player = new Player(n, m);
				} else {
					Random rand = new Random();
					int n_ale = rand.nextInt(1000);
					if (n_ale <= 3) { // 0,3% superpastilha
						SuperCheckpoint superCheckpoint = new SuperCheckpoint(n, m);
						superCheckpointList.add(superCheckpoint);
					} else if (n_ale <= 450) { //44,7% de pastihas comuns
						Checkpoint checkpoint = new Checkpoint(n, m);
						checkpointList.add(checkpoint);
					} else if (n_ale <= 950) { //50% de paredes
						Wall wall = new Wall(n, m);
						wallList.add(wall);
					}
					//Os outros 5% é para espaço em branco
				}
			}
		}
		
		return new LabyrinthMap(j, i, player, checkpointList, superCheckpointList, wallList,
				random, evasive, chaser, jumper);
	}
}
