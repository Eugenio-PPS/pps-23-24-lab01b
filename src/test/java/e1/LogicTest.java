package e1;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
public class LogicTest {
    private static Optional<Pair<Integer, Integer>> getKnightPosition(Logics board, int size) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board.hasKnight(i, j)) {
                    return Optional.of(new Pair<>(i, j));
                }
            }
        }

        return Optional.empty();
    }
    private static Optional<Pair<Integer, Integer>> getPawnPosition(Logics board, int size) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board.hasPawn(i, j)) {
                    return Optional.of(new Pair<>(i, j));
                }
            }
        }

        return Optional.empty();
    }

    @Test
    public void cannotCreateChessboardWithNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(-1));
    }

    @Test
    public void cannotCreateChessboardWithZeroSize() {
        assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(0));
    }
    @Test
    public void oneByOneBoardThrowsStackOverflowError() {
        assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(1));
    }

    @Test
    public void chessboardHasBothKnightAndPawn() {
        int boardSize = 2;
        Logics board = new LogicsImpl(boardSize);

        boolean pawnFound = this.getPawnPosition(board, boardSize).isPresent();
        boolean knightFound = this.getKnightPosition(board, boardSize).isPresent();

        assertTrue(pawnFound);
        assertTrue(knightFound);
    }

    @Test
    public void chessboardHasBothKnightAndPawnInDifferentPositions() {
        int boardSize = 2;
        Logics board = new LogicsImpl(boardSize);

        Pair<Integer, Integer> pawn = getPawnPosition(board, boardSize).get();
        Pair<Integer, Integer> knight = getKnightPosition(board, boardSize).get();

        assertNotEquals(pawn, knight);
    }

    @Test
    public void cannotMoveKnightOutOfBounds() {
        int boardSize = 2;
        Logics board = new LogicsImpl(boardSize);

        assertThrows(IndexOutOfBoundsException.class, () -> board.hit(2, 1));
    }

    @Test
    public void cannotMoveKnightOutOfItsRange() {
        int boardSize = 10;
        Logics board = new LogicsImpl(boardSize);

        Pair<Integer, Integer> knightPosition = getKnightPosition(board, boardSize).get();

        assertFalse(board.hit(
                Math.min(knightPosition.getX() + 2, boardSize - 1),
                Math.min(knightPosition.getY() + 2, boardSize - 1)
        ));
    }

    @Test
    public void canMoveKnightInItsRange() {
        int boardSize = 10;
        Logics board = new LogicsImpl(boardSize);

        Pair<Integer, Integer> knightPosition = getKnightPosition(board, boardSize).get();
        boolean canMoveUp = knightPosition.getX() < boardSize - 2;
        boolean canMoveRight = knightPosition.getY() < boardSize - 1;

        Pair<Integer, Integer> newKnightPosition = new Pair<>(
                canMoveUp ? knightPosition.getX() + 2 : knightPosition.getX() - 2,
                canMoveRight ? knightPosition.getY() + 1 : knightPosition.getX() - 1
        );

        board.hit(newKnightPosition.getX(), newKnightPosition.getY());

        assertEquals(newKnightPosition, getKnightPosition(board, boardSize).get());
    }

    @Test
    public void knightCanHit() {
        int boardSize = 4;
        Logics board = new LogicsImpl(boardSize, new Pair<>(0, 0), new Pair<>(2, 1));

        assertTrue(board.hit(0, 0));
    }
}
