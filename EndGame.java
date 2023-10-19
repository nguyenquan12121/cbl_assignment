import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class EndGame {
    public static void addComponentsToPane (JFrame frame)  {
        JPanel endPanel=new JPanel();
        //create panel to hold 2 buttons
        endPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //add button to the pane
        JButton play = new JButton("Update DB");
        JButton exit = new JButton("Return to Start Screen!");
        JTextField userName = new JTextField("Please enter your name!");
        endPanel.add(play);
        endPanel.add(exit);
        endPanel.add(userName);
        endPanel.setBackground(Color.red);
        play.addActionListener(e ->{
            new Game();
        });
        exit.addActionListener(e -> System.exit(-1));
        frame.add(endPanel);

    }

    private static void createAndShowGUI() {

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


