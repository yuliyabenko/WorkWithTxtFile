import java.io.*;
import java.util.List;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
        String myJarPath = Program.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String dirPath = new File(myJarPath).getAbsolutePath();
        FileStatistic fs = new FileStatistic();
        //Read the file and write its lines to the list
        List<String> list = fs.readFile(dirPath + "\\RobertFrost.txt");
        //Write statistic about first line(in list index=0) into DB
        fs.writeToDataBase(fs.lineStatistic(list,0));
        //Write statistic about all file into DB
        fs.writeToDataBase(fs.fileStatistic(list));
    }
}
