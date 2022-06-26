package com.telran.oscar.utils;


import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviders {


    @DataProvider
    public Iterator<Object[]> newUserRegistrationValidData() throws IOException {
        CSVReader fileReader = new CSVReader(new FileReader("src/test/resources/register_user_positive_data.csv"));
        Iterator<String[]> fileReaderInterator = fileReader.iterator();
        List<Object[]> returnValue = new ArrayList<>();
        while (fileReaderInterator.hasNext()) {
            String[] nextRecord = fileReaderInterator.next();
            returnValue.add(nextRecord);
        }
        return returnValue.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginInvalidPasswordData() throws IOException {
        CSVReader fileReader = new CSVReader(new FileReader("src/test/resources/login_invalid_password_data.csv"));
        Iterator<String[]> fileReaderInterator = fileReader.iterator();
        List<Object[]> returnValue = new ArrayList<>();
        while (fileReaderInterator.hasNext()) {
            String[] nextRecord = fileReaderInterator.next();
            returnValue.add(nextRecord);
        }
        return returnValue.iterator();
    }

    @DataProvider
    public Iterator<Object[]> registrationWithInvalidEmail() throws IOException {
        CSVReader fileReader = new CSVReader(new FileReader("src/test/resources/registration_invalid_email_data.csv"));
        Iterator<String[]> fileReaderInterator = fileReader.iterator();
        List<Object[]> returnValue = new ArrayList<>();
        while (fileReaderInterator.hasNext()) {
            String[] nextRecord = fileReaderInterator.next();
            returnValue.add(nextRecord);
        }
        return returnValue.iterator();
    }

    @DataProvider
    public Iterator<Object[]> registrationWithInvalidPassword() throws IOException {
        CSVReader fileReader = new CSVReader(new FileReader("src/test/resources/registration_invalid_password_data.csv"));
        Iterator<String[]> fileReaderInterator = fileReader.iterator();
        List<Object[]> returnValue = new ArrayList<>();
        while (fileReaderInterator.hasNext()) {
            String[] nextRecord = fileReaderInterator.next();
            returnValue.add(nextRecord);
        }
        return returnValue.iterator();
    }

    @DataProvider
    public Iterator<Object[]> productToView() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{0});
        list.add(new Object[]{new Random().nextInt(4)+8});
        list.add(new Object[]{19});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> shipmentAddress() throws IOException {
        CSVReader fileReader = new CSVReader(new FileReader("src/test/resources/shipmentAddress.csv"));
        Iterator<String[]> fileReaderInterator = fileReader.iterator();
        List<Object[]> returnValue = new ArrayList<>();
        while (fileReaderInterator.hasNext()) {
            String[] nextRecord = fileReaderInterator.next();
            returnValue.add(nextRecord);
        }
        return returnValue.iterator();
    }

}