import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;



public class GameContainer {
    PlayPanel pp;
    MenuPanel mainButtonPanel;
    public GameContainer(){
        pp = new PlayPanel();
        mainButtonPanel = new MenuPanel(pp);
    }

    public void addComponentsToPane (JFrame frame)  {
        //Add menu panel to the east
        frame.add(mainButtonPanel, BorderLayout.EAST) ;

        //Allow buttons inside the menu panel to interact with the play panel
        frame.add(pp);
    }

    public void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        //Set up content pane
        this.addComponentsToPane(frame);
        frame.pack();
        frame.setVisible(true);
    }
}


