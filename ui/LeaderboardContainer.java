package ui;

import entity.LeaderboardEntry;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utils.ReadData;


/**frame for leaderboard.
 * 
 */
public class LeaderboardContainer extends JPanel {
    BufferedImage backgroundImage;

    /**constructor.
     * 
     */    
    public LeaderboardContainer() {
    }
    
    /**add panels to frame.
     * 
     */    
    public static void addComponentsToPane(JFrame frame) {
        LeaderboardPanel lp = new LeaderboardPanel(frame);
        List<LeaderboardEntry> list = ReadData.readFromFile("leaderboard.txt");
        lp.addLeaderboard(list);
        lp.initTable();
        frame.add(lp);
        list.clear();
    }

    /**update score.
     * 
     */
    public static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Leaderboard");
        frame.setLocationRelativeTo(null);
        //Set up content pane
        addComponentsToPane(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setVisible(true);
    }

}

