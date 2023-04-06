package com.example.dreispringbootcsvparser.services;

import com.example.dreispringbootcsvparser.domain.Employee;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.lang3.tuple.MutableTriple;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 */
@Component
public class CsvParserService {

    /**
     * parses csv file from given filepath
     *
     * @param filePath
     * @throws Exception
     */
    public void parse(String filePath) throws Exception {
        File file = new File(filePath);
        List<Employee> users;
        CsvValidator validator = new CsvValidator();
        CsvCustomExceptionHandler exceptionHandler = new CsvCustomExceptionHandler();

        try (Reader reader = new BufferedReader(new FileReader(file))) {
            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Employee.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withVerifier(validator)
                    .withExceptionHandler(exceptionHandler)
                    .build();

            // convert `CsvToBean` object to list of users
            users = csvToBean.parse();

            System.out.printf("Number of errors: %s \nLines with error:\n%s%s",
                    (validator.getList().size() + exceptionHandler.getList().size()),
                    Arrays.toString(validator.getList().toArray()).replace("[", "").replace("],", "\n").replace("]", "\n"),
                    Arrays.deepToString(exceptionHandler.getList().toArray()).replace(" ", "").replace("[", "").replace("],", "\n").replace("]", "\n")
            );

            System.out.println("Average salary per employee:");
            users.stream()
                    .collect(Collectors
                            .groupingBy(e -> new MutableTriple<Long, String, String>(e.getEmpId(), e.getFirstName(), e.getLastName()), Collectors.averagingDouble(Employee::getSalary)))
                    .forEach((k, v) -> System.out.printf("%s %s: %.1f EUR\n", k.getMiddle(), k.getRight(), v));

        } catch (FileNotFoundException | RuntimeException e) {
            System.out.println(e.toString());
        }


    }
}
