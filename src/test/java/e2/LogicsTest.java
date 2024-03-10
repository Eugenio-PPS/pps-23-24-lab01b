package e2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class LogicsTest {
    private Logics logics;
    private static final int BOARD_SIZE = 5;

    @BeforeEach
    public void init() {
        this.logics = new LogicsImpl(
                BOARD_SIZE,
                // Deterministically place mines
                (p) -> p.getY() % 2 == 0 && p.getX() % 2 == 0
        );
    }

    @Test
    public void boardHasAtLeastOneMine() {
        boolean mineFound = false;
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                mineFound = mineFound || logics.hasMine(new Pair<>(i, j));
            }
        }
        assertTrue(mineFound);
    }

    @Test
    public void aCellCanBeFlagged() {
        Pair<Integer, Integer> cellPosition = new Pair<>(1, 1);
        this.logics.flag(cellPosition);
        assertTrue(this.logics.isFlagged(cellPosition));
    }

    @Test
    public void calculateNumberOfNeighbouringMines() {
        assertEquals(4, this.logics.getNumberOfNeighbouringMines(new Pair<>(1, 1)));
    }

    @Test
    public void aCellCanBeClicked() {
        Pair<Integer, Integer> cellPosition = new Pair<>(1, 1);
        this.logics.click(cellPosition);
        assertTrue(this.logics.isClicked(cellPosition));
    }

    @Test
    public void calculateIfAllMinesFlaggedReturnsFalseIfNotAllMinesAreFlagged() {
        assertFalse(this.logics.allMinesAreFlagged());
    }

    @Test
    public void calculateIfAllMinesFlaggedReturnsTrueIfAllMinesAreFlagged() {
        for(int i = 0; i < BOARD_SIZE; i += 2) {
            for(int j = 0; j < BOARD_SIZE; j += 2) {
                this.logics.flag(new Pair<>(i, j));
            }
        }
        assertTrue(this.logics.allMinesAreFlagged());
    }
}
