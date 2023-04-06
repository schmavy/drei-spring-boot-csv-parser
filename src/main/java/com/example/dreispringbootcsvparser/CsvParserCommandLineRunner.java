package com.example.dreispringbootcsvparser;

import com.example.dreispringbootcsvparser.services.CsvParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CsvParserCommandLineRunner implements CommandLineRunner {

    private final CsvParserService csvParser;

    @Autowired
    public CsvParserCommandLineRunner(CsvParserService csvParser) {
        this.csvParser = csvParser;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0) {
            String csvFilePath = args[0];
            csvParser.parse(csvFilePath);
        } else {
            System.out.println("No CSV file path provided.");
        }
    }
}
