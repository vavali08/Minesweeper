package org.cis1200.minesweeper;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

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

        // Button Panel
        final JPanel control_panel = new JPanel();
        control_panel.setLayout(new GridLayout(2, 2));
        frame.add(control_panel, BorderLayout.NORTH);

        //Reset button
        final JButton reset = new JButton("New Game");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        //Instruction Button
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

        //Save Button
        final JButton save = new JButton("Save Game");
        //String filePath;
        save.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    String filePath = JOptionPane.showInputDialog("Enter the File Path for where you would like to save"
                            + " your game");
                    try {
                        board.toFile(filePath);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
        });


        control_panel.add(save);

        //Load Button
        final JButton load = new JButton("Load Game");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = JOptionPane.showInputDialog("Enter the File Path from which you would like to load"
                        + " your game");
                try {
                    board.fromFile(filePath);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "This file was not found :(",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex2) {
                    JOptionPane.showMessageDialog(null, "Your input was not valid",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex3) {
                    JOptionPane.showMessageDialog(null, "Your input was not valid",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        control_panel.add(load);




        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        board.reset();

    }




}
