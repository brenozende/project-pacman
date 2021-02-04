package br.unicamp.ic.mc322.projeto.labyrinth;

import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LabyrinthMapLoader {

	private static final LabyrinthMapLoader INSTANCE = new LabyrinthMapLoader();

	private LabyrinthMapLoader() {
	}

	public static LabyrinthMapLoader getInstance() {
		return INSTANCE;
	}

	public LabyrinthMap loadMapFromFile(String path) {
		File file = new File(path);
		try {
			BufferedReader rBuff = new BufferedReader(new FileReader(file));
			String line = null;
			char[] b;
			int i = 0;
			int j = 0;
			int lastColumn = 0;
			List<Wall> wallList = new LinkedList<Wall>();
			List<Checkpoint> checkpointList = new LinkedList<Checkpoint>();
			List<SuperCheckpoint> superCheckpointList = new LinkedList<SuperCheckpoint>();
			Player player = null;
			RandomGhost random = null;
			EvasiveGhost evasive = null;
			ChaserGhost chaser = null;
			JumperGhost jumper = null;
			do {
				line = rBuff.readLine();
				if (line != null) {
					b = line.toCharArray();
					for (char a : b) {
						switch (a) {
						case 'A':
							random = new RandomGhost(j, i);
							break;
						case 'E':
							evasive = new EvasiveGhost(j, i);
							break;
						case 'P':
							chaser = new ChaserGhost(j, i);
							break;
						case 'R':
							jumper = new JumperGhost(j, i);
							break;
						case 'W':
							Wall wall = new Wall(j, i);
							wallList.add(wall);
							break;
						case 'J':
							player = new Player(j, i);
							break;
						case '-':
							Checkpoint checkpoint = new Checkpoint(j, i);
							checkpointList.add(checkpoint);
							break;
						case '@':
							SuperCheckpoint superCheckpoint = new SuperCheckpoint(j, i);
							superCheckpointList.add(superCheckpoint);
							break;
						case '.':
							Wall invWall = new Wall(j, i);
							invWall.setInv();
							wallList.add(invWall);
							break;
						default:
							break;
						}
						j++;
						if (lastColumn < j) {
							lastColumn = j;
						}
					}
					j = 0;
					i++;
				}

			} while (line != null);
			rBuff.close();
			return new LabyrinthMap(lastColumn, i, player, checkpointList, superCheckpointList, wallList,
					random, evasive, chaser, jumper);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
