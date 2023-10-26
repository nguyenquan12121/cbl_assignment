import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
class EndGameContainer{
        
        public static void addComponentsToPane (JFrame frame, int score,LeaderboardEntry entry)  {
            EndGamePanel endPanel=new EndGamePanel(frame, score, entry);
            EndGameLeaderboardPanel endGameLeaderboardPanel = new EndGameLeaderboardPanel();
            frame.setLayout(new GridLayout(1,2));
            frame.add(endPanel);
            frame.add(endGameLeaderboardPanel);
        }
        
        public static void createAndShowGUI(int score, LeaderboardEntry entry) {
    
            //Create and set up the window.
            JFrame frame = new JFrame("Welcome Screen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1280, 720));
            //Set up content pane
            addComponentsToPane(frame, score, entry);
    
            frame.pack();
            frame.setVisible(true);
        }

    
    
}