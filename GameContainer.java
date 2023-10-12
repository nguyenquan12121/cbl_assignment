import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class GameContainer {
    private static BufferedImage scaledImage;
    public static void addComponentsToPane(JFrame frame) {

        JPanel buttonPanel=new JPanel();//create panel to hold button
        //add button to the pane
        JButton b3 = new JButton("Bounce!");
        JButton stop = new JButton("Stop!");
        buttonPanel.add(b3);
        buttonPanel.add(stop);
        frame.add(buttonPanel, BorderLayout.EAST);//use contentPane default BorderLayout
        buttonPanel.setBackground(Color.red);
        //add line to the pane
        DrawingLine line1 = new DrawingLine();
        scaledImage = BallLabel.drawBall();
        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        line1.setBackground(Color.orange);
        line1.add(picLabel);
        frame.add(line1);

    }

    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        //Set up content pane
        addComponentsToPane(frame);

        //add menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.add(new JMenuItem("Do nothing"));
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}


