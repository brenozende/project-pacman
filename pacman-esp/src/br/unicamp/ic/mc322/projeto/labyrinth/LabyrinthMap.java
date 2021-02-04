package br.unicamp.ic.mc322.projeto.labyrinth;

import java.util.List;
import br.unicamp.ic.mc322.projeto.game.LabyrinthObjectVisitor;

public class LabyrinthMap {
	private RandomGhost random;
	private ChaserGhost chaser;
	private JumperGhost jumper;
	private EvasiveGhost evasive;
	private Player player;
	private List<Checkpoint> checkpoints;
	private List<SuperCheckpoint> superCheckpoints;
	private List<Wall> walls;
	private int widht; // largura
	private int height; // altura
	private int score = 0;
	private int lifes = 3;
	private int activationTime = 0;

	protected LabyrinthMap(int widht, int height, Player player, List<Checkpoint> checkpoints,
			List<SuperCheckpoint> superCheckpoints, List<Wall> walls, RandomGhost random,
			EvasiveGhost evasive, ChaserGhost chaser, JumperGhost jumper) {
		this.height = height;
		this.widht = widht;
		this.player = player;
		this.checkpoints = checkpoints;
		this.superCheckpoints = superCheckpoints;
		this.walls = walls;
		this.random = random;
		this.chaser = chaser;
		this.jumper = jumper;
		this.evasive = evasive;
	}

	private void incrementScore(boolean superPoint) {
		if (superPoint) {
			score += 100;
		} else {
			score += 10;
		}
		if (score % 5000 == 0 && score != 0) {
			this.ganhaVida();
		}
	}

	private void perdeVida() {
		this.lifes--;
	}

	private void ganhaVida() {
		this.lifes++;
	}

	// Se alterar o mapa, rever posicoes!!!
	private void ghostCross(Ghost ghost) {
		if (ghost.getActive()) {
			this.perdeVida();
			player.getCoordinate().changeCoordinates(39, 14);
			random.getCoordinate().changeCoordinates(38, 6);
			chaser.getCoordinate().changeCoordinates(36, 6);
			jumper.getCoordinate().changeCoordinates(34, 6);
			evasive.getCoordinate().changeCoordinates(40, 6);
		} else {
			score += 300;
			ghost.getCoordinate().changeCoordinates(39, 4);
		}
	}

	private void ghostSearch() {
		if (player.getCoordinate().equals(random.getCoordinate())) {
			this.ghostCross(random);
		} else if (player.getCoordinate().equals(chaser.getCoordinate())) {
			this.ghostCross(chaser);
		} else if (player.getCoordinate().equals(jumper.getCoordinate())) {
			this.ghostCross(jumper);
		} else if (player.getCoordinate().equals(evasive.getCoordinate())) {
			this.ghostCross(evasive);
		}
	}

	private void moverFantasmas() {
		random.move(walls);
		chaser.move(walls, player.getCoordinate());
		jumper.move(walls);
		evasive.move(walls, random.getCoordinate(), chaser.getCoordinate(), jumper.getCoordinate());
	}

	private void ghostsActivity(boolean active) {
		random.setActive(active);
		chaser.setActive(active);
		jumper.setActive(active);
		evasive.setActive(active);
	}

	public int getWidth() {
		return this.widht;
	}

	public int getHeight() {
		return this.height;
	}

	public int getLife() {
		return this.lifes;
	}

	public int getScore() {
		return this.score;
	}

	public void updateMap(Direction direction) {
		// Verifica e regula o tempo de ativacao dos fantasmas
		if (activationTime >= 1) {
			activationTime--;
			if (activationTime == 0) {
				this.ghostsActivity(true);
			}
		}
		player.move(direction, walls);
		this.ghostSearch(); // Procura identificar se o player bateu no fantasma
		moverFantasmas();
		this.ghostSearch();
		for (Checkpoint checkpoint : this.checkpoints) {
			if (player.getCoordinate().equals(checkpoint.getCoordinate())) {
				if (!checkpoint.isConquered()) {
					this.incrementScore(false); // soma pontuacao normal
					checkpoint.setConquered();
				}
			}
		}
		for (SuperCheckpoint superCheckpoint : this.superCheckpoints) {
			if (player.getCoordinate().equals(superCheckpoint.getCoordinate())) {
				if (!superCheckpoint.isConquered()) {
					this.incrementScore(true); // soma super pontuacao
					superCheckpoint.setConquered();
					// Desativa os fantasmas por 50 rodadas
					activationTime = 50;
					this.ghostsActivity(false);
				}
			}
		}
	}

	public boolean isDone() {
		if (lifes <= 0) {
			return true;
		}
		for (Checkpoint checkpoint : this.checkpoints) {
			if (!checkpoint.isConquered()) {
				return false;
			}
		}
		return true;
	}

	public void accept(LabyrinthObjectVisitor visitor) {
		for (Wall wall : walls) {
			wall.accept(visitor);
		}
		for (Checkpoint checkpoint : checkpoints) {
			checkpoint.accept(visitor);
		}
		for (SuperCheckpoint superCheckpoint : superCheckpoints) {
			superCheckpoint.accept(visitor);
		}
		player.accept(visitor);
		random.accept(visitor);
		chaser.accept(visitor);
		jumper.accept(visitor);
		evasive.accept(visitor);
	}

}
