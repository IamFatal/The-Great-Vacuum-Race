package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;

/**
 * A class that represents the basic functionality of the vacuum game.
 * 
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 * 
 * @author Shray Sharma
 */
public class VacuumGame {

    // a random number generator to move the DustBalls
    private Random random;

    // the grid
    private Grid<Sprite> grid;

    // the first player
    private Vacuum vacuum1;

    /// the second player
    private Vacuum vacuum2;

    // the dirt (both static dirt and mobile dust balls)
    private List<Dirt> dirts;

    /**
     * Creates a new VacuumGame that corresponds to the given input text file.
     * Assumes that the input file has one or more lines of equal lengths, and
     * that each character in it (other than newline) is a character that 
     * represents one of the sprites in this game.
     * @param layoutFileName path to the input grid file
     * @throws IOException
     */
    public VacuumGame(String layoutFileName) throws IOException {
        this.dirts = new ArrayList<Dirt>();
        this.random = new Random();

        // open the file, read the contents, and determine 
        // dimensions of the grid
        int[] dimensions = getDimensions(layoutFileName);
        this.grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);

        // open the file again, read the contents, and store them in grid
        Scanner sc = new Scanner(new File(layoutFileName));
        String nextLine = sc.nextLine();

        for (int i = 0; i < dimensions[0]; i++) {
			for (int j = 0; j < dimensions[1]; j++) {
				grid.setCell(i, j, makeSprite(nextLine.charAt(j), i, j));
			}
			if (sc.hasNextLine()) {
				nextLine = sc.nextLine();
			}
        }

        sc.close();
    }

    /**
     * Returns the grid of Sprites being used by this game.
     * @return the grid of Sprites being used by this game.
     */
    public Grid<Sprite> getGrid() {
		return grid;
	}

    /**
     * Returns player 1's vacuum.
     * @return player 1's vacuum.
     */
	public Vacuum getVacuumOne() {
		return vacuum1;
	}

    /**
     * Returns player 2's vacuum.
     * @return player 2's vacuum.
     */
	public Vacuum getVacuumTwo() {
		return vacuum2;
	}
	
	/**
	 * Returns the number of rows in the grid.
	 * @return the number of rows in the grid.
	 */
	public int getNumRows() {
		return grid.getNumRows();
	}
	
	/**
	 * Returns the number of columns in the grid.
	 * @return the number of columns in the grid.
	 */
	public int getNumColumns() {
		return grid.getNumColumns();
	}
	
	/**
	 * Returns the object in the grid at the given row and column.
	 * @param i the row number of the desired cell.
	 * @param j the column number of the desired cell.
	 * @return the object at the given row and column of the grid.
	 */
	public Sprite getSprite(int i, int j) {
		return grid.getCell(i, j);
	}
	
	/**
	 * Returns true if the next move is valid.
	 * @param nextMove the next move.
	 * @return true if the move is valid, else false.
	 */
	public boolean move(char nextMove) {
		return isValidMove(nextMove);
	}
	
	/**
	 * Returns true if this game is finished, else false.
	 * @return true if this game is finished, else false.
	 */
	public boolean gameOver() {
		return (dirts.isEmpty());
	}

	/**
	 * Returns 1 if player 1 wins, and 2 if player 2 wins
	 * or if the game ends in a tie.
	 * @return 1 if player 1 wins, and 2 if player 2 wins
	 * or if the game ends in a tie.
	 */
	public int getWinner() {
		return vacuum1.getScore() > vacuum2.getScore() ? 1 : 2;
	}
	
	/**
     * Returns the dimensions of the grid in the file named layoutFileName.
     * @param layoutFileName path of the input grid file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the grid that
     * corresponds to the given input grid file
     * @throws IOException
     */
    private int[] getDimensions(String layoutFileName) throws IOException {       

        Scanner sc = new Scanner(new File(layoutFileName));

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }
    
    private Sprite makeSprite(char symbol, int row, int column) {
    	
    	if (symbol == Constants.P1) {
    		this.vacuum1 = new Vacuum(Constants.P1, row, column,
    				Constants.CAPACITY);
    		return this.vacuum1;
    	}
    	else if (symbol == Constants.P2) {
    		this.vacuum2 = new Vacuum(Constants.P2, row, column,
    				Constants.CAPACITY);
    		return this.vacuum2;
    	}
    	else if (symbol == Constants.CLEAN) {
    		return new CleanHallway(Constants.CLEAN, row, column);
    	}
    	else if (symbol == Constants.DIRT) {
    		this.dirts.add(new Dirt(Constants.DIRT, row, column,
    				Constants.DIRT_SCORE));
    		return this.dirts.get(dirts.size() - 1);
    	}
    	else if (symbol == Constants.DUST_BALL) {
    		this.dirts.add(new DustBall(Constants.DUST_BALL, row, column,
    				Constants.DUST_BALL_SCORE));
    		return this.dirts.get(dirts.size() - 1);
    	}
    	else if (symbol == Constants.DUMPSTER) {
    		return new Dumpster(Constants.DUMPSTER, row, column);
    	}
    	else {
    		return new Wall(Constants.WALL, row, column);
    	}
    }
    
    private boolean isValidMove(char move) {
    	
    	if (move == Constants.P1_UP){
    		moveAllDustBalls();
    		return makeMove(this.vacuum1, Constants.UP, 0);
    	}
    	else if (move == Constants.P2_UP) {
    		moveAllDustBalls();
    		return makeMove(this.vacuum2, Constants.UP, 0);
    	}
    	else if (move == Constants.P1_LEFT) {
    		moveAllDustBalls();
    		return makeMove(this.vacuum1, 0, Constants.LEFT);
    	}
    	else if (move == Constants.P2_LEFT) {
    		moveAllDustBalls();
    		return makeMove(this.vacuum2, 0, Constants.LEFT);
    	}
    	else if (move == Constants.P1_DOWN) {
    		moveAllDustBalls();
    		return makeMove(this.vacuum1, Constants.DOWN, 0);
    	}
    	else if (move == Constants.P2_DOWN) {
    		moveAllDustBalls();
    		return makeMove(this.vacuum2, Constants.DOWN, 0);
    	}
    	else if (move == Constants.P1_RIGHT) {
    		moveAllDustBalls();
    		return makeMove(this.vacuum1, 0, Constants.RIGHT);
    	}
    	else if (move == Constants.P2_RIGHT) {
    		moveAllDustBalls();
    		return makeMove(this.vacuum2, 0, Constants.RIGHT);
    	}
    	
    	return false;
    }
    
    private boolean makeMove(Vacuum vacuum, int i, int j) {
    	int row = vacuum.getRow();
    	int col = vacuum.getColumn();
    	Sprite nextSprite = getSprite(row + i, col + j);
    	
    	if (nextSprite instanceof Wall || nextSprite instanceof Vacuum) {
    		return false;
    	}
    	else if (nextSprite instanceof CleanHallway) {
    		moveVacuum(vacuum, i, j, nextSprite);
    		return true;
    	}
    	else if (nextSprite instanceof Dumpster) {
    		vacuum.empty();
    		moveVacuum(vacuum, i, j, nextSprite);
    		return true;
    	}
    	else if (nextSprite instanceof Dirt) {		
    		if (vacuum.clean(((Dirt) nextSprite).getValue())) {
    			this.dirts.remove(nextSprite);
    			moveVacuum(vacuum, i, j,
    					new CleanHallway(Constants.CLEAN, row + i, col + j));
    		}
    		else {
    			moveVacuum(vacuum, i, j, nextSprite);
    		}
    		
    		return true;
    	}
    	
    	return false;
    }
    
    private void moveVacuum(Vacuum vacuum, int i, int j, Sprite sprite) {
    	int row = vacuum.getRow();
    	int col = vacuum.getColumn();
    	
    	// set original cell to the Sprite underneath vacuum
    	// move vacuum to specified cell
    	if (!(vacuum.getUnder() instanceof DustBall)) {
    		this.grid.setCell(row, col, vacuum.getUnder());
    	}
		vacuum.moveTo(row + i, col + j);
		this.grid.setCell(row + i, col + j, vacuum);
		vacuum.setUnder(sprite);
    }
    
    private void moveAllDustBalls() {
    	List<DustBall> dustBalls = getDustBalls();
    	Sprite otherSprite;
    	int row;
    	int col;
    	int[] move;
    	
    	// move every DustBall in this game in a random direction
    	for (int i = 0; i < dustBalls.size(); i++) {
    		row = dustBalls.get(i).getRow();
    		col = dustBalls.get(i).getColumn();
    		move = getRandomMove();
    		otherSprite = getSprite(row + move[0], col + move[1]);
    		
    		// generate a random move, if invalid, generate new one until
    		// valid move is found or DustBall is trapped
    		int k = 0;
    		while (!(otherSprite instanceof CleanHallway ||
    				otherSprite instanceof Dirt) && k < 4) {
    			move = getRandomMove();
        		otherSprite = getSprite(row + move[0], col + move[1]);
        		k++;
    		}
    		
    		if (otherSprite instanceof CleanHallway) {
    			Dirt newDirt = new Dirt(Constants.DIRT, row, col,
    					Constants.DIRT_SCORE);
    			moveDustBall(row, col, dustBalls.get(i), move, newDirt);
    		}
    		else if (otherSprite instanceof Dirt) {
    			this.dirts.remove(otherSprite);
    			Dirt newDirt = new Dirt(Constants.DIRT, row, col,
    					Constants.DIRT_SCORE);
    			moveDustBall(row, col, dustBalls.get(i), move, newDirt);
    		}
    		
    		k = 0;
    	}
    }
    
    private void moveDustBall(int row, int col, DustBall db,
    		int[] move, Dirt newDirt) {
    	
    	// move DustBall to specified cell and dirty the old cell
		this.dirts.add(newDirt);
		this.grid.setCell(row, col, newDirt);
		db.moveTo(row + move[0], col + move[1]);
		this.grid.setCell(row + move[0], col + move[1], db);
    }
    
    private List<DustBall> getDustBalls() {
    	List<DustBall> dustBalls = new ArrayList<DustBall>();
    	
    	// find every DustBall in the list of dirts
    	for (int i = 0; i < dirts.size(); i++) {
			if (dirts.get(i) instanceof DustBall) {
				dustBalls.add((DustBall) dirts.get(i));
			}
    	}
    	
    	return dustBalls;
    }
    
    private int[] getRandomMove() {
    	String[] moves = {"up", "down", "left", "right"};
    	int randInt = random.nextInt(4);
    	String randMove = moves[randInt];
    	int[] move = {0, 0};
    	
    	if (randMove == "up") {
    		move[0] = Constants.UP;
    		move[1] = 0;
    	}
    	else if (randMove == "down") {
    		move[0] = Constants.DOWN;
    		move[1] = 0;
    	}
    	else if (randMove == "left") {
    		move[0] = 0;
    		move[1] = Constants.LEFT;
    	}
    	else if (randMove == "right") {
    		move[0] = 0;
    		move[1] = Constants.RIGHT;
    	}
    	
    	return move;
    }
    
}
