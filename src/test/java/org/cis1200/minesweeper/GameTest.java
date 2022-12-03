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
    public void testResetGameAlreadyExists() {
        // add code for game already being played

    }

    @Test
    public void testResetAfterGameOver() {
        // add code for game already being played

    }

}
