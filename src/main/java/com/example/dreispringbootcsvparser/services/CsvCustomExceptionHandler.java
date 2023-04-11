package com.example.dreispringbootcsvparser.services;

import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.opencsv.exceptions.CsvException;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the CsvExceptionHandler interface that collects information about CSV parsing errors
 * by adding the problematic CSV line to a list.
 */
public class CsvCustomExceptionHandler implements CsvExceptionHandler {

    private final List<String[]> list = new ArrayList<String[]>();

    /**
     * Handles a CSV parsing exception by adding the problematic CSV line to a list.
     *
     * @param e The CsvException to handle.
     * @return Always returns null, as this implementation does not handle exceptions, but simply collects information about them.
     */
    @Override
    public CsvException handleException(CsvException e) {
        list.add(e.getLine());
        return null;
    }

    /**
     * Returns the list of problematic CSV lines collected by this exception handler.
     *
     * @return The list of problematic CSV lines.
     */
    public List<String[]> getList() {
        return list;
    }
}