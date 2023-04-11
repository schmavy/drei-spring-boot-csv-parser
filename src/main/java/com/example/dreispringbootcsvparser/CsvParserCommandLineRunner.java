package com.example.dreispringbootcsvparser;

import com.example.dreispringbootcsvparser.services.CsvParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CsvParserCommandLineRunner implements CommandLineRunner {

    private final CsvParserService csvParser;

    /**
     * Command line runner that parses a CSV file and prints its contents to the console.
     */
    @Autowired
    public CsvParserCommandLineRunner(CsvParserService csvParser) {
        this.csvParser = csvParser;
    }

    /**
     * Parses the CSV file specified in the command line arguments and prints its contents to the console.
     *
     * @param args The command line arguments. Must contain the path to the CSV file.
     * @throws Exception If there is an error parsing the CSV file.
     */
    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0) {
            String csvFilePath = args[0];
            csvParser.print(csvFilePath);
        } else {
            System.out.println("No CSV file path provided.");
        }
    }
}
