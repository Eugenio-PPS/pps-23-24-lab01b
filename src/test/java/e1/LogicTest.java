package e1;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
public class LogicTest {

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

        assertNotNull(board.getKnightPosition());
        assertNotNull(board.getPawnPosition());
    }

    @Test
    public void chessboardHasBothKnightAndPawnInDifferentPositions() {
        int boardSize = 2;
        Logics board = new LogicsImpl(boardSize);

        Pair<Integer, Integer> pawn = board.getPawnPosition();
        Pair<Integer, Integer> knight = board.getKnightPosition();

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

        Pair<Integer, Integer> knightPosition = board.getKnightPosition();

        assertFalse(board.hit(
                Math.min(knightPosition.getX() + 2, boardSize - 1),
                Math.min(knightPosition.getY() + 2, boardSize - 1)
        ));
    }

    @Test
    public void canMoveKnightInItsRange() {
        int boardSize = 10;
        Logics board = new LogicsImpl(boardSize);

        Pair<Integer, Integer> knightPosition = board.getKnightPosition();
        boolean canMoveUp = knightPosition.getX() < boardSize - 2;
        boolean canMoveRight = knightPosition.getY() < boardSize - 1;

        Pair<Integer, Integer> newKnightPosition = new Pair<>(
                canMoveUp ? knightPosition.getX() + 2 : knightPosition.getX() - 2,
                canMoveRight ? knightPosition.getY() + 1 : knightPosition.getX() - 1
        );

        board.hit(newKnightPosition.getX(), newKnightPosition.getY());

        assertEquals(newKnightPosition, board.getKnightPosition());
    }

    @Test
    public void knightCanHit() {
        int boardSize = 4;
        Logics board = new LogicsImpl(boardSize, new Pair<>(0, 0), new Pair<>(2, 1));

        assertTrue(board.hit(0, 0));
    }

    @Test
    public void canGetKnightPosition() {
        Pair<Integer, Integer> knightPosition = new Pair<>(1, 1);
        Pair<Integer, Integer> pawnPosition = new Pair<>(2, 1);
        Logics board = new LogicsImpl(5, pawnPosition, knightPosition);
        assertEquals(knightPosition, board.getKnightPosition());
    }

    @Test
    public void canGetPawnPosition() {
        Pair<Integer, Integer> knightPosition = new Pair<>(1, 1);
        Pair<Integer, Integer> pawnPosition = new Pair<>(2, 1);
        Logics board = new LogicsImpl(5, pawnPosition, knightPosition);
        assertEquals(pawnPosition, board.getPawnPosition());
    }
}
