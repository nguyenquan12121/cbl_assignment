package ui;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.LeaderboardEntry;
import utils.ReadData;



public class LeaderboardContainer extends JPanel {
    BufferedImage backgroundImage;
    public LeaderboardContainer(){
    }
    public static void addComponentsToPane (JFrame frame)  {
        LeaderboardPanel lp = new LeaderboardPanel(frame);
        List<LeaderboardEntry> list = ReadData.readFromFile("leaderboard.txt");
        lp.addLeaderboard(list);
        lp.initTable();
        frame.add(lp);
        list.clear();
    }

    public static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Leaderboard");
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        //Set up content pane
        addComponentsToPane(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setVisible(true);
    }

}

