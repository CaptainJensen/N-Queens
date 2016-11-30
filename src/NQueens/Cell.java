package NQueens;

public class Cell {
	private int r;
	private int c;
	
	public Cell(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}	
}
