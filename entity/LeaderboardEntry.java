package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/** Class to store player's record.
* 
*/
public class LeaderboardEntry {
    public Integer score;
    public String userName;
    public Date date;
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm:ss yyyy");

    /** Constructor with score. Used by the Game class 
    * near the end of the session. 
    * 
    */
    public LeaderboardEntry(Integer score) {
        this.score = score;
        this.date = new Date();
    }

    /** Constructor with Date, name and score.
    * 
    */
    public LeaderboardEntry(Date date, String name, Integer score) {
        this.score = score;
        this.date = date;
        this.userName = name;
    }

    public void updateUsername(String name) {
        this.userName = name;
    }

    @Override
    public String toString() {
        return sdf.format(this.date) + "-" + this.userName + "-" + this.score;
    }
    
}