package sprites;

/** A DustBall sprite. */
public class DustBall extends Dirt implements Moveable{

	/**
	 * {@inheritDoc}
	 */
	public DustBall(char symbol, int row, int column, int value) {
		super(symbol, row, column, value);
	}

	/**
	 * {@inheritDoc}
	 */
	public void moveTo(int row, int column) {
		this.row = row;
		this.column = column;
	}

}
