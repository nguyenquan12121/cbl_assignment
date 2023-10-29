package ui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

class ButtonPanel extends JPanel {
    JButton bounce;

    public ButtonPanel() {
        loadGraphics();
    }

    /** Setup the panel's objectss.
    * 
    */
    public void loadGraphics() {
        //add button to the pane
        this.setLayout(new GridLayout(1, 1));
        bounce = new JButton();
        bounce.setIcon(new ImageIcon("images/Play_Button3.png")); 
        bounce.setBorder(null);
        bounce.setOpaque(false);
        bounce.setContentAreaFilled(false);
        bounce.setBorderPainted(false);        
        this.add(bounce);
        this.setBackground(Color.BLACK);
    }
}