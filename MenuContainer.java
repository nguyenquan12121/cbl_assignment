import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class MenuContainer {
    public MenuContainer(){

    }
    public static void addComponentsToPane (JFrame frame)  {
        JPanel buttonPanel=new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new GridBagLayout());
        //create panel to hold 2 buttons
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.PAGE_AXIS));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        //add button to the pane
        JLabel jl = new JLabel("<html><h1><strong><i>Hot Highstriker</i></strong></h1><hr></html>");
        JButton play = new JButton("Play!");
        JButton leaderboard = new JButton("Show leaderboard!");
        JButton exit = new JButton("Exit!");
        buttonPanel.add(jl, gbc);
        buttonPanel.add(play, gbc);
        buttonPanel.add(leaderboard, gbc);
        buttonPanel.add(exit, gbc);
        gbc.weighty = 1;
        buttonPanel.setBackground(Color.red);
        play.addActionListener(e ->{
            frame.dispose();
            new Game();
        });
        exit.addActionListener(e -> System.exit(-1));
        frame.add(buttonPanel, BorderLayout.CENTER);

    }

    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Welcome Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
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


