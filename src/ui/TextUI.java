package ui;

import java.util.Scanner;

import game.VacuumGame;

/** A simple Text UI for VacuumGame.*/
public class TextUI implements UI {

	private VacuumGame game;
	
	/** Initializes a GUI for the given VacuumGame.
	 * @param game The VacuumGame of this GUI 
	 */
	public TextUI(VacuumGame game) {
		this.game = game;
	}
	
	@Override
	public void launchGame() {
		char nextMove;
		Scanner sc = new Scanner(System.in);
		
		// print grid and keep accepting moves until game is over
		while (!(game.gameOver())) {
			System.out.println(game.getGrid().toString());
			System.out.print("Enter move: ");
			nextMove = sc.next().charAt(0);
			this.game.move(nextMove);
			System.out.println();
		}
		
		sc.close();
	}

	@Override
	public void displayWinner() {
		
		if (!this.game.gameOver()) {
	        return;
	    }
	    
	    int won = this.game.getWinner();
		String message;

		if (won == 1) {
			message = "Congratulations Player 1! You won the game with a score of " + 
					this.game.getVacuumOne().getScore() + ".";
		} else { 
			message = "Congratulations Player 2! You won the game with a score of " + 
					this.game.getVacuumTwo().getScore() + ".";
		}
		
		System.out.println(message);
	}
}
