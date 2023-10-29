package ui;

import entity.LeaderboardEntry;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JFrame;
import utils.ReadData;

/** Frame containing 2 panels, the one with buttons and the one displaying the leaderboard.
 * 
 */
public class EndGameContainer {
    static EndGameLeaderboardPanel endGameLeaderboardPanel;
    static EndGamePanel endPanel;
    static String filePath = "leaderboard.txt";

    /** Init 2 panels with the leaderboard entry.
     * 
     */
    public static void initPanel(LeaderboardEntry entry) {
        List<LeaderboardEntry> list = ReadData.readFromFile(filePath);
        endGameLeaderboardPanel = new EndGameLeaderboardPanel();
        endGameLeaderboardPanel.addLeaderboard(list);
        endGameLeaderboardPanel.initTable();
        endPanel = new EndGamePanel(entry, endGameLeaderboardPanel);
        
    }

    /** Set up frame.
    * 
    */    
    public static void addComponentsToPane(JFrame frame) {
        endPanel.addFrame(frame);
        frame.setLayout(new GridLayout(1, 2));
        EndPanel ep = new EndPanel(endPanel, endGameLeaderboardPanel);
        frame.add(ep);
    }
    
    /** Display the frame.
    * 
    */
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


