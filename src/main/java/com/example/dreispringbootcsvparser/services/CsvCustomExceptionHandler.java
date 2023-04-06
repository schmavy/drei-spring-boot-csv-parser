package com.example.dreispringbootcsvparser.services;

import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.opencsv.exceptions.CsvException;

import java.util.ArrayList;
import java.util.List;

public class CsvCustomExceptionHandler implements CsvExceptionHandler {

    private final List<String[]> list = new ArrayList<String[]>();
    @Override
    public CsvException handleException(CsvException e) throws CsvException {
        list.add(e.getLine());
        return null;
    }

    public List<String[]> getList() {
        return list;
    }
}