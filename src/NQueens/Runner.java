package NQueens;

public class Runner {


	public static void main(String[] args) {
		int size = 4;
		int queensPlaced = 0;
		Chessboard cb = new Chessboard(size);
		
		do {
			cb.scoreSpaces();
			if (!cb.checkBoard()) {
				queensPlaced = 0;
				cb.refreshBoard();
			}
			else {
				cb.findAndPlace();
				queensPlaced++;
			}
		} while (queensPlaced < size);
		
		cb.printGrid();
	}
}
