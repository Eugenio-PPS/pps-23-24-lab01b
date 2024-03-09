package e2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    @Test
    void cellByDefaultStartsWithoutAMine() {
        Cell cell = new CellImpl();

        assertFalse(cell.hasMine());
    }

    @Test
    void cellCanStartWithAMine() {
        Cell cell = new CellImpl(true);

        assertTrue(cell.hasMine());
    }
}
