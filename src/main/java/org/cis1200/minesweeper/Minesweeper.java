package org.cis1200.minesweeper;

public class Minesweeper {

    /**
     * the game board
     */
    private Square[][] board = new Square[9][9];

    /**
     * stores whether the game has ended. 0 if the game is still going, 1 if the user lost, 2 if the user won
     */
    private int gameOver;


    /**
     * keeps track of whether the game has started(whether the first square has been clicked)
     */
    private boolean gameStarted;

    /**
     * the x coordinate of the first square clicked, or -1 if the game has not yet started
     */
    private int firstX;

    /**
     * the y coordinate of the first square clicked, or -1 if the game has not yet started
     */
    private int firstY;

    /**
     * constructor for minesweeper
     */
    public Minesweeper() {
        reset();
    }

    /**
     * resets game board by setting all square variables to defaults
     */
    public void reset() {
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                board[r][c] = new Square();
            }
        }

        gameOver = 0;
        gameStarted = false;
        firstY = -2;
        firstX = -2;
    }



    /**
     * After the first coordinate is clicked, places the mines on the gameboard and calculates the number of
     * mines surrounding each square. The first square clicked MUST have numMines = 0 after the mines are placed.
     * The game has now started
     * @param x x coordinate of the first square clicked
     * @param y y coordinate of the first square clicked
     */
    public void firstClick(int x, int y) {
        if(!gameStarted) {
            firstX = x;
            firstY = y;
            placeMines();
            board[y][x].uncover();
            uncoverSurrounding(x, y);
            gameStarted = true;
        }
    }

    /**
     * Executes whenever a square is clicked. Uncovers the square and executes the corresponding code.
     * @param x x coordinate of square clicked
     * @param y y coordinate of square clicked
     */
    public void click(int x, int y) {
        Square s = board[y][x];
        if (s.isFlagged()) {
            s.changeFlagged();
        } else {
            s.uncover();
            if (s.isMine()) {
                gameOver = 1;
            } else if(s.getNumMines() == 0) {
                uncoverSurrounding(x, y);
            } else if (didUserWin()) {
                gameOver = 2;
            }
        }

    }

    /**
     * Executes whenever a square is right-clicked. Changes whether the square is flagged
     * @param x x coordinate of square clicked
     * @param y y coordinate of square clicked
     */
    public void rightClick(int x, int y) {
        Square s = board[y][x];
        if(s.isCovered()) {
            s.changeFlagged();
        }

    }

    /**
     * Checks if a square is valid/is within the bounds of the board
     * @param x x coordinate of square
     * @param y y coordinate of square
     * @return true if coordinates of square are valid, false if they are not
     */
    public boolean isValidSquare(int x, int y) {
        return(x >= 0 && x < 9 && y >= 0 && y < 9);
    }

    /**
     * places the mines on the board and updates the numMines of each square. A mine cannot be placed if placing this
     * mine will make numMines of a surrounding square greater than 7.
     */
    public void placeMines() {
        int numMines = 0;
        while(numMines < 15) {
            int x = (int)(Math.random() * 9);
            int y = (int)(Math.random() * 9);
            if(minePlaceable(x, y)) {
                placeMine(x, y);
                numMines++;
            }

        }
    }

    /**
     * places a single mine at a point and increments the numMines of the surrounding squares
     * @param x
     * @param y
     */
    public void placeMine(int x, int y) {
        board[y][x].setMine();

        for(int r = y - 1; r <= y + 1; r++) {
            for(int c = x - 1; c <= x + 1; c++) {
                if(!(r == y && c == x) && isValidSquare(c, r)) {
                    board[r][c].incrMines();
                }
            }
        }

    }

    /**
     * checks whether a mine can be placed in a specific square. For this to be true, the numMines of all surrounding
     * squares must be less than or equal to six, and the square cannot already have a mine. The square also cannot
     * be adjacent to the first square clicked.
     * @param x x coordinate of square to check
     * @param y y coordinate of square to check
     * @return true if a mine is placeable on this square, false if it is not
     */
    public boolean minePlaceable(int x, int y) {

        Square s = board[y][x];
        if(s.isMine()) {
            return false;
        }

        //checking surrounding squares
        for(int r = y - 1; r <= y + 1; r++) {
            for(int c = x - 1; c <= x + 1; c++) {
                if(isValidSquare(c, r)) {
                    if (r == firstY && c == firstX) {
                        return false;
                    }
                    if (board[r][c].getNumMines() >= 6) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * If a square is uncovered that has numMines = 0, the surrounding squares are also uncovered. This function is
     * recursive, as if any of the surrounding squares also have numMines = 0, then this function will be called on them
     * as well.
     * @param x
     * @param y
     */
    public void uncoverSurrounding(int x, int y) {
        for(int r = y - 1; r <= y + 1; r++) {
            for (int c = x - 1; c <= x + 1; c++) {
                if (isValidSquare(c, r)) {
                    Square s = board[r][c];
                    if (s.isCovered()) {
                        s.uncover();
                        if (s.getNumMines() == 0) {
                            uncoverSurrounding(c, r);
                        }
                    }
                }
            }
        }

    }

    public void flagSquare(int x, int y) {
        board[y][x].changeFlagged();
    }

    /**
     * whether the user has won the game yet.
     * @return  true if all of the non-mine squares have been uncovered, false otherwise
     */
    public boolean didUserWin() {
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                Square s = board[r][c];
                if(!s.isMine() && s.isCovered()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @return whether the game has ended
     */
    public int isGameOver() {
        return gameOver;
    }

    /**
     *
     * @return whether the game has begun
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     *
     * @return the 2d array containing the Squares
     */
    public Square[][] getBoard() {
        return board;
    }


    public void printBoard() {
        for (Square[] row : board) {
            for(Square s : row) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
