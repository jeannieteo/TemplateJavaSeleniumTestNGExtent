package utilities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvReaderOptions {

    public static ArrayList<String> getOptions(String resourceName) {
        ArrayList<String> options = new ArrayList<>();

        try (InputStream inputStream = openResource(resourceName);
                CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 0 && !nextLine[0].isBlank()) {
                    options.add(nextLine[0].trim());
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new IllegalStateException("Could not read options from " + resourceName, e);
        }

        return options;
    }

    private static InputStream openResource(String resourceName) {
        InputStream inputStream = CsvReaderOptions.class.getClassLoader().getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found on classpath: " + resourceName);
        }
        return inputStream;
    }
}
