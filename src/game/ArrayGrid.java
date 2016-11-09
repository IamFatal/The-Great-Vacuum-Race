package game;

import java.util.Arrays;

/**
 * A simple grid created using 2D arrays.
 * @param <T> This describes my type parameter.
 */
public class ArrayGrid<T> implements Grid<T> {

	private T[][] grid;
	private int numRows;
	private int numColumns;
	
	/**
	 * Initializes an ArrayGrid of type T with the given dimensions.
	 * @param numRows number of rows in this grid
	 * @param numColumns number of columns in this grid
	 */
	public ArrayGrid(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.grid = (T[][]) new Object[numRows][numColumns];
	}

	/**
	 *{@inheritDoc}
	 */
	public void setCell(int row, int column, T item) {
		this.grid[row][column] = item;
	}

	/**
	 *{@inheritDoc}
	 */
	public T getCell(int row, int column) {
		return grid[row][column];
	}

	/**
	 *{@inheritDoc}
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 *{@inheritDoc}
	 */
	public int getNumColumns() {
		return numColumns;
	}
	
	/**
	 *{@inheritDoc}
	 */
	public boolean equals(Object other) {
		return Arrays.deepEquals(grid, (T[][]) other);
	}
	
	/**
	 *{@inheritDoc}
	 */
	public String toString() {
		String txtGrid = "";
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				txtGrid += this.grid[i][j].toString();
			}
			txtGrid += "\n";
		}
		
		return txtGrid;
	}
	
}
