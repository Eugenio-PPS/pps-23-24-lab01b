package e2;

import e1.Pair;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class NeighbourListTest {
    NeighbourList<Integer> list;
    private static final int LIST_WIDTH = 3;
    private static final int LIST_HEIGHT = 3;
    @BeforeEach
    public void init() {
        this.list = new NeighbourListImpl<>(new Pair<>(LIST_WIDTH, LIST_HEIGHT));
    }

    @Test
    public void cellStartsEmpty() {
        Optional<Integer> result = this.list.get(new Pair<>(1, 1));
        assertTrue(result.isEmpty());
    }

    @Test
    public void cannotGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> this.list.get(new Pair<>(LIST_WIDTH + 1, LIST_HEIGHT + 1))
        );
    }

    @Test
    public void cannotGetNegativeIndexes() {
        assertThrows(IllegalArgumentException.class,
                () -> this.list.get(new Pair<>(-1, -1))
        );
    }

    @Test
    public void cellCanBeSet() {
        int value = 42;
        this.list.set(new Pair<>(1, 1), value);
        Optional<Integer> result = this.list.get(new Pair<>(1, 1));
        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(value, result.get())
        );
    }

    @Test
    public void cannotSetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> this.list.set(new Pair<>(LIST_WIDTH + 1, LIST_HEIGHT + 1), 42)
        );
    }

    @Test
    public void cannotSetNegativeIndexes() {
        assertThrows(IllegalArgumentException.class,
                () -> this.list.set(new Pair<>(-1, -1), 42)
        );
    }

}
