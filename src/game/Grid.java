package game;

/**
 * A simple Grid interface.
 * @param <T> This describes my type parameter
 */
public interface Grid<T> {
	
	/**
	 * Places item at given row and column on this grid.
	 * @param row the given row number.
	 * @param column the given column number.
	 * @param item an object of type T to be placed on this grid
	 */
	public void setCell(int row, int column, T item);
	
	/**
	 * Returns the item in this grid at the given row and column.
	 * @param row the given row number.
	 * @param column the given column number.
	 * @return the item in this grid at the given row and column.
	 */
	public T getCell(int row, int column);
	
	/**
	 * Returns the number of rows in this grid.
	 * @return the number of rows in this grid.
	 */
	public int getNumRows();
	
	/**
	 * Returns the number of columns in this grid.
	 * @return the number of columns in this grid.
	 */
	public int getNumColumns();
	
	/**
	 *{@inheritDoc}
	 */
	public boolean equals(Object other);
	
	/**
	 * Returns the string representation of this grid.
	 * @return the string representation of this grid.
	 */
	public String toString();
}
