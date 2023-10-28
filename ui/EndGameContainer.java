package ui;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;

import entity.LeaderboardEntry;
import utils.ReadData;
public class EndGameContainer{
        static EndGameLeaderboardPanel endGameLeaderboardPanel;
        static EndGamePanel endPanel;
        static String filePath = "leaderboard.txt";

        public static void initPanel(LeaderboardEntry entry){
            List<LeaderboardEntry> list = ReadData.readFromFile(filePath);
            endGameLeaderboardPanel = new EndGameLeaderboardPanel();
            endGameLeaderboardPanel.addLeaderboard(list);
            endGameLeaderboardPanel.initTable();
            endPanel=new EndGamePanel(entry, endGameLeaderboardPanel);
            
        }
        
        public static void addComponentsToPane (JFrame frame)  {
            endPanel.addFrame(frame);
            frame.setLayout(new GridLayout(1,2));
            EndPanel ep = new EndPanel(endPanel, endGameLeaderboardPanel);
            frame.add(ep);
        }

        
        public static void createAndShowGUI() {
    
            //Create and set up the window.
            JFrame frame = new JFrame("End Game Screen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);
            frame.setLocation(100, 100);
            //Set up content pane
            addComponentsToPane(frame);
    
            frame.pack();
            frame.setVisible(true);
        }

    
    
}


