package com.example.dreispringbootcsvparser;

import com.example.dreispringbootcsvparser.domain.Employee;
import com.example.dreispringbootcsvparser.services.CsvParserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CsvParserTest {

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
    public void testParse_missingFile() throws Exception {
        CsvParserService service = new CsvParserService();
        String filePath = "src/test/resources/missing.csv";
        assertNull(service.parse(filePath));
    }

    @Test
    public void testParse_invalidCsv() throws Exception {
        CsvParserService service = new CsvParserService();
        String filePath = "src/test/resources/wrong_file.csv";
        assertNull(service.parse(filePath));
    }

    @Test
    public void testParse_validVsc() throws Exception {
        CsvParserService service = new CsvParserService();
        String filePath = "src/test/resources/all_valid.csv";

        Employee u1 = new Employee(10, "Joe", "Doe", "1001", new GregorianCalendar(2021, Calendar.JANUARY, 30).getTime(), 1000.0f);
        Employee u2 = new Employee(12, "John", "Smith", "1001", new GregorianCalendar(2021, Calendar.JANUARY, 31).getTime(), 2000.0f);
        Employee u3 = new Employee(13, "Mike", "Smith", "1002", new GregorianCalendar(2021, Calendar.JANUARY, 31).getTime(), 3000.0f);
        Employee u4 = new Employee(10, "Joe", "Doe", "1001", new GregorianCalendar(2021, Calendar.JANUARY, 30).getTime(), 1000.0f);
        Employee u5 = new Employee(12, "John", "Smith", "1001", new GregorianCalendar(2021, Calendar.JANUARY, 31).getTime(), 2000.0f);
        Employee u6 = new Employee(13, "Mike", "Smith", "1002", new GregorianCalendar(2021, Calendar.JANUARY, 31).getTime(), 3000.0f);
        Employee[] employeeList = new Employee[]{u1, u2, u3, u4, u5, u6};

        assertArrayEquals(service.parse(filePath).toArray(), employeeList);
    }


}