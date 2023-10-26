import java.awt.Dimension;

import javax.swing.JFrame;
class EndGameContainer{    
        public static void addComponentsToPane (JFrame frame, int score)  {
            EndGamePanel endPanel=new EndGamePanel(frame, score);
            frame.add(endPanel);
        }
    
        public static void createAndShowGUI(int score) {
    
            //Create and set up the window.
            JFrame frame = new JFrame("Welcome Screen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1280, 720));
            //Set up content pane
            addComponentsToPane(frame, score);
    
            frame.pack();
            frame.setVisible(true);
        }

    
    
    
}