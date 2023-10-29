package utils;

import entity.LeaderboardEntry;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**Class for writing data to the txt file.
* 
*/
public class WriteData {
    public static FileWriter fileWriter;

    /** Writes entries to file.
    * 
    */    
    public static void writeToFile(String filePath, List<LeaderboardEntry> list) {
        try {
            fileWriter = new FileWriter(new File(filePath));        
            for (LeaderboardEntry le: list) {
                fileWriter.write(le.toString() + "\n");
            }            
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}