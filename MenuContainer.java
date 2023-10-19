import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class MenuContainer {
    public MenuContainer(){

    }
    public static void addComponentsToPane (JFrame frame)  {
        JPanel buttonPanel=new JPanel();
        //create panel to hold 2 buttons
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //add button to the pane
        JButton play = new JButton("Play!");
        JButton exit = new JButton("Exit!");
        buttonPanel.add(play);
        buttonPanel.add(exit);
        buttonPanel.setBackground(Color.red);
        play.addActionListener(e ->{
            frame.dispose();
            new Game();
        });
        exit.addActionListener(e -> System.exit(-1));
        frame.add(buttonPanel);

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


