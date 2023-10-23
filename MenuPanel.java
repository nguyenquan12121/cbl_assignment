import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class MenuPanel extends JPanel implements ActionListener {
    Timer timer; 
    JButton bounce,stop, reset, next;
    JLabel force;
    long display;
    PlayPanel ppMain;
    long startTime = -1l;
    long endTime = -1l;
    public MenuPanel(PlayPanel pp){
        this.ppMain = pp;
        timer = new Timer(10, this);
        timer.start();
        JPanel buttonPanel=new JPanel();
        //create panel to hold 2 buttons
        buttonPanel.setLayout(new GridLayout(2, 2,5,5));
        //add button to the pane
        bounce = new JButton("Bounce!");
        stop = new JButton("Stop!");
        reset = new JButton("Reset!");
        next = new JButton("Next!");
        buttonPanel.add(bounce);
        buttonPanel.add(stop);
        buttonPanel.add(reset);
        buttonPanel.add(next);
        buttonPanel.setBackground(Color.red);
        buttonPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


        //Main left panel to hold buttons and text
        //Set text field
        force = new JLabel("0.00");
        force.setBackground(Color.green);
        //Center the text
        force.setHorizontalAlignment(JLabel.CENTER);        
        this.setLayout(new BorderLayout());        
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(force, BorderLayout.CENTER);

    
        this.bounce.addMouseListener(
            // I wanted to use MouseAction but it doesnt work
            new MouseListener() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent arg0) {
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent arg0) {
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent arg0) {
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent arg0) {
                    startTime = System.currentTimeMillis();
                    timer.start();
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent arg0) {
                    endTime = System.currentTimeMillis();
                    //ppMain.setTimer(true, duration);
                    //spring timer is used in actionlistener to pull down the lever so set it false here
                    ppMain.setSpringTimer(false, display);
                    ppMain.launchBall();
                }
            }

        );
        this.stop.addActionListener(e -> ppMain.setTimer(false, 0l));
        this.reset.addActionListener( e ->{
            startTime = -1l;
            endTime = -1l;           
            force.setText("0.00");
            timer.stop();
            ppMain.reset();
        });    
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (startTime != -1l){
            display = System.currentTimeMillis() - startTime;
            ppMain.setSpringTimer(true, display);
            force.setText(Long.toString(display));
        repaint();
        }
        if (endTime !=-1l){
            timer.stop();
        }
    }
}