package e2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class LogicsTest {
    private Logics logics;
    private static final int BOARD_SIZE = 5;

    @BeforeEach
    public void init() {
        this.logics = new LogicsImpl(BOARD_SIZE);
    }

    @Test
    public void boardHasAtLeastOneMine() {
        Pair<Integer, Integer> minePosition = new Pair<>(1, 1);
        boolean mineFound = false;
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                mineFound = mineFound || logics.hasMine(new Pair<>(i, j));
            }
        }
        assertTrue(mineFound);
    }
}
