package org.cis1200.minesweeper;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {


    //Creating gameboard to be used for testing clicks - except all are covered
    public Minesweeper testingBoard() {
        /**
         * Image of sample testing board after firstclick @ (1, 1)
         * 0 0 0 0 0 2 m _ _
         * 0 0 0 0 0 2 m _ _
         * 2 2 1 0 0 1 1 3 m
         * m m 3 1 0 0 0 2 m
         * _ m m 3 2 2 1 1 _
         * m _ m _ m m _ _ _
         * _ _ _ _ _ _ _ _ _
         * _ _ _ _ _ _ _ m _
         * _ m _ _ _ m _ _ _
         */


        Minesweeper m = new Minesweeper();
        Square[][] b = m.getBoard();

        m.placeMine(6, 0);
        m.placeMine(6, 1);
        m.placeMine(8, 2);
        m.placeMine(8, 3);
        m.placeMine(0, 3);
        m.placeMine(1, 3);
        m.placeMine(1, 4);
        m.placeMine(2, 4);
        m.placeMine(0, 5);
        m.placeMine(2, 5);
        m.placeMine(4, 5);
        m.placeMine(5, 5);
        m.placeMine(7, 7);
        m.placeMine(1, 8);
        m.placeMine(5, 8);

        return m;
    }


    @Test
    public void testResetFirstTime() {
        Minesweeper m = new Minesweeper();

        assertEquals(m.isGameOver(), 0);
        assertEquals(m.isGameStarted(), false);

        Square[][] b = m.getBoard();
        Square def = new Square();

        for (Square[] row : b) {
            for(Square s : row) {
                assertEquals(def, s);
            }
        }

    }

    @Test
    public void testFirstClickCorner() {
        Minesweeper m = new Minesweeper();
        Square[][] b = m.getBoard();
        m.firstClick(0, 0);

        m.printBoard();

        assertEquals(0, m.getBoard()[0][0].getNumMines());

        assertFalse(b[0][1].isMine());
        assertFalse(b[1][1].isMine());
        assertFalse(b[1][0].isMine());

        assertFalse(b[0][1].isCovered());
        assertFalse(b[1][1].isCovered());
        assertFalse(b[1][0].isCovered());

        int numMines = 0;
        for(int i = 0; i < b.length; i++) {
            for(int j = 0; j < b[i].length; j++) {
                if (b[i][j].isMine()) {
                    numMines++;
                }
            }
        }
        assertEquals(15, numMines);

    }

    @Test
    public void testFirstClickNotCorner() {
        Minesweeper m = new Minesweeper();
        Square[][] b = m.getBoard();
        m.firstClick(1, 1);
        m.printBoard();

        assertEquals(0, m.getBoard()[1][1].getNumMines());
        for(int i = 0; i <= 2; i++) {
            for(int j = 0; j <= 2; j++) {
                assertFalse(b[i][j].isMine());
                assertFalse(b[i][j].isCovered());
            }
        }

        int numMines = 0;
        for(int i = 0; i < b.length; i++) {
            for(int j = 0; j < b[i].length; j++) {
                if (b[i][j].isMine()) {
                    numMines++;
                }
            }
        }
        assertEquals(15, numMines);

    }

    @Test
    public void testMinePlaceableTrue() {
        Minesweeper m = new Minesweeper();

        assertTrue(m.minePlaceable(1, 1));

    }

    @Test
    public void testMinePlaceableOnFirstSquare() {
        Minesweeper m = new Minesweeper();
        Square[][] b = m.getBoard();
        m.firstClick(1, 1);
        assertFalse(m.minePlaceable(1, 1));

    }

    @Test
    public void testMinePlaceableAdjacentFirstSquare() {
        Minesweeper m = new Minesweeper();
        Square[][] b = m.getBoard();
        m.firstClick(1, 1);
        assertFalse(m.minePlaceable(1, 2));
    }

    @Test
    public void testMinePlaceableAlreadyMine() {
        Minesweeper m = new Minesweeper();
        Square[][] b = m.getBoard();
        b[2][1].setMine();
        assertFalse(m.minePlaceable(1, 2));

    }

    @Test
    public void testMinePlaceableTooManyMines() {
        Minesweeper m = new Minesweeper();
        Square[][] b = m.getBoard();
        b[2][1].setNumMines(6);
        assertFalse(m.minePlaceable(1,1));
    }

    @Test
    public void testClickAlreadyUncovered() {
        Minesweeper m1 = testingBoard();
        Minesweeper m2 = testingBoard();
        Square[][] b = m1.getBoard();
        m1.getBoard()[0][0].uncover();
        m2.getBoard()[0][0].uncover();

        m1.click(0,0);
        assertEquals(m2.getBoard()[0][0], m1.getBoard()[0][0]);

    }

    @Test
    public void testClickMine() {
        Minesweeper m1 = testingBoard();
        m1.click(6,0);
        assertFalse(m1.getBoard()[0][6].isCovered());
        assertEquals(1, m1.isGameOver());
    }

    @Test
    public void testClickNonMine() {
        Minesweeper m1 = testingBoard();
        m1.click(0, 0);
        assertEquals(new Square(false, false, false, 0), m1.getBoard()[0][0]);

    }

    @Test
    public void testClickFlagged() {
        Minesweeper m1 = testingBoard();
        Minesweeper m2 = testingBoard();
        m1.rightClick(0, 0);
        m1.click(0, 0);
        assertEquals(m2.getBoard()[0][0], m1.getBoard()[0][0]);
    }

    @Test
    public void rightClickUncoveredDoesNothing() {
        Minesweeper m1 = testingBoard();
        Minesweeper m2 = testingBoard();
        m1.click(0, 0);
        m2.click(0, 0);
        m1.rightClick(0, 0);

        assertEquals(m2.getBoard()[0][0], m1.getBoard()[0][0]);

    }

    @Test
    public void rightClickFlagNonMine() {
        Minesweeper m1 = testingBoard();
        m1.rightClick(0, 0 );
        assertTrue(m1.getBoard()[0][0].isFlagged());

    }

    @Test
    public void rightClickUnflagNonMine() {
        Minesweeper m1 = testingBoard();
        m1.getBoard()[0][0] = new Square(true, false, true, 0);
        m1.rightClick(0, 0);
        assertFalse(m1.getBoard()[0][0].isFlagged());

    }

    @Test
    public void rightClickFlagMine() {
        Minesweeper m1 = testingBoard();
        m1.getBoard()[0][6] = new Square(true, true, false, 1);
        m1.rightClick(6, 0);
        assertTrue(m1.getBoard()[0][6].isFlagged());

    }

    @Test
    public void rightClickUnflagMine() {
        Minesweeper m1 = testingBoard();
        m1.getBoard()[0][6] = new Square(true, true, true, 1);
        m1.rightClick(6, 0);
        assertFalse(m1.getBoard()[0][6].isFlagged());

    }

    @Test
    public void testUserWinFalse() {
        Minesweeper m1 = testingBoard();
        assertFalse(m1.didUserWin());
    }

    @Test
    public void testUserWon() {
        Minesweeper m1 = testingBoard();
        Square[][] b = m1.getBoard();
        for(int i = 0; i < b.length; i++) {
            for(int j = 0; j < b[i].length; j++)
                if (!b[i][j].isMine()) {
                    m1.click(j, i);
                }
        }
        assertTrue(m1.didUserWin());
        assertEquals(2, m1.isGameOver());

    }



    @Test
    public void testResetGameAlreadyExists() {
        Minesweeper m = testingBoard();
        m.reset();

        assertEquals(m.isGameOver(), 0);
        assertEquals(m.isGameStarted(), false);

        Square[][] b = m.getBoard();
        Square def = new Square();

        for (Square[] row : b) {
            for(Square s : row) {
                assertEquals(def, s);
            }
        }

    }

    @Test
    public void testResetAfterGameOver() {
        Minesweeper m = testingBoard();
        Square[][] b = m.getBoard();
        for(int i = 0; i < b.length; i++) {
            for(int j = 0; j < b[i].length; j++)
                if (!b[i][j].isMine()) {
                    m.click(j, i);
                }
        }
        assertTrue(m.didUserWin());
        assertEquals(2, m.isGameOver());

        m.reset();

        assertEquals(m.isGameOver(), 0);
        assertEquals(m.isGameStarted(), false);

        Square def = new Square();

        for (Square[] row : b) {
            for(Square s : row) {
                assertEquals(def, s);
            }
        }

    }

}
