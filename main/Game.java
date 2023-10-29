package main;

import entity.LeaderboardEntry;
import java.util.List;
import ui.EndGameContainer;
import ui.GameContainer;
import ui.MenuPanel;
import ui.PlayPanel;


/** Main Game Class, handles game state, rendering and updating.
 * 
 */
public class Game implements Runnable {
    PlayPanel pp;
    MenuPanel mainButtonPanel;
    boolean run = true;
    GameContainer gameContainer;
    Thread animator;
    LeaderboardEntry entry;
    List<LeaderboardEntry> list;
    final int ticks = 128;
    final int fps = 144;
    String filePath = "leaderboard.txt";
    private Integer totalScore;
    Long startTime;
    boolean startedTransition;
    
    /** Constructor for the game class.
     * 
     */    
    public Game() {
        pp = new PlayPanel();
        mainButtonPanel = new MenuPanel(pp);
        gameContainer = new GameContainer(pp, mainButtonPanel);
        gameContainer.createAndShowGUI();
        GameState.state = GameState.IDLE;
        startAnimation();
    }

    /** Start the main thread to handle updating and rendering.
    * 
    */
    public void startAnimation() {
        animator = new Thread(this);
        animator.start();
    }

    /** Handles painting of all the game's objects.
    * 
    */
    public void render() {
        mainButtonPanel.repaint();
        pp.repaint();
    }

    /** Used to close the current frame, read data from the text file and set the frame
     * for the end game screen. 
    */
    public void setUpEndScreen() {
        totalScore = mainButtonPanel.getFinalScore();
        gameContainer.exitPanel();
        animator.interrupt();
        entry = new LeaderboardEntry(totalScore);
        EndGameContainer.initPanel(entry);
        EndGameContainer.createAndShowGUI();
        run = false;
    }

    /** Handles updating coordinated of all the game's objects.
    * 
    */
    public void update(Long currTime) {
        switch (GameState.state) {
            //Start of the game
            case IDLE:
                startedTransition = false;
                mainButtonPanel.resetPanel();
                pp.reset();
                break;
            //User holding down button
            case PREPARE:
                mainButtonPanel.updateClock();
                pp.compressSpring();
                break;
            //User lets go of the button
            case LAUNCHED:
                pp.updateBallLaunch();
                break;
            //2 seconds delay after each round
            case TRANSITION:
                if (!startedTransition) {
                    startTime = System.nanoTime();
                    startedTransition = true;
                }
                mainButtonPanel.updateTransitionTime(startTime, currTime);
                break;
            case END:
                //launch EndScreen
                setUpEndScreen();
                break;
            default:
                break;
        }
    }


    @Override
    public void run() {
        long beforeTime;
        long currTime;
        double timePerTick = 1000000000 / ticks;
        double timePerFrame = 1000000000 / fps;
        double deltaT = 0;
        double deltaF = 0;
        beforeTime = System.nanoTime();
        while (run) {
            currTime = System.nanoTime();
            deltaT += (currTime - beforeTime) / timePerTick;
            deltaF += (currTime - beforeTime) / timePerFrame;
            beforeTime = currTime;
            if (deltaT >= 1) {
                update(currTime);
                deltaT--;
                
            }
            if (deltaF >= 1) {
                render();
                deltaF--;
            }
        }
    }
}