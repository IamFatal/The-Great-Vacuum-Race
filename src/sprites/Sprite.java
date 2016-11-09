package sprites;

/** A sprite represented by any character which keeps track of its location. */
public abstract class Sprite {

	protected char symbol;
	protected int row;
	protected int column;
	
	/**
	 * Initializes a Sprite object.
	 * @param symbol the char that represents this Sprite.
	 * @param row the row this Sprite is positioned at.
	 * @param column the column this Sprite is positioned at.
	 */
	public Sprite (char symbol, int row, int column) {
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}

	/**
	 * Returns the symbol that represents this Sprite.
	 * @return the symbol that represents this Sprite.
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Returns the row that this Sprite is positioned at.
	 * @return the row that this Sprite is positioned at.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column that this Sprite is positioned at.
	 * @return the column that this Sprite is positioned at.
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object other) {
		return (other instanceof Sprite && 
				this.symbol == ((Sprite) other).getSymbol() &&
				this.row == ((Sprite) other).getRow() &&
				this.column == ((Sprite) other).getColumn());
	}
	
	/**
	 * Returns the string representation of this Sprite's symbol.
	 * @return the string representation of this Sprite's symbol.
	 */
	public String toString() {
		return Character.toString(this.symbol);
	}
	
}
