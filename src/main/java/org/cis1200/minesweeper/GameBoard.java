package org.cis1200.minesweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoard extends JPanel {

    private Minesweeper m; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 270;
    public static final int BOARD_HEIGHT = 270;

    private Mine mine;

    private Flag flag;

    public GameBoard(JLabel statusInit) {
        m = new Minesweeper();
        status = statusInit;
        mine = new Mine();
        flag = new Flag();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point p = e.getPoint();
                if (SwingUtilities.isRightMouseButton(e)) {
                    m.rightClick(p.x / 30, p.y /30);
                } else {
                    if(!m.isGameStarted()) {
                        m.firstClick(p.x / 30, p.y / 30);
                    } else {
                        m.click(p.x / 30, p.y / 30);
                    }
                }



                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board
            }
        });
    }


    /**
     * resets game for user
     */
    public void reset() {
        m.reset();
        status.setText("Playing game...");
        repaint();

        requestFocusInWindow();
    }

    /**
     * updates JLabel with game state
     */
    private void updateStatus() {
        if(m.isGameOver() == 0) {
            status.setText("Playing game...");
        } else if (m.isGameOver() == 1) {
            status.setText("You lose.");
        } else if (m.isGameOver() == 2) {
            status.setText("You win!");
        }
    }

    /**
     * draws the game board
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        fillGrid(g);
    }

    /**
     * draws the grid
     * @param g graphics context
     */
    public void drawGrid(Graphics g) {
        //draw horizontal lines -
        for(int y = 0; y <= 270; y += 30) {
            g.drawLine(0, y, 270, y);
        }
        //draw vertical lines -
        for(int x = 0; x <= 270; x += 30) {
            g.drawLine(x, 0, x, 270);
        }
    }

    /**
     * draws the symbols corresponding to the shapes
     * @param g graphics context
     */
    public void fillGrid(Graphics g) {
        Square[][] b = m.getBoard();
        for(int r = 0; r < b.length; r++) {
            for(int c = 0; c < b[r].length; c++) {
                String state = b[r][c].toString();
                if(state.equals("F")) {
                    flag.draw(g, c*30 + 2, r*30+2);
                } else if(!(state.equals("m") || state.equals("_"))) {
                    g.setColor(Color.gray);
                    g.fillRect(c * 30 +1, r * 30 + 1, 28, 28);
                    if(state.equals("M")) {
                        mine.draw(g, c*30 + 2, r*30+2);

                    } else {
                        if(!state.equals("0")) {
                            g.setColor(Color.black);
                            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
                            g.drawString(state, c * 30 + 5, r * 30 + 25);
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }


}
