package sprites;

public interface Moveable {
	
	/**
	 * Moves this object to the new row and column.
	 * @param row the new row number
	 * @param column the new column number
	 */
	public void moveTo(int row, int column);
}
