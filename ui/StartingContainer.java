package ui;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class StartingContainer extends JPanel {
    BufferedImage backgroundImage;
    public StartingContainer(){
    }
    public static void addComponentsToPane (JFrame frame)  {
        StartingPanel sp = new StartingPanel(frame);
        frame.add(sp);
    }



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