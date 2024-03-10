package e2;

import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NeighboursTest {
    @Test
    public void canBeStreamed() {
        Pair<Integer, Integer> p = new Pair<>(1,1);
        Neighbours neighbours = new Neighbours(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(p)
        );
        assertEquals(8, neighbours.toList().stream().count());
    }


}
