import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class EndGame extends JPanel {
    static int highScore;

    public static void setHighScore(int score){
        highScore = score;
    }
    public static void addComponentsToPane (JFrame frame)  {
        JPanel endPanel=new JPanel();
        //create panel to hold 2 buttons
        JLabel scoreLabel = new JLabel();
        endPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //add button to the pane
        JButton play = new JButton("Update DB");
        JButton exit = new JButton("Return to Start Screen!");
        JTextField userName = new JTextField("Please enter your name!");
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);        
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        scoreLabel.setText("CONGRATULATIONS! YOUR HIGH SCORE IS "+ highScore);
        endPanel.add(scoreLabel);
        endPanel.add(play);
        endPanel.add(exit);
        endPanel.add(userName);
        endPanel.setLayout(new GridLayout(5, 1, 0,0));  
        endPanel.setBackground(Color.red);
        play.addActionListener(e ->{
        });
        exit.addActionListener(e -> System.exit(-1));
        frame.add(endPanel);

    }

    public static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Welcome Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        //Set up content pane
        addComponentsToPane(frame);


        frame.pack();
        frame.setVisible(true);
    }

}


