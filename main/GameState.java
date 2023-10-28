package main;
public enum GameState{
    IDLE,
    PREPARE,
    LAUNCHED,
    TRANSITION,
    END;
    public static GameState state = IDLE;
}