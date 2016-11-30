package NQueens;

import java.util.ArrayList;
import java.util.Random;

public class Chessboard {
	private int[][] board;
	public final int QUEEN = -2;
	public final int ELIMINATED = -1;
	static Random randy = new Random();
	/**
	 * constructor
	 * initializes board to be of size n-by-n and containing all zeroes
	 */
	public Chessboard(int n) {
		board = new int[n][n];
		refreshBoard();
	}
	
	/**
	 * sets value of all cells in board to 0
	 */
	public void refreshBoard() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				board[r][c] = 0;
			}
		}
	}

	/**
	 * prints the contents of each square in board in tabular format
	 * if square contains QUEEN, print 'Q'
	 * if square contains ELIMINATED, print '~'
	 * otherwise, print '-'
	 */
	public void printGrid() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] == QUEEN) {
					System.out.print("Q\t");
				}
				else if (board[r][c] == ELIMINATED) {
					System.out.print("~\t");
				}
				else {
					System.out.print("-\t");
				}
			}
			System.out.println();
		} 
	}
	
	/**
	 * calls scoreSquare method for each square on board
	 * places result from each call to scoreSquare in that square
	 */
	public void scoreSpaces() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				board[r][c] = scoreSquare(r,c);
			}
		}
	}

	/**
	 * returns true if there is an available square on board
	 * returns false if there are no available squares
	 */
	public boolean checkBoard() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] != QUEEN && board[r][c] != ELIMINATED) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * changes the value of board[row][col] to QUEEN
	 * changes the value of all squares that are eliminated by this queen to 	 
	 * ELIMINATED 
	 */
	public void placeQueen(int row, int col) {		
		// move down col
		for (int r = 0; r < board.length; r++) {
			board[r][col] = ELIMINATED;
		}
		// move across row
		for (int c = 0; c < board[row].length; c++) {
			board[row][c] = ELIMINATED;
		}
		// move up & left
		for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
			board[r][c] = ELIMINATED;
		}
		// move up & right
		for (int r = row, c = col; r >= 0 && c < board[r].length; r--, c++) {
			board[r][c] = ELIMINATED;
		}
		// move down & left
		for (int r = row, c = col; r < board.length && c >= 0; r++, c--) {
			board[r][c] = ELIMINATED;
		}
		// move down & right
		for (int r = row, c = col; r < board.length && c < board[r].length; r++, c++) {
			board[r][c] = ELIMINATED;
		}
		// ** do not eliminate the current cell **
		// set current cell to QUEEN
		board[row][col] = QUEEN;
	}
	
	/**
	 * returns ELIMINATED if square at row, col contains ELIMINATED 
	 * returns QUEEN if square at row, col contains QUEEN
	 * otherwise, counts the number of squares that would become unavailable 
	 * if the square at row, col were to receive a queen (including the 	  
     * square at row,col); this count is returned
	 */
	public int scoreSquare(int row, int col) {
		if (board[row][col] == QUEEN || board[row][col] == ELIMINATED) {
			return board[row][col];
		}
		else {
			// ** do not overcount the current cell **
			int count = 1;
			// move down col
			for (int r = 0; r < board.length; r++) {
				if (board[r][col] != ELIMINATED && r != row) {
					count++;
				}
			}
			// move across row
			for (int c = 0; c < board[row].length; c++) {
				if (board[row][c] != ELIMINATED && c != col) {
					count++;
				}
			}
			// move up & left
			for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
				if (board[r][c] != ELIMINATED && (r != row || c != col)) {
					count++;
				}
			}
			// move up & right
			for (int r = row, c = col; r >= 0 && c < board[r].length; r--, c++) {
				if (board[r][c] != ELIMINATED && (r != row || c != col)) {
					count++;
				}
			}
			// move down & left
			for (int r = row, c = col; r < board.length && c >= 0; r++, c--) {
				if (board[r][c] != ELIMINATED && (r != row || c != col)) {
					count++;
				}
			}
			// move down & right
			for (int r = row, c = col; r < board.length && c < board[r].length; r++, c++) {
				if (board[r][c] != ELIMINATED && (r != row || c != col)) {
					count++;
				}
			}			
			return count;
		}		
	}
	
	/**
	 * calculates the smallest non-negative score on the board
	 * finds the squares that contain this score
	 * randomly selects one of these squares 
	 * calls placeQueen method to place a queen in this square
	 */
	public void findAndPlace() {
		// find non-negative min
		int min = Integer.MAX_VALUE;
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] >= 0 && board[r][c] < min) {
					min = board[r][c];
				}
			}
		}
		// create list of cells that contain min
		ArrayList<Cell> mins = new ArrayList<Cell>();
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] == min) {
					mins.add(new Cell(r,c));
				}
			}
		}
		// randomly select one of the values in the list
		Cell choice = mins.get(randy.nextInt(mins.size()));
		// call placeQueen	
		this.placeQueen(choice.getR(),choice.getC());
	}
}




