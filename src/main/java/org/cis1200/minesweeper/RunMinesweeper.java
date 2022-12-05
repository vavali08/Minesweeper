package org.cis1200.minesweeper;


import javax.swing.*;
import java.awt.*;

public class RunMinesweeper implements Runnable {

    @Override
    public void run() {
        final JFrame frame = new JFrame("Minesweeper");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);

        // Game board
        final org.cis1200.minesweeper.GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        //button for instructions
        final JButton instructions = new JButton("Instructions");

        String instructionsText =
                "Welcome to Minesweeper! Your goal is to locate the ten mines on the gameboard." +
                "\n Click any square to begin. The number shown on a square indicates the number of mines surrounding" +
                        " that square." +
                "\n Once you have begun, left click to uncover a square, and right click to add/remove a flag. Flags" +
                        " allow you to easily keep track of where the mines are." +
                "\n There are two ways to end the game. If you uncover a mine, you will lose. Once you uncover all of" +
                        " the squares which are not mines, you win! Enjoy!";

        instructions.addActionListener(e -> JOptionPane.showMessageDialog(null, instructionsText,
                "Instructions", JOptionPane.INFORMATION_MESSAGE));
        control_panel.add(instructions);


        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        board.reset();

    }




}
