package com.example.dreispringbootcsvparser;

import com.example.dreispringbootcsvparser.services.CsvParserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CsvCommandLineRunnerTest {

    @Autowired
    private CsvParserCommandLineRunner csvParserCommandLineRunner;

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private PrintStream oldPrintStream;

    @BeforeEach
    public void beforeEach() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        oldPrintStream = System.out;
        System.setOut(printStream);
    }

    @AfterEach
    public void afterEach() {
        System.setOut(oldPrintStream);
        outputStream.reset();
    }

    @Test
    public void testConsoleOutput_Original() throws Exception {
        String filePath = "src/test/resources/original.csv";
        String[] args = {filePath};
        csvParserCommandLineRunner.run(args);
        String output = outputStream.toString();
        String[] trueLines = new String[]{"Number of errors: 4",
                "12,John,Smith,9999,2021-10-31,4500.00",
                "13,Mike,Smith,1002,2021/11/31,4800.00",
                "10,Joe,Doe,1001,2021-07-31,",
                "11,Bill,Bones,1003,2021-08-31,4800,00",
                "Average salary per employee:",
                "Bill Bones: 4262,6 EUR",
                "John Smith: 4537,5 EUR",
                "Joe Doe: 5225,0 EUR",
                "Mike Smith: 5488,9 EUR"
        };
        assertTrue(Stream.of(trueLines).allMatch(output::contains));
    }

    @Test
    public void testConsoleOutput_All_Valid() throws Exception {
        String filePath = "src/test/resources/all_valid.csv";
        String[] args = {filePath};
        csvParserCommandLineRunner.run(args);
        String output = outputStream.toString();
        String[] trueLines = new String[]{"No errors in CSV file found!",
                "Average salary per employee:",
                "Joe Doe: 1000,0 EUR",
                "John Smith: 2000,0 EUR",
                "Mike Smith: 3000,0 EUR"
        };
        assertTrue(Stream.of(trueLines).allMatch(output::contains));
    }


    @Test
    public void testConsoleOutput_Not_Existing() throws Exception {
        String filePath = "src/test/resources/not_existing.csv";
        String[] args = {filePath};
        csvParserCommandLineRunner.run(args);
        String output = outputStream.toString();
        assertTrue(output.contains("The system cannot find the file specified"));
    }

    @Test
    public void testConsoleOutput_No_Path() throws Exception {
        csvParserCommandLineRunner.run();
        String output = outputStream.toString();
        assertTrue(output.contains("No CSV file path provided."));
    }

}