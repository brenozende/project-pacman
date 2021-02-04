package br.unicamp.ic.mc322.projeto.game;

import java.util.Scanner;
import br.unicamp.ic.mc322.projeto.labyrinth.Direction;
import br.unicamp.ic.mc322.projeto.labyrinth.LabyrinthMap;
import br.unicamp.ic.mc322.projeto.labyrinth.LabyrinthMapLoader;
import br.unicamp.ic.mc322.projeto.labyrinth.LabyrinthMapRandom;

public class GameEngine {
	private TextRenderManager renderManager;
	private Scanner scanner;
	private LabyrinthMap labyrinthMap;
	
	public GameEngine() {
		scanner = new Scanner(System.in);
	}
	
	private Direction readCommandFromKeyboard() {
		String string;
		Direction d = null;
		int tentativas = 0;  //Para evitar da msg aparecer com frequencia
		do {
			string = scanner.nextLine();
			tentativas++;
			if (string.length() == 1) {
				switch (string.charAt(0)) {
				case 'w':
					d = Direction.UP;
					break;
				case 'a':
					d = Direction.LEFT;
					break;
				case 's':
					d = Direction.DOWN;
					break;
				case 'd':
					d = Direction.RIGHT;
					break;
				}
			}
			if (d == null && tentativas >= 2) {
				System.out.println("Comando inválido, tente novamente");
			}
		} while (d == null);
		return d;
	}
	
	private void gameLoop() {
		this.renderManager = new TextRenderManager(labyrinthMap.getWidth(), labyrinthMap.getHeight());
		Direction newDirection;
		while (!labyrinthMap.isDone()) { //loop principal
			renderManager.render(labyrinthMap);
			System.out.print("Vida:  " + labyrinthMap.getLife() + "       Pontos:  " + labyrinthMap.getScore() + "\n\n");
			newDirection = readCommandFromKeyboard();
			labyrinthMap.updateMap(newDirection);
		}
		if (labyrinthMap.getLife() <= 0) {
			System.out.println("Você perdeu! Suas vidas acabaram, tente novamente!");
		} else {
			System.out.println("Você venceu! Parabéns!");
		}
		System.out.println("********* Fim de Jogo *******");
	}
	
	private boolean validateInput() {
		try {
			System.out.print("Deseja mapa aleatorio? (true or false)");
			boolean map = scanner.nextBoolean();
			return map;
		} catch (Exception e) {
			System.out.println("Opcao invalida");
			scanner.next();  //Discarta a entrada ruim
			return this.validateInput();
	    }
	}
	
	public void runGame() {
		boolean map;
		System.out.println("******* Jogo Iniciado ********");
		map = validateInput();
		if (!map) {
			labyrinthMap = LabyrinthMapLoader.getInstance().loadMapFromFile("src/br/unicamp/ic/mc322/projeto/mapaFx.txt");
		} else {
			labyrinthMap = LabyrinthMapRandom.getInstance().createRandomMap();
		}
		this.gameLoop();
	}
}
