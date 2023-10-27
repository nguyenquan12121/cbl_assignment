import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;



public class GameContainer {
    PlayPanel pp;
    MenuPanel mainButtonPanel;
    JFrame frame;
    public GameContainer(PlayPanel pp, MenuPanel mp){
        frame = new JFrame("Game");
        this.pp = pp;
        this.mainButtonPanel = mp;
    }

    public void addComponentsToPane (JFrame frame)  {
        //Add menu panel to the east
        frame.add(mainButtonPanel, BorderLayout.EAST) ;

        //Allow buttons inside the menu panel to interact with the play panel
        frame.add(pp);
    }

    public void createAndShowGUI() {

        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 900));
        frame.setLocation(00, 00);
        this.addComponentsToPane(frame);
        frame.pack();
        frame.setVisible(true);
    }
    public void exitPanel(){
        this.frame.dispose();
    }
}


