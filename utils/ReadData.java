package utils;

import entity.LeaderboardEntry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



/**Class for reading data from the txt file.
* 
*/
public class ReadData {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss yyyy");
    static List<LeaderboardEntry> list = new ArrayList<LeaderboardEntry>();
    public static Scanner scanner;

    /**Class for reading data from the txt file.
    * 
    */    
    public static List<LeaderboardEntry> readFromFile(String filePath) {
        Date date = null;  
        try {
            scanner = new Scanner(new FileInputStream(filePath));        
        } catch (Exception e) {
            try {
                FileWriter fileWriter = new FileWriter(new File(filePath));
                fileWriter.write("");
                fileWriter.close();
                return new ArrayList<>();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String[] data = scanner.nextLine().split("-");
            try {
                date = dateFormat.parse(data[0]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String userName = data[1];
            Integer score = Integer.parseInt(data[2]);
            LeaderboardEntry entry = new LeaderboardEntry(date, userName, score);
            list.add(entry);
        }
        scanner.close();
        //sort the table from high to low
        Collections.sort(list, new Comparator<LeaderboardEntry>() {
            public int compare(LeaderboardEntry entryOne,
                            LeaderboardEntry entryTwo) {
                return entryTwo.score - entryOne.score;
            }
        });
        return list;          
    }
}