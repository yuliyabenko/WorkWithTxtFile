import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileStatistic {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USER = "root";
    static final String PASSWORD = "root";

    public List<String> readFile (String path){
        File file = new File(path);
        fileName = file.getName();
        List<String> list = new ArrayList<>();
        try (FileReader fw = new FileReader(file);
             BufferedReader bw = new BufferedReader(fw)){
            Scanner sc = new Scanner(bw);
            while (sc.hasNextLine()){
                list.add(sc.nextLine());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : list){
            System.out.println(s);
        }
        return list;
    }

    public int lineLength(String string){
        System.out.println("Line length = " + string.length());
        return string.length();
    }

    public int averageWordLength(String string){
        StringTokenizer str = new StringTokenizer(string);
        int overallLength = 0, countWord = str.countTokens();
        while (str.hasMoreTokens()) {
            overallLength += str.nextToken().length();
        }
        System.out.println("Average word length = " + (overallLength/countWord));
        return overallLength/countWord;
    }

    public String shortestWord(String string){
        String shortestWord = "";
        int minLengthOfWord = 0;
        StringTokenizer str = new StringTokenizer(string);
        while (str.hasMoreTokens()) {
            String word = str.nextToken();
            int wordLength = word.length();
            if (shortestWord == "") {
                shortestWord = word;
                minLengthOfWord = wordLength;
            } else if (wordLength < minLengthOfWord) {
                shortestWord = word;
                minLengthOfWord = wordLength;
            }
        }
        if ( minLengthOfWord > 0) {
            System.out.println("Shortest word = " + shortestWord);
        } else {
            System.out.println("Empty string!");
        }
        return shortestWord;
    }

    public String longestWord(String string){
        String longestWord = "";
        int maxLengthOfWord = 0;
        StringTokenizer str = new StringTokenizer(string);
        while (str.hasMoreTokens()) {
            String word = str.nextToken();
            int wordLength = word.length();
            if (longestWord == "") {
                longestWord = word;
                maxLengthOfWord = wordLength;
            } else if (wordLength > maxLengthOfWord) {
                longestWord = word;
                maxLengthOfWord = wordLength;
            }
        }
        if ( maxLengthOfWord > 0) {
            System.out.println("Longest word = " + longestWord);
        } else {
            System.out.println("Empty string!");
        }
        return longestWord;
    }

    public LineFileStat lineStatistic(List<String> list, int index){
        LineFileStat lineStat = new LineFileStat();
        lineStat.setName(fileName + ", line " + (index+1));
        lineStat.setLength(lineLength(list.get(index)));
        lineStat.setAverageWordLength(averageWordLength(list.get(index)));
        lineStat.setShortestWord(shortestWord(list.get(index)));
        lineStat.setLongestWord(longestWord(list.get(index)));
        return lineStat;
    }

    public LineFileStat fileStatistic(List<String> strings){
        String allLines = "";
        LineFileStat fileStat = new LineFileStat();
        for (String s : strings){
            allLines += s;
        }
        fileStat.setName(fileName);
        fileStat.setLength(lineLength(allLines));
        fileStat.setAverageWordLength(averageWordLength(allLines));
        fileStat.setShortestWord(shortestWord(allLines));
        fileStat.setLongestWord(longestWord(allLines));
        return fileStat;
    }

    public void writeToDataBase(LineFileStat lineFileStat){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {
            PreparedStatement insertSQL =connection.prepareStatement("INSERT INTO statistic"
                    + "(line_or_file, length, average_word_length, shortest_word, longest_word) " + "VALUES('"
                    + lineFileStat.getName() + "', " + lineFileStat.getLength() + ", " + lineFileStat.getAverageWordLength()
                    + ", '" + lineFileStat.getShortestWord() + "', '" + lineFileStat.getLongestWord() + "');");
            insertSQL.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
