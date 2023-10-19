import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;



public class GameContainer {

    public static void addComponentsToPane (JFrame frame)  {


        MenuPanel mainButtonPanel = new MenuPanel();
        //Add menu panel to the east
        frame.add(mainButtonPanel, BorderLayout.EAST) ;
        PlayPanel pp = new PlayPanel();
        //Allow buttons inside the menu panel to interact with the play panel
        mainButtonPanel.startAnimation(pp);
        mainButtonPanel.reset(pp);
        frame.add(pp);

    }

    public static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        //Set up content pane
        addComponentsToPane(frame);


        frame.pack();
        frame.setVisible(true);
    }
}


