package sprites;

/** A Dirt sprite. */
public class Dirt extends Sprite {

	// the score a vacuum is given for cleaning this dirt
	protected int value;
	
	/**
	 * Initializes a Dirt object.
	 * @param symbol the char that represents this Sprite.
	 * @param row the row number this dirt is placed.
	 * @param column the column number this dirt is placed.
	 * @param value the score accrued for cleaning this dirt.
	 */
	public Dirt(char symbol, int row, int column, int value) {
		super(symbol, row, column);
		this.value = value;
	}
	
	/**
	 * Returns the value of this dirt.
	 * @return the value of this dirt.
	 */
	public int getValue() {
		return value;
	}

}
