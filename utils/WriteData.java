package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import entity.LeaderboardEntry;

public class WriteData{
    public static FileWriter fileWriter;
    public static void writeToFile(String filePath,List<LeaderboardEntry> list){
        try{
            fileWriter = new FileWriter(new File(filePath));        
            for (LeaderboardEntry le: list){
                fileWriter.write(le.toString()+"\n");
            }            
            fileWriter.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}