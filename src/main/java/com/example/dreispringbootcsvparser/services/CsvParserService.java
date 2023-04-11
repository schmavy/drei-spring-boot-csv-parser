package com.example.dreispringbootcsvparser.services;

import com.example.dreispringbootcsvparser.domain.Employee;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.lang3.tuple.MutableTriple;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Service that
 */
@Component
public class CsvParserService {

    CsvCustomExceptionHandler exceptionHandler = new CsvCustomExceptionHandler();

    /**
     * Parses the CSV file at the specified path.
     *
     * @param filePath The path to the CSV file.
     * @throws Exception If there is an error reading the CSV file.
     */
    public List<Employee> parse(String filePath) throws Exception {
        File file = new File(filePath);
        List<Employee> users;

        try (Reader reader = new BufferedReader(new FileReader(file))) {
            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Employee.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withExceptionHandler(exceptionHandler)
                    .build();

            // convert `CsvToBean` object to list of users
            users = csvToBean.parse();
            return users;

        } catch (FileNotFoundException | RuntimeException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * Prints a list of Employee objects grouped by their id with their sorted calculated average salery and errors if any occurred.
     *
     * @param filePath The path to the CSV file.
     * @throws Exception If there is an error reading the CSV file.
     */
    public void print(String filePath) throws Exception {
        List<Employee> users = this.parse(filePath);
        if (users != null) {
            if (exceptionHandler.getList().size() > 0) {
                System.out.printf("Number of errors: %s\nLines with error:\n%s",
                        (exceptionHandler.getList().size()),
                        Arrays.deepToString(exceptionHandler.getList().toArray()).replace(" ", "").replace("[", "").replace("],", "\n").replace("]", "\n")
                );
            } else {
                System.out.println("No errors in CSV file found!");
            }

            if (users.size() > 0) {
                System.out.println("Average salary per employee:");
                users.stream()
                        .collect(Collectors.groupingBy(e -> new MutableTriple<Long, String, String>(e.getEmpId(), e.getFirstName(), e.getLastName()), Collectors.averagingDouble(Employee::getSalary)))
                        .entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .forEach((entry) -> System.out.printf("%s %s: %.1f EUR\n", entry.getKey().getMiddle(), entry.getKey().getRight(), entry.getValue()));
            } else {
                System.out.println("No valid Users found in CSV File");
            }
        }
    }


}
