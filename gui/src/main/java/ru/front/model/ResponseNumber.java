package ru.front.model;

import ru.lukyanov.model.PhoneNumber;

import java.util.ArrayList;

public class ResponseNumber {

    private String response;
    private ArrayList<PhoneNumber> numbers;

    public String getResponse() {
        return response;
    }

    public ArrayList<PhoneNumber> getNumbers() {
        return numbers;
    }

    public ResponseNumber() {
    }
}
