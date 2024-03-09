package e2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    Cell cell;

    @BeforeEach
    void init() {
        this.cell = new CellImpl();
    }
    @Test
    void cellByDefaultStartsWithoutAMine() {
        assertFalse(this.cell.hasMine());
    }

    @Test
    void cellCanStartWithAMine() {
        Cell cell = new CellImpl(true);

        assertTrue(cell.hasMine());
    }

    @Test
    void cellStartsNotFlagged() {
        assertFalse(this.cell.isFlagged());
    }

    @Test
    void cellCanBeFlagged(){
        this.cell.flag();
        assertTrue(this.cell.isFlagged());
    }

    @Test
    void cellCanBeUnFlagged(){
        this.cell.flag();
        this.cell.removeFlag();
        assertFalse(this.cell.isFlagged());
    }

    @Test
    void cellStartsAsNotClicked() {
        assertFalse(this.cell.isClicked());
    }

    @Test
    void cellCanBeClicked() {
        this.cell.click();
        assertTrue(this.cell.isClicked());
    }
}
