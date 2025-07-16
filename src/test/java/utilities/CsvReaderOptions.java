package utilities;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvReaderOptions {

    public static ArrayList<String> getOptions(String filePath) {
        ArrayList<String> optionss = new ArrayList<String>();
        //String filePath = "src/main/resources/options.csv";

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            String[] header = reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                optionss.add(nextLine[0]); // Assuming the options are in the first column
            }
        } catch (IOException|CsvValidationException e) {
            e.printStackTrace();
        }
        return optionss;}

}