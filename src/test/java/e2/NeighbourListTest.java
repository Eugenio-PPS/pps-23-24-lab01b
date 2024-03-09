package e2;

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

    @Test
    public void topLeftCornerHasBottomAndRightNeighbours() {
        Neighbours neighbours = this.list.neighbours(new Pair<>(0, 0));
        assertAll(
                () -> assertTrue(neighbours.getLeft().isEmpty()),
                () -> assertTrue(neighbours.getTop().isEmpty()),
                () -> assertTrue(neighbours.getBottom().isPresent()),
                () -> assertTrue(neighbours.getRight().isPresent()),
                () -> assertEquals(new Pair<>(0, 1), neighbours.getBottom().get()),
                () -> assertEquals(new Pair<>(1, 0), neighbours.getRight().get())
        );
    }

    @Test
    public void bottomRightCornerHasTopAndLeftNeighbours() {
        Neighbours neighbours = this.list.neighbours(new Pair<>(LIST_WIDTH - 1, LIST_HEIGHT - 1));
        assertAll(
                () -> assertTrue(neighbours.getLeft().isPresent()),
                () -> assertTrue(neighbours.getTop().isPresent()),
                () -> assertTrue(neighbours.getBottom().isEmpty()),
                () -> assertTrue(neighbours.getRight().isEmpty()),
                () -> assertEquals(new Pair<>(LIST_WIDTH - 2, LIST_HEIGHT - 1), neighbours.getLeft().get()),
                () -> assertEquals(new Pair<>(LIST_WIDTH - 1, LIST_HEIGHT - 2), neighbours.getTop().get())
        );
    }

    @Test
    public void middleCellHasAllNeighbours() {
        int x = LIST_WIDTH - 2;
        int y = LIST_HEIGHT - 2;
        Neighbours neighbours = this.list.neighbours(new Pair<>(x, y));
        assertAll(
                () -> assertTrue(neighbours.getLeft().isPresent()),
                () -> assertTrue(neighbours.getTop().isPresent()),
                () -> assertTrue(neighbours.getBottom().isPresent()),
                () -> assertTrue(neighbours.getRight().isPresent()),
                () -> assertEquals(new Pair<>(x - 1, y), neighbours.getLeft().get()),
                () -> assertEquals(new Pair<>(x, y - 1), neighbours.getTop().get()),
                () -> assertEquals(new Pair<>(x, y + 1), neighbours.getBottom().get()),
                () -> assertEquals(new Pair<>(x + 1, y), neighbours.getRight().get())
        );
    }

    @Test
    public void cannotGetNeighboursOfOutOfBoundsPosition() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> this.list.neighbours(new Pair<>(LIST_WIDTH, LIST_HEIGHT))
        );
    }

    @Test
    public void cannotGetNeighboursOfNegativePosition() {
        assertThrows(
                IllegalArgumentException.class,
                () -> this.list.neighbours(new Pair<>(-1, -1))
        );
    }

    @Test
    public void canBeInitialisedWithInitialiser() {
        int value = 1;
        NeighbourList<Integer> list = new NeighbourListImpl<>(
                new Pair<>(LIST_WIDTH, LIST_HEIGHT),
                (position) -> Optional.of(value)
        );

        Optional<Integer> result = list.get(new Pair<>(0, 0));

        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(value, result.get())
        );
    }

}
