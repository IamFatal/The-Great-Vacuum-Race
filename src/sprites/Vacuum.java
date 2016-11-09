package sprites;

import game.Constants;

/** A Vacuum sprite which keeps track of its 
 *  score, fullness, location, and the Sprite it is on top of.
 */
public class Vacuum extends Sprite implements Moveable{

	// this vacuum's score total
	private int score;
	
	// the maximum amount of dirt this vacuum can hold
	private int capacity;
	
	// the amount of dirt this vacuum is carrying
	private int fullness;
	
	// the Sprite underneath this vacuum
	private Sprite under;
	
	/**
	 * Initializes a Vacuum object.
	 * @param symbol the character that represents this sprite.
	 * @param row the row where this Vacuum is positioned.
	 * @param column the column where this Vacuum is positioned.
	 * @param capacity the amount of dirt this Vacuum can hold.
	 */
	public Vacuum(char symbol, int row, int column, int capacity) {
		super(symbol, row, column);
		this.score = Constants.INIT_SCORE;
		this.capacity = capacity;
		this.fullness = Constants.EMPTY;
		this.under = new CleanHallway(Constants.CLEAN, row, column);
	}

	/**
	 * Checks if this Vacuum can pick up a dirt.
	 * @param score amount of points this Vacuum receives for cleaning the tile.
	 * @return true if this Vacuum can pick up a dirt, otherwise false.
	 */
	public boolean clean(int score) {
		if (this.fullness < this.capacity) {
			this.score += score;
			this.fullness += Constants.FULLNESS_INC;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Empties this Vacuum.
	 */
	public void empty() {
		this.fullness = Constants.EMPTY;
	}
	
	/**
	 * Returns the score of this Vacuum.
	 * @return the score of this Vacuum.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Returns the Sprite underneath this Vacuum.
	 * @return the Sprite underneath this Vacuum.
	 */
	public Sprite getUnder() {
		return under;
	}

	/**
	 * Sets the Sprite underneath this Vacuum to the Sprite given.
	 * @param under the Sprite which is underneath this Vacuum.
	 */
	public void setUnder(Sprite under) {
		this.under = under;
	}

	/**
	 * {@inheritDoc}
	 */
	public void moveTo(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object other) {
		return (other instanceof Vacuum && 
				this.symbol == ((Vacuum) other).getSymbol() &&
				this.row == ((Vacuum) other).getRow() &&
				this.column == ((Vacuum) other).getColumn() &&
				this.under == ((Vacuum) other).getUnder() &&
				this.score == ((Vacuum) other).getScore());
	}

}
