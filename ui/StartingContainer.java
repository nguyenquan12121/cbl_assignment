package ui;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**Frame for staring screen.
* 
*/
public class StartingContainer extends JPanel {
    BufferedImage backgroundImage;

    /**Handles scoring.
    * 
    */    
    public StartingContainer() {
    }

    /**Add panel to frame.
    * 
    */    
    public static void addComponentsToPane(JFrame frame) {
        StartingPanel sp = new StartingPanel(frame);
        frame.add(sp);
    }

    /**Displays frame.
    * 
    */
    public static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Welcome Screen");
        frame.setSize(800, 600);
        frame.setLocation(100, 100);
        //Set up content pane
        addComponentsToPane(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}