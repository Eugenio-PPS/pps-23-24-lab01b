package e2;

import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class NeighbourTableTest {
    NeighbourTable<Integer> list;
    private static final int LIST_WIDTH = 3;
    private static final int LIST_HEIGHT = 3;
    public static final Pair<Integer, Integer> BOARD_SIZE = new Pair<>(LIST_WIDTH, LIST_HEIGHT);

    @BeforeEach
    public void init() {
        this.list = new NeighbourTableImpl<>(BOARD_SIZE);
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
                () -> assertTrue(neighbours.left().isEmpty()),
                () -> assertTrue(neighbours.top().isEmpty()),
                () -> assertTrue(neighbours.topLeft().isEmpty()),
                () -> assertTrue(neighbours.bottom().isPresent()),
                () -> assertTrue(neighbours.bottomRight().isPresent()),
                () -> assertTrue(neighbours.bottomLeft().isEmpty()),
                () -> assertTrue(neighbours.right().isPresent()),
                () -> assertTrue(neighbours.topRight().isEmpty()),
                () -> assertEquals(new Pair<>(0, 1), neighbours.bottom().get()),
                () -> assertEquals(new Pair<>(1, 0), neighbours.right().get())
        );
    }

    @Test
    public void bottomRightCornerHasTopAndLeftNeighbours() {
        Neighbours neighbours = this.list.neighbours(new Pair<>(LIST_WIDTH - 1, LIST_HEIGHT - 1));
        assertAll(
                () -> assertTrue(neighbours.left().isPresent()),
                () -> assertTrue(neighbours.top().isPresent()),
                () -> assertTrue(neighbours.topLeft().isPresent()),
                () -> assertTrue(neighbours.bottom().isEmpty()),
                () -> assertTrue(neighbours.bottomRight().isEmpty()),
                () -> assertTrue(neighbours.bottomLeft().isEmpty()),
                () -> assertTrue(neighbours.right().isEmpty()),
                () -> assertTrue(neighbours.topRight().isEmpty()),
                () -> assertEquals(new Pair<>(LIST_WIDTH - 2, LIST_HEIGHT - 1), neighbours.left().get()),
                () -> assertEquals(new Pair<>(LIST_WIDTH - 1, LIST_HEIGHT - 2), neighbours.top().get())
        );
    }

    @Test
    public void middleCellHasAllNeighbours() {
        int x = LIST_WIDTH - 2;
        int y = LIST_HEIGHT - 2;
        Neighbours neighbours = this.list.neighbours(new Pair<>(x, y));
        assertAll(
                () -> assertTrue(neighbours.toList().stream().allMatch(Optional::isPresent)),
                () -> assertEquals(new Pair<>(x - 1, y), neighbours.left().get()),
                () -> assertEquals(new Pair<>(x - 1, y - 1), neighbours.topLeft().get()),
                () -> assertEquals(new Pair<>(x, y - 1), neighbours.top().get()),
                () -> assertEquals(new Pair<>(x + 1, y - 1), neighbours.topRight().get()),
                () -> assertEquals(new Pair<>(x, y + 1), neighbours.bottom().get()),
                () -> assertEquals(new Pair<>(x + 1, y + 1), neighbours.bottomRight().get()),
                () -> assertEquals(new Pair<>(x - 1, y + 1), neighbours.bottomLeft().get()),
                () -> assertEquals(new Pair<>(x + 1, y), neighbours.right().get())
        );
    }

    @Test
    public void cannotGetNeighboursOfOutOfBoundsPosition() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> this.list.neighbours(BOARD_SIZE)
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
        NeighbourTable<Integer> list = new NeighbourTableImpl<>(
                BOARD_SIZE,
                (position) -> Optional.of(value)
        );

        Optional<Integer> result = list.get(new Pair<>(0, 0));

        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(value, result.get())
        );
    }

    @Test
    public void listHasSize() {
        assertEquals(BOARD_SIZE, this.list.size());
    }

}
