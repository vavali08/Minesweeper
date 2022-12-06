package org.cis1200.minesweeper;

public class Square {
    /**
     * Class storing information for each square on the Minesweeper board
     */

    /**
     * stores whether the square is covered
     */
    private boolean covered;

    /**
     * stores whether a certain square contains a mine
     */
    private boolean mine;

    /**
     * stores whether a certain square has been flagged by the user
     */
    private boolean flagged;

    /**
     * stores the number of mines in the eight squares surrounding this square
     */
    private int numMines;


    /**
     * Constructor for Square object with all values provided
     *
     * @param isCovered - whether the square is covered
     * @param isMine - whether the square contains a mine or not
     * @param isFlagged - whether the square is marked or not
     * @param numMines - how many mines are surrounding the square
     */
    public Square(boolean isCovered, boolean isMine, boolean isFlagged, int numMines) {
        this.covered = isCovered;
        this.mine = isMine;
        this.flagged = isFlagged;
        this.numMines = numMines;
    }


    /**
     * Constructor for Square Object setting all the values to defaults
     */
    public Square() {
        this.covered = true;
        this.mine = false;
        this.flagged = false;
        this.numMines = 0;
    }

    /**
     *
     * @return true if the square is covered, false if the square is uncovered
     */
    public boolean isCovered() {
        return covered;
    }

    /**
     *
     * @return true if the square is flagged, false if the square is unflagged
     */
    public boolean isFlagged() {
        return flagged;
    }

    /**
     *
     * @return number of mines in the squares surrounding this square
     */
    public int getNumMines() {
        return numMines;
    }

    /**
     * uncovers a square
     */
    public void uncover() {
        covered = false;
    }

    /**
     *
     * @param num the number of mines of this square
     */
    public void setNumMines(int num) {
        numMines = num;
    }

    /**
     * increases the number of mines by 1
     */
    public void incrMines() {
        numMines++;
    }

    /**
     * changes whether the square is flagged
     */
    public void changeFlagged() {
        this.flagged = !flagged;
    }

    /**
     *
     * @return whether the square contains a mine
     */
    public boolean isMine() {
        return mine;
    }

    /**
     * makes the current square into a mine
     */
    public void setMine() {
        this.mine = true;
    }

    // for testing purposes
    @Override
    public boolean equals(Object o) {
        if (!o.getClass().equals(this.getClass())) {
            return false;
        }
        Square s = (Square) o;
        return (this.covered == s.covered && this.mine == s.mine && this.flagged == s.flagged &&
                this.numMines == s.numMines);
    }

    // for testing purposes
    @Override
    public String toString() {
        if (covered) {
            if (flagged) {
                return "F";
            } else if (mine) {
                return "m";
            } else {
                return "_";
            }
        } else {
            if (mine) {
                return "M";
            } else {
                return "" + numMines;
            }
        }
    }
}
