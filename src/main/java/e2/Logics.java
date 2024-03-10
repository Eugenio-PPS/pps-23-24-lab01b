package e2;

public interface Logics {

    boolean hasMine(Pair<Integer, Integer> minePosition);

    void flag(Pair<Integer, Integer> cellPosition);

    boolean isFlagged(Pair<Integer, Integer> cellPosition);

    int getNumberOfNeighbouringMines(Pair<Integer, Integer> position);
}
