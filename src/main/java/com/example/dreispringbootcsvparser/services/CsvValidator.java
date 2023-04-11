package com.example.dreispringbootcsvparser.services;

import com.example.dreispringbootcsvparser.domain.Employee;
import com.opencsv.bean.BeanVerifier;
import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.opencsv.exceptions.CsvException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvValidator implements BeanVerifier<Employee> {
    private final List<Employee> list = new ArrayList<>();
    private static final String[] validEmpIds = {"1001", "1002", "1003"};

    /**
     * Method to verify an Employee bean.
     *
     * @param bean the Employee bean to be verified.
     * @return true if bean passes all validations, false otherwise.
     */
    @Override
    public boolean verifyBean(Employee bean) {
        //check department
        if (!Arrays.asList(validEmpIds).contains(bean.getDepId())) {
            list.add(bean);
        }
        //other checks inside model
        return true;
    }

    /**
     * Method to get the list of invalid Employee beans.
     *
     * @return returns the list of invalid Employee beans.
     */
    public List<Employee> getList() {
        return list;
    }

}
