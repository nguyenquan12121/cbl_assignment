import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;

class MenuPanel extends JPanel implements ActionListener {
    ButtonPanel bp;
    InformationPanel ip;
    Timer timer; 
    int score=0;
    long display;
    PlayPanel ppMain;
    long startTime = -1l;
    long endTime = -1l;
    public MenuPanel(PlayPanel pp){
        this.ppMain = pp;
        timer = new Timer(10, this);
        timer.start();
        ip = new InformationPanel();
        bp = new ButtonPanel();
        this.ppMain.addInfoPanel(ip);
        this.setLayout(new BorderLayout());        
        this.add(bp, BorderLayout.NORTH);
        this.add(ip);        
    
        bp.bounce.addMouseListener(
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
        bp.stop.addActionListener(e -> ppMain.setTimer(false, 0l));
        bp.reset.addActionListener( e ->{
            startTime = -1l;
            endTime = -1l;           
            ip.force.setText("Force: 0");
            ip.scoreLabel.setText("Score: 0");
            timer.stop();
            ppMain.reset();
        });
        bp.stop.addActionListener(e -> {
            ip.updateRound();
        });    
    }


    
    @Override
    public void actionPerformed(ActionEvent e){
        if (startTime != -1l){
            display = System.currentTimeMillis() - startTime;
            ppMain.setSpringTimer(true, display);
            ip.force.setText("Force: "+ Long.toString(display));
        repaint();
        }
        if (endTime !=-1l){
            timer.stop();
        }
    }
}
