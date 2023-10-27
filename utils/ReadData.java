package utils;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


import entity.LeaderboardEntry;

public class ReadData{
    static SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy");
    static List<LeaderboardEntry> list = new ArrayList();
    public static Scanner scanner;
    public static List<LeaderboardEntry> readFromFile(String filePath){
        Date date = null;  

        try{
            scanner = new Scanner(new FileInputStream(filePath));        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        while(scanner.hasNext()){
            String[] data = scanner.nextLine().split("-");
            try{
                date = dateFormat.parse(data[0]);
            }
            catch (ParseException e){
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
	    	//ascending order
	    	return entryTwo.score - entryOne.score;
        }            
        });
        return list;          
    }
}