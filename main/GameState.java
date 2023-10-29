package main;

/** Enums for various states of the game.
* 
*/
public enum GameState {
    IDLE,
    PREPARE,
    LAUNCHED,
    TRANSITION,
    END;
    public static GameState state = IDLE;
}