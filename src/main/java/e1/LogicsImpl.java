package e1;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final Random random = new Random();
	private final int size;

	private static void checkSize(int size) {
		if(size < 2) {
			throw new IllegalArgumentException("Board should be 2x2 or larger!");
		}
	}
	 
    public LogicsImpl(int size){
		checkSize(size);
    	this.size = size;
        this.pawn = this.randomEmptyPosition();
        this.knight = this.randomEmptyPosition();
    }

	public LogicsImpl(int size, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition){
		checkSize(size);
		this.size = size;
		this.pawn = pawnPosition;
		this.knight = knightPosition;
	}

	private final Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Below a compact way to express allowed moves for the knight
		int x = row-this.knight.getX();
		int y = col-this.knight.getY();
		if (validKnightMove(x, y)) {
			this.knight = new Pair<>(row,col);
			return this.pawn.equals(this.knight);
		}
		throw new IllegalArgumentException("Illegal knight move");
	}

	private static boolean validKnightMove(int row, int col) {
		return row != 0 && col != 0 && Math.abs(row) + Math.abs(col) == 3;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}

	@Override
	public Pair<Integer, Integer> getKnightPosition() {
		return this.knight;
	}

	@Override
	public Pair<Integer, Integer> getPawnPosition() {
		return this.pawn;
	}
}
