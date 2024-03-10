package e2;

public interface Logics {

    boolean hasMine(Pair<Integer, Integer> minePosition);

    void flag(Pair<Integer, Integer> cellPosition);

    boolean isFlagged(Pair<Integer, Integer> cellPosition);

    long getNumberOfNeighbouringMines(Pair<Integer, Integer> position);

    void click(Pair<Integer, Integer> cellPosition);

    boolean isClicked(Pair<Integer, Integer> cellPosition);

    boolean allMinesAreFlagged();
}
